/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import baseDeDatos.BaseDeDatos;
import clases.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Portatil
 */
@Path("login")
public class LoginResource {

    @Context
    private UriInfo context;
    

    /**
     * Creates a new instance of LoginResource
     */
    public LoginResource() {
    }

    /**
     * PUT method for updating or creating an instance of LoginResource
     * @param content representation for the resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public String putXml(Usuario u) {
        try {
            String password;
            Algorithm algorithmHS = Algorithm.HMAC256("secret");
            if(BaseDeDatos.b==null){
                BaseDeDatos ba = new BaseDeDatos();
                password = BaseDeDatos.b.comprobarUsuario(u.email);
            }else{
                password = BaseDeDatos.b.comprobarUsuario(u.email);
            }
            if(password!=null && password.equals(u.password)){                
                String token = JWT.create().withClaim("email", u.email).sign(algorithmHS);                
                return token;
            }
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(LoginResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LoginResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
