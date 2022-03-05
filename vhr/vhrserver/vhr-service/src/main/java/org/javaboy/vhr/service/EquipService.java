package org.javaboy.vhr.service;

import org.javaboy.vhr.mapper.EquipMapper;
import org.javaboy.vhr.model.Equip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @作者 江南一点雨
 * @公众号 江南一点雨
 * @微信号 a_java_boy
 * @GitHub https://github.com/lenve
 * @博客 http://wangsong.blog.csdn.net
 * @网站 http://www.javaboy.org
 * @时间 2019-10-01 18:00
 */
@Service
public class EquipService {
    @Autowired
    EquipMapper equipMapper;

    public List<Equip> getAllEquips(String kind) {
        return equipMapper.getAllEquips(kind);
    }

    public Integer addEquip(Equip equip) {

        return equipMapper.insertSelective(equip);
    }

    public Integer updateEquipById(Equip equip) {
        return equipMapper.updateByPrimaryKeySelective(equip);
    }

    public Integer deleteEquipById(Integer id) {
        return equipMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteEquipsByIds(Integer[] ids) {
        return equipMapper.deleteEquipsByIds(ids);
    }
}
