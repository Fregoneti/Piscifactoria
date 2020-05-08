package Controller;

import Controller.Scenes;
import com.mycompany.maven.App;
import Model.Jugador;
import dao.jugadorDao;
import Utils.ConnectionUtil;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrimaryController extends Controllers implements Initializable {

    private java.sql.Connection con;

    @FXML
    private Button enter;
    @FXML
    private Button crearJugador;

    @FXML
    private TextField searchPattern;

    /**
     *
     * /**@Override public void initialize(URL url, ResourceBundle rb) {
     * this.contacts = FXCollections.observableArrayList();
     *
     * //loading this.con =
     * ConnectionUtil.connect(AppController.currentConnection); if (con != null)
     * { List<Contact> c = ContactDao.getAll(con); this.contacts.addAll(c); }
     *
     * nameCol.setCellValueFactory(cellData -> { return new
     * SimpleObjectProperty<>(cellData.getValue().getNickname()); });
     * nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
     * nameCol.setOnEditCommit( new
     * EventHandler<TableColumn.CellEditEvent<Contact, String>>() { @Override
     *
     * p
     *
     *
     *
     * ublic void handle(TableColumn.CellEditEvent<Contact, String> t) {
     *
     * Contact selected = (Contact) t.getTableView().getItems().get(
     * t.getTablePosition().getRow()); selected.setNickname(t.getNewValue());
     * ContactDao cc = new ContactDao(selected); cc.save(); } } );
     *
     * birthCol.setCellValueFactory(cellData -> { return new
     * SimpleObjectProperty<>(cellData.getValue().getBirthDate().toString());
     * }); birthCol.setCellFactory(TextFieldTableCell.forTableColumn());
     * birthCol.setOnEditCommit((TableColumn.CellEditEvent<Contact, String> t)
     * -> { Contact selected = (Contact) t.getTableView().getItems().get(
     * t.getTablePosition().getRow());
     * selected.setBirthDate(LocalDate.parse(t.getNewValue())); //habría que
     * validar ContactDao cc = new ContactDao(selected); cc.save(); });
     *
     * contactTable.setEditable(true);
     *
     * contactTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
     *
     * contactTable.getSelectionModel().selectedItemProperty().addListener(
     * (observable, oldValue, newValue) -> showChannels(newValue) ); //Add
     * observable contactTable.setItems(contacts);
     *
     * this.channels = FXCollections.observableArrayList();
     *
     * typeCol.setCellValueFactory(cellData -> { return new
     * SimpleObjectProperty<>(cellData.getValue().getType()); });
     * typeCol.setCellFactory(TextFieldTableCell.forTableColumn());
     * typeCol.setOnEditCommit((TableColumn.CellEditEvent<Channel, String> t) ->
     * { Channel selected = (Channel) t.getTableView().getItems().get(
     * t.getTablePosition().getRow()); selected.setType(t.getNewValue());
     * ChannelDao cc = new ChannelDao(selected); cc.save(); });
     * valueCol.setCellValueFactory(cellData -> { return new
     * SimpleObjectProperty<>(cellData.getValue().getValue()); });
     * valueCol.setCellFactory(TextFieldTableCell.forTableColumn());
     * valueCol.setOnEditCommit((TableColumn.CellEditEvent<Channel, String> t)
     * -> { Channel selected = (Channel) t.getTableView().getItems().get(
     * t.getTablePosition().getRow()); selected.setValue(t.getNewValue());
     * //habría que validar ChannelDao cc = new ChannelDao(selected); cc.save();
     * });
     *
     * channelTable.setEditable(true);
     *
     * channelTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
     *
     * channelTable.setItems(channels); showChannels(null);
     *
     * searchPattern.setOnAction(new EventHandler<ActionEvent>() {
     * @Override public void handle(ActionEvent t) { search();
     * //<--searchPattern.getText() }
     *
     * });
     *
     * }
     *
     *
     * @Override void onLoad() { this.app.controller.title("CRUD - JAVAFX");
     * this.app.controller.enableCon(); }
     *
     */
    @FXML
    public void searchJugador() {
        String pattern = searchPattern.getText();
        pattern = pattern.trim();
        List<Jugador> newJ;
        if (pattern.equals("")) {
            newJ = jugadorDao.getAll(con);
        } else {
            newJ = jugadorDao.getByName(con, pattern);
        }

    }

    @FXML
    public void goCrearJugador() throws IOException {
        App.setRoot("Crear_perfil");

    }
    
    @FXML
    public void goPerfilesCreados() throws IOException {
        App.setRoot("perfilesCreados");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
