package com.allangomes.wallet.externals.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Asset {
    private String id;
    private String symbol;
    private BigDecimal priceUsd;
}
