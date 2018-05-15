/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import baseDeDatos.BaseDeDatos;
import clases.ListaAgendas;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import filtros.FiltroAutenticacion;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Portatil
 */
@Path("obtenerAgendas")
public class ObtenerAgendasResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ObtenerAgendasResource
     */
    public ObtenerAgendasResource() {
    }

    /**
     * Retrieves representation of an instance of
     * recursos.ObtenerAgendasResource
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

}
