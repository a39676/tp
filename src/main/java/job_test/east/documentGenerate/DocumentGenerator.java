package job_test.east.documentGenerate;

import java.util.List;

import job_test.east.documentGenerate.reapal.domain.ParamAttr;
import job_test.east.documentGenerate.reapal.service.GeneratorService;

public class DocumentGenerator {

	public static void main(String[] args) {
		GeneratorService gs = new GeneratorService();
		String inputFilePath = "d:/auxiliary/tmp/tmp.txt";

		List<ParamAttr> attrs = gs.makeAttrsFromFile(inputFilePath);

		System.out.println(gs.paramAttrToStringForJava(attrs));

		System.out.println(gs.paramAttrToStringForServerComment(attrs));
	}

}
