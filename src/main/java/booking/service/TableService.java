package booking.service;

import booking.dao.PlaceDao;
import booking.dao.TablesDao;
import booking.model.JSON_Resp;
import booking.model.Place;
import booking.model.Table;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/table")
public class TableService {
    @GET
    @Produces({ MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML })
    public List<Table> getTables_JSON(){
        TablesDao tablesDao = new TablesDao();
        return tablesDao.getTables();
    }

    @GET
    @Path("{place_id}")
    @Produces({ MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML })
    public List<Table> getTablesById_JSON(@PathParam("place_id") int id) {
        TablesDao tablesDao = new TablesDao();
        return tablesDao.getTablesByPlace(id);
    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML})
    public JSON_Resp addTable(Table table, @HeaderParam("password")String adminpass){
        JSON_Resp json_resp = new JSON_Resp();
        TablesDao tablesDao = new TablesDao();
        PlaceDao placeDao = new PlaceDao();
        if(placeDao.checkAdminPass(table.getPlaceId(), adminpass)){
            tablesDao.addTableByPlaceId(table);
            json_resp.setResponse("PlaceId " + table.getPlaceId() +
                                ", table №" + table.getTableNumber());
        } else json_resp.setResponse("Wrong admin pass");
        return json_resp;
    }
}
