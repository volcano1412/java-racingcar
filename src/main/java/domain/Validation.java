package domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class Validation {

	private final String inputStr;

	public Validation(String inputStr) {

		this.inputStr = inputStr;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Validation that = (Validation)o;
		return Objects.equals(inputStr, that.inputStr);
	}

	@Override
	public int hashCode() {

		return Objects.hash(inputStr);
	}

	public String[] getSplitStr(String inputStr) {

		return inputStr.split(" ");
	}

	public Boolean isNullChk(String userInput) {

		if (userInput == null) {
			return true;
		}
		if (userInput.equals(" ")) {
			return true;
		}
		return false;
	}

	public Boolean isNumberChk(String userInput) {

		try {
			Integer.parseInt(userInput);
			return false;
		} catch (NumberFormatException numberFormatException) {
			return true;
		}
	}

	public Boolean isOperatorChk(String userInput) {

		if (!Pattern.matches(Calculator.CALCULATOR_SYMBOL_REGEX, userInput)) {
			return true;
		}
		return false;
	}

	public boolean isIndexChk(int index, String userInput) {

		if (index % 2 == 0) {
			return isNumberChk(userInput);
		}
		return isOperatorChk(userInput);
	}

	public void inputValidation(int index, String input, int maxIndex) {

		if (isNullChk(input)) {
			throw new IllegalArgumentException("null 값 입력");
		}
		if (isIndexChk(index, input)) {
			throw new IllegalArgumentException("index에 해당하는 타입이 맞지 않음");
		}
		if (maxIndex == index) {
			lastIndexChk(input);
		}
	}

	public void lastIndexChk(String input) {

		if (isNumberChk(input)) {
			throw new IllegalArgumentException("마지막문자가 숫자형이 아닙니다");
		}
	}

	public void inputValidationRepeat() {

		String[] arrStr = getSplitStr(inputStr);
		for (int i = 0; i < arrStr.length; i++) {
			inputValidation(i, arrStr[i], arrStr.length);
		}
	}
}
