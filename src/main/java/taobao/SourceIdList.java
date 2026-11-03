package taobao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import toolPack.ioHandle.FileUtilCustom;

public class SourceIdList {

	private static List<String> idList = new ArrayList<>();
	private static FileUtilCustom ioUtil = new FileUtilCustom();

	static {
		String content = ioUtil.getStringFromFile(System.getProperty("user.home") + "/param/sourceIdList.txt");
		idList.addAll(Arrays.asList(content.split(System.lineSeparator())));
		System.out.println(idList);
	}

	public static List<String> getIdList() {
		return idList;
	}

	public static void setIdList(List<String> idList) {
		SourceIdList.idList = idList;
	}

}
