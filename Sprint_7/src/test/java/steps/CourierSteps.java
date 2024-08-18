package steps;

import clients.CourierClient;
import dto.Courier;
import dto.DeleteCourier;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;


public class CourierSteps {
    private final CourierClient courierClient;

    public CourierSteps(CourierClient courierClient) {
        this.courierClient = courierClient;
    }

    @Step("Создание курьера")
    public ValidatableResponse createNewCourier(String login, String password, String firstName) {
        Courier courier = new Courier();
        courier
                .setLogin(login)
                .setPassword(password)
                .setFirstName(firstName);
        return courierClient.createCourier(courier)
                .then();
    }

    @Step("Создание курьера без логина")
    public ValidatableResponse createNewCourier(String password, String firstName) {
        Courier courier = new Courier();
        courier
                .setPassword(password)
                .setFirstName(firstName);
        return courierClient.createCourier(courier)
                .then();
    }
    @Step("Выполняем вход в аккаунт курьера")
    public  ValidatableResponse loginCourier(String login, String password) {
        Courier courier = new Courier();
        courier
                .setLogin(login)
                .setPassword(password);
        return courierClient.loginCourier(courier)
                .then();
    }

    @Step("Удаляем учетную запись курьера")
    public ValidatableResponse deleteCourier(String login, String password) {
        Courier courier = new Courier();
        courier
                .setLogin(login)
                .setPassword(password);
        int id = courierClient.loginCourier(courier)
                .then().extract().path("id");

        DeleteCourier deleteCourier = new DeleteCourier();
        deleteCourier.setId(id);
        return courierClient.deleteCourier(deleteCourier)
                .then();
    }
}
