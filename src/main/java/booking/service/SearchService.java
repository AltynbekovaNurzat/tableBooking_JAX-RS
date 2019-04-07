package booking.service;

import booking.dao.PlaceDao;
import booking.model.Place;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/search")
public class SearchService {
    @GET
    @Produces({MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML})
    public List<Place> getPlacesBySearch_JSON(@HeaderParam("search") String search){
        PlaceDao placeDao = new PlaceDao();
        return placeDao.getPlacesBySearch(search);
    }
}
