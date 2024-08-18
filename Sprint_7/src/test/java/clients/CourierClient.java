package clients;

import dto.Courier;
import dto.DeleteCourier;
import io.qameta.allure.Step;
import io.restassured.response.Response;


public class CourierClient extends RestClient {
    private static final String PATH = "api/v1/courier";
    private static final String LOGIN_PATH = "api/v1/courier/login";
    private static final String DELETE_PATH = "api/v1/courier/:id";

    public Response createCourier(Courier courier) {
        return getDefaultRequestSpecification()
                .body(courier)
                .when()
                .post(PATH);
    }

    public Response loginCourier(Courier courier) {
        return  getDefaultRequestSpecification()
                .body(courier)
                .when()
                .post(LOGIN_PATH);
    }



    @Step("Удаляем учетную запись курьера")
    public Response deleteCourier(DeleteCourier deleteCourier) {
        return getDefaultRequestSpecification()
                .body(deleteCourier)
                .when()
                .delete(DELETE_PATH);
    }
}

