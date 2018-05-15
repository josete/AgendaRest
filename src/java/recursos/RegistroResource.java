/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import baseDeDatos.BaseDeDatos;
import clases.Usuario;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Portatil
 */
@Path("registro")
public class RegistroResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RegistroResource
     */
    public RegistroResource() {
    }

    /**
     * Retrieves representation of an instance of recursos.RegistroResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of RegistroResource
     * @param content representation for the resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(Usuario u) {
        if(BaseDeDatos.b==null){
            BaseDeDatos ba = new BaseDeDatos();
            BaseDeDatos.b.insertarUsuario(u);
        }else{
            BaseDeDatos.b.insertarUsuario(u);
        }
    }
}
