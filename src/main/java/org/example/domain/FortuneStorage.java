package org.example.domain;

public interface FortuneStorage {
    int id();
    String text ();

    Fortune getRandomFortune();
}
