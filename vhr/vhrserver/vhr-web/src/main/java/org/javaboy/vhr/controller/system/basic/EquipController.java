package org.javaboy.vhr.controller.system.basic;

import org.apache.ibatis.annotations.Delete;
import org.javaboy.vhr.model.Equip;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.service.EquipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @作者 江南一点雨
 * @公众号 江南一点雨
 * @微信号 a_java_boy
 * @GitHub https://github.com/lenve
 * @博客 http://wangsong.blog.csdn.net
 * @网站 http://www.javaboy.org
 * @时间 2019-10-01 17:59
 */
@RestController
@RequestMapping("/equip/equip/")
public class EquipController {
    @Autowired
    EquipService equipService;
    @GetMapping("/{kind}")
    public List<Equip> getAllEquips(@PathVariable String kind) {
        return equipService.getAllEquips(kind);
    }

    @PostMapping("/")
    public RespBean addEquip(@RequestBody Equip equip) {
        if (equipService.addEquip(equip) == 1) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @PutMapping("/")
    public RespBean updateEquipById(@RequestBody Equip equip) {
        if (equipService.updateEquipById(equip) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteEquipById(@PathVariable Integer id) {
        if (equipService.deleteEquipById(id) == 1) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @DeleteMapping("/")
    public RespBean deleteEquipsByIds(Integer[] ids) {
        if (equipService.deleteEquipsByIds(ids) == ids.length) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}