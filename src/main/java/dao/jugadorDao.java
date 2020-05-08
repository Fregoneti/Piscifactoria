/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Controller.AppController;
import Utils.ConnectionUtil;
import Model.Jugador;
import Utils.Dialog;
import java.nio.channels.Channel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class jugadorDao extends Jugador implements Dao {

    @Override
    public void save() {
        queries q;
        List<Object> params = new ArrayList<>();
        params.add(this.getNombre());

        if (this.id == -1) {
            q = queries.INSERT;
        } else {
            q = queries.UPDATE;
            params.add(this.id);
        }

        try {
            //Comienza transacción
            con.setAutoCommit(false);

            int rs = ConnectionUtil.execUpdate(con, q.getQ(), params, (q == queries.INSERT ? true : false));
            if (q == jugadorDao.queries.INSERT) {
                this.id = rs;
            }

            //Fin de la transacción
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException ex) {
            Dialog.showError("ERROR", "Error guardando jugador", ex.toString());
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    enum queries {
        INSERT("INSERT INTO jugador (id,nombre,experiencia, nivel, dinero) VALUES (NULL,?,0,0,100)"),
        ALL("SELECT * FROM jugador"),
        GETBYID("SELECT * FROM jugador WHERE id=?"),
        FINDBYID("SELECT * FROM jugador WHERE id IN "), //<-- ojo con esta, hay formas más elegantes
        FINDBYNAME("SELECT * FROM jugador WHERE name LIKE ?"),
        UPDATE("UPDATE jugador SET nombre = ? WHERE id = ?"),
        REMOVE("DELETE FROM jugador WHERE id=?");
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

    public jugadorDao(int id, String nombre) {
        super(id, nombre);
        try {
            con = ConnectionUtil.connect((Model.Connection) AppController.currentConnection);
        } catch (SQLException ex) {
            Logger.getLogger(jugadorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        persist = false;
    }

    public jugadorDao() {
        super();
        try {
            con = ConnectionUtil.connect((Model.Connection) AppController.currentConnection);
        } catch (SQLException ex) {
            Logger.getLogger(jugadorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        persist = false;
    }

    //DAO
    public jugadorDao(Jugador j) {
        this(j.getId(), j.getNombre());
    }

    public jugadorDao(int i) {
        this();
        List<Object> params = new ArrayList<>();
        params.add(i);
        try {
            ResultSet rs = ConnectionUtil.execQuery(con, queries.GETBYID.getQ(), params);

            if (rs != null) {

                while (rs.next()) {
                    Jugador c = instanceBuilder(rs);
                    this.id = c.getId();
                    this.nombre = c.getNombre();

                }

            }
        } catch (SQLException ex) {
            Dialog.showError("ERRPR", "Error cargando el contacto", ex.toString());
        }
    }

    public void persist() {
        this.persist = true;
    }

    public void detach() {
        this.persist = false;
    }

    @Override
    public void setNombre(String nombre) {
        super.setNombre(nombre);
        if (persist) {
            save();
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
    public static Jugador instanceBuilder(ResultSet rs) {
        //ojo rs.getMetaData()
        Jugador c = new Jugador();
        if (rs != null) {
            try {
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));

            } catch (SQLException ex) {
                Dialog.showError("Error SQL", "SQL creando jugador", ex.toString());
            }

        }
        return c;
    }

    /**
     * public static List<Jugador> selectAll(String pattern) { List<Jugador>
     * result = new ArrayList<>();
     *
     * try { java.sql.Connection conn = ConnectionUtil.getConnection(); String q
     * = "SELECT * FROM item";
     *
     * if (pattern.length() > 0) { q += " WHERE title LIKE ?"; }
     * PreparedStatement ps = conn.prepareStatement(q);
     *
     * if (pattern.length() > 0) { ps.setString(1, pattern + "%"); }
     *
     * ResultSet rs = ps.executeQuery(); if (rs != null) { while (rs.next()) {
     * Jugador j = new Jugador(); j.setId(rs.getInt("id"));
     * j.setNombre(rs.getString("nombre"));
     *
     * result.add(j); } } } catch (SQLException ex) { System.out.println(ex);
     * Logger.getLogger(jugadorDao.class.getName()).log(Level.SEVERE, null, ex);
     * }
     *
     * return result; }
     *
     * public static List<Jugador> selectAll(){ return selectAll(""); }
     *
     *
     */
    public static List<Jugador> getAll(Connection con) {
        List<Jugador> result = new ArrayList<>();
        try {
            ResultSet rs = ConnectionUtil.execQuery(con, queries.ALL.getQ(), null);
            if (rs != null) {
                while (rs.next()) {
                    Jugador n = jugadorDao.instanceBuilder(rs);
                    result.add(n);
                }
            }
        } catch (SQLException ex) {
            Dialog.showError("ERRPR", "Error cargando el jugador", ex.toString());
        }
        return result;
    }

    public static List<Jugador> getByName(Connection con, String name) {
        List<Jugador> result = new ArrayList<>();
        try {
            ResultSet rs = ConnectionUtil.execQuery(con, queries.FINDBYNAME.getQ(), name + "%");
            if (rs != null) {
                while (rs.next()) {
                    Jugador n = jugadorDao.instanceBuilder(rs);
                    result.add(n);
                }
            }
        } catch (SQLException ex) {
            Dialog.showError("ERRPR", "Error cargando el jugador", ex.toString());
        }
        return result;
    }

    public static List<Jugador> getById(Connection con, List<Integer> ids) {
        List<Jugador> result = new ArrayList<>();
        try {
            List<String> newList = new ArrayList<String>(ids.size());
            for (Integer myInt : ids) {
                newList.add(String.valueOf(myInt));
            }
            String queryTotal = queries.FINDBYID.getQ() + "(" + String.join(",", newList) + ");";

            ResultSet rs = ConnectionUtil.execQuery(con, queryTotal, null);
            if (rs != null) {
                while (rs.next()) {
                    Jugador n = jugadorDao.instanceBuilder(rs);
                    result.add(n);
                }
            }
        } catch (SQLException ex) {
            Dialog.showError("ERRPR", "Error cargando el jugador", ex.toString());
        }
        return result;
    }

}
