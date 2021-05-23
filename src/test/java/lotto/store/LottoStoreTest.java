package lotto.store;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoStoreTest {

	@Test
	@DisplayName(value = "구매 가능한 로또의 개수는 budget / price 이다")
	void count() {
		LottoStore lottoStore = new LottoStore(10000, 1000);
		assertThat(lottoStore.count()).isEqualTo(10);
	}
}
