package de.bmgamez.ui;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    TableColumn<Class, String> tableColumn1;

    @FXML
    TableView<Class> tableView1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableColumn1.setCellValueFactory(new PropertyValueFactory<Class, String>("klasse"));

        refresh();
    }

    @FXML
    public void refresh() {

        Task<List<Class>> task = new Task<List<Class>>() {
            @Override
            protected List<Class> call() throws Exception {
                return fetchData();
            }

            @Override
            protected void succeeded() {
                tableView1.getItems().clear();
                tableView1.getItems().addAll(getValue());
            }
        };

        new Thread(task).start();
    }

    private List<Class> fetchData() {

        List<Class> ClassList = new ArrayList<>();

        ClassList.add(new Class("1"));
        ClassList.add(new Class("2"));
        ClassList.add(new Class("3"));

        return ClassList;
    }

    private int counter = 1;
}

