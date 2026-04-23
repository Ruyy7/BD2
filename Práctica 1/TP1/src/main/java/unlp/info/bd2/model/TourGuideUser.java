package unlp.info.bd2.model;


// import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "tourguideusers")
@PrimaryKeyJoinColumn(name = "user_id")
// @DiscriminatorValue("TourGuide") <- Punto SINGLE_TABLE

public class TourGuideUser extends User {
    private String education;

    @ManyToMany(mappedBy = "tourGuideList")
    private List<Route> routes;

    public TourGuideUser(String username, String password, String fullName, String email, Date birthdate, String phoneNumber, String educaction) {
        super(username, password, fullName, email, birthdate, phoneNumber);
        this.education = educaction;
    }

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
