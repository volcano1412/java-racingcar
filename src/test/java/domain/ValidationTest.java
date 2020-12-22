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
	private String[] arrayStr;

	@BeforeEach
	void init() {

		inputStr = "2 + 3 * 4 /";		// 테스트 기준 계산식
		validation = new Validation(inputStr);
		Calculator calculator = new Calculator(inputStr);
		arrayStr = calculator.getSplitStr();
	}

	@Test
	@DisplayName("객체 생성 테스트")
	void initValidationTest() {

		assertThat(validation).isEqualTo(new Validation(inputStr));
	}

	@ParameterizedTest
	@DisplayName("null, 공백 체크")
	@ValueSource(strings = {" "})
	void isnullChkTest(String userInput) {

		assertThatIllegalArgumentException().isThrownBy(()->{
			validation.isNullChk(userInput);
		}).withMessageMatching("null 값 입력");
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
	@DisplayName("validation Exception처리 : null입력")
	@CsvSource(value = {"2:1", "4:+"}, delimiter = ':')
	void inputValidationNullTest(int index, String input) {

		assertThatIllegalArgumentException().isThrownBy(()->{
			validation.inputTypeValidation(index, null);
		}).withMessageMatching("null 값 입력");
	}

	@ParameterizedTest
	@DisplayName("validation Exception처리 : index별 유효성")
	@CsvSource(value = {"2:+","1:4"}, delimiter = ':')
	void inputTypeValidationTest(int index, String input) {

		assertThatIllegalArgumentException().isThrownBy(()->{
			validation.inputTypeValidation(index, input);
		}).withMessageMatching("index에 해당하는 타입이 맞지 않음");
	}

	@Test
	@DisplayName("수식 길이 확인")
	void formulaLengthTest() {

		assertThatIllegalArgumentException().isThrownBy(()->{
			validation.formulaLength(arrayStr);
		}).withMessageMatching("수식이 맞지 않습니다");
	}

	@Test
	@DisplayName("input값 validation")
	void inputValidationTest() {

		assertThatIllegalArgumentException().isThrownBy(()->{
			validation.inputValidation(arrayStr);
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("validation : 예외처리")
	void validationExceptionTest() {

			assertThat(validation.validationException(arrayStr)).isTrue();
	}
}