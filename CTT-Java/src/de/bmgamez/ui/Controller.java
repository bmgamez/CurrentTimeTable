package de.bmgamez.ui;

import de.bmgamez.backend.Reader;
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
    TableColumn<Stunde, String> tableColumnHour1;
    @FXML
    TableColumn<Stunde, String> tableColumnContent1;
    @FXML
    TableColumn<Stunde, String> tableColumnHour2;
    @FXML
    TableColumn<Stunde, String> TableColumnContent2;

    @FXML
    TableView<Stunde> tableView1;
    @FXML
    TableView<Stunde> tableView2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tableColumnHour1.setCellValueFactory(new PropertyValueFactory<Stunde, String>("stunde"));

        Task<List<Stunde>> task = new Task<List<Stunde>>() {
            @Override
            protected List<Stunde> call() throws Exception {
                return setDay();
            }

            @Override
            protected void succeeded() {
                tableView1.getItems().clear();
                tableView1.getItems().addAll(getValue());
            }
        };

        new Thread(task).start();

        tableColumnContent1.setCellValueFactory(new PropertyValueFactory<Stunde, String>("stunde"));

        Task<List<Stunde>> task1 = new Task<List<Stunde>>() {
            @Override
            protected List<Stunde> call() throws Exception {
                return setHours("mi");
            }

            @Override
            protected void succeeded() {
                tableView1.getItems().clear();
                tableView1.getItems().addAll(getValue());
            }
        };

        new Thread(task1).start();
    }

    private List<Stunde> setDay() {

        List<Stunde> StundeList = new ArrayList<>();

        StundeList.add(new Stunde("1"));
        StundeList.add(new Stunde("2"));
        StundeList.add(new Stunde("3"));
        StundeList.add(new Stunde("4"));
        StundeList.add(new Stunde("5"));
        StundeList.add(new Stunde("6"));
        StundeList.add(new Stunde("7"));
        StundeList.add(new Stunde("8"));
        StundeList.add(new Stunde("9"));
        StundeList.add(new Stunde("10"));
        StundeList.add(new Stunde("11"));

        return StundeList;
    }

    private List<Stunde> setHours(String day) {

        List<Stunde> HourList = new ArrayList<>();

        Reader reader = new Reader();

        for (int i = 1; i <= 11; i++) {
            HourList.add(new Stunde(reader.getPlan("resources/plan.csv", "mo", i)));
        }

        return HourList;
    }
}

