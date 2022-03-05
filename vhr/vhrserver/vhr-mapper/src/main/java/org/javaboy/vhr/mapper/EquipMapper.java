package org.javaboy.vhr.mapper;


import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.model.Equip;

import java.util.List;

public interface EquipMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Equip record);

    int insertSelective(Equip record);


    Equip selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Equip record);

    int updateByPrimaryKey(Equip record);

    List<Equip> getAllEquips(@Param("kind") String kind);

    Integer deleteEquipsByIds(@Param("ids") Integer[] ids);
}