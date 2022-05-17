package test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import toolPack.httpHandel.HttpUtil;

public class TmpTest16 {

	public static void main(String[] args) throws Exception {
		HttpUtil h = new HttpUtil();
		String content = h.sendGet("http://gzzk.gz.gov.cn/zwgk/zkyw/index.html");
		
		System.out.println(content);
		
		Element doc = Jsoup.parse(content);
		Elements targetUL = doc.select("ul.clearfix");
		Elements targetAlinkList = targetUL.select("a[href]");
		for (Element eleA : targetAlinkList) {
			System.out.println("title: " + eleA.attr("title"));
			System.out.println("target: " + eleA.attr("target"));
			System.out.println(eleA.attr("href"));
		}

	}
}
