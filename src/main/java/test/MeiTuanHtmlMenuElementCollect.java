package test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

import toolPack.ioHandle.FileUtilCustom;

public class MeiTuanHtmlMenuElementCollect {

	public static void main(String[] args) {
		FileUtilCustom ioUtil = new FileUtilCustom();
		String inputFilePath = System.getProperty("user.home") + "/tmp/menuInput.html";
//		String outputFilePath = System.getProperty("user.home") + "/tmp/menuOutput.html";
		String htmlStr = ioUtil.getStringFromFile(inputFilePath);
		Document doc = Jsoup.parse(htmlStr);

		List<String> imgSrcList = getImgSrcList(doc);
		List<String> nameList = getNameList(doc);
		List<String> unitList = getUnitList(doc);
		List<String> priceList = getPriceList(doc);

		for (int i = 0; i < imgSrcList.size(); i++) {
			Element foodContent = generateFoodHtml(imgSrcList.get(i), nameList.get(i), priceList.get(i),
					unitList.get(i), "x1");
			System.out.println(foodContent.outerHtml());
		}
	}

	private static List<String> getImgSrcList(Document doc) {
		Elements imgTags = doc.select("img");

		List<String> resultList = new ArrayList<>();

		for (Element img : imgTags) {
			String src = img.attr("src").trim();
			String width = img.attr("width").trim();
			String height = img.attr("height").trim();

			// 判断三个属性是否均不为空
			if (!StringUtils.isAnyEmpty(src, width, height)) {
				resultList.add(src);
			}
		}

		// 输出结果
//		System.out.println(resultList);
		return resultList;
	}

	private static List<String> getNameList(Document doc) {
		// 1. 查找所有 class 以 "info_" 开头的 div
		Elements infoDivs = doc.select("div[class^=info_]");
		List<String> resultList = new ArrayList<>();

		for (Element infoDiv : infoDivs) {
			// 2. 在 infoDiv 内部查找 class 以 "name_" 开头的 div
			Element nameEl = infoDiv.selectFirst("div[class^=name_]");
			String name = nameEl != null ? nameEl.text() : "";

			// 输出结果
			resultList.add(name);
		}

		return resultList;
	}

	private static List<String> getUnitList(Document doc) {
		// 1. 查找所有 class 以 "info_" 开头的 div
		Elements infoDivs = doc.select("div[class^=info_]");
		List<String> resultList = new ArrayList<>();

		for (Element infoDiv : infoDivs) {
			// 3. 在 infoDiv 内部查找 class 以 "unit_" 开头的 div
			Element unitEl = infoDiv.selectFirst("div[class^=unit_]");
			String unit = unitEl != null ? unitEl.text() : "";

			// 输出结果
			resultList.add(unit);
		}

		return resultList;
	}

	private static List<String> getPriceList(Document doc) {
		// 1. 查找所有 class 以 "info_" 开头的 div
		Elements infoDivs = doc.select("div[class^=info_]");
		List<String> resultList = new ArrayList<>();

		for (Element infoDiv : infoDivs) {
			// 4. 在 infoDiv 内部查找 data-tag="price" 的 div
			Element priceEl = infoDiv.selectFirst("div[class^=oprice_]");
			String price = priceEl != null ? priceEl.text() : "";

			resultList.add(price);
		}
		return resultList;
	}

	public static Element generateFoodHtml(String imgSrc, String name, String price, String description, String count) {
		// 1. 创建根容器元素
		Element root = new Element(Tag.valueOf("div"), "").addClass("food_jk2rgX");

		// 2. 添加图片节点 (动态传入 imgSrc)
		root.appendElement("img").attr("src", imgSrc).addClass("foodPicture_x7oSwL");

		// 3. 构建 foodContent_uDcND5 内容区
		Element foodContent = root.appendElement("div").addClass("foodContent_uDcND5");

		// 3.1 构建 foodLine1_yRjEJT (名称、符号、价格)
		Element foodLine1 = foodContent.appendElement("div").addClass("foodLine1_yRjEJT");

		foodLine1.appendElement("div").addClass("foodName_Es9jW_").text(name);

//		foodLine1.appendElement("div").addClass("foodYen2_A6PSZn").text("¥");

		foodLine1.appendElement("div").addClass("foodPrice_qTQKCd").text(price);

		// 3.2 构建描述信息
		foodContent.appendElement("div").addClass("foodDescription_KTFFlz").text(description);

		// 3.3 构建数量
		foodContent.appendElement("div").addClass("foodCount_QBWTbQ").text(count);

		// 4. 返回格式化/未格式化的 HTML 文本
		// 如果需要与你输入的 HTML 完全一致的排版，开启 outputSettings 调整缩进：
//        root.ownerDocument(new Document("")); // 附加临时 Doc 以使用 Settings
//        root.ownerDocument().outputSettings().prettyPrint(true).indentAmount(1);
//        return root.outerHtml();
//		System.out.println(root.outerHtml());
		return root;
	}
}
