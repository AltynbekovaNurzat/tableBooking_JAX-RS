package booking.service;


import booking.dao.UserDao;
import booking.model.JSON_Resp;
import booking.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/user")
public class AuthService {
    @POST
    @Produces({ MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML })
    public JSON_Resp registration(User user){
        UserDao userDao = new UserDao();
        JSON_Resp json_resp = new JSON_Resp();
        if(userDao.isLoginExist(user.getLogin())){
            json_resp.setResponse("Login " + user.getLogin() + " is already exist");
        } else {
            json_resp.setResponse("Registration was successful");
            userDao.addUser(user);
        }
        return json_resp;
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML })
    public JSON_Resp authorization(@HeaderParam("login") String login,
                                   @HeaderParam("password") String password){
        UserDao userDao = new UserDao();
        JSON_Resp json_resp = new JSON_Resp();
        if(userDao.checkUser(login, password)!=0){
            json_resp.setResponse("Authorization was successful");
        } else {
            json_resp.setResponse("Wrong login or password");
        }
        return json_resp;
    }
}
