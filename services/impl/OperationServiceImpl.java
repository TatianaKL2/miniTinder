package kg.megacom.miniTinder.services.impl;

import kg.megacom.miniTinder.models.User;
import kg.megacom.miniTinder.models.enums.Gender;
import kg.megacom.miniTinder.services.OperationService;
import kg.megacom.miniTinder.services.UserService;

import java.util.Scanner;

public class OperationServiceImpl implements OperationService {

    Scanner scn = new Scanner(System.in);
    UserService userService = new UserServiceImpl();

    @Override
    public void registration() {

        System.out.println("--Регистрация--");
        User user = new User();

        System.out.print("Введите имя: ");
        user.setName(scn.nextLine());

        System.out.println("Введите логин: ");
        user.setLogin(scn.next());

        System.out.print("Введите пароль: ");
        user.setPassword(scn.next());

        System.out.print("Краткая информация о себе: ");
        user.setInfo(scn.nextLine());

        System.out.print("Выберите пол: ");
        System.out.println(Gender.valueOf(String.valueOf(scn.nextInt())).toString());
        switch (scn.nextInt()) {
            case 1:
                user.setGender(Gender.MALE);
                break;
            case 2:
                user.setGender(Gender.FEMALE);
                break;
            case 3:
                user.setGender(Gender.OTHER);
                break;
        }
        userService.save(user);
        System.out.println("Регистрация прошла успешно");
}

    @Override
    public String signIn(){
            System.out.println("--Вход--");
            System.out.print("Введите логин: ");
            String login = scn.next();
            System.out.print("Введите пароль: ");
            String password = scn.next();

            for (int i = 3; i > 0; i--) {
                if (!userService.checkSignIn(login, password)) {
                    System.out.println("Неверный логин или пароль. У вас осталось " + i + " попыток");
                    System.out.print("Введите логин: ");
                    login = scn.next();
                    System.out.print("Введите пароль: ");
                    password = scn.next();
                }
            } return "Вход выполнен успешно";
    }

}
