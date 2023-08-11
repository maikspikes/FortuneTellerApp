package org.example.infra.presentation;

import com.google.gson.Gson;
import org.example.application.FortuneTellerService;
import org.jetbrains.annotations.NotNull;
import spark.Request;
import spark.Response;
import spark.Spark;

public class FortuneController {
    private final FortuneTellerService fortuneTellerService;

    public FortuneController(FortuneTellerService fortuneTellerService) {
        this.fortuneTellerService = fortuneTellerService;
    }

    public void listen(int port) {
        Spark.port(port);

        Spark.get("/fortune", this::getRandomFortune);
    }

    @NotNull
    private String getRandomFortune(Request request, Response response) {
        String fortune = this.fortuneTellerService.tellFortune();

        FortuneResponse fortuneResponse = new FortuneResponse(fortune);
        response.type("application/json");

        return new Gson().toJson(fortuneResponse);
    }
}
