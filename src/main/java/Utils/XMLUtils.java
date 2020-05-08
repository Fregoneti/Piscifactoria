/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Model.Connection;
import Model.ConnectionsWrapper;
import java.io.File;
import Utils.Dialog;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Carlos
 */
public class XMLUtils {

    public static String file = "conn.xml";

    public static List<Connection> loadDataXML() {
        List<Connection> result = new ArrayList<>();
        File f = new File(file);
        if (f.canRead()) {
            try {
                JAXBContext context = JAXBContext.newInstance(ConnectionsWrapper.class);
                Unmarshaller um = context.createUnmarshaller();
                ConnectionsWrapper wrapper = (ConnectionsWrapper) um.unmarshal(f);
                result.addAll(wrapper.getConns());
            } catch (JAXBException ex) {
                ex.printStackTrace();
                Dialog.showError("ERROR", "Error writing " + file, ex.toString());
                result = new ArrayList<>();
            }
        }
        return result;
    }

    public static void writeDataXML(List<Connection> data) {
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(ConnectionsWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            ConnectionsWrapper wrapper = new ConnectionsWrapper();
            wrapper.setConns(data);
            m.marshal(wrapper, new File(file));
        } catch (JAXBException ex) {
            ex.printStackTrace();
            Dialog.showError("ERROR", "Error reading " + file, ex.toString());
        }

    }

}
