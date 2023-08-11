package org.example.application;

import org.example.domain.Fortune;
import org.example.domain.FortuneStorage;

public class FortuneTellerService {
    private final FortuneStorage fortuneStorage;

    public FortuneTellerService(FortuneStorage fortuneStorage) {
        this.fortuneStorage = fortuneStorage;
    }

    public String tellFortune() {
        Fortune fortune = this.fortuneStorage.getRandomFortune();

        return fortune.getText();
    }
}
