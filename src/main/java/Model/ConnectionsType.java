package Model;

public enum ConnectionsType {

    MYSQL("mySQL"),
    H2("H2");
    
    private String type;

    private ConnectionsType(String type) {
        this.type=type;
    }
    public String getType(){
        return this.type;
    } 
}
