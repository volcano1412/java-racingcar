package domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class Calculator {

	public static final String CALCULATOR_STRING_SEPARATOR = " ";

	private final String userInput;

	public Calculator(String userInput) {

		this.userInput = userInput;
	}

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

}
