/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import clases.AgendaObjeto;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import metodos.CrearXsd;
import metodos.ImportarExportar;
import org.xml.sax.SAXException;

/**
 * REST Web Service
 *
 * @author Portatil
 */
@Path("agenda/validar")
public class ValidarAgenda {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Validar
     */
    public ValidarAgenda() {
    }

    /**
     * Retrieves representation of an instance of recursos.Validar
     *
     * @return an instance of java.lang.String
     */
    /*@GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }*/
    /**
     * PUT method for updating or creating an instance of Validar
     *
     * @param content representation for the resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public String putXml(AgendaObjeto a) {
        File schemaFile = new File("validador.xsd");
        if (!schemaFile.exists()) {
            CrearXsd.crear();
        }
        ImportarExportar i = new ImportarExportar("agenda.xml");
        File f = i.guardar(a);
        Source xmlFile = new StreamSource(f);
        SchemaFactory schemaFactory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(schemaFile);
            javax.xml.validation.Validator validator = schema.newValidator();
            validator.validate(xmlFile);
            System.out.println(xmlFile.getSystemId() + " is valid");
            return "true";
        } catch (SAXException e) {
            System.out.println(xmlFile.getSystemId() + " is NOT valid reason:" + e);
            return "false";
        } catch (IOException ex) {
            Logger.getLogger(ValidarAgenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "false";
    }
}
