package com.kodcu.entity;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 12.10.2018
 */


record SimpleData(String key, String value)
{
    public SimpleData(){
        this("", "");
    }
}
