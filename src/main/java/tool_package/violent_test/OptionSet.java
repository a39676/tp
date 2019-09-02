package tool_package.violent_test;

public class OptionSet  {
	
	
	protected static StringCoolieBuilder wordBuilder = new StringCoolieBuilder();
	
	protected static Chameleon chameleon;
	
	// ????
	protected static boolean flag = true;
	
//	protected static String target = "";
	
	private static void optionSetBeforeRun() {
		wordBuilder.setWordLength(1, 10);
		wordBuilder.setTmpDictionary(ViolentConstant.BaseCharacterNumeric);
//		wordBuilder.exceptTheseCharacters('0', '1', '2');
//		wordBuilder.setIncludeCharacters('9');
//		wordBuilder.setIncludeString("some");
//		wordBuilder.addTheseCharacters(new Character[] {'a'});
//		wordBuilder.startWithStr("0b35pob0"); 
		chameleon = Chameleon.setMatcher(ViolentConstant.Office2007Matcher);
		flag = chameleon.setDecryptor("D:/auxiliary/tmp/test6.xlsx");
//		chameleon.setTarget("e20945acdbda624db14db2178e1e002763b0a9e9");// mysql 5698745
//		chameleon.setTarget("B61335BFF7E839DD1C5E68905D587EAD216FFC4B");// mysql something20
//		chameleon.setTarget("81F5E21E35407D884A6CD4A731AEBFB6AF209E1B"); // mysql root
//		chameleon.setTarget("FABE5482D5AADF36D028AC443D117BE1180B9725"); // mysql mfnb0 FABE5482D5AADF36D028AC443D117BE1180B9725
	}
	
	static {
		optionSetBeforeRun();
		System.out.println("option setup.");
	}
}
