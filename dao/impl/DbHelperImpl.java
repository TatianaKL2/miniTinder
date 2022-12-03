package kg.megacom.miniTinder.dao.impl;

import kg.megacom.miniTinder.dao.DbHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbHelperImpl implements DbHelper {
    @Override
    public PreparedStatement preparedStatement(String sql) {
        try {
            Connection connection = DriverManager.getConnection
                    ("jdbc:5432://localhost:5432/tinder_db", "postgres", "postgres");
            return connection.prepareStatement(sql);
        } catch (SQLException throwables){
            throw new RuntimeException("Произошла ошибка при подключении к БД");
        }
    }
}
