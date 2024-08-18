package tests;

import steps.CourierSteps;
import clients.CourierClient;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CourierLoginTest {
    private CourierSteps courierSteps;
    private String login;

    private String password;
    private String firstName;


    @Before
    public void setUp() {
        courierSteps = new CourierSteps(new CourierClient());
        login = RandomStringUtils.randomAlphabetic(8);
        password = RandomStringUtils.randomAlphabetic(6);
        firstName = RandomStringUtils.randomAlphabetic(6);
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
    @DisplayName("Авторизация зарегистрированного курьера")
    public void courierLoginTest(){
        courierSteps
                .createNewCourier(login, password, firstName);
        courierSteps
                .loginCourier(login, password)
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Авторизация зарегистрированного курьера без ввода пароля")
    public void loginCourierWithoutPasswordTest(){
        courierSteps
                .createNewCourier(login, password, firstName);
        courierSteps
                .loginCourier(login, "")
                .statusCode(SC_BAD_REQUEST)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Авторизация курьера без ввода логина")
    public void loginCourierWithoutLoginTest(){
        courierSteps
                .createNewCourier(login, password, firstName);
        courierSteps
                .loginCourier("", password)
                .statusCode(SC_BAD_REQUEST)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"));
    }
    @Test
    @DisplayName("Авторизация незарегистрированного курьера")
    public void loginUnregisteredCourierTest(){
        String randomLogin = RandomStringUtils.randomAlphabetic(8);
        String randomPassword = RandomStringUtils.randomAlphabetic(8);
        courierSteps
                .loginCourier(randomLogin, randomPassword)
                .statusCode(SC_NOT_FOUND)
                .assertThat()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Авторизация курьера, ввод неверного пароля")
    public void courierLoginWithWrongPasswordTest(){
        courierSteps
                .createNewCourier(login, password, firstName);
        courierSteps
                .loginCourier(login, "12345678")
                .statusCode(SC_NOT_FOUND)
                .assertThat()
                .body("message", equalTo("Учетная запись не найдена"));
    }

}

