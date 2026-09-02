package test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import toolPack.ioHandle.FileUtilCustom;

public class OrderTextParser {

	public static void main(String[] args) {
		FileUtilCustom ioU = new FileUtilCustom();
		String rawText = ioU.getStringFromFile(System.getProperty("user.home") + "/tmp/tmp.txt");
//		System.out.println(rawText);
		try {
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode root = mapper.createObjectNode();
			List<ObjectNode> itemList = new ArrayList<>();
			ObjectNode currentItem = null;

			BufferedReader reader = new BufferedReader(new StringReader(rawText));
			String line;

			// 1. 逐行解析文本
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				if (line.isEmpty())
					continue;

				String[] parts = line.split("[:：]", 2);
				if (parts.length < 2)
					continue;

				String key = parts[0].trim();
				String value = parts[1].trim();

				switch (key) {
				case "商品总数量" -> root.put(key, Integer.parseInt(value));
				case "订单总付款" -> root.put(key, Double.parseDouble(value));
				case "订单编号", "付款时间", "买家旺旺", "收货姓名", "收货电话", "收货地址" -> root.put(key, value);

				case "商品标题" -> {
					currentItem = mapper.createObjectNode();
					currentItem.put(key, value);
					itemList.add(currentItem);
				}
				case "商品数量" -> {
					if (currentItem != null)
						currentItem.put(key, Integer.parseInt(value));
				}
				case "商品ID", "商品规格sku" -> {
					if (currentItem != null)
						currentItem.put(key, value);
				}
				}
			}

			// 2. 按商品ID进行排序（支持长整型比较或字典序排序）
			itemList.sort(Comparator.comparing(item -> item.get("商品ID").asText()));

			// 3. 将排序后的 List 转为 ArrayNode 添加回根节点
			ArrayNode sortedItems = mapper.createArrayNode();
			sortedItems.addAll(itemList);
			root.set("商品列表", sortedItems);

			// 4. 格式化输出 JSON
			String jsonOutput = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
			System.out.println(jsonOutput);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}