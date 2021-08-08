package com.allangomes.wallet.v1.commands;

import com.allangomes.wallet.v1.dto.WalletAsset;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

public class AssetPerformance {

    @Data
    @AllArgsConstructor
    public static class Input {
        private final WalletAsset asset;
    }

    @Data
    @AllArgsConstructor
    public static class Output {
        private final WalletAsset asset;
        private final BigDecimal total;
        private final float performance;
    }
}
