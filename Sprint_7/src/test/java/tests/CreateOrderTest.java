package tests;

import java.util.List;

import Steps.OrderSteps;
import clients.OrderClient;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private OrderSteps orderSteps;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final int metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final List<String> colour;

    public CreateOrderTest(String firstName, String lastName, String address, int metroStation, String phone, int rentTime, String deliveryDate, String comment, List<String> colour) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.colour = colour;
    }

    @Before
    public void setup(){
        orderSteps = new OrderSteps(new OrderClient());
    }

    @Parameterized.Parameters
    public static Object[][] testDataForCreateOrder() {
        return new Object[][]{
                {"Vasiliy", "Petrov", "65 Lenin Prospect", 1, "+7 964 432 10 98", 3, "2024-07-19", "Буду ждать у 1го подъезда", List.of("GREY", "BLACK")},
                {"Andrey", "Vodkin", "18 Slava Lane", 8, "+7 952 123 45 67", 8, "2024-08-11", "Позвоните заранее", List.of("GREY")},
                {"Anna", "Pavlova", "12 Pastor Street", 4, "+7 911 111 22 33", 1, "2024-08-02", "", List.of("BLACK")},
                {"Ivan", "Alekseev", "26 Revolution Square", 3, "+7 963 861 14 15", 12, "2024-05-05", "Цвет неважен", null}
        };
    }

    @Test
    @DisplayName("Create order")
    public void CreateOrderTest(){
        orderSteps
                .createOrder(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, colour)
                .statusCode(SC_CREATED)
                .assertThat()
                .body("track", notNullValue());
    }

}