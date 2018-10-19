package com.kodcu.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 13.10.2018
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockExchange implements Serializable {
    private String time;
    private List<StockExchangeData> dataList;
}
