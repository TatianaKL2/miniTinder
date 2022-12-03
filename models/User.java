package kg.megacom.miniTinder.models;

import kg.megacom.miniTinder.models.enums.Gender;

public class User {
    private Long id;
    private String name;
    private String login;
    private String password;
    private String info;
    private Gender gender;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "ID пользователя" + id + "\n"+
                "Имя: " + name + "\n" +
                "Логин: " + login + "\n" +
                "Пароль: " + password + "\n" +
                "Информация: " + info + "\n" +
                "Пол: " + gender + "\n----------";
    }
}
