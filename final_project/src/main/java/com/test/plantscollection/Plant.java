package com.test.plantscollection;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Plant {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty harmful;

    public Plant(int id, String name, String harmful) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.harmful = new SimpleStringProperty(harmful);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int idin) {
        id.set(idin);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String isHarmful() {
        return harmful.get();
    }

    public void setHarmful(String harmful) {
        this.harmful.set(harmful);
    }

}
