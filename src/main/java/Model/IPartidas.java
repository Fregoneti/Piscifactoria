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
public interface IPartidas {
    LocalDate getTiempo_jugado();
    void setTiempo_jugado(LocalDate tiempo_jugado);
}
