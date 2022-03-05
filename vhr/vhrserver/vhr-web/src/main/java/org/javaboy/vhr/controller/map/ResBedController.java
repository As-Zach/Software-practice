package org.javaboy.vhr.controller.map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.model.ResBed;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.service.ResBedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/res/manage/")
public class ResBedController {
    @Autowired
    ResBedService resBedService;

    @GetMapping("/")
    public List<ResBed> getAllRes() {
        return resBedService.getAllRes();
    }

    @GetMapping("/{name}")
    public List<ResBed> selectById(@PathVariable String name){
        return resBedService.selectById(name);
    }

    @PostMapping("/")
    public RespBean addImage(@RequestBody ResBed image) {
        if (resBedService.addImage(image) == 1) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }
    @DeleteMapping("/{iid}")
    public RespBean deleteImage(@PathVariable Integer iid) {
        if (resBedService.deleteImageById(iid) == 1) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败");
    }
}
