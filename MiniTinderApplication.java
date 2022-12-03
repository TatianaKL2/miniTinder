package kg.megacom.miniTinder;


import kg.megacom.miniTinder.services.OperationService;
import kg.megacom.miniTinder.services.impl.OperationServiceImpl;

import java.util.Scanner;

public class MiniTinderApplication {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		OperationService operationService = new OperationServiceImpl();
		System.out.println("1.Вход");
		System.out.println("2.Регистрация");
		switch (scn.nextInt()) {
			case 1 -> operationService.signIn();
			case 2 -> operationService.registration();
		}

		}
	}

