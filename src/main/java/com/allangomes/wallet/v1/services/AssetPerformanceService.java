package com.allangomes.wallet.v1.services;

import com.allangomes.wallet.externals.api.ExternalAssetService;
import com.allangomes.wallet.v1.commands.AssetPerformance;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class AssetPerformanceService {

    private ExternalAssetService externalAssetService;

    public AssetPerformanceService(ExternalAssetService externalAssetService) {
        this.externalAssetService = externalAssetService;
    }

    @SneakyThrows
    public AssetPerformance.Output handle(AssetPerformance.Input input) {
        val asset = input.getAsset();
        try {
            log.info("Submitted request {} at {}", asset.getSymbol(), new Date());

            val capAsset = externalAssetService.getAsset(asset.getSymbol());
            asset.setCurrent(capAsset.getPriceUsd());

            return new AssetPerformance.Output(
                    asset,
                    asset.getCurrent().getTotal(),
                    asset.getPerformance()
            );
        } catch (Exception e) {
            log.error("Error to try get performance of {}", asset.getSymbol());
            throw e;
        }
    }
}
