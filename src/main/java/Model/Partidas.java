/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDate;

/**
 *
 * @author Carlos
 */
public class Partidas implements IPartidas{
    LocalDate tiempo_jugado;

    public LocalDate getTiempo_jugado() {
        return tiempo_jugado;
    }

    public void setTiempo_jugado(LocalDate tiempo_jugado) {
        this.tiempo_jugado = tiempo_jugado;
    }

    
    
}
