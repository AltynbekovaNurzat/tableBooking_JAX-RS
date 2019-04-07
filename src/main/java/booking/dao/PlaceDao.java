package booking.dao;

import booking.model.Place;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class PlaceDao extends Dao {

    private final HashMap<Integer, Place> places = new HashMap();

    public PlaceDao() {
        loadData();
    }

    private void loadData() {
        String SQL = "select * from places";
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                places.put(rs.getInt("id"),
                        new Place(rs.getInt("id"),
                                rs.getInt("category_id"),
                                rs.getString("name"),
                                rs.getString("address"),
                                rs.getString("imgurl")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void addPlace(Place place){
        String SQL = "insert into places(name, address, category_id, adminpass) values(?, ?, ?, ?)";
        try(Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setString(1, place.getName());
            stmt.setString(2, place.getAddress());
            stmt.setInt(3, place.getCategory());
            stmt.setString(4, place.getAdminPass());
            stmt.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public List<Place> getPlacesBySearch(String search){
        List<Place> searchPlaces = new ArrayList<>();
        String SQL = "select * from places where name like '%" + search + "%'";
        try(Connection conn = connect()){
            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                searchPlaces.add(new Place(rs.getInt("id"),
                        rs.getInt("category_id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("imgurl")));
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return searchPlaces;
    }

    public int getLastId(){
        String SQL = "SELECT id FROM places ORDER BY ID DESC LIMIT 1";
        try(Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt("id");
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public List<Place> getPlacesByCategory(int index){
        List<Place> placesList = new ArrayList();
        String SQL = "select * from places where category_id="+index;
        try(Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                placesList.add(new Place(rs.getInt("id"),
                        rs.getInt("category_id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("imgurl")));
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return placesList;
    }

    public boolean checkAdminPass(int placeId, String password){
        String SQL = "select adminpass from places where id="+placeId;
        try(Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            if(password.equals(rs.getString("adminpass"))) return true;
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public List<Place> getPlaces(){
        Collection<Place> c = places.values();
        List<Place> list = new ArrayList();
        list.addAll(c);
        return list;
    }
}
