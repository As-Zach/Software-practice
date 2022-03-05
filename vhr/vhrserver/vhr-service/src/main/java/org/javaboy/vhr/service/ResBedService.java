package org.javaboy.vhr.service;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.mapper.ResBedMapper;
import org.javaboy.vhr.model.ResBed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ResBedService {
    @Autowired
    ResBedMapper resBedMapper;

    public List<ResBed> getAllRes() {
        return resBedMapper.getAllImages();
    }
    public List<ResBed> selectById(@Param("name") String name){return resBedMapper.selectById(name);}
    public Integer addImage(ResBed image) {
        return resBedMapper.insert(image);
    }
    public Integer deleteImageById(Integer iid) {
        return resBedMapper.deleteById(iid);
    }
}
