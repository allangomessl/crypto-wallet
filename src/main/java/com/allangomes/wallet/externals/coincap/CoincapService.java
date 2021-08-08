package com.allangomes.wallet.externals.coincap;

import com.allangomes.wallet.externals.api.ExternalAssetService;
import com.allangomes.wallet.externals.api.dto.Asset;
import lombok.val;
import org.springframework.stereotype.Component;

@Component
public class CoincapService implements ExternalAssetService {
    
    private CoincapServiceApi coincapServiceApi;

    public CoincapService(CoincapServiceApi coincapServiceApi) {
        this.coincapServiceApi = coincapServiceApi;
    }

    @Override
    public Asset getAsset(String asset) {
        val assetResource = coincapServiceApi.getAsset(asset, 1);
        return assetResource.getData().stream().findFirst().orElseGet(null);
    }
}
