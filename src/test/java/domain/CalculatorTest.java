package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CalculatorTest {

	private String ex;
	private Calculator calculator;

	@BeforeEach
	void init() {

		//ex = "2 + 3 * 4 / 2"; // 테스트 기준 계산식 // 복잡한 계산식
		ex = "7 + 4 * 2 - 1 / 7 + 17 / 10 * 22";	// 복잡한 계산식 //44
		calculator = new Calculator(ex);
	}

	@Test
	@DisplayName("객체 생성 테스트")
	void initCalculatorTest() {

		// 같은 객체 생성
		assertThat(calculator).isEqualTo(new Calculator(ex));
	}

	@Test
	@DisplayName("연산")
	void operationTest() {


	}

}
