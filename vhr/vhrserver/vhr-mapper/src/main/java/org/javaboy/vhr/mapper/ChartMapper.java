package org.javaboy.vhr.mapper;
import java.util.List;
import org.javaboy.vhr.model.Chart;
public interface ChartMapper {
    int insert(Chart chart);
    List<Integer> getdatay();
    List<String> getdatax();
    List<Chart> getAllChart();
}
