//package job_test.hsbc;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//import toolPack.ioHandle.FileUtilCustom;
//
//public class JsonToPojo {
//
//	private String packname = "packname";
//
//	public static void main(String[] args) {
//		JsonToPojo t = new JsonToPojo();
//		JSONObject j = new JSONObject();
//		j.put("k1", "v1");
//		j.put("k2", "v1");
//		j.put("k3", "v1");
////		j.put("k4", "v1");
//		j.put("k5", "v1");
//		j.put("k6", "v1");
//
//		System.out.println(t.generateAnotationJsonPropertyOrder(j));
//	}
//
//	public List<String> getKeysOrder(JSONObject json) {
//		@SuppressWarnings("rawtypes")
//		Iterator keys = json.keys();
//		List<String> keyList = new ArrayList<String>();
//		while (keys.hasNext()) {
//			keyList.add(String.valueOf(keys.next()));
//		}
//		return keyList;
//	}
//
//	public String readJsonFile(String filePath) {
//		FileUtilCustom ioUtil = new FileUtilCustom();
//		return ioUtil.getStringFromFile(filePath);
//	}
//
//	public String generateAnotationJsonPropertyOrder(JSONObject json) {
//		List<String> keyList = getKeysOrder(json);
//		String anotation = "@JsonPropertyOrder({";
//
//		for (String key : keyList) {
//			anotation += ("\"" + key + "\",");
//		}
//		anotation = anotation.substring(0, anotation.length() - 1);
//
//		anotation += "})";
//		return anotation;
//	}
//
//	public String generateAttribute(JSONObject json) {
//		List<String> keyList = getKeysOrder(json);
//		String attributes = "";
//		String format = "@JsonProperty(\"%s\")\n private %s %s;";
//		String attributeType = null;
//		
//		for(String key : keyList) {
//			if(json.get(key) instanceof String) {
//				attributeType = "String";
//			} else if(json.get(key) instanceof JSONArray){
//				attributeType = "List<String>";
//			} else {
//				attributeType = "String";
//			}
//			attributes += String.format(format, key, attributeType, key);
//		}
//		attributes = attributes.substring(0, attributes.length() - 1);
//		
//		attributes += "})";
//		return attributes;
//	}
//}
