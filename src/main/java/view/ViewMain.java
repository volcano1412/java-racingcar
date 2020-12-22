package view;

import java.util.Scanner;

public class ViewMain {

	Scanner sc = new Scanner(System.in);

	public void print(String msg) {

		System.out.println(msg);
	}

	public String inputValue() {

		return sc.nextLine();
	}
}
