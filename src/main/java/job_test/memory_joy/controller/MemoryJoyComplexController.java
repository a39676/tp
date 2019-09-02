package job_test.memory_joy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import job_test.memory_joy.mapper.ScenesMapper;

@Controller
@RequestMapping(value = "/memoryJoy")
public class MemoryJoyComplexController {

	@Autowired
	ScenesMapper scenesMapper;
	
	@GetMapping(value = "/getSceneByName")
	public void s() {
		String[] tmpNameList = new String[] {"蓬莱村", "神秘村落"};
		List<String> scenesNameList = new ArrayList<String>();
		for(String sceneName : tmpNameList) {
			scenesNameList.add(sceneName);
		}
		
		System.out.println(scenesMapper.getSceneByName(scenesNameList));
	}
}
