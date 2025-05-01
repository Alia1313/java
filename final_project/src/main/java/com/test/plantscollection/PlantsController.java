package com.test.plantscollection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class PlantsController implements Initializable {
    @FXML
    private TextField txtPlant;

    @FXML
    private TableView<Plant> tablePlants;

    @FXML
    private ComboBox<String> harmfulComboBox;

    @FXML
    private TableColumn<Plant, Integer> idCol;
    
    @FXML
    private TableColumn<Plant, String> nameCol;
    
    @FXML
    private TableColumn<Plant, String> harmfulCol;

    @FXML
    public void onDeleteButtonClick() throws IOException, SQLException {
        Plant plant = tablePlants.getSelectionModel().getSelectedItem();
        if (plant != null) {
            DBAdapter.deletePlant(plant.getId());
            DBAdapter.deletePlantProperties(plant.getId());
            updateTable();
        }
    }

    @FXML
    public void onCreateButtonClick() throws IOException, SQLException {
        String plantName = txtPlant.getText();
        if (!plantName.isEmpty() && harmfulComboBox.getValue() != null) {
            DBAdapter.insertPlant(plantName, harmfulComboBox.getValue());
            updateTable();
        }
    }

    @FXML
    public void onUpdateButtonClick() throws IOException, SQLException {
        Plant plant = tablePlants.getSelectionModel().getSelectedItem();
        if (plant != null && !txtPlant.getText().isEmpty() && harmfulComboBox.getValue() != null) {
            DBAdapter.updatePlant(plant.getId(), txtPlant.getText(), harmfulComboBox.getValue());
            updateTable();
        }
    }

    @FXML
    public void onPropertiesButtonClick() throws IOException {
        if (tablePlants.getSelectionModel().getSelectedItem() != null) {
            Stage newStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(PlantsCollection.class.getResource("properties-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 400);
            PropertiesController controller = fxmlLoader.getController();
            controller.setPlantId(tablePlants.getSelectionModel().getSelectedItem().getId());
            newStage.setTitle("Свойства " + tablePlants.getSelectionModel().getSelectedItem().getName());
            newStage.setScene(scene);
            newStage.show();
        }
    }

    public void updateTable() throws IOException, SQLException {
        ArrayList<Plant> data = DBAdapter.selectPlants();
        ObservableList<Plant> data_new = FXCollections.observableArrayList(data);
        tablePlants.setItems(data_new);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        harmfulCol.setCellValueFactory(new PropertyValueFactory<>("harmful"));

        harmfulComboBox.getItems().addAll("Да", "Нет");

        try {
            updateTable();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}