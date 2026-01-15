package test;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import toolPack.ioHandle.FileUtilCustom;

public class Tmp35 {

	public static void main(String[] args) {
		String folder = System.getProperty("user.home") + "/tmp";
		FileUtilCustom ioUtil = new FileUtilCustom();
		for (int pageIndex = 1; pageIndex < 3; pageIndex++) {
			String htmlContent = ioUtil.getStringFromFile(folder + "/p" + pageIndex + ".html");
			Document doc = Jsoup.parse(htmlContent);
//			System.out.println(doc.childNodes());
			Elements productImageList = doc.getElementsByAttributeValue("class", "product-desc-extend-image");
			List<String> imgSrcList = new ArrayList<>();
			for (int i = 0; i < productImageList.size(); i++) {
				Element img = productImageList.get(i);
				String src = img.attr("src");
				String oldPrefix = "./p" + pageIndex + "_files/";
//				https://gw.alicdn.com/imgextra/O1CN01vSGh4A1Y6ArKuo356_!!2220549273009.jpg_.webp
				src = src.replaceAll(oldPrefix, "https://gw.alicdn.com/imgextra/");
				src = src.replaceAll("ft=t&", "");
				imgSrcList.add(src);
			}

			Elements titleLinkList = doc.getElementsByAttributeValue("class", "title-link");
			List<String> productNameList = new ArrayList<>();
			List<String> productUrlList = new ArrayList<>();
			for (int i = 0; i < titleLinkList.size(); i++) {
				Element productDesc = titleLinkList.get(i);
				String productName = Jsoup.parse(productDesc.html()).text();
				productNameList.add(productName);
				productUrlList.add(productDesc.attr("href"));
			}

//			<a href='' target='_blank'> <img src='' style='width: 10%;'></a><br>
			for (int i = 0; i < productImageList.size(); i++) {
				String tmpHtmlStr = "<a href='" + productUrlList.get(i) + "' target='_blank'> <img src='"
						+ imgSrcList.get(i) + "' style='width: 10%;'>" + productNameList.get(i) + "</a><br>";
				System.out.println(tmpHtmlStr);
			}
		}
	}
}
