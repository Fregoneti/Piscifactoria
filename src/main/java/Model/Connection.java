package Model;

import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Connection{
    public static ConnectionsType connectionTypes;
    
    private StringProperty name;
    private String type;
    private String server;
    private String userName;
    private String password;
    
    public Connection(String name){
        this.name=new SimpleStringProperty(name);
        this.type=ConnectionsType.MYSQL.getType();
        this.server="";
        this.userName="";
        this.password="";
    }

    public Connection(){
        this("");
    }
    public StringProperty getN(){
        return name;
    }

    public String getName() {
        return name.getValue();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Connection{" + "name=" + name + ", type=" + type + ", server=" + server + ", userName=" + userName + ", password=" + password + '}';
    }

    @Override
    public boolean equals(Object o){
        if(o==this){
            return true;
        }else{
            if(o instanceof Connection){
                Connection other=(Connection)o;
                if(name.getValue().equals(((Connection) o).name.getValue())){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
    } 
}
