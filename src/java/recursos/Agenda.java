/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import baseDeDatos.BaseDeDatos;
import clases.AgendaObjeto;
import clases.ListaAgendas;
import clases.PersonaObjeto;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import filtros.FiltroAutenticacion;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.HttpHeaders;
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
    @FiltroAutenticacion
    @Produces(MediaType.APPLICATION_XML)
    public ListaAgendas getXml(@Context HttpHeaders httpheaders) {
        //TODO return proper representation object
        String token = httpheaders.getHeaderString("Authorization");
        DecodedJWT jwt = JWT.decode(token);
        Map<String, Claim> claims = jwt.getClaims();
        String email = claims.get("email").asString();
        ArrayList<String> agendas;
        if (BaseDeDatos.b == null) {
            BaseDeDatos ba = new BaseDeDatos();
            agendas = BaseDeDatos.b.obtenerAgendas(email);
        } else {
            agendas = BaseDeDatos.b.obtenerAgendas(email);
        }
        ListaAgendas agendasLista = new ListaAgendas(agendas);
        return agendasLista;
    }
    
    @POST
    @FiltroAutenticacion
    @Produces(MediaType.APPLICATION_XML)
    public void postXml(@Context HttpHeaders httpheaders) {
        String token = httpheaders.getHeaderString("Authorization");
        DecodedJWT jwt = JWT.decode(token);
        Map<String, Claim> claims = jwt.getClaims();
        String email = claims.get("email").asString();
        if (BaseDeDatos.b == null) {
            BaseDeDatos ba = new BaseDeDatos();
            BaseDeDatos.b.crearAgenda(email);
        } else {
            BaseDeDatos.b.crearAgenda(email);
        }
    }
    
    @GET
    @Path("{id}")
    @FiltroAutenticacion
    @Produces(MediaType.APPLICATION_XML)
    public AgendaObjeto getAgenda(@PathParam("id") String id) {
        if (BaseDeDatos.b == null) {
            BaseDeDatos ba = new BaseDeDatos();
            return BaseDeDatos.b.obtenerAgenda(Integer.parseInt(id));
        } else {
            return BaseDeDatos.b.obtenerAgenda(Integer.parseInt(id));
        }
    }

}
