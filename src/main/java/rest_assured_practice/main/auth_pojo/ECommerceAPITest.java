package rest_assured_practice.main.auth_pojo;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import rest_assured_practice.main.auth_pojo.pojo.LoginRequest;
import rest_assured_practice.main.auth_pojo.pojo.LoginResponse;
import rest_assured_practice.main.auth_pojo.pojo.OrderDetail;
import rest_assured_practice.main.auth_pojo.pojo.Orders;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ECommerceAPITest {

	public static void main(String[] args) {
		// Set up Base Request Specification
		RequestSpecification req = new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON)
				.build();

		// Login
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserEmail("rahulshetty@gmail.com");
		loginRequest.setUserPassword("Iamking@000");

		RequestSpecification reqLogin = given()
				.relaxedHTTPSValidation()
				.log().all()
				.spec(req)
				.body(loginRequest);

		LoginResponse loginResponse = reqLogin.when()
				.post("/api/ecom/auth/login")
				.then().log().all()
				.extract().response()
				.as(LoginResponse.class);

		String token = loginResponse.getToken();
		String userId = loginResponse.getUserId();

		// Add Product
		RequestSpecification addProductBaseReq = new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", token)
				.build();

		RequestSpecification reqAddProduct = given()
				.log().all()
				.spec(addProductBaseReq)
				.param("productName", "Laptop")
				.param("productAddedBy", userId)
				.param("productCategory", "fashion")
				.param("productSubCategory", "shirts")
				.param("productPrice", "11500")
				.param("productDescription", "Lenova")
				.param("productFor", "men")
				.multiPart("productImage", new File("//users//rahulshetty//documents//laptop.png"));

		String addProductResponse = reqAddProduct.when()
				.post("/api/ecom/product/add-product")
				.then().log().all()
				.extract().response().asString();

		JsonPath js = new JsonPath(addProductResponse);
		String productId = js.get("productId");

		// Create Order
		RequestSpecification createOrderBaseReq = new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", token)
				.setContentType(ContentType.JSON)
				.build();

		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setCountry("India");
		orderDetail.setProductOrderedId(productId);

		List<OrderDetail> orderDetailList = new ArrayList<>();
		orderDetailList.add(orderDetail);

		Orders orders = new Orders();
		orders.setOrders(orderDetailList);

		RequestSpecification createOrderReq = given()
				.log().all()
				.spec(createOrderBaseReq)
				.body(orders);

		String responseAddOrder = createOrderReq.when()
				.post("/api/ecom/order/create-order")
				.then().log().all()
				.extract().response().asString();

		System.out.println(responseAddOrder);

		// Delete Product
		RequestSpecification deleteProdBaseReq = new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", token)
				.setContentType(ContentType.JSON)
				.build();

		RequestSpecification deleteProdReq = given()
				.log().all()
				.spec(deleteProdBaseReq)
				.pathParam("productId", productId);

		String deleteProductResponse = deleteProdReq.when()
				.delete("/api/ecom/product/delete-product/{productId}")
				.then().log().all()
				.extract().response().asString();

		JsonPath js1 = new JsonPath(deleteProductResponse);
		Assert.assertEquals("Product Deleted Successfully", js1.get("message"));
	}
}
