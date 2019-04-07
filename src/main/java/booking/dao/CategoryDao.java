package booking.dao;

import booking.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao extends Dao{
    public void addCategory(String name){
        String SQL = "insert into categories(name) values(?)";
        try(Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setString(1, name);
            stmt.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public List<Category> getCategories(){
        List<Category> categories = new ArrayList<>();
        String SQL = "select * from categories";
        try(Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                categories.add(new Category(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return categories;
    }
}
