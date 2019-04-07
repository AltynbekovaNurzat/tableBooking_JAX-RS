package booking.dao;

import booking.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static java.lang.Thread.sleep;

public class TablesDao extends Dao{
    private final HashMap<Integer, Table> tables = new HashMap();

    public TablesDao(){
        loadData();
    }

    private void loadData() {
        String SQL = "select * from tables";
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tables.put(rs.getInt("id"),
                        new Table(rs.getInt("id"),
                                rs.getInt("table_number"),
                                rs.getInt("numofseats"),
                                rs.getInt("place_id"),
                                rs.getBoolean("isbooked"),
                                rs.getInt("user_id")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Table> getTablesByPlace(int index){
        List<Table> tableList = new ArrayList();
        String SQL = "select * from tables where place_id="+index;
        try(Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                tableList.add(new Table(rs.getInt("id"),
                        rs.getInt("table_number"),
                        rs.getInt("numofseats"),
                        rs.getInt("place_id"),
                        rs.getBoolean("isbooked"),
                        rs.getInt("user_id")));
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return tableList;
    }

    public boolean bookTableByPlaceId(Table tb, int userId) {
        String SQL = "update tables set isbooked = true, user_id = ?, booked_time=? where place_id= ? and table_number = ?";
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, userId);
            stmt.setTimestamp(2, null);
            stmt.setInt(3, tb.getPlaceId());
            stmt.setInt(4, tb.getTableNumber());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }

    public boolean addTableByPlaceId(Table tb) {
        String SQL = "insert  into tables(table_number, numofseats, place_id) values(?, ?, ?)";
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, tb.getTableNumber());
            stmt.setInt(2, tb.getNumOfSeats());
            stmt.setInt(3, tb.getPlaceId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }

    public boolean removeBookTableById(Table tb) {
        String SQL = "update tables set isbooked = false, user_id= null where place_id= ? and table_number = ?";
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, tb.getPlaceId());
            stmt.setInt(2, tb.getTableNumber());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }

    public boolean checkTableByPlaceAndNumber(int placeId, int tableNumber){
        String SQL = "select isbooked from tables where place_id=? and table_number=?";
        boolean isBooked = true;
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, placeId);
            stmt.setInt(2, tableNumber);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            isBooked = rs.getBoolean("isbooked");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return isBooked;
    }

    public int getUserIdByPlaceIdAndNumber(int placeId, int tableNUmber){
        String SQL = "select user_id from tables where place_id = ? and table_number = ?";
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, placeId);
            stmt.setInt(2, tableNUmber);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt("user_id");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public List<Table> getTables(){
        Collection<Table> c = tables.values();
        List<Table> list = new ArrayList();
        list.addAll(c);
        return list;
    }

    // код для автосъема брони
    public List<Table> getAllTables(){
        List<Table> tableList = new ArrayList();
        String SQL = "select * from tables";
        try(Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                tableList.add(new Table(rs.getInt("id"),
                        rs.getInt("table_number"),
                        rs.getInt("numofseats"),
                        rs.getInt("place_id"),
                        rs.getBoolean("isbooked"),
                        rs.getInt("user_id")));
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return tableList;
    }

    public static long compareTwoTimeStamps(java.sql.Timestamp currentTime, java.sql.Timestamp bookedTime)
    {
        long milliseconds1 = bookedTime.getTime();
        long milliseconds2 = currentTime.getTime();
        long diff = milliseconds2 - milliseconds1;
        long diffSeconds = diff / 1000;
        long diffMinutes = diff / (60 * 1000);
        long diffHours = diff / (60 * 60 * 1000);
        long diffDays = diff / (24 * 60 * 60 * 1000);
        return diffMinutes;
    }

    public static void bookingAutoCancelation(){
        try {
            sleep(60000);
            TablesDao tablesDao = new TablesDao();
            for (Table table : tablesDao.getAllTables()){
                Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                if(table.getBookedTime()!=null){
                    long diffMin = compareTwoTimeStamps(currentTime,table.getBookedTime());
                    if (diffMin>30){
                        tablesDao.removeBookTableById(table);
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bookingAutoCancelation();
    }

    public static void main(String[] args) {
        //bookingAutoCancelation();
    }

}
