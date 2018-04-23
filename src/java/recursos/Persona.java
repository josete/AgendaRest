/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import clases.AgendaObjeto;
import clases.PersonaObjeto;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import metodos.ImportarExportar;

/**
 * REST Web Service
 *
 * @author Portatil
 */
@Path("persona")
public class Persona {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Persona
     */
    public Persona() {
    }

    /**
     * Retrieves representation of an instance of recursos.Persona
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{nombre}")
    @Produces(MediaType.APPLICATION_XML)
    public PersonaObjeto getXml(@PathParam("nombre") String nombre) {
        //TODO return proper representation object
        ImportarExportar i = new ImportarExportar("agenda.xml");
        AgendaObjeto a = i.cargar();
        for (PersonaObjeto p : a.getPersonas()) {
            if (p.getNombre().equals(nombre)) {
                return p;
            }
        }
        return null;
    }

    /**
     * PUT method for updating or creating an instance of Persona
     *
     * @param content representation for the resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void putXml(@FormParam("nombre") String nombre, @FormParam("email") String email, @FormParam("telefono") String telefono) {
        ImportarExportar i = new ImportarExportar("agenda.xml");
        AgendaObjeto a = i.cargar();
        PersonaObjeto p = new PersonaObjeto(nombre, telefono, email);
        a.anadirPersona(p);
        i.guardar(a);
    }
}