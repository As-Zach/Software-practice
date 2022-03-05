package org.javaboy.vhr.service;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.mapper.ChartMapper;
import org.javaboy.vhr.model.Chart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ChartService {
    @Autowired
    ChartMapper chartMapper;

    public List<Chart> getAllChart(){return chartMapper.getAllChart();}
    public List<String> getdatax(){return chartMapper.getdatax();}
    public List<Integer> getdatay(){return chartMapper.getdatay();}
    public Integer addChart(Chart chart){return chartMapper.insert(chart);}


}
