package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculatorTypeTest {

	@Test
	@DisplayName("enum 사칙연산 테스트")
	void enumUsedTest() {

		assertThat(CalculatorType.MULTIPLICATION.calculate(3,4)).isEqualTo(12);
		assertThat(CalculatorType.DIVISION.calculate(8,2)).isEqualTo(4);
		assertThat(CalculatorType.PLUS.calculate(3,4)).isEqualTo(7);
		assertThat(CalculatorType.MINUS.calculate(7,4)).isEqualTo(3);
	}
}
