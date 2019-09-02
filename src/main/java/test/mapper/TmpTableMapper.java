package test.mapper;

import test.pojo.TmpTable;

public interface TmpTableMapper {
    int insert(TmpTable record);

    int insertSelective(TmpTable record);
}