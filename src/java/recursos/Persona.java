/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import baseDeDatos.BaseDeDatos;
import clases.PersonaObjeto;
import filtros.FiltroAutenticacion;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

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
    @Path("{idAgenda}")
    @FiltroAutenticacion
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<PersonaObjeto> getXml(@PathParam("idAgenda") String idAgenda,@QueryParam("nombre") String nombre) {
        if (BaseDeDatos.b == null) {
            BaseDeDatos ba = new BaseDeDatos();
            return BaseDeDatos.b.obtenerPersona(nombre,Integer.parseInt(idAgenda));
        } else {
            return BaseDeDatos.b.obtenerPersona(nombre,Integer.parseInt(idAgenda));
        }
    }

    /**
     * PUT method for updating or creating an instance of Persona
     *
     * @param content representation for the resource
     */
    @POST
    @Path("{idAgenda}")
    @FiltroAutenticacion
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(PersonaObjeto p,@PathParam("idAgenda") String idAgenda) {
        if (BaseDeDatos.b == null) {
            BaseDeDatos ba = new BaseDeDatos();
            BaseDeDatos.b.insertarPersona(p,Integer.parseInt(idAgenda));
        } else {
            BaseDeDatos.b.insertarPersona(p,Integer.parseInt(idAgenda));
        }
    }
    
    @DELETE
    @Path("{idPersona}")
    @FiltroAutenticacion
    @Consumes(MediaType.APPLICATION_XML)
    public void borrar(@PathParam("idPersona") String idPersona) {
        if (BaseDeDatos.b == null) {
            BaseDeDatos ba = new BaseDeDatos();
            BaseDeDatos.b.borrarPersona(Integer.parseInt(idPersona));
        } else {
            BaseDeDatos.b.borrarPersona(Integer.parseInt(idPersona));
        }
    }
    
    @PUT
    @Path("{idPersona}")
    @FiltroAutenticacion
    @Consumes(MediaType.APPLICATION_XML)
    public void actualizar(PersonaObjeto p,@PathParam("idPersona") String idPersona) {
        if (BaseDeDatos.b == null) {
            BaseDeDatos ba = new BaseDeDatos();
            BaseDeDatos.b.actualizarPersona(p,Integer.parseInt(idPersona));
        } else {
            BaseDeDatos.b.actualizarPersona(p,Integer.parseInt(idPersona));
        }
    }
}
