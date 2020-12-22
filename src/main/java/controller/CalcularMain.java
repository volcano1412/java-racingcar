package controller;

import domain.Calculator;
import domain.Validation;
import view.ViewMain;

public class CalcularMain {
	public static void main(String[] args) {

		ViewMain viewMain = new ViewMain();
		Validation validation = new Validation();
		Calculator calculator = new Calculator();
		String input = "";
		String[] arrayStr = new String[0];
		boolean initset = true;

		while (initset) {	//입력 값 validation 예외시 다시입력
			viewMain.print("수식을 입력해주세요");
			input = viewMain.inputValue();
			arrayStr = new Calculator(input).getSplitStr();
			initset = validation.validationException(arrayStr);	//유효성검사 이상 없을 시  false반환
		}

		String result = calculator.operationRepeat(arrayStr);
		viewMain.print("결과 : " + result);
	}
}
