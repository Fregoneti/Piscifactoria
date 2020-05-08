/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Carlos
 */
public interface IPez {
    String getCrecimiento();
    String getEspecie();
    int getID();
    void setCrecimiento(String crecimiento);
    void setEspecie(String especie);
    void setID(int id);
}
