package test;

import java.time.LocalDate;
import java.util.Base64;
import java.util.UUID;

import toolPack.complexTool.ChinaMainLandIdNumGenerator;

public class TmpTest16 {

	public static void main(String[] args) throws Exception {

		String encodedString = "Z3VvcnVpMjMzMzMz";
		byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
		String decodedString = new String(decodedBytes);
		System.out.println(decodedString);

		encodedString = "MTgyMjE1MDg5MjE=";
		decodedBytes = Base64.getDecoder().decode(encodedString);
		decodedString = new String(decodedBytes);
		System.out.println(decodedString);
		
		System.out.println(UUID.randomUUID());

		ChinaMainLandIdNumGenerator ig = new ChinaMainLandIdNumGenerator();
		LocalDate startDate = LocalDate.now().minusYears(6).minusMonths(2);
		LocalDate endDate = LocalDate.now().minusYears(6);
		System.out.println(ig.getRandomId(startDate, endDate));
	}
}
