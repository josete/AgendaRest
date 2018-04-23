/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import clases.AgendaObjeto;
import clases.PersonaObjeto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import metodos.ImportarExportar;

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
    public AgendaObjeto getXml() {
        ImportarExportar i = new ImportarExportar("agenda.xml");
        return i.cargar();
    }

}
