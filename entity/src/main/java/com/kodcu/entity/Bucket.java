package com.kodcu.entity;

import java.io.Serializable;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 2019-05-25
 */

public class Bucket implements Serializable
{

    private String bucketKey;
    private long token;

    public Bucket(){}

    public Bucket(String bucketKey, long token) {
        this.bucketKey = bucketKey;
        this.token = token;
    }

    public String getBucketKey() {
        return bucketKey;
    }

    public void setBucketKey(String bucketKey) {
        this.bucketKey = bucketKey;
    }

    public long getToken() {
        return token;
    }

    public void setToken(long token) {
        this.token = token;
    }
}
