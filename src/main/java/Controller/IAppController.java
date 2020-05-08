/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Jugador;
import java.util.Map;

/**
 *
 * @author Carlos
 */
public interface IAppController {
    boolean createUser();
    boolean editUser();
    boolean deleteUser();   
    Map<Jugador,Integer> listAllUser(); 
}
