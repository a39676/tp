package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class TmpTest17 {

	public static void main(String[] args) throws Exception {

//		@SuppressWarnings("unused")
//		SetProxyWhenWindowsEnvironment s = new SetProxyWhenWindowsEnvironment();

		UUID u = UUID.randomUUID();
		String str = u.toString();
		System.out.println(str.replaceAll("-", "").substring(0, 16));
		u = UUID.randomUUID();
		str = u.toString();
		System.out.println(str.replaceAll("-", "").substring(0, 16));

		List<String> d = new ArrayList<>();
		str = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0; i < str.length(); i++) {
			d.add(String.valueOf(str.charAt(i)));
		}
		System.out.println(d);

		int r = new Random().nextInt(0, d.size());
		List<String> result = new ArrayList<>();
		result.clear();
		for (int i = 0; i < 43; i++) {
			r = new Random().nextInt(0, d.size());
			result.add(d.get(r));
			System.out.print(d.get(r));
		}
		System.out.println();
//		while(!result.contains("Z")) {}

//		String urlEncoded = URLEncoder.encode("https://fdjaoreql.top/aiChatFromWechat", StandardCharsets.UTF_8);
//		System.out.println(urlEncoded);
	}

}
