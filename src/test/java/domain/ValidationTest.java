package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ValidationTest {

	private String inputStr;
	private Validation validation;

	@BeforeEach
	void init() {

		inputStr = "2 + 3 * 4 / 2";		// 테스트 기준 계산식
		validation = new Validation(inputStr);
	}

	@Test
	@DisplayName("객체 생성 테스트")
	void initValidationTest() {

		assertThat(validation).isEqualTo(new Validation(inputStr));
	}

	@Test
	@DisplayName("입력 값 split")
	void inputSplitTest() {

		String[] arr = validation.getSplitStr(inputStr);
	}

	@ParameterizedTest
	@DisplayName("null, 공백 체크")
	@ValueSource(strings = {" "})
	void isnullChkTest(String userInput) {

		assertThat(validation.isNullChk(userInput)).isTrue();
	}

	@ParameterizedTest
	@DisplayName("숫자 체크")
	@ValueSource(strings = {"1", "45"})
	void isnumberChkTest(String userInput) {

		assertThat(validation.isNumberChk(userInput)).isFalse();
	}

	@ParameterizedTest
	@DisplayName("연산자 체크")
	@ValueSource(strings = {"+", "-", "*", "/"})
	void isOperatorChkTest(String userInput) {

		assertThat(validation.isOperatorChk(userInput)).isFalse();
	}

	@ParameterizedTest
	@DisplayName("index값 체크")
	@CsvSource(value = {"1:0", "+:1"}, delimiter = ':')
	void isIndexChkTest(String userInput, int index) {

		assertThat(validation.isIndexChk(index, userInput)).isFalse();
	}

	@ParameterizedTest
	@DisplayName("마지막 index 숫자체크")
	@ValueSource(strings = {"+", "q"})
	void lastIndexChkTest(String input) {

		assertThatIllegalArgumentException().isThrownBy(()->{
			validation.lastIndexChk(input);
		}).withMessageMatching("마지막문자가 숫자형이 아닙니다");
	}

	@ParameterizedTest
	@DisplayName("validation Exception처리 : null입력")
	@CsvSource(value = {"2:1:3", "4:+:3"}, delimiter = ':')
	void inputValidationNullTest(int index, String input, int maxIndex) {

		assertThatIllegalArgumentException().isThrownBy(()->{
			validation.inputValidation(index, null, maxIndex);
		}).withMessageMatching("null 값 입력");
	}

	@ParameterizedTest
	@DisplayName("validation Exception처리 : index별 유효성")
	@CsvSource(value = {"2:+:3","1:4:3"}, delimiter = ':')
	void inputValidationTest(int index, String input, int maxIndex) {

		assertThatIllegalArgumentException().isThrownBy(()->{
			validation.inputValidation(index, input, maxIndex);
		}).withMessageMatching("index에 해당하는 타입이 맞지 않음");
	}

	@ParameterizedTest
	@DisplayName("validation Exception처리 : 마지막index 유효성")
	@CsvSource(value = {"3:*:3"}, delimiter = ':')
	void lastInputValidationTest(int index, String input, int maxIndex) {

		assertThatIllegalArgumentException().isThrownBy(()->{
			validation.inputValidation(index, input, maxIndex);
		}).withMessageMatching("마지막문자가 숫자형이 아닙니다");
	}

	@Test
	@DisplayName("validation 반복 호출")
	void inputValidationRepeatTest() {

		assertThatCode(() -> {
			validation.inputValidationRepeat();
		}).doesNotThrowAnyException();
	}
}
