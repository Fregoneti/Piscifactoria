/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Controller.AppController;
import Model.Piscisfactorias;
import Model.Piscisfactorias;
import Utils.ConnectionUtil;
import Utils.Dialog;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class piscisfactoriaDao extends Piscisfactorias implements Dao{

    @Override
    public void persist() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void detach() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
     enum queries {
        INSERT("INSERT INTO pisciscfactoria (int id) VALUES (0)"),
        ALL("SELECT * FROM piscisfactoria"),
        GETBYID("SELECT * FROM piscisfactoria WHERE id=?"),
        FINDBYID("SELECT * FROM piscisfactoria WHERE id IN "), //<-- ojo con esta, hay formas más elegantes
        FINDBYNAME("SELECT * FROM piscisfactoria WHERE name LIKE ?"),
        UPDATE("UPDATE piscisfactoria SET nombre = ? WHERE id = ?"),
        REMOVE("DELETE FROM piscisfactoria WHERE id=?");
        private String q;

        queries(String q) {
            this.q = q;
        }

        public String getQ() {
            return this.q;
        }
    }

    Connection con;
    private boolean persist;


    public piscisfactoriaDao() {
        super();
        try {
            con = ConnectionUtil.connect((Model.Connection) AppController.currentConnection);
        } catch (SQLException ex) {
            Logger.getLogger(jugadorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        persist = false;
    }


    public piscisfactoriaDao(int i) {
        this();
        List<Object> params = new ArrayList<>();
        params.add(i);
        try {
            ResultSet rs = ConnectionUtil.execQuery(con, queries.GETBYID.getQ(), params);

            if (rs != null) {

                while (rs.next()) {
                    Piscisfactorias p = instanceBuilder(rs);
                    this.id = p.getId();
                    
                }

            }
        } catch (SQLException ex) {
            Dialog.showError("ERRPR", "Error cargando la Piscisfactoria", ex.toString());
        }
    }


  

    @Override
    public void setId(int id) {
        /*super.setId(id); 
        if(persist){
            save();
        }*/
        //primary key cannot be changed
    }

  

    // UTILS for JUGADOR DAO
    public static Piscisfactorias instanceBuilder(ResultSet rs) {
        //ojo rs.getMetaData()
        Piscisfactorias p = new Piscisfactorias();
        if (rs != null) {
            try {
                p.setId(rs.getInt("id"));

              
            } catch (SQLException ex) {
                Dialog.showError("Error SQL", "SQL creando jugador", ex.toString());
            }

        }
        return p;
    }

    public static List<Piscisfactorias> getAll(Connection con) {
        List<Piscisfactorias> result = new ArrayList<>();
        try {
            ResultSet rs = ConnectionUtil.execQuery(con, queries.ALL.getQ(), null);
            if (rs != null) {
                while (rs.next()) {
                    Piscisfactorias p = piscisfactoriaDao.instanceBuilder(rs);
                    result.add(p);
                }
            }
        } catch (SQLException ex) {
            Dialog.showError("ERRPR", "Error cargando el jugador", ex.toString());
        }
        return result;
    }

    

    public static List<Piscisfactorias> getById(Connection con, List<Integer> ids) {
        List<Piscisfactorias> result = new ArrayList<>();
        try {
            List<String> newList = new ArrayList<String>(ids.size());
            for (Integer myInt : ids) {
                newList.add(String.valueOf(myInt));
            }
            String queryTotal = queries.FINDBYID.getQ() + "(" + String.join(",", newList) + ");";

            ResultSet rs = ConnectionUtil.execQuery(con, queryTotal, null);
            if (rs != null) {
                while (rs.next()) {
                    Piscisfactorias p = piscisfactoriaDao.instanceBuilder(rs);
                    result.add(p);
                }
            }
        } catch (SQLException ex) {
            Dialog.showError("ERRPR", "Error cargando el jugador", ex.toString());
        }
        return result;
    }
    
}
