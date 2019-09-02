package job_test.memory_joy.mapper;

import job_test.memory_joy.pojo.MonsterRecord;

public interface MonsterRecordMapper {
    int insert(MonsterRecord record);

    int insertSelective(MonsterRecord record);
}