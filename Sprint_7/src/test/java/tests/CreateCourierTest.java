package tests;

import steps.CourierSteps;
import clients.CourierClient;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateCourierTest {
    private CourierSteps courierSteps;
    private String login;
    private String password;
    private String firstName;

    @Before
    public void setUp() {
        courierSteps = new CourierSteps(new CourierClient());
        login = RandomStringUtils.randomAlphabetic(8);
        password = RandomStringUtils.randomAlphabetic(6);
        firstName = RandomStringUtils.randomAlphabetic(4);
    }

    @After
    public void tearDown() {
        try {
            courierSteps.deleteCourier(login, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Создание нового курьера")
    public void createNewCourier() {
        courierSteps
                .createNewCourier(login, password, firstName)
                .statusCode(SC_CREATED)
                .assertThat()
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Создание второго идентичного курьера")
    public void SameCourierCreateTest(){
        courierSteps
                .createNewCourier(login, password, firstName);

        courierSteps
                .createNewCourier(login, password, firstName)
                .statusCode(HttpStatus.SC_CONFLICT)
                .assertThat()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Создание курьера без заполнения поля логин")
    public void createCourierWithoutLoginTest() {
        courierSteps
                .createNewCourier(password, firstName)
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));

    }
}
