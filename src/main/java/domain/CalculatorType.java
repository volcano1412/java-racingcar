package domain;

import java.util.function.BiFunction;
import java.util.function.Function;

public enum CalculatorType {

	PLUS((num1, num2) -> num1 + num2),
	MINUS((num1, num2) -> num1 - num2),
	DIVISION((num1, num2) -> num1 / num2),
	MULTIPLICATION((num1, num2) -> num1 * num2);

	private BiFunction<Integer, Integer, Integer> expression;

	CalculatorType(BiFunction<Integer, Integer, Integer> expression) {

		this.expression = expression;
	}

	public Integer calculate(Integer num1, Integer num2) {

		return expression.apply(num1, num2);
	}


}
