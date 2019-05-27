package com.kodcu.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 17.10.2018
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockExchangeData implements Serializable
{
    private String name;
    private int rate;
}
