/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author Carlos
 */
public enum Scenes {
    ROOT("root"),
    PRIMARY("primary"),
    CPER("Crear_perfil"),
    ADMINISTRAR("perfilesCreados");

    private String url;

    Scenes(String fxmlFile) {
        this.url = fxmlFile;
    }

    public String getUrl() {
        return url;
    }
}
