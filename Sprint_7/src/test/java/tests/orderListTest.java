package tests;

import Steps.OrderSteps;
import clients.OrderClient;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.notNullValue;

public class orderListTest {
    private OrderSteps orderSteps;

    @Before
    public void setUp(){
        orderSteps = new OrderSteps(new OrderClient());
    }

    @Test
    @DisplayName("Получение списка заказов")
    public void ordersListTest() {
        orderSteps
                .getOrderList()
                .statusCode(SC_OK)
                .assertThat()
                .body("orders", notNullValue());
    }
}