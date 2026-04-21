package unlp.info.bd2.model;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "routes")
public class Route {

    private Long id;

    private String name;

    private float price;

    private float totalKm;

    private int maxNumberUsers;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable (name = "route_stops", joinColumns = @JoinColumn(name = "route_id"))
    private List<Stop> stops;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "route_drivers", joinColumns = @JoinColumn(name = "route_id"))
    private List<DriverUser> driverList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "route_tourGuides", joinColumns = @JoinColumn(name = "route_id"))
    private List<TourGuideUser> tourGuideList;

    public Route(String name, float price, float totalKm, int maxNumberUsers, List<Stop> stops) {
        this.name = name;
        this.price = price;
        this.totalKm = totalKm;
        this.maxNumberUsers = maxNumberUsers;
        this.stops = stops;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotalKm() {
        return totalKm;
    }

    public void setTotalKm(float totalKm) {
        this.totalKm = totalKm;
    }

    public int getMaxNumberUsers() {
        return maxNumberUsers;
    }

    public void setMaxNumberUsers(int maxNumberUsers) {
        this.maxNumberUsers = maxNumberUsers;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    public List<DriverUser> getDriverList() {
        return driverList;
    }

    public void setDriverList(List<DriverUser> driverList) {
        this.driverList = driverList;
    }

    public List<TourGuideUser> getTourGuideList() {
        return tourGuideList;
    }

    public void setTourGuideList(List<TourGuideUser> tourGuideList) {
        this.tourGuideList = tourGuideList;
    }

    public void addDriver(DriverUser driverUser1) {
        this.driverList.add(driverUser1);
    }

    public void addTourGuide(TourGuideUser tourGuideUser1) {
        this.tourGuideList.add(tourGuideUser1);
    }

}
