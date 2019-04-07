package booking.service;

import booking.dao.PlaceDao;
import booking.dao.UserDao;
import booking.model.JSON_Resp;
import booking.model.Place;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/place")
public class PlaceService {
    @GET
    @Produces({ MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML })
    public List<Place> getPlaces_JSON(){
        PlaceDao placeDao = new PlaceDao();
        return placeDao.getPlaces();
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML })
    public List<Place> getPlacesByCategory_JSON(@PathParam("id") int id){
        PlaceDao placeDao = new PlaceDao();
        return placeDao.getPlacesByCategory(id);
    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML })
    public JSON_Resp addPlace(Place place, @HeaderParam("password") String superPassword){
        PlaceDao placeDao = new PlaceDao();
        UserDao userDao = new UserDao();
        placeDao.addPlace(place);
        if(userDao.checkSuperPass(superPassword)){
            return new JSON_Resp(place, placeDao.getLastId());
        }
        return new JSON_Resp("Wrong password");
    }
}
