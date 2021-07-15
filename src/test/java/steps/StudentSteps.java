package steps;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentSteps {


    private Response response;



    @When("I check the details of student {int}")
    public void check_student_id(Integer studentId) {
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.baseURI = "https://it-foundations.app.ap.assurity.cloud/";
        response = RestAssured.get("people/" + studentId);
    }

    @Then("I can see that their name is {string}")
    public void check_name(String string) {
        RestAssured.defaultParser = Parser.JSON;
        System.out.println(response.asPrettyString());
        String firstName = response.path("firstName");
        String lastName = response.path("lastName");
        String fullName = firstName + " " + lastName;
        assertEquals(string, fullName);
    }

    @And("they have a {string} from {string}")
    public void check_degree_uni(String degree, String uni){
        RestAssured.defaultParser = Parser.JSON;
        String getDegree = response.path("degree");
        String getUni = response.path("university");
        assertEquals(degree, getDegree);
        assertEquals(uni, getUni);
    }

}
