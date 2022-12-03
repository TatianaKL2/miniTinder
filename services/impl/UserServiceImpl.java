package kg.megacom.miniTinder.services.impl;

import kg.megacom.miniTinder.dao.DbHelper;
import kg.megacom.miniTinder.dao.impl.DbHelperImpl;
import kg.megacom.miniTinder.models.User;
import kg.megacom.miniTinder.models.enums.Gender;
import kg.megacom.miniTinder.services.UserService;
import kg.megacom.miniTinder.services.exceptions.SqlException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    DbHelper dbHelper = new DbHelperImpl();
    @Override
    public void save(User user) {
            try (PreparedStatement preparedStatement = dbHelper.preparedStatement
                    ("insert into tb_user (name, login, password, info, gender) values (?,?,?,?,?)")){

                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getLogin());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.setString(4, user.getInfo());
                preparedStatement.setString(5, user.getGender().toString());
                preparedStatement.executeUpdate();

        } catch (SQLException e){
            throw new SqlException ("Произошла ошибка при сохранении пользователя");
        }

    }

    @Override
    public void update(User user) {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("update tb_user set name = ?, login = ?, password = ?, info = ?, gender = ? where id = ?")){

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getInfo());
            preparedStatement.setString(5, user.getGender().toString());
            preparedStatement.setLong(6, user.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            throw new SqlException("Произошла ошибка при обновлении пользователя");
        }

    }

    @Override
    public List<User> findAll() {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("select * from tb_user")){
            List<User> userList = new ArrayList<>();
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setInfo(rs.getString("info"));
                user.setGender(Gender.valueOf(rs.getString("gender")));
                userList.add(user);

            } return userList;
        } catch (SQLException e){
            throw new SqlException("Произошла ошибка при выводе списка пользователей");
        }
    }

    @Override
    public List<User> findFemaleUsers() {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("select * from tb_user where gender = ?")){
            preparedStatement.setString(1, "FEMALE");

            List<User> userList = new ArrayList<>();
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setInfo(rs.getString("info"));
                userList.add(user);

            }return userList;
        } catch (SQLException e){
            throw new SqlException("Произошла ошибка при выводе девушек");
        }

    }

    @Override
    public List<User> findMaleUsers() {
        try (PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("select * from tb_user where gender = ?")) {
            preparedStatement.setString(1, "MALE");

            List<User> userList = new ArrayList<>();
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setInfo(rs.getString("info"));
                userList.add(user);

            }
            return userList;
        } catch (SQLException e) {
            throw new SqlException("Произошла ошибка при выводе парней");

        }
    }

        @Override
        public List<User> findOtherUsers () {
            try (PreparedStatement preparedStatement = dbHelper.preparedStatement
                    ("select * from tb_user where gender = ?")) {
                preparedStatement.setString(1, "OTHER");

                List<User> userList = new ArrayList<>();
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getLong("id"));
                    user.setName(rs.getString("name"));
                    user.setLogin(rs.getString("login"));
                    user.setPassword(rs.getString("password"));
                    user.setInfo(rs.getString("info"));
                    userList.add(user);
                }
                return userList;
            } catch (SQLException e) {
                throw new SqlException("Произошла ошибка при выводе других");
            }
        }

    @Override
    public User findById(Long id) {
        try (PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("select * from tb_user where id = ?")) {
            preparedStatement.setLong(1, id);

            User user = new User();
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setInfo(rs.getString("info"));
                user.setGender(Gender.valueOf(rs.getString("gender")));

            }
            return user;
        } catch (SQLException e) {
            throw new SqlException("Произошла ошибка при выводе пользователя");
        }
    }
    @Override
    public boolean checkSignIn(String login, String password) {
        try (PreparedStatement preparestatement = dbHelper.getStmt
                ("select login, password from tb_user where login = ? and password = ?")) {
            preparestatement.setString(1, login);
            preparestatement.setString(2, password);

            ResultSet rs = preparestatement.executeQuery();
            String login = null;
            String password = null;

            while (rs.next()) {
                login = resultSet.getString("login");
                password = resultSet.getString("password");
            }

            if (login.equals(login) & password.equals(password)) {
                return true;
            }

        } catch (SQLException e) {
            throw new SqlException ("Произошла ошибка");
        }
        return false;
}
