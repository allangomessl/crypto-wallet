package com.allangomes.wallet.externals.coincap;

import com.allangomes.wallet.externals.api.dto.Asset;
import com.allangomes.wallet.externals.api.dto.Resource;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "coincap", url = "https://api.coincap.io/v2/")
public interface CoincapServiceApi {

    @RequestMapping(value = "/assets", method = RequestMethod.GET)
    Resource<Asset> getAsset(
            @RequestParam String search,
            @RequestParam(defaultValue = "1") int limit
    );

}
