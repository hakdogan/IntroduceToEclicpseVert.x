package com.kodcu.entity;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 2019-05-25
 */

@Getter
@Setter
public class Bucket implements Serializable {
    private String bucketKey;
    private long token;
}
