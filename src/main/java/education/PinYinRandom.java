package education;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PinYinRandom {

	public static void main(String[] args) throws Exception {
		List<String> tmp = Arrays.asList("ai", "bai", "pai", "mai", "dai", "tai", "nai", "lai", "gai", "kai", "hai",
				"zhai", "chai", "shai", "zai", "sai", "wai", "ei", "bei", "pei", "mei", "fei", "dei", "tei", "nei",
				"lei", "gei", "kei", "nei", "zhei", "shei", "zei", "cei", "wei", "dui", "tui", "gui", "kui", "hui",
				"zhui", "chui", "shui", "rui", "zui", "cui", "sui", "ao", "bao", "pao", "mao", "dao", "tao", "nao",
				"lao", "gao", "kao", "hao", "zhao", "chao", "shao", "rao", "zao", "cao", "sao", "yao", "ou", "pou",
				"mou", "fou", "dou", "tou", "nou", "lou", "gou", "kuo", "huo", "zhou", "chou", "shou", "rou", "zou",
				"cou", "sou", "you", "miu", "diu", "niu", "liu", "jiu", "qiu", "xiu", "bie", "pie", "mie", "die", "tie",
				"nie", "lie", "jie", "qie", "xiu", "jue", "que", "xue", "yue", "er", "an", "ban", "pan", "man", "fan",
				"dan", "tan", "nan", "lan", "gan", "kan", "han", "zhan", "chan", "shn", "ran", "zan", "can", "san",
				"yan", "wan", "en", "ben", "pen", "men", "fen", "den", "nen", "gen", "ken", "hen", "zhen", "chen",
				"shen", "ren", "zen", "cen", "sen", "wen", "bin", "pin", "min", "nin", "lin", "jin", "qin", "xin",
				"yin", "dun", "tun", "nun", "lun", "gun", "kun", "hun", "zhun", "chun", "shun", "run", "zun", "cun",
				"sun", "jun", "qun", "xun", "yun", "ang", "bang", "pang", "mang", "fang", "dang", "tang", "nang",
				"lang", "gang", "kang", "hang", "zhang", "chang", "shang", "rang", "zang", "cang", "sang", "yang",
				"wang", "eng", "beng", "peng", "meng", "feng", "deng", "teng", "neng", "leng", "geng", "keng", "heng",
				"zheng", "cheng", "sheng", "reng", "zeng", "ceng", "seng", "weng", "bing", "ping", "ming", "ding",
				"ting", "ning", "ling", "jing", "qing", "xing", "ying", "dong", "tong", "nong", "long", "gong", "kong",
				"hong", "zhong", "chong", "rong", "zong", "cong", "song", "yong", "a", "ba", "pa", "ma", "fa", "da",
				"ta", "na", "la", "ga", "ka", "ha", "zha", "cha", "sha", "ra", "za", "ca", "sa", "ya", "wa", "o", "bo",
				"po", "mo", "fo", "lo", "yo", "wo", "e", "me", "de", "te", "ne", "le", "ge", "ke", "he", "zhe", "che",
				"she", "re", "ze", "ce", "se", "ye", "bi", "pi", "mi", "di", "ti", "ni", "li", "ji", "qi", "xi", "zhi",
				"chi", "shi", "ri", "zi", "ci", "si", "yi", "bu", "pu", "mu", "fu", "du", "tu", "nu", "lu", "gu", "ku",
				"hu", "zhu", "chu", "shu", "ru", "zu", "cu", "su", "wu", "nü", "lü", "ju", "qu", "xu", "yu", "dia",
				"lia", "jia", "qia", "xia", "biao", "piao", "miao", "diao", "tiao", "niao", "liao", "jiao", "qiao",
				"xiao", "bian", "pian", "mian", "dian", "tian", "nian", "lian", "jian", "qian", "xian", "niang",
				"liang", "jiang", "qiang", "xiang", "jiong", "qiong", "xiong", "gua", "kua", "hua", "zhua", "chua",
				"shua", "rua", "duo", "tuo", "nuo", "luo", "guo", "kuo", "huo", "zhuo", "chuo", "shuo", "ruo", "zuo",
				"cuo", "suo", "guai", "kuai", "huai", "zhuai", "chuai", "shuai", "duan", "tuan", "nuan", "luan", "guan",
				"kuan", "huan", "zhuan", "chuan", "shuan", "ruan", "zuan", "cuan", "suan", "guang", "kuang", "huang",
				"zhuang", "chuang", "shuang", "juan", "quan", "xuan", "yuan");
		List<String> allPinYin = new ArrayList<>();
		allPinYin.addAll(tmp);
		Random r = new Random();
		String tmpPinYin = null;
		int tmpInt = 0;
		for (int i = 0; i < 50 && !allPinYin.isEmpty(); i++) {
			tmpInt = r.nextInt(0, allPinYin.size());
			tmpPinYin = allPinYin.get(tmpInt);
			allPinYin.remove(tmpInt);
			System.out.print(tmpPinYin + ", ");
		}
	}
}
