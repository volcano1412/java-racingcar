package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CalculatorTest {

	String ex;
	Calculator calculator;
	String[] arrStr;
	@BeforeEach
	void init() {

		//ex = "2 + 3 * 4 / 2"; // 테스트 기준 계산식 // 복잡한 계산식
		ex = "7 + 4 * 2 - 1 / 7 + 17 / 10 * 22";	// 복잡한 계산식 //44
		calculator = new Calculator(ex);
		arrStr = calculator.getSplitStr();
	}


	@Test
	@DisplayName("객체 생성 테스트")
	void initCalculatorTest() {

		// 같은 객체 생성
		assertThat(calculator).isEqualTo(new Calculator(ex));
	}

	@Test
	@DisplayName("입력값 split 테스트")
	void splitExTest() {

		assertThat(calculator.getSplitStr()).isEqualTo(new String[] {"2", "+", "3", "*", "4", "/", "2"}); // 안에 있는 값을 제대로 확인

	}

	@Test
	@DisplayName("계산 몇 번인지 테스트")
	void calculateRepeatCountTest() {
		//사칙연산 몇 번하는지
		assertThat(calculator.calculateRepeatCount(arrStr)).isEqualTo(2);
	}


	@ParameterizedTest
	@DisplayName("기호가 + - * / 인지 여부 확인")
	@ValueSource(strings = {"3", "+", "5", "*", "1"})
	void symbolCheckTest(String inputStr) {
		assertThat(calculator.symbolCheck(inputStr)).isEqualTo(true);
	}

	@ParameterizedTest
	@DisplayName("연산자 count")
	@CsvSource(value = {"1:0", "+:1"}, delimiter = ':')
	void symbolCountTest(String str, int count) {

		assertThat(calculator.symbolCount(str, count)).isEqualTo(2);
	}

	@ParameterizedTest
	@DisplayName("연산실행")
	@CsvSource(value = {"1:+:0", "5:*:1", "7:/:2"}, delimiter = ':')
	void operationTest(int num1, String sym, int num2) {
		int num = calculator.operation(num1, sym, num2);
		assertThat(num).isEqualTo(1);
	}

	@Test
	@DisplayName("연산결과")
	void operationRepeatTest() {
		int resultNum = calculator.operationRepeat(arrStr);
		assertThat(resultNum).isEqualTo(44);

	}

}
