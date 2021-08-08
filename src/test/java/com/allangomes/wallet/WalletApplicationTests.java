package com.allangomes.wallet;

import com.allangomes.wallet.externals.api.ExternalAssetService;
import com.allangomes.wallet.externals.api.dto.Asset;
import com.allangomes.wallet.v1.commands.WalletPerformance;
import com.allangomes.wallet.v1.dto.Wallet;
import com.allangomes.wallet.v1.services.WalletPerformanceService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class WalletApplicationTests {

	@MockBean
    private ExternalAssetService externalAssetService;

	@Autowired
	@InjectMocks
    private WalletPerformanceService walletPerformanceService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@SneakyThrows
	@Test
	void test() {

        Mockito.when(externalAssetService.getAsset("BTC"))
                .then((Answer<Asset>) invocation -> {
					Thread.sleep(2000);
					return new Asset("bitcoin", "BTC", new BigDecimal(45000));
				});

        Mockito.when(externalAssetService.getAsset("ETH"))
				.then((Answer<Asset>) invocation -> {
					Thread.sleep(2000);
					return new Asset("ethereum", "ETC", new BigDecimal(3000));
				});

        Mockito.when(externalAssetService.getAsset("XMR"))
				.then((Answer<Asset>) invocation -> {
					Thread.sleep(2000);
					return new Asset("monero", "XMR", new BigDecimal(250));
				});

		Mockito.when(externalAssetService.getAsset("LTC"))
				.then((Answer<Asset>) invocation -> {
					Thread.sleep(2000);
					return new Asset("litecoin", "LTC", new BigDecimal(150));
				});

		val classloader = Thread.currentThread().getContextClassLoader();
		val sample = classloader.getResourceAsStream("inputs/sample.csv");

		val wallet = Wallet.fromInputStream(sample);
		val input = new WalletPerformance.Input(wallet);

		val result = walletPerformanceService.handle(input);


		val output = String.format(
				"total=%s,best_asset=%s,best_performance=%f,worst_asset=%s,worst_performance=%f",
				result.getTotal(),
				result.getBest().getSymbol(),
				result.getBest().getPerformance(),
				result.getWorst().getSymbol(),
				result.getWorst().getPerformance()
		);

		Assertions.assertEquals(result.getTotal(), new BigDecimal(48400));

		Assertions.assertEquals(result.getBest().getSymbol(), "BTC");
		Assertions.assertEquals(result.getBest().getPerformance(),350F);

		Assertions.assertEquals(result.getWorst().getSymbol(), "LTC");
		Assertions.assertEquals(result.getWorst().getPerformance(), -25F);

		log.info(output);
	}
}
