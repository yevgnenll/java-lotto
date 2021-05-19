package calculator;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class StringCalculatorTest {

	@Test
	@DisplayName(value = "null 혹은 empty string 을 전달하면 0을 반환한다")
	void emptyString() {
		int empty = StringCalculator.sumByDelimiter("");
		int inputNull = StringCalculator.sumByDelimiter(null);
		assertThat(empty).isEqualTo(0);
		assertThat(inputNull).isEqualTo(0);
	}

	@ParameterizedTest
	@CsvSource(value = {"123:123", "1:1", "01:1"}, delimiter = ':')
	@DisplayName(value = "구분자가 없는 숫자인 경우 숫자로 변경해 반환한다")
	void noDelimiter(String text, int value) {
		int result = StringCalculator.sumByDelimiter(text);
		assertThat(result).isEqualTo(value);
	}

	@ParameterizedTest
	@CsvSource(value = {"1,2,3,4,5,6,7,8,9:45", "123,456,789:1368"}, delimiter = ':')
	@DisplayName(value = ", 혹은 | 구분자를 사용하는 경우 숫자로 분리하여 합한 값을 반환한다")
	void sumTokenizing(String text, int value) {
		int resultByComma = StringCalculator.sumByDelimiter(text);
		assertThat(resultByComma).isEqualTo(value);
		int resultByVerticalBar = StringCalculator.sumByDelimiter(text);
		assertThat(resultByVerticalBar).isEqualTo(value);
	}

	@ParameterizedTest
	@ValueSource(strings = {"0,2,4,-1", "-3,4,5", "23, 4a1, 8", "1a, 67, 9"})
	@DisplayName(value = "양수가 아닌 숫자 혹은 문자열이 입력 될 경우 Exception 을 발생시킨다")
	void negativeNumber(String text) {
		assertThatThrownBy(() -> StringCalculator.sumByDelimiter(text))
			.isInstanceOf(RuntimeException.class);
	}

	@Test
	@DisplayName(value = "delimiter 가 포함된 text 가 null 혹은 empty string 을 전달하면 0을 반환한다")
	void emptyStringCustom() {
		int empty = StringCalculator.sumByCustomDelimiter("");
		int inputNull = StringCalculator.sumByCustomDelimiter(null);
		assertThat(empty).isEqualTo(0);
		assertThat(inputNull).isEqualTo(0);
	}

}
