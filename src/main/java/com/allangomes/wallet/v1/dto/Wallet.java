package com.allangomes.wallet.v1.dto;

import com.allangomes.wallet.extensions.InputStreamExtensions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.val;

import java.io.InputStream;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Wallet {

    private Map<String, WalletAsset> assets;

    @SneakyThrows
    public static Wallet fromInputStream(InputStream input) {
        val stream = InputStreamExtensions.readLines(input);
        val lines =  stream.skip(1);

        val result = lines.parallel()
                .map(WalletAsset::fromString)
                .collect(Collectors.toMap(
                    WalletAsset::getSymbol,
                    it -> it
                ));

        return new Wallet(result);
    }
}
