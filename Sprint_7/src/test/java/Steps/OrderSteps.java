package Steps;

import java.util.List;

import clients.OrderClient;
import dto.OrderCreate;
import dto.OrderList;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class OrderSteps {
    private final OrderClient orderClient;

    public OrderSteps(OrderClient orderClient) {
        this.orderClient = orderClient;
    }

    @Step("Создание заказа")
    public ValidatableResponse createOrder(String firstName, String lastName, String address, int metroStation, String phone,
                                           int rentTime, String deliveryDate, String comment, List<String> color) {
        OrderCreate orderCreate = new OrderCreate();
        orderCreate
                .setFirstName(firstName)
                .setLastName(lastName)
                .setAddress(address)
                .setMetroStation(metroStation)
                .setPhone(phone)
                .setRentTime(rentTime)
                .setDeliveryDate(deliveryDate)
                .setComment(comment)
                .setColor(color);
        return orderClient.createOrder(orderCreate)
                .then();
    }

    @Step("Получаем список заказов")
    public ValidatableResponse getOrderList() {
        OrderList orderList = new OrderList();
        return orderClient.getOrders(orderList)
                .then();
    }
}
