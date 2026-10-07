package test;

import com.github.houbb.opencc4j.util.ZhConverterUtil;

public class Tmp42 {

	public static void main(String[] args) {
		String original = "亲爱的手作人：\n"
				+ "\n"
				+ "小店主营 DIY 小配件，单价低、利润极薄。\n"
				+ "\n"
				+ "由于配件种类繁杂，从仓储分类、拣货数件到打包配料，都需要大量的人力与包材成本。同时，运费都是直接支付给快递公司的，因此全店实行不包邮，还请亲亲体谅小本经营的艰难~\n"
				+ "\n"
				+ "其实所谓的“包邮”，往往是将运费打入了每件商品的价格中；单买看似划算，但买得越多，其实变相支付的运费就越高。为了让大家能以最实惠的底价选购多样配件，我们选择价格透明，不把运费藏在商品里。\n"
				+ "\n"
				+ "感谢您的理解与支持，祝您手作愉快，创作出更多美好作品！";
		System.out.println(original);
		String result = ZhConverterUtil.toTraditional(original);
		System.out.println(result);
		Integer start = 5;
		Integer end = 10;
		for (int i = start; i <= end; i = i + 2) {
			System.out.print(i);
			if (i + 2 < end) {
				System.out.print(",");
			}
		}
		System.out.println();
		for (int i = start + 1; i <= end; i = i + 2) {
			System.out.print(i);
			if (i + 1 < end) {
				System.out.print(",");
			}
		}
	}
}
