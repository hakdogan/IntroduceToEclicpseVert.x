package com.kodcu.entity;

import java.io.Serializable;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 17.10.2018
 */

public class StockExchangeData implements Serializable
{
    private String name;
    private int rate;

    public StockExchangeData(){}

    public StockExchangeData(String name, int rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
