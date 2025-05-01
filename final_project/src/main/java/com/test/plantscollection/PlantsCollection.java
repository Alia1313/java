package com.test.plantscollection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class PlantsCollection extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DBAdapter.init();
        FXMLLoader fxmlLoader = new FXMLLoader(PlantsCollection.class.getResource("plants-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 500);
        stage.setTitle("Коллекция растений");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}