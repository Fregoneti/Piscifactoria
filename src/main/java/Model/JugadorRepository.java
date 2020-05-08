/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Carlos
 */
public class JugadorRepository {

    private static JugadorRepository instance = null;
    public Set<Jugador> jugador;

    private JugadorRepository() {
        jugador = new TreeSet<>();
    }

    public static JugadorRepository getInstance() {
        if (instance == null) {
            instance = new JugadorRepository();
        }
        return instance;
    }
}
