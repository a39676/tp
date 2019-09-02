package job_test.phone_project.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import ioHandle.FileUtilCustom;
import job_test.phone_project.service.PhoneProjectService;
import tool_package.http_tools.JuHeHttpTool;

@Service
public class PhoneProjectServiceImpl implements PhoneProjectService{
	
//	"http://apis.juhe.cn/mobile/get?phone=13800138000&dtype=json&key=6f93fcb77088aa0f1d888945f23b4a4c";
	private FileUtilCustom ioTool = new FileUtilCustom();
	private String opNumberPart = "178";
	private Integer numberStart = 0;
	private Integer numberEnd = 9999;
	private JuHeHttpTool hc = new JuHeHttpTool();
	private String filePath = "d:/auxiliary/phoneProject/" + opNumberPart + ".txt";
	
	public void juhe01() throws InterruptedException {
		String juHeMainUrl = "http://apis.juhe.cn/mobile/get";
		
		List<String> keys = new ArrayList<String>();
		keys.add("6f93fcb77088aa0f1d888945f23b4a4cf");
		
		ArrayList<Integer> numberList = createRandomList(numberStart, numberEnd);
		String url = null;
		
		int count = 0;
		StringBuilder sb = new StringBuilder();
		while(!numberList.isEmpty()) {
			url = createJuHeUrls(juHeMainUrl, keys.get(0), opNumberPart, numberList.get(0));
			sb.append(String.format("%04d", numberList.get(0)) + " : " + hc.get(url) + "\n");
			TimeUnit.MICROSECONDS.sleep(ThreadLocalRandom.current().nextInt(80, 120));
			numberList.remove(0);
			count += 1;
			if(count % 300 == 0 || numberList.isEmpty()) {
				System.out.println(opNumberPart + "-" + count + " : " + new Date());
				ioTool.byteToFileAppendAtEnd(sb.toString().getBytes(), filePath);
				sb.setLength(0);
			}
		}
		
		
		
	}
	
	public void writeToFile(String str) {
		ioTool.byteToFileAppendAtEnd(str.getBytes(), filePath);
	}
	
	public String createJuHeUrls(String mainUrl, String key, String opNumberPart, Integer number) {
		return mainUrl + "?phone=" + opNumberPart + String.format("%04d", number) + "&key=" + key + "&dtype=json";
	}
	
	public ArrayList<Integer> createRandomList(Integer start, Integer end) {
		ArrayList<Integer> ownList = new ArrayList<Integer>();
		ArrayList<Integer> targetList = new ArrayList<Integer>();
		
		for(int i = start; i <= end; i++) {
			ownList.add(i);
		}
		
		int i = 0;
		while(!ownList.isEmpty()) {
			i = ThreadLocalRandom.current().nextInt(0, ownList.size());
			targetList.add(ownList.get(i));
			ownList.remove(i);
		}
		
		return targetList;
	}
	
	public static void main(String[] args) throws InterruptedException {
		PhoneProjectServiceImpl test = new PhoneProjectServiceImpl();
		
		test.juhe01();
	}
}
