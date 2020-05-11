package com.kodcu.entity;

import java.io.Serializable;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 17.10.2018
 */

record StockExchangeData(String name, int rate) implements Serializable
{
    StockExchangeData(){
        this("", 0);
    }
}
