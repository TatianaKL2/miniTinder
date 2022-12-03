package kg.megacom.miniTinder.services;

import kg.megacom.miniTinder.models.User;

import java.util.List;

public interface UserService {
    void save(User user);
    void update(User user);
    List<User> findAll();
    List<User> findFemaleUsers();
    List<User> findMaleUsers();
    List<User> findOtherUsers();
    User findById (Long id);
    public boolean checkSignIn(String login, String password);
}
