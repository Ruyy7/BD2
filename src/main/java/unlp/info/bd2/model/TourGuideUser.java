package unlp.info.bd2.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
// @Table(name = "tourguideusers") <- Lo use para JOINED
// @DiscriminatorValue("TourGuide") <- Punto SINGLE_TABLE

public class TourGuideUser extends User {

    private String education;

    @ManyToMany(mappedBy = "tourGuideList")
    private List<Route> routes;

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

}
