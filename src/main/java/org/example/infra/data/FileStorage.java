package org.example.infra.data;

import org.example.domain.Fortune;
import org.example.domain.FortuneStorage;

public class FileStorage implements FortuneStorage {
    @Override
    public int id() {
        return 0;
    }

    @Override
    public String text() {
        return null;
    }

    @Override
    public Fortune getRandomFortune() {
        return null;
    }
}
