package org.example.api.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import org.example.api.config.ConfigManager;
import org.example.api.support.ApiContext;
import org.example.api.support.ScenarioState;
import org.example.api.support.DataStore;

public class Hooks {

    private final ApiContext apiContext;
    private final ScenarioState scenarioState;
    private final DataStore dataStore;

    // ✅ PicoContainer will inject these
    public Hooks(ApiContext apiContext,
                 ScenarioState scenarioState,
                 DataStore dataStore) {
        this.apiContext = apiContext;
        this.scenarioState = scenarioState;
        this.dataStore = dataStore;
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        String baseUrl = ConfigManager.INSTANCE.get("base.url");
        if (baseUrl == null || baseUrl.isEmpty()) {
            throw new RuntimeException(
                    "base.url is missing in config properties"
            );
        }

        RestAssured.baseURI = baseUrl;

        System.out.println("Starting Scenario: " + scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {

        if (scenario.isFailed() && apiContext.getResponse() != null) {
            scenario.log("Scenario failed. Response:");
            scenario.log(apiContext.getResponse().asPrettyString());
        }

        apiContext.reset();
        scenarioState.reset();
        dataStore.clear();

        System.out.println("✅ Finished Scenario: " + scenario.getName());
    }
}
