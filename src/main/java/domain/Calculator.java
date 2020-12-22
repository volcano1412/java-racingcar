package domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class Calculator {

	public static final String CALCULATOR_STRING_SEPARATOR = " ";
	public static final String CALCULATOR_SYMBOL_REGEX = "^[-|+|*|/]$";
	private String userInput;

	public Calculator(String userInput) {

		this.userInput = userInput;
	}

	public Calculator() {}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Calculator that = (Calculator)o;

		return Objects.equals(userInput, that.userInput);
	}

	@Override
	public int hashCode() {

		return Objects.hash(userInput);
	}

	public String[] getSplitStr() {

		return this.userInput.split(CALCULATOR_STRING_SEPARATOR);
	}

	//연산 횟수
	public int calculateRepeatCount(String[] arrStr) {

		int count = 0;
		for (String str : arrStr) {
			count = symbolCount(str, count);
		}

		return count;
	}

	public int symbolCount(String str, int count) {

		if (symbolCheck(str)) {
			count++;
		}

		return count;
	}

	public Boolean symbolCheck(String inputStr) {

		return Pattern.matches(CALCULATOR_SYMBOL_REGEX,inputStr);
	}

	//연산 실행
	public int operation(int num1, String sym, int num2) {

		int result = 0;

		if (sym.equals("+")) {
			result = num1 + num2;
		}
		if (sym.equals("-")) {
			result = num1 - num2;
		}
		if (sym.equals("*")) {
			result = num1 * num2;
		}
		if (sym.equals("/")) {
			result = num1 / num2;
		}

		return result;
	}

	//연산 반복
	public String operationRepeat(String[] arrStr) {

		int count = calculateRepeatCount(arrStr);
		int resultValue = Integer.parseInt(arrStr[0]);

		for (int i = 1; i < count * 2; i = i + 2) {
			int num1 = resultValue;
			int num2 = Integer.parseInt(arrStr[i + 1]);
			resultValue = operation(num1, arrStr[i], num2);
		}

		return String.valueOf(resultValue);
	}
}
