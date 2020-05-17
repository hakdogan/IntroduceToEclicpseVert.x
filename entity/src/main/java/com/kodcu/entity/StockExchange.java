package com.kodcu.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 13.10.2018
 */

public record StockExchange(String time, List<StockExchangeData> dataList) implements Serializable
{
    public StockExchange(){
        this("", List.of());
    }
}
