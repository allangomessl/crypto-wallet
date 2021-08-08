package com.allangomes.wallet.externals;

import com.allangomes.wallet.externals.api.ExternalAssetService;
import com.allangomes.wallet.externals.api.dto.Asset;
import org.springframework.boot.test.context.TestComponent;

import java.math.BigDecimal;

@TestComponent
public class ExternalAssetServiceFake implements ExternalAssetService {

    @Override
    public Asset getAsset(String asset) {
        switch (asset) {
            case "BTC": return new Asset("bitcoin", "BTC", new BigDecimal(45000));
            case "ETC": return new Asset("ethereum", "ETC", new BigDecimal(3000));
            case "XMR": return new Asset("monero", "XMR", new BigDecimal(250));
            case "LTC": return new Asset("litecoin", "LTC", new BigDecimal(150));
            default: throw new RuntimeException("Asset not found");
        }
    }
}
