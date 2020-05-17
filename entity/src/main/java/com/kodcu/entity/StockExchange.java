package com.kodcu.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 13.10.2018
 */

public class StockExchange implements Serializable
{
    private String time;
    private List<StockExchangeData> dataList;

    public StockExchange(){}

    public StockExchange(String time, List<StockExchangeData> dataList) {
        this.time = time;
        this.dataList = dataList;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<StockExchangeData> getDataList() {
        return dataList;
    }

    public void setDataList(List<StockExchangeData> dataList) {
        this.dataList = dataList;
    }
}
