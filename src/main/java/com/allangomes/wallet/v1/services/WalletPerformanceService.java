package com.allangomes.wallet.v1.services;

import com.allangomes.wallet.v1.commands.AssetPerformance;
import com.allangomes.wallet.v1.commands.WalletPerformance;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WalletPerformanceService {

    private final AssetPerformanceService assetPerformanceService;
    private final ForkJoinPool pool;

    @Autowired
    public WalletPerformanceService(ForkJoinPool pool, AssetPerformanceService assetPerformanceService) {
        this.assetPerformanceService = assetPerformanceService;
        this.pool = pool;
    }

    @SneakyThrows
    public WalletPerformance.Output handle(WalletPerformance.Input input) {
        log.info("Now is {}", new Date());
        val wallet = input.getWallet();
        val stream = wallet.getAssets().values().parallelStream();

        val assets = pool
                .submit(() -> stream
                        .map(AssetPerformance.Input::new)
                        .map(assetPerformanceService::handle)
                        .collect(Collectors.toList()))
                .get();



        val total = assets.stream()
                .map(AssetPerformance.Output::getTotal)
                .reduce(BigDecimal::add);

        val bestAsset = assets.stream()
                .max(Comparator.comparing(AssetPerformance.Output::getPerformance))
                .map(AssetPerformance.Output::getAsset);

        val worstAsset = assets.stream()
                .min(Comparator.comparing(AssetPerformance.Output::getPerformance))
                .map(AssetPerformance.Output::getAsset);


        return new WalletPerformance.Output(
                wallet,
                total.orElse(BigDecimal.ZERO),
                bestAsset.orElse(null),
                worstAsset.orElse(null)
        );
    }
}
