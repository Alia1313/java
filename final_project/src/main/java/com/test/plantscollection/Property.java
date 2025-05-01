package com.test.plantscollection;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Property {
    private SimpleIntegerProperty id;
    private SimpleStringProperty text;

    public Property(int propertyId, String text) {
        this.id = new SimpleIntegerProperty(propertyId);
        this.text = new SimpleStringProperty(text);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int propertyId) {
        this.id.set(propertyId);
    }

    public String getText() {
        return text.get();
    }

    public void setText(String text) {
        this.text.set(text);
    }
}
