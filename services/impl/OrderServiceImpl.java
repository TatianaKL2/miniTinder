package kg.megacom.miniTinder.services.impl;

import kg.megacom.miniTinder.dao.DbHelper;
import kg.megacom.miniTinder.dao.impl.DbHelperImpl;
import kg.megacom.miniTinder.models.Order;
import kg.megacom.miniTinder.models.User;
import kg.megacom.miniTinder.services.OrderService;
import kg.megacom.miniTinder.services.UserService;
import kg.megacom.miniTinder.services.exceptions.SqlException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    DbHelper dbHelper = new DbHelperImpl();
    UserService userService = new UserServiceImpl();
    @Override
    public void save(Order order) {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("insert into tb_order (sender_id, recipient_id, message, match) values (?,?,?,?)")) {

            preparedStatement.setLong(1, order.getSenderId().getId());
            preparedStatement.setLong(2, order.getRecipientId().getId());
            preparedStatement.setString(3, order.getMessage());
            preparedStatement.setBoolean(4, false);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new SqlException("Произошла ошибка при сохранении заявки");
        }
    }

    @Override
    public void update(Order order) {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("update tb_order set match = ? where id =?")) {

            preparedStatement.setBoolean(1, true);
            preparedStatement.setLong(2, order.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new SqlException("Произошла ошибка при обновлении совпадения");
        }

    }

    @Override
    public List<Order> findAll() {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("select * from tb_order")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Order> ordersList = new ArrayList<>();
            while (resultSet.next()){
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setSenderId(userService.findById(resultSet.getLong("id")));
                order.setRecipientId(userService.findById(resultSet.getLong("id")));
                order.setMessage(resultSet.getString("message"));
                order.setMatch(resultSet.getBoolean("match"));

                ordersList.add(order);
            } return ordersList;

        } catch (SQLException e) {
            throw new SqlException("Произошла ошибка при выводе списка заявок");
        }
    }

    @Override
    public Order findById(Long id) {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("select * from tb_order where id =?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Order order = new Order();
            while (resultSet.next()){

                order.setId(resultSet.getLong("id"));
                order.setSenderId(userService.findById(resultSet.getLong("id")));
                order.setRecipientId(userService.findById(resultSet.getLong("id")));
                order.setMessage(resultSet.getString("message"));
                order.setMatch(resultSet.getBoolean("match"));

            } return order;

        } catch (SQLException e) {
            throw new SqlException("Произошла ошибка при выводе пользователя");
        }
    }

    @Override
    public List<Order> findMatch(User user) {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("select * from tb_order where (sender_id = ? or recipient_id = ?) and match = ?")){

            preparedStatement.setLong(1, user.getId());
            preparedStatement.setLong(2, user.getId());
            preparedStatement.setBoolean(3, true);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Order> ordersList = new ArrayList<>();
            while (resultSet.next()){
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setSenderId(userService.findById(resultSet.getLong("sender_id")));
                order.setRecipientId(userService.findById(resultSet.getLong("recipient_id")));
                order.setMessage(resultSet.getString("message"));
                order.setMatch(resultSet.getBoolean("match"));

                ordersList.add(order);
            } return ordersList;

        } catch (SQLException e) {
            throw new SqlException("Произошла ошибка при выводе списке \"Взаимно\" ");
        }
    }

    @Override
    public List<Order> findWait(User user) {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("select * from tb_order where recipient_id = ? and match =?")) {
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setBoolean(2, false);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Order> ordersList = new ArrayList<>();

            while (resultSet.next()){
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setSenderId(userService.findById(resultSet.getLong("sender_id")));
                order.setRecipientId(userService.findById(resultSet.getLong("recipient_id")));
                order.setMessage(resultSet.getString("message"));
                order.setMatch(resultSet.getBoolean("match"));
                ordersList.add(order);

            } return ordersList;
        } catch (SQLException e) {
            throw new SqlException("Произошла ошибка при выводе списка \"В ожидании\" ");
        }
    }

    @Override
    public List<Order> findYourOrders(User user) {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("select * from tb_order where sender_id = ? and match = ?")) {
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setBoolean(2, false);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Order> ordersList = new ArrayList<>();

            while (resultSet.next()){
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setSenderId(userService.findById(resultSet.getLong("sender_id")));
                order.setRecipientId(userService.findById(resultSet.getLong("recipient_id")));
                order.setMessage(resultSet.getString("message"));
                order.setMatch(resultSet.getBoolean("match"));
                ordersList.add(order);

            } return ordersList;
        } catch (SQLException e) {
            throw new SqlException("Произошла ошибка при выводе списка ваших заявок");
        }
    }
}
