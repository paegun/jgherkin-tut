package jgherkin.date;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DayStepDefinitions {
    private String today;
    private Boolean today_is_weekend;

    @Given("today is {stringValue}")
    public void given_today_is(String dayName) {
        today = dayName;
    }

    @When("asked if today is a weekend")
    public void when_asked_if_today_is_a_weekend() {
        today_is_weekend = (today.equals("Sunday") ||
                today.equals("Saturday"));
    }

    @Then("the answer should be {booleanValue}")
    public void then_the_answer_should_be_boolean(Boolean isAnswer) {
        assertEquals(isAnswer, today_is_weekend);
    }
}