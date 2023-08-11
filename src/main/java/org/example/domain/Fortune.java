package org.example.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Fortune {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(canBeNull = false)
    private String text;


    public Fortune() {
        // all persisted classes must define a no-arg constructor with at least package visibility
    }
    public Fortune(int id, String text) {
        this.id = id;
        this.text =text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
