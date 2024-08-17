package dto;
import java.util.List;


public class OrderCreate {
    private String firstName;
    private String lastName;
    private String address;
    private int metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color;

    public OrderCreate() {
    }

    public OrderCreate(String firstName, String lastName, String address, int metroStation, String phone,
                       int rentTime, String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    public String getFirstName() {
        return firstName;
    }

    public OrderCreate setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public OrderCreate setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public OrderCreate setAddress(String address) {
        this.address = address;
        return this;
    }

    public int getMetroStation() {
        return metroStation;
    }

    public OrderCreate setMetroStation(int metroStation) {
        this.metroStation = metroStation;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public OrderCreate setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public int getRentTime() {
        return rentTime;
    }

    public OrderCreate setRentTime(int rentTime) {
        this.rentTime = rentTime;
        return this;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public OrderCreate setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public OrderCreate setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public List<String> getColor() {
        return color;
    }

    public OrderCreate setColor(List<String> color) {
        this.color = color;
        return this;
    }

}
