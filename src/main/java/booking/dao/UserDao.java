package booking.dao;

import booking.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class UserDao extends Dao{
    private final HashMap<Integer, User> users = new HashMap();
    private final String superPassword = "tablebooking123";

    public UserDao() {
        loadData();
    }

    private void loadData(){
        String SQL = "select * from users";

        try(Connection conn = connect()){
            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                users.put(rs.getInt("id"),
                        new User(rs.getInt("id"), rs.getString("login"),
                                rs.getString("password"), rs.getString("fullname")));
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public boolean isLoginExist(String login){
        String SQL = "select login from users";
        try(Connection conn = connect()){
            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                if(login.equals(rs.getString("login"))) return true;
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public void addUser(User user){
        String SQL = "insert into users(login, password, fullname) values(?, ?, ?)";
        try(Connection conn = connect()){
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getFullname());
            stmt.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public List<User> getUsers(){
        Collection<User> c = users.values();
        List<User> list = new ArrayList();
        list.addAll(c);
        return list;
    }

    public int getUserIdByLogin(String login){
        String SQL = "select id from users where login=?";
        int id = 0;
        try(Connection conn = connect()){
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            id = rs.getInt("id");
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return id;
    }

    public int checkUser(String login, String password){
        UserDao userDao = new UserDao();
        for(User u : userDao.getUsers()){
            if (login.equals(u.getLogin()) && password.equals(u.getPassword())){
                return u.getId();
            }
        }
        return 0;
    }

    public boolean checkSuperPass(String password){
        if(password.equals(superPassword)) return true;
        return false;
    }
}
