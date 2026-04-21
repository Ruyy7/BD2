package unlp.info.bd2.model;

import java.util.Date;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "purchases")
public class Purchase {

    Long id;

    private String code;

    private float totalPrice;

    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "route_id", nullable = false)
    private Route route;

    @OneToOne(optional = true, mappedBy = "purchase", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Review review;

    @OneToMany (mappedBy = "purchase", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<ItemService> itemServiceList;

    public Purchase(String code, User user, Route route) {
        this.code = code;
        this.date = new Date();
        this.user = user;
        this.route = route;
    }

    public Purchase(String code, Date date, User user, Route route) {
        this.code = code;
        this.date = date;
        this.user = user;
        this.route = route;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public List<ItemService> getItemServiceList() {
        return itemServiceList;
    }

    public void setItemServiceList(List<ItemService> itemServiceList) {
        this.itemServiceList = itemServiceList;
    }
    
    public void addItem(ItemService item) {
        itemServiceList.add(item);
        item.setPurchase(this);
        item.getService().getItemServiceList().add(item);
        this.totalPrice += item.getService().getPrice() * item.getQuantity();
    }

    public void removeItem(ItemService item) {
        itemServiceList.remove(item);
        item.getService().getItemServiceList().remove(item);
        item.setPurchase(null);
        this.totalPrice -= item.getService().getPrice() * item.getQuantity();
    }
}
