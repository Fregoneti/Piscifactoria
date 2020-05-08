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
public class Jugador {
    public int id;
    public String nombre;
    public double experiencia;
    public int nivel;
    float dinero;

    public Jugador() {
      this(-1, "", 0, 0,100);
    }

    public Jugador(int id, String nombre, double experiencia, int nivel, int dinero) {
        this.id = id;
        this.nombre = nombre;
        this.experiencia = experiencia;
        this.nivel = nivel;
        this.dinero = 100;
    }

    public Jugador(int id, String nombre ) {
        this.id=id;
        this.nombre = nombre;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(double experiencia) {
        this.experiencia = experiencia;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public float getDinero() {
        return dinero;
    }

    public void setDinero(float dinero) {
        this.dinero = dinero;
    }

    @Override
    public String toString() {
        return "Jugador{" + "nombre=" + nombre + ", experiencia=" + experiencia + ", nivel=" + nivel + ", dinero=" + dinero + '}';
    }

 

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jugador other = (Jugador) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
}
