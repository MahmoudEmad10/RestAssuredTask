package AddingNewDevice;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;

public class addDevice {

    private static final String BASE_URL = "https://api.restful-api.dev/objects";

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @Description("Validate API response")
    public void AddNewDevice() {
        Response response = given().header("Content-Type", "application/json")
                .body(new File("src/main/resources/newDevice.json"))
                .when()
                .post()
                .then()
                .assertThat()
                .statusCode(200).extract().response();
        System.out.println(response);
        String id = response.jsonPath().getString("id");
        String name = response.jsonPath().getString("name");
        String createdAt = response.jsonPath().getString("createdAt");
        String year = response.jsonPath().getString("data.year");
        double price = response.jsonPath().getDouble("data.price");
        String cpuModel = response.jsonPath().getString("data.'CPU model'");
        String hardDiskSize = response.jsonPath().getString("data.'Hard disk size'");


        Assert.assertEquals(name, "Apple Max Pro 1TB");
        Assert.assertEquals(year, "2023");
        Assert.assertEquals(price, 7999.99);
        Assert.assertEquals(cpuModel, "Apple ARM A7");
        Assert.assertEquals(hardDiskSize, "1 TB");

        Assert.assertNotNull(id);
        Assert.assertNotNull(createdAt);


        System.out.println(id);
        System.out.println(name);
        System.out.println(year);
        System.out.println(price);
        System.out.println(cpuModel);
        System.out.println(hardDiskSize);

    }


}


