package Controller;

import com.mycompany.maven.App;
import Model.Connection;
import dao.jugadorDao;
import Model.Jugador;
import Utils.ConnectionUtil;
import Utils.Dialog;
import Utils.MapEntry;
import Utils.PreferencesUtil;
import Utils.XMLUtils;
import dao.jugadorDao;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;

public class PerfilesCreadosController extends Controllers implements Initializable {

    //OBSERVABLE <--------
    
    public ObservableList<Jugador> data;

    @FXML
    private TableView<Jugador> table;
    
    @FXML
    private TableColumn<Jugador, String> n_jugador;
    @FXML
    public Label L_tiempoJugado;

    private java.sql.Connection con;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.data = FXCollections.observableArrayList();

        //se ejecuta automaticamente cuando un controlador (es decir, una escena) es cargada
        List<Jugador> misJugadores = jugadorDao.getAll(con);
        data.addAll(misJugadores);

        this.n_jugador.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getNombre());

        });
        table.setItems(data);
        
        
        //editables
        
        n_jugador.setCellFactory(TextFieldTableCell.forTableColumn());
        n_jugador.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Jugador, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Jugador, String> t) {

                Jugador selected = (Jugador) t.getTableView().getItems().get(
                        t.getTablePosition().getRow());

                selected.setNombre(t.getNewValue());  //<<- update lista en vista

                jugadorDao dao = new jugadorDao(selected); //update en mysql
                dao.save();
            }
        }
        );
    }

    @FXML

    public void deleteJugador() {
        Jugador selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if(!showConfirm(selected.getNombre())){
                return;
            }
            data.remove(selected);
        } else {
            showWarning("¡Cuidado!", "Ningun jugador a borrar", "Seleccione un jugador antes de borrar");
        }
    }

    public void showWarning(String tittle, String header, String description) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(tittle);
        alert.setHeaderText(header);
        alert.setContentText(header);
        alert.showAndWait();
    }
    
    public boolean showConfirm(String title){
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setHeaderText("Apunto de eliminar");
        alert.setContentText("A punto de borrar"+n_jugador);
        
        Optional<ButtonType> result=alert.showAndWait();
        if(result.get()==ButtonType.OK){
            return true;
        }else{
            return false;
        }
    }

    @FXML
    public void back() throws IOException {
        App.setRoot("primary");
    }

    /**
     * *
     * @Override public void initialize(URL url, ResourceBundle rb) {
     * this.jugadores = FXCollections.observableArrayList();
     *
     * try { //loading this.con =
     * ConnectionUtil.connect(AppController.currentConnection); } catch
     * (SQLException ex) {
     * Logger.getLogger(PerfilesCreadosController.class.getName()).log(Level.SEVERE,
     * null, ex); } if (con != null) { List<Jugador> c = jugadorDao.getAll(con);
     * this.jugadores.addAll(c); }
     *
     * nombreCol.setCellValueFactory(cellData -> { return new
     * SimpleObjectProperty<>(cellData.getValue().getNombre()); });
     * nombreCol.setCellFactory(TextFieldTableCell.forTableColumn());
     * nombreCol.setOnEditCommit( new
     * EventHandler<TableColumn.CellEditEvent<Jugador, String>>() { @Override
     * public v
     * oid handle(TableColumn.CellEditEvent<Jugador, String> t) {
     *
     * Jugador selected = (Jugador) t.getTableView().getItems().get(
     * t.getTablePosition().getRow()); selected.setNombre(t.getNewValue());
     * jugadorDao cc = new jugadorDao(selected); cc.save(); } } );
     *
     *
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
     * searchPattern.setOnAction(new EventHandler<ActionEvent>(){
     * @Override public void handle(ActionEvent t) { search();
     * //<--searchPattern.getText() }
     *
     * });
     *
     * }
     *
     * private void showChannels(Contact c) { channels.clear(); if (c != null) {
     * List<Channel> lc = ChannelDao.getByContact(con, c.getId());
     * channels.addAll(lc); menuChannel.setDisable(false);
     * deleteContact.setDisable(false); }else{ menuChannel.setDisable(true);
     * deleteContact.setDisable(true); } }
     *
     * @Override void onLoad() { this.app.controller.title("CRUD - JAVAFX");
     * this.app.controller.enableCon(); }
     *
     * @FXML public void addContact() { Contact nuevo=new Contact(); ContactDao
     * nuevoDao=new ContactDao(nuevo); nuevoDao.save();
     * nuevo.setId(nuevoDao.getId()); contacts.add(nuevo); }
     *
     * @FXML public void removeContact() { Contact selected =
     * contactTable.getSelectionModel().getSelectedItem(); if (selected != null)
     * { contacts.remove(selected); ContactDao cc = new ContactDao(selected);
     * cc.remove(); } }
     *
     *
     *
     * @FXML public void removeChannel() { Jugador selected =
     * juga.getSelectionModel().getSelectedItem(); if (selected != null) {
     * Jugador selectedC = channelTable.getSelectionModel().getSelectedItem();
     * if (selectedC != null) { channels.remove(selectedC); (new
     * jugadorDao(selectedC)).remove(); } } }
     *
     * @FXML public void search(){ String pattern=searchPattern.getText();
     * pattern=pattern.trim(); contacts.clear(); List<Contact> newC;
     * if(pattern.equals("")){ newC=ContactDao.getAll(con); }else{
     * newC=ContactDao.getByName(con, pattern); }
     *
     * //Extra funcion -> buscar también por contacto
     *
     * List<Contact> newC2=new ArrayList<>(); if(!pattern.equals("")){
     * List<Channel> lc=ChannelDao.getByValue(con, pattern); Set<Integer>
     * listId=new HashSet<>(); lc.stream().forEach(c->{
     * listId.add(c.getId_contact()); }); if(listId.size()>0){
     * newC2=ContactDao.getById(con, new ArrayList(listId)); } }
     * contacts.addAll(newC); for(Contact c:newC2){ if(!contacts.contains(c)){
     * contacts.add(c); } } } *
     */
}
