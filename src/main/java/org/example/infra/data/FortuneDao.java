package org.example.infra.data;

import org.example.domain.Fortune;
import org.example.domain.FortuneStorage;

public class FortuneDao implements FortuneStorage {
    // return new Fortune(id, text);

    private Fortune fortune;
    FortuneDao(Fortune fortune){
        this.fortune = fortune;
    }

    @Override
    public int id() {
        return fortune.getId();
    }

    @Override
    public String text() {
        return fortune.getText();
    }

    @Override
    public Fortune getRandomFortune() {
        // Implement logic to retrieve a random fortune

        return fortune;
    }

}
