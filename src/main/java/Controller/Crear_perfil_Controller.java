/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Jugador;
import javafx.fxml.FXML;
import dao.jugadorDao;
import Model.JugadorRepository;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

/**
 *
 * @author Carlos
 */

public class Crear_perfil_Controller extends Controllers implements Initializable {
    
    JugadorRepository j= JugadorRepository.getInstance();
    
 
    
    
    
    
    
    @FXML
    private TableView<Jugador> jugadorTable;
    
    @FXML
    public void addJugador() {
        Jugador nuevo=new Jugador();
        jugadorDao nuevoDao=new jugadorDao(nuevo);
        nuevoDao.save();
        nuevo.setId(nuevoDao.getId());
        j.jugador.add(nuevo);
        
    }

    @FXML
    public void removeJugador() {
        Jugador selected = jugadorTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            j.jugador.remove(selected);
            jugadorDao cc = new jugadorDao(selected);
            cc.remove();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }

    
}
