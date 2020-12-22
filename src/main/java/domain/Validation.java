package domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class Validation {

	public static final String CALCULATOR_SYMBOL_REGEX = "^[-|+|*|/]$";
	private String inputStr;

	public Validation(String inputStr) {

		this.inputStr = inputStr;
	}

	public Validation() {}

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

	public void isNullChk(String userInput) {

		if (userInput == null) {
			throw new IllegalArgumentException("null 입력");
		}

		if (userInput.isEmpty()) {
			throw new IllegalArgumentException("비어 있는 문자열");
		}
	}

	public Boolean isNumberChk(String userInput) {

		try {
			Integer.parseInt(userInput);
			return true;
		} catch (NumberFormatException numberFormatException) {
			return false;
		}
	}

	public boolean isNotNumber(String userInput) {

		return !isNumberChk(userInput);
	}

	public Boolean isOperatorChk(String userInput) {

		if (!Pattern.matches(CALCULATOR_SYMBOL_REGEX, userInput)) {
			return true;
		}

		return false;
	}

	public boolean isIndexChk(int index, String userInput) {

		if (index % 2 == 0) {
			return isNotNumber(userInput);
		}

		return isOperatorChk(userInput);
	}

	public void inputTypeValidation(int index, String input) {

		isNullChk(input);
		if (isIndexChk(index, input)) {
			throw new IllegalArgumentException("index에 해당하는 타입이 맞지 않음");
		}
	}

	public void formulaLength(String[] arrayStr) {

		if (arrayStr.length % 2 == 0) {
			throw new IllegalArgumentException("수식이 맞지 않습니다");
		}
	}

	public void inputValidation(String[] arrayStr) {

		formulaLength(arrayStr);
		for (int i = 0; i < arrayStr.length; i++) {
			inputTypeValidation(i, arrayStr[i]);
		}
	}

	public Boolean validationException(String[] arrayStr) {

		try {
			inputValidation(arrayStr);
			return false;
		} catch (IllegalArgumentException illegalArgumentException) {
			return true;
		}
	}
}
