package org.javaboy.vhr.mapper;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.model.Employee;
import org.javaboy.vhr.model.ResBed;
public interface ResBedMapper {
    int deleteById(Integer id);
    int insert(ResBed record);
    List<ResBed> selectById(@Param("name") String name);
    int updateById(Integer id);
    List<ResBed> getAllImages();
}
