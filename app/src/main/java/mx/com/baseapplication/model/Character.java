package mx.com.baseapplication.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Character implements Serializable {

    private Integer id;
    private String name;
    private String status;
    private String species;
    private Object type;
    private String gender;
    private Origin origin;
    private Locacion location;
    private String image;
    private ArrayList<String> episode;
    private String url;
    private String created;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public Locacion getLocation() {
        return location;
    }

    public void setLocation(Locacion location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList getEpisode() {
        return episode;
    }

    public void setEpisode(ArrayList episode) {
        this.episode = episode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
