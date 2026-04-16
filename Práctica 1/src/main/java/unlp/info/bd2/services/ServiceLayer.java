package unlp.info.bd2.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import unlp.info.bd2.model.DriverUser;
import unlp.info.bd2.model.ItemService;
import unlp.info.bd2.model.Purchase;
import unlp.info.bd2.model.Review;
import unlp.info.bd2.model.Route;
import unlp.info.bd2.model.Service;
import unlp.info.bd2.model.Stop;
import unlp.info.bd2.model.Supplier;
import unlp.info.bd2.model.TourGuideUser;
import unlp.info.bd2.model.User;
import unlp.info.bd2.repositories.PurchaseRepository;
import unlp.info.bd2.repositories.ReviewRepository;
import unlp.info.bd2.repositories.RouteRepository;
import unlp.info.bd2.repositories.ServiceRepository;
import unlp.info.bd2.repositories.SupplierRepository;
import unlp.info.bd2.repositories.UserRepository;
import unlp.info.bd2.utils.ToursException;

public class ServiceLayer implements ToursService {

    private PurchaseRepository purchaseRepository;
    private ReviewRepository reviewRepository;
    private RouteRepository routeRepository;
    private ServiceRepository serviceRepository;
    private SupplierRepository supplierRepository;
    private UserRepository userRepository;

    @Transactional
    public User createUser(String username, String password, String fullName, String email, Date birthdate, String phoneNumber) throws ToursException {
        User user = new User(username,password,fullName,email,birthdate,phoneNumber);
        this.userRepository.save(user);
        return user;
    }

    @Transactional
    public DriverUser createDriverUser(String username, String password, String fullName, String email, Date birthdate, String phoneNumber, String expedient) throws ToursException {
        DriverUser driverUser = new DriverUser(username, password, fullName, email, birthdate, phoneNumber, expedient);
        this.userRepository.save(driverUser);
        return driverUser;
    }

    @Transactional
    public TourGuideUser createTourGuideUser(String username, String password, String fullName, String email, Date birthdate, String phoneNumber, String education) throws ToursException {
        TourGuideUser tourGuideUser = new TourGuideUser(username, password, fullName, email, birthdate, phoneNumber, education);
        this.userRepository.save(tourGuideUser);
        return tourGuideUser;
    }

    @Transactional
    public Optional<User> getUserById(Long id) throws ToursException {
        return this.userRepository.getUserById(id);
    }

    @Transactional
    public Optional<User> getUserByUsername(String username) throws ToursException {
        return this.userRepository.getUserByUsername(username);
    }

    @Transactional
    public User updateUser(User user) throws ToursException {
        this.userRepository.update(user);
    }

    @Transactional
    public void deleteUser(User user) throws ToursException {
        this.userRepository.delete(user);
    }

    @Transactional
    public Stop createStop(String name, String description) throws ToursException {
    }

    @Transactional
    public List<Stop> getStopByNameStart(String name) {
    }

    @Transactional
    public Route createRoute(String name, float price, float totalKm, int maxNumberOfUsers, List<Stop> stops)
        Route route = new Route(name, price, totalKm, maxNumberOfUsers, stops);
        this.routeRepository.save(route);
        return route;
    }

    @Transactional
    public Optional<Route> getRouteById(Long id) {
        return this.routeRepository.getRouteById(id);
    }

    @Transactional
    public List<Route> getRoutesBelowPrice(float price) {
        return this.routeRepository.getRoutesBelowPrice(price);
    }

    // Incluir saves
    @Transactional
    public void assignDriverByUsername(String username, Long idRoute) throws ToursException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assignDriverByUsername'");
    }

    @Transactional
    public void assignTourGuideByUsername(String username, Long idRoute) throws ToursException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assignTourGuideByUsername'");
    }

    @Transactional
    public Supplier createSupplier(String businessName, String authorizationNumber) throws ToursException {
        Supplier supplier = new Supplier(businessName, authorizationNumber);
        this.supplierRepository.save(supplier);
        return supplier;
    }

    @Transactional
    public Service addServiceToSupplier(String name, float price, String description, Supplier supplier)
            throws ToursException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addServiceToSupplier'");
    }

    @Transactional
    public Service updateServicePriceById(Long id, float newPrice) throws ToursException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateServicePriceById'");
    }

    @Transactional
    public Optional<Supplier> getSupplierById(Long id) {
        this.supplierRepository.getSupplierById(id);
    }

    @Transactional
    public Optional<Supplier> getSupplierByAuthorizationNumber(String authorizationNumber) {
        this.supplierRepository.getSupplierByAuthorizationNumber(authorizationNumber);
    }

    @Transactional
    public Optional<Service> getServiceByNameAndSupplierId(String name, Long id) throws ToursException {
        this.serviceRepository.getServiceByNameAndSupplierId(name, id);
    }

    @Transactional
    public Purchase createPurchase(String code, Route route, User user) throws ToursException {
        
    }

    @Transactional
    public Purchase createPurchase(String code, Date date, Route route, User user) throws ToursException {
        Purchase purchase = new Purchase(code, date, user, route);
        this.purchaseRepository.save(purchase);
        return purchase;
    }

    @Transactional
    public ItemService addItemToPurchase(Service service, int quantity, Purchase purchase) throws ToursException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addItemToPurchase'");
    }

    @Transactional
    public Optional<Purchase> getPurchaseByCode(String code) {
        this.purchaseRepository.getPurchaseByCode(code);
    }

    @Transactional
    public void deletePurchase(Purchase purchase) throws ToursException {
        this.purchaseRepository.delete(purchase);
    }

    @Transactional
    public Review addReviewToPurchase(int rating, String comment, Purchase purchase) throws ToursException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addReviewToPurchase'");
    }

    @Transactional
    public void deleteRoute(Route route) throws ToursException {
        this.routeRepository.delete(route);
    }

    @Transactional
    public List<Purchase> getAllPurchasesOfUsername(String username) {
        return this.purchaseRepository.getAllPurchasesOfUsername(username);
    }

    @Transactional
    public List<User> getUserSpendingMoreThan(float mount) {
        return this.userRepository.getUserSpendingMoreThan(mount);
    }

    @Transactional
    public List<Supplier> getTopNSuppliersInPurchases(int n) {
        return this.supplierRepository.getTopNSuppliersInPurchases(n);
    }

    @Transactional
    public long getCountOfPurchasesBetweenDates(Date start, Date end) {
        return this.getCountOfPurchasesBetweenDates(start, end);
    }

    @Transactional
    public List<Route> getRoutesWithStop(Stop stop) {
        this.routeRepository.getRoutesWithStop(stop);
    }

    @Transactional
    public Long getMaxStopOfRoutes() {
        this.routeRepository.getMaxStopOfRoutes();
    }

    @Transactional
    public List<Route> getRoutsNotSell() {
        this.routeRepository.getRoutesNotSell();
    }

    @Transactional
    public List<Route> getTop3RoutesWithMaxRating() {
        this.routeRepository.getTop3RoutesWithMaxRating();
    }

    @Transactional
    public Service getMostDemandedService() {
        this.serviceRepository.getMostDemandedService();
    }

    @Transactional
    public List<TourGuideUser> getTourGuidesWithRating1() {
        this.userRepository.getTourGuidesWithRating1();
    }   
}