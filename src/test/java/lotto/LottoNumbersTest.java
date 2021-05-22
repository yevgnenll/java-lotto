package lotto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumbersTest {

	@Test
	@DisplayName(value = "로또 넘버를 6개 입력받아야 하나의 로또 세트가 완성된다")
	void createLottoSet() {
		LottoNumbers lottoNumber = new LottoNumbers();
		lottoNumber.add(new LottoNumber(1));
		lottoNumber.add(new LottoNumber(2));
		lottoNumber.add(new LottoNumber(3));
		lottoNumber.add(new LottoNumber(4));
		lottoNumber.add(new LottoNumber(5));
		lottoNumber.add(new LottoNumber(6));
		assertThat(lottoNumber.isComplete()).isTrue();
	}

	@Test
	@DisplayName(value = "로또 넘버 개수가 6개 미만이면 false를 반환한다")
	void isCompleteLotto() {
		LottoNumbers lottoNumber = new LottoNumbers();
		lottoNumber.add(new LottoNumber(1));
		lottoNumber.add(new LottoNumber(2));
		assertThat(lottoNumber.isComplete()).isFalse();
	}

	@Test
	@DisplayName(value = "로또 넘버는 6개까지만 add가 가능하다")
	void addLessThanEqualSix() {
		LottoNumbers lottoNumber = new LottoNumbers();
		lottoNumber.add(new LottoNumber(1));
		lottoNumber.add(new LottoNumber(2));
		lottoNumber.add(new LottoNumber(3));
		lottoNumber.add(new LottoNumber(4));
		lottoNumber.add(new LottoNumber(5));
		lottoNumber.add(new LottoNumber(6));
		lottoNumber.add(new LottoNumber(7));
		lottoNumber.add(new LottoNumber(8));
		lottoNumber.add(new LottoNumber(9));
		assertThat(lottoNumber.isComplete()).isTrue();
	}


}