package com.kodcu.entity;

import java.io.Serializable;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 2019-05-25
 */

public record Bucket(String bucketKey, long token) implements Serializable {}
