package be.thomasmore.party.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Venue {
    @Id
    private int id;
    private String venueName;
    private String linkMoreInfo;
    private int capacity;
    private boolean foodProvided;
    private boolean indoor;
    private boolean outdoor;
    private boolean freeParkingAvailable;
    private String city;
    private double distanceFromPublicTransportInKm;


    public Venue() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Venue(String venueName, String linkMoreInfo, int capacity, boolean indoor, String city ){
        this.venueName = venueName;
        this.linkMoreInfo = linkMoreInfo;
        this.capacity = capacity;
        indoor = true;
        this.city = city;


    }

    public String getCity() {
        return city;
    }

    public boolean isIndoor() {
        return indoor;
    }

    public int getCapacity() {
        return capacity;
    }


    public String getLinkMoreInfo() {
        return linkMoreInfo;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public void setLinkMoreInfo(String linkMoreInfo) {
        this.linkMoreInfo = linkMoreInfo;
    }
}