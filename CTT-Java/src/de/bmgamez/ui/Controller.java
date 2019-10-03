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
    TableColumn<Class, String> tableColumnHour1;
    @FXML
    TableColumn<Class, String> tableColumnContent1;
    @FXML
    TableColumn<Class, String> tableColumnHour2;
    @FXML
    TableColumn<Class, String> TableColumnContent2;

    @FXML
    TableView<Class> tableView1;
    @FXML
    TableView<Class> tableView2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableColumnHour1.setCellValueFactory(new PropertyValueFactory<Class, String>("klasse"));

        Task<List<Class>> task = new Task<List<Class>>() {
            @Override
            protected List<Class> call() throws Exception {
                return setDay();
            }

            @Override
            protected void succeeded() {
                tableView1.getItems().clear();
                tableView1.getItems().addAll(getValue());
            }
        };

        new Thread(task).start();
    }

    private List<Class> setDay() {

        List<Class> ClassList = new ArrayList<>();

        ClassList.add(new Class("1"));
        ClassList.add(new Class("2"));
        ClassList.add(new Class("3"));
        ClassList.add(new Class("4"));
        ClassList.add(new Class("5"));
        ClassList.add(new Class("6"));
        ClassList.add(new Class("7"));
        ClassList.add(new Class("8"));
        ClassList.add(new Class("9"));
        ClassList.add(new Class("10"));
        ClassList.add(new Class("11"));

        return ClassList;
    }

    private int counter = 1;
}

