package test;

import net.sf.json.JSONArray;

public class TmpTest12 {

	public static void main(String[] args) {
		String str = "[\r\n"
				+ "  [\r\n"
				+ "    1590710400000,\r\n"
				+ "    \"0.00178260\",\r\n"
				+ "    \"0.00181650\",\r\n"
				+ "    \"0.00177670\",\r\n"
				+ "    \"0.00180350\",\r\n"
				+ "    \"945282.10000000\",\r\n"
				+ "    1590796799999,\r\n"
				+ "    \"1697.61516735\",\r\n"
				+ "    56820,\r\n"
				+ "    \"440487.16000000\",\r\n"
				+ "    \"791.28009769\",\r\n"
				+ "    \"0\"\r\n"
				+ "  ],\r\n"
				+ "  [\r\n"
				+ "    1590796800000,\r\n"
				+ "    \"0.00180340\",\r\n"
				+ "    \"0.00184990\",\r\n"
				+ "    \"0.00180270\",\r\n"
				+ "    \"0.00182790\",\r\n"
				+ "    \"1078276.20000000\",\r\n"
				+ "    1590883199999,\r\n"
				+ "    \"1966.38915867\",\r\n"
				+ "    68371,\r\n"
				+ "    \"511193.20000000\",\r\n"
				+ "    \"932.43340720\",\r\n"
				+ "    \"0\"\r\n"
				+ "  ]]"
				;
		JSONArray j = JSONArray.fromObject(str);
		System.out.println(j);
		JSONArray ja = j.getJSONArray(0);
		System.out.println(ja);
		System.out.println(ja.get(0));
	}
}
