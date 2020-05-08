package Model;

public class Pez implements IPez {

    int id;
    String crecimiento;
    String especie;

    public Pez(int id, String crecimiento, String especie) {
        this.id = id;
        this.crecimiento = crecimiento;
        this.especie = especie;
    }

    public Pez() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCrecimiento() {
        return crecimiento;
    }

    public void setCrecimiento(String crecimiento) {
        this.crecimiento = crecimiento;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    @Override
    public int getID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
   

 

   

}
