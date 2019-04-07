package booking.service;

import booking.dao.CategoryDao;
import booking.dao.PlaceDao;
import booking.dao.UserDao;
import booking.model.Category;
import booking.model.JSON_Resp;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/category")
public class CategoryService {
    @POST
    @Produces({ MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML })
    public JSON_Resp addCategory(Category category, @HeaderParam("password") String superPassword){
        CategoryDao categoryDao = new CategoryDao();
        UserDao userDao = new UserDao();
        if(userDao.checkSuperPass(superPassword)){
            categoryDao.addCategory(category.getName());
            return new JSON_Resp("New category " + category.getName() + " has been added");
        }
        return new JSON_Resp("Wrong password");
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML })
    public List<Category> getCategories(){
        CategoryDao categoryDao = new CategoryDao();
        return categoryDao.getCategories();
    }
}
