package be.thomasmore.party.model;

public class Venue {
    private String venueName;
    private String linkMoreInfo;
    private int capacity;
    private boolean indoor;
    private String city;


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