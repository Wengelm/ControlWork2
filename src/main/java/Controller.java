

import com.sun.webkit.Timer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import static java.lang.Long.parseLong;


public class Controller implements Initializable {


    @FXML
    TableView<Rentabel> rentabelTableView;
    @FXML
    TableColumn<Rentabel, Integer> id;
    @FXML
    TableColumn<Rentabel, Integer> year;
    @FXML
    TableColumn<Rentabel, Long> proceeds;
    @FXML
    TableColumn<Rentabel, Long> proceedsBySells;
    @FXML
    TableColumn<Rentabel, Long> profit;
    @FXML
    TableColumn<Rentabel, Long> profitBySells;
    @FXML
    TableColumn<Rentabel, Long> capital;
    @FXML
    TableColumn<Rentabel, Long> clearprofit;

    @FXML
    TextField idTX;
    @FXML
    TextField yearTX;
    @FXML
    TextField proceedsTX;
    @FXML
    TextField proceedsBySellsTX;
    @FXML
    TextField profitTX;
    @FXML
    TextField profitBySellsTX;
    @FXML
    TextField capitalTX;
    @FXML
    TextField clearprofitTX;
    @FXML
    TextField rent1;
    @FXML
    TextField rent2;
    @FXML
    TextField rent3;


    @FXML
    private void insertButton() {
        String query = "insert into rentabel values('" + idTX.getText() + "','" + yearTX.getText() + "','" + proceedsTX.getText() + "','" + proceedsBySellsTX.getText() + "','" + profitTX.getText() + "','" + profitBySellsTX.getText() + "','" + capitalTX.getText() + "','" + clearprofitTX.getText() + "')";
        executeQuery(query);
        showRent();
    }


    @FXML
    private void updateButton() {
        String query = "UPDATE rentabel SET year='" + yearTX.getText() + "',proceeds='" + proceedsTX.getText() + "',proceedsBySells=" + proceedsBySellsTX.getText() + ",profit=" + profitTX.getText() + ",profitBySells=" + profitBySellsTX.getText() + ",capital=" + capitalTX.getText() + ",clearprofit=" + clearprofitTX.getText() + " WHERE ID=" + idTX.getText() + "";
        executeQuery(query);
        showRent();
    }

    @FXML
    private void deleteButton() {
        String query = "DELETE FROM rentabel WHERE ID=" + idTX.getText() + "";
        executeQuery(query);
        showRent();
    }

    public void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showRent();


    }


    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/rentabel?useUnicode=true&serverTimezone=UTC", "root", "LOL2013");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Rentabel> getRentList() {
        ObservableList<Rentabel> rentList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM rentabel ";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Rentabel r;
            while (rs.next()) {
                r = new Rentabel(rs.getInt("id"), rs.getInt("year"), rs.getInt("proceeds"), rs.getInt("proceedsBySells"), rs.getInt("profit"), rs.getInt("profitBySells"), rs.getInt("capital"), rs.getInt("clearprofit"));
                rentList.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rentList;
    }

    // I had to change ArrayList to ObservableList I didn't find another option to do this but this works :)
    public void showRent() {
        ObservableList<Rentabel> list = getRentList();

        id.setCellValueFactory(new PropertyValueFactory<Rentabel, Integer>("id"));
        year.setCellValueFactory(new PropertyValueFactory<Rentabel, Integer>("year"));
        proceeds.setCellValueFactory(new PropertyValueFactory<Rentabel, Long>("proceeds"));
        proceedsBySells.setCellValueFactory(new PropertyValueFactory<Rentabel, Long>("proceedsBySells"));
        profit.setCellValueFactory(new PropertyValueFactory<Rentabel, Long>("profit"));
        profitBySells.setCellValueFactory(new PropertyValueFactory<Rentabel, Long>("profitBySells"));
        capital.setCellValueFactory(new PropertyValueFactory<Rentabel, Long>("capital"));
        clearprofit.setCellValueFactory(new PropertyValueFactory<Rentabel, Long>("clearprofit"));

        rentabelTableView.setItems(list);
    }

    @FXML
    public void handle(MouseEvent event) {
        Rentabel rentabel = rentabelTableView.getSelectionModel().getSelectedItem();
        idTX.setText(String.valueOf(rentabel.getId()));
        yearTX.setText(String.valueOf(rentabel.getYear()));
        proceedsTX.setText(String.valueOf(rentabel.getProceeds()));
        proceedsBySellsTX.setText(String.valueOf(rentabel.getProceedsBySells()));
        profitTX.setText(String.valueOf(rentabel.getProfit()));
        profitBySellsTX.setText(String.valueOf(rentabel.getProfitBySells()));
        capitalTX.setText(String.valueOf(rentabel.getCapital()));
        clearprofitTX.setText(String.valueOf(rentabel.getClearprofit()));
    }

    @FXML
    public void calcRent() {

        float profit = Float.parseFloat(profitTX.getText());
        float proceeds = Float.parseFloat(proceedsTX.getText());
        float profitBySells = Float.parseFloat(profitBySellsTX.getText());
        float proceedsBySells = Float.parseFloat(proceedsBySellsTX.getText());
        float clearprofit = Float.parseFloat(clearprofitTX.getText());
        float capetal = Float.parseFloat(capitalTX.getText());

        float res1 = (profit / proceeds) * 100;
        float res2 = (profitBySells / proceedsBySells) * 100;
        float res3 = (clearprofit / capetal) * 100;


        rent1.setText(String.valueOf(res1));
        rent2.setText(String.valueOf(res2));
        rent3.setText(String.valueOf(res3));

    }

}