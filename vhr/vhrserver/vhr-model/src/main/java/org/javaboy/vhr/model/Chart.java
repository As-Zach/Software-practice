package org.javaboy.vhr.model;
import java.io.Serializable;
public class Chart implements Serializable{
    private Integer id;
    private String datax;
    private Integer datay;
    private Integer temp;

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDatax() {
        return datax;
    }

    public void setDatax(String datax) {
        this.datax = datax;
    }

    public Integer getDatay() {
        return datay;
    }

    public void setDatay(Integer datay) {
        this.datay = datay;
    }
}
