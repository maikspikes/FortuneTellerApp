package org.example.domain;

import com.j256.ormlite.field.DatabaseField;

public class Fortune {
    @DatabaseField(id = true)
    private String fortuneName;
    @DatabaseField(canBeNull = false)
    private String password;


    Fortune() {
        // all persisted classes must define a no-arg constructor with at least package visibility
    }
}
