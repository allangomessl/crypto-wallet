package com.allangomes.wallet.v1.dto;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.val;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class WalletAsset {

    private final String symbol;
    private final BigDecimal amount;
    private final Position average;
    private Position current = null;
    private float performance = 0;

    public WalletAsset(String symbol, BigDecimal amount, BigDecimal avgPrice) {
        this.symbol = symbol;
        this.amount = amount;
        this.average = new Position(avgPrice, amount);
    }

//    public WalletAsset plus(WalletAsset coin) {
//        if (!this.symbol.equals(coin.symbol)) {
//            throw new RuntimeException("You can't plus different currencies");
//        }
//        val amount = this.amount.add(coin.amount);
//        val firstCost = this.amount.multiply(this.averagePrice);
//        val secondCost = coin.amount.multiply(coin.averagePrice);
//        val cost = firstCost.add(secondCost);
//        val avgPrice = cost.divide(amount, RoundingMode.FLOOR);
//        return new WalletAsset(coin.symbol, avgPrice, amount);
//    }

    @SneakyThrows
    public static WalletAsset fromString(String line) {
        val arr = line.split(",");
        return new WalletAsset(arr[0], new BigDecimal(arr[1]), new BigDecimal(arr[2]));
    }

    public void setCurrent(BigDecimal price) {
        this.current = new Position(price, this.getAmount());
        val sub = price.subtract(this.average.price);
        val div = sub.divide(this.average.price, RoundingMode.UNNECESSARY);
        val mul = div.multiply(new BigDecimal(100));
        this.performance = mul.floatValue();
    }

    @Data
    public static class Position {
        private BigDecimal price;
        private BigDecimal total;

        public Position(BigDecimal price, BigDecimal amount) {
            this.price = price.setScale(10, RoundingMode.DOWN);
            total = price.multiply(amount);
        }
    }
}
