package com.allangomes.wallet.v1.commands;

import com.allangomes.wallet.v1.dto.Wallet;
import com.allangomes.wallet.v1.dto.WalletAsset;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

public class WalletPerformance {

    @Data
    @AllArgsConstructor
    public static class Input {
        private final Wallet wallet;
    }


    @Data
    @AllArgsConstructor
    public static class Output {
        private final Wallet wallet;
        private final BigDecimal total;
        private final WalletAsset best;
        private final WalletAsset worst;
    }
}
