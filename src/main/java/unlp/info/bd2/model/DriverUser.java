package unlp.info.bd2.model;


import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "driveusers") 
@PrimaryKeyJoinColumn(name = "user_id")
// @DiscriminatorValue("Driver") <- Punto SINGLE_TABLE

public class DriverUser extends User {

    private String expedient;

    private List<Route> routes;
    @ManyToMany(mappedBy = "driverList", fetch = FetchType.LAZY)

    public String getExpedient() {
        return expedient;
    }

    public void setExpedient(String expedient) {
        this.expedient = expedient;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRouts(List<Route> routs) {
        this.routes = routs;
    }
}
