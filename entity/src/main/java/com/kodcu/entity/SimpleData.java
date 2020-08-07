package com.kodcu.entity;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 12.10.2018
 */

public class SimpleData
{
    private String key;
    private String value;

    public SimpleData(){}

    public SimpleData(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
