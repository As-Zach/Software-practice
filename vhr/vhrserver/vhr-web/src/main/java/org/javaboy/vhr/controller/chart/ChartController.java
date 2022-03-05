package org.javaboy.vhr.controller.chart;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.model.Chart;
import org.javaboy.vhr.model.ResBed;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/time/chart/")
public class ChartController {
    @Autowired
    ChartService chartService;
    @GetMapping("/")
    public List<Chart> getAllChart() {
        return chartService.getAllChart();
    }

/*    @GetMapping("/")
    public List<String> getdatax(){return chartService.getdatax();}

    @GetMapping("/")
    public List<Integer> getdatay(){return chartService.getdatay(); }*/

    @PostMapping("/")
    public RespBean addChart(@RequestBody Chart chart) {
        if (chartService.addChart(chart) == 1) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

}
