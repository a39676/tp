package job_test.memory_joy.mapper;

import java.util.List;

import job_test.memory_joy.pojo.Scenes;

public interface ScenesMapper {
    int insert(Scenes record);

    int insertSelective(Scenes record);
    
    List<Scenes> getSceneByName(List<String> sceneNameList);
}