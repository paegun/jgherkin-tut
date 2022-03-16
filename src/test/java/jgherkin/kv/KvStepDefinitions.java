package jgherkin.kv;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KvStepDefinitions {
    private final HashMap<String, Object> kvStore = new HashMap<>();
    private String uniqueId;
    private Object storedValue;
    private Object retrievedValue;
    private HashMap<String, String> keyValues;
    private Set<String> keyListing;

    @Given("stored uniqueValue in the kvstore at uniqueId")
    public void given_stored_uniqueValue_at_uniqueKey() {
        if (uniqueId == null) uniqueId = UUID.randomUUID().toString();
        storedValue = UUID.randomUUID().toString();
        kvStore.put(uniqueId, storedValue);
    }

    @Given("deleted from the kvstore at uniqueId")
    public void given_deleted_at_uniqueKey() {
        if (uniqueId == null) uniqueId = UUID.randomUUID().toString();
        kvStore.remove(uniqueId);
    }

    @Given("stored {stringDictionaryValue} in the kvstore")
    public void given_stored_stringDictionaryValue(HashMap<String, String> keyValues) {
         for(Map.Entry<String, String> kv: keyValues.entrySet()) {
             kvStore.put(kv.getKey(), kv.getValue());
         }
         this.keyValues = keyValues;
    }

    @When("getting {stringValue} from the kvstore")
    public void when_requested_uniqueValue_at_uniqueKey(String key) {
        key = keyMaybeUnique(key);
        retrievedValue = kvStore.get(key);
    }

    @When("keyListing from the kvstore")
    public void when_requested_keyListing() {
        keyListing = kvStore.keySet();
        //keyListing = Set.<String>of( "q", "w", "e", "r", "t", "y" );
    }

    @Then("the value should be {stringValue}")
    public void then_the_value_at_key_should_equal_expected(Object expectedValue) {
        expectedValue = valueMaybeUnique(expectedValue);
        assertEquals(expectedValue, retrievedValue);
    }

    @Then("the keys {stringListValue} should be present in the keyListing")
    public void then_the_keys_should_be_present_in_the_keyListing(List<String> keys) {
        for(String expectedKey: keys) {
            assertTrue(keyListing.contains(expectedKey),
                    String.format("%s expected w/i %s", expectedKey, keyListing));
        }
    }

    private String keyMaybeUnique(String key) {
        if (key == null) return key;
        if (key.equals("uniqueId")) key = uniqueId;
        return key;
    }

    private Object valueMaybeUnique(Object value) {
        if (value == null) return value;
        if (value.equals("uniqueValue")) value = storedValue;
        return value;
    }
}
