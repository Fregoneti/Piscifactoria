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
public class Piscisfactorias {

    public Pez[] peces;

    public int id;

    public Piscisfactorias() {
        this(new Pez[10], -1);
    }

    public Piscisfactorias(Pez[] capacidad, int id) {
        this.peces = capacidad;
        this.id = id;
    }

    public Pez[] getPeces() {
        return peces;
    }

    public void setPeces(Pez[] peces) {
        this.peces = peces;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Piscisfactorias{" + "peces=" + peces + ", id=" + id + '}';
    }


}
