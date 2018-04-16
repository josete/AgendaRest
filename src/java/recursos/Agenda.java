/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Portatil
 */
@Path("agenda")
public class Agenda {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Agenda
     */
    public Agenda() {
    }

    /**
     * Retrieves representation of an instance of recursos.Agenda
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        String xml = "";
        File agenda = new File("agenda.xml");
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(agenda);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                xml += linea;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return xml;
    }
    
    @POST
    @Produces(MediaType.APPLICATION_XML)
    public void setXml(String xml){
        
    }

    /**
     * PUT method for updating or creating an instance of Agenda
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
