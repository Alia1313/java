package com.test.plantscollection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Window;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class PropertiesController implements Initializable {
    private int plantId;

    @FXML 
    private TableView<Property> tableProperty;

    @FXML
    private TextField txtProperty;

    @FXML
    private TableColumn<Property, String> textCol;

    public void setPlantId(int plantId) {
        this.plantId = plantId;
        try {
            updateTable();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onAddButtonClick() throws IOException, SQLException {
        String property = txtProperty.getText();
        if (!property.isEmpty()) {
            DBAdapter.insertProperty(plantId, property);
            updateTable();
        }
    }

    @FXML
    public void onDeleteButtonClick() throws IOException, SQLException {
        Property property = tableProperty.getSelectionModel().getSelectedItem();
        if (property != null) {
            DBAdapter.deleteProperty(property.getId());
            updateTable();
        }
    }

    public void updateTable() throws IOException, SQLException {
        ArrayList<Property> data = DBAdapter.selectProperties(plantId);
        ObservableList<Property> data_new = FXCollections.observableArrayList(data);
        tableProperty.setItems(data_new);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textCol.setCellValueFactory(new PropertyValueFactory<>("text"));

        try {
            updateTable();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
