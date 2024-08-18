package clients;


import dto.OrderCreate;
import dto.OrderList;

import io.restassured.response.Response;


public class OrderClient extends RestClient {
    private static final String PATH = "api/v1/orders";

    public Response createOrder(OrderCreate orderCreate) {
        return getDefaultRequestSpecification()
                .body(orderCreate)
                .when()
                .post(PATH);
    }

    public Response getOrders(OrderList orderList) {
        return getDefaultRequestSpecification()
                .body(orderList)
                .when()
                .get(PATH);
    }
}