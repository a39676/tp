package test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import toolPack.ioHandle.FileUtilCustom;

public class Tmp43 {

	public static void main(String[] args) {
		FileUtilCustom ioUtil = new FileUtilCustom();
		String filePath = System.getProperty("user.home") + "/tmp/tmp.html";
		String htmlStr = ioUtil.getStringFromFile(filePath);
		Document doc = Jsoup.parse(htmlStr);

		// 1. 查找元素
		Elements prices = doc.select("div.foodPrice_qTQKCd");

		// 2. 修改文本内容
		for (Element priceEle : prices) {
//			System.out.println("修改前: " + priceEle.text());

			// 直接将文字更改为新的价格
			BigDecimal price = new BigDecimal(priceEle.text());
			BigDecimal newPrice = price.multiply(BigDecimal.TWO);
			System.out.println(newPrice);
			priceEle.text(String.valueOf(newPrice.setScale(2, RoundingMode.HALF_UP)));

			// 如果内部需要插入包含标签的内容，可用 price.html("<span>￥35.00</span>");
		}

		// 3. 打印修改后的完整 HTML
//		System.out.println("修改后 HTML:\n" + doc.body().html());
		ioUtil.byteToFile(doc.body().html(), filePath);
	}
}
