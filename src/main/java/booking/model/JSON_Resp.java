package booking.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="response")
@XmlAccessorType(XmlAccessType.FIELD)
public class JSON_Resp {
    private String response;

    public JSON_Resp() {}

    public JSON_Resp(String response){
        this.response = response;
    }

    public JSON_Resp(Place place, int id){
        this.response = "Place " + place.getName() + " has been added, ID:" + id + ", admin pass: " + place.getAdminPass();
    }

    public JSON_Resp(Table table) {
        this.response = "Table N" + table.getTableNumber() + " has been booked";
    }

    public String getResponse(){
        return this.response;
    }

    public void setResponse(String response){
        this.response = response;
    }
}
