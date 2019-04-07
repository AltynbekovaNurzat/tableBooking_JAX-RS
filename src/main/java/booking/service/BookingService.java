package booking.service;

import booking.dao.PlaceDao;
import booking.dao.TablesDao;
import booking.dao.UserDao;
import booking.model.JSON_Resp;
import booking.model.Table;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/booking")
public class BookingService {
    @POST
    @Produces({ MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML })
    public JSON_Resp bookTablesById(Table tb, @HeaderParam("login") String login, @HeaderParam("password")String pass) {
        TablesDao tablesDao = new TablesDao();
        UserDao userDao = new UserDao();
        JSON_Resp json_resp = new JSON_Resp(tb);
        if(userDao.checkUser(login, pass)!=0){
            if(!tablesDao.checkTableByPlaceAndNumber(tb.getPlaceId(), tb.getTableNumber()))
                tablesDao.bookTableByPlaceId(tb, userDao.getUserIdByLogin(login));
            else json_resp.setResponse("Table is already booked");
        } else json_resp.setResponse("Wrong login or password");
        return json_resp;
    }

    @DELETE
    @Produces({ MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML })
    public JSON_Resp removeBooking(Table tb, @HeaderParam("password") String adminPass){
        JSON_Resp json_resp = new JSON_Resp("Wrong admin password");
        TablesDao tablesDao = new TablesDao();
        PlaceDao placeDao = new PlaceDao();
        if(placeDao.checkAdminPass(tb.getPlaceId(), adminPass)){
            tablesDao.removeBookTableById(tb);
            json_resp.setResponse("Table is free now");
        }
        return json_resp;
    }

    @PUT
    @Produces({ MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML })
    public JSON_Resp removeBookingByClient(Table tb, @HeaderParam("login") String login, @HeaderParam("password")String pass){
        JSON_Resp json_resp = new JSON_Resp("Wrong login or password");
        TablesDao tablesDao = new TablesDao();
        UserDao userDao = new UserDao();
        System.out.println(userDao.checkUser(login, pass));
        System.out.println(tb.getUserId());
        if(userDao.checkUser(login, pass)==tablesDao.getUserIdByPlaceIdAndNumber(tb.getPlaceId(), tb.getTableNumber())){
            tablesDao.removeBookTableById(tb);
            json_resp.setResponse("Table is free now");
        }
        return json_resp;
    }
}