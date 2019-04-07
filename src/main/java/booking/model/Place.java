package booking.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="place")
@XmlAccessorType(XmlAccessType.FIELD)
public class Place {
    private int id;
    private String name;
    private String address;
    private int category;
    private String adminPass;
    private List<Table> tables = new ArrayList();
    private String imgURL;

    public Place() {}

    public Place(int id, int category, String name, String address, String imgURL){
        this.setId(id);
        this.category = category;
        this.setName(name);
        this.setAddress(address);
        this.setImgURL(imgURL);
    }

    public Place(int id, String name, String address){
        this.setId(id);
        this.setName(name);
        this.setAddress(address);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) { this.address = address; }

    public int getCategory() { return category; }

    public void setCategory(int category) { this.category = category; }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(ArrayList<Table> tables) {
        this.tables = tables;
    }

    public String getAdminPass() {
        return adminPass;
    }

    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}

