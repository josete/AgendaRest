/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDeDatos;

import clases.AgendaObjeto;
import clases.PersonaObjeto;
import clases.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Portatil
 */
public class BaseDeDatos {

    DataSource dataSource;
    Statement statement;
    ResultSet resultSet;
    Connection c;

    public static BaseDeDatos b = null;

    public BaseDeDatos() {
        if (b == null) {
            try {
                InitialContext context = new InitialContext();
                dataSource = (DataSource) context.lookup("jdbc/agenda");
                b = this;
            } catch (NamingException ex) {
                Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void insertarUsuario(Usuario u) {
        try {
            String sentencia = "insert into usuarios (email,password) values('" + u.email + "','" + u.password + "')";
            c = dataSource.getConnection();
            statement = c.createStatement();
            statement.execute(sentencia);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexiones();
        }
    }

    public String comprobarUsuario(String email) {
        try {
            String sentencia = "select password from usuarios where email = '" + email + "'";
            c = dataSource.getConnection();
            statement = c.createStatement();
            resultSet = statement.executeQuery(sentencia);
            while (resultSet.next()) {
                return resultSet.getString("password");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexiones();
        }
        return null;
    }

    public ArrayList<String> obtenerAgendas(String email) {
        try {
            String sentencia = "select id from agendas where idUsuario = ( SELECT id FROM usuarios where email = '" + email + "' )";
            c = dataSource.getConnection();
            statement = c.createStatement();
            resultSet = statement.executeQuery(sentencia);
            ArrayList<String> agendas = new ArrayList<>();
            while (resultSet.next()) {
                agendas.add("Agenda " + resultSet.getString("id"));
            }
            return agendas;
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexiones();
        }
        return null;
    }

    public void crearAgenda(String email) {
        try {
            String sentencia = "select id from usuarios where email = '" + email + "'";
            c = dataSource.getConnection();
            statement = c.createStatement();
            resultSet = statement.executeQuery(sentencia);
            int id = 0;
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            cerrarConexiones();
            sentencia = "insert into agendas (idUsuario) values ("+id+")";
            c = dataSource.getConnection();
            statement = c.createStatement();
            statement.execute(sentencia);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexiones();
        }
    }

    public AgendaObjeto obtenerAgenda(int id) {
        try {
            String sentencia = "select * from contactos where idAgenda = " + id;
            c = dataSource.getConnection();
            statement = c.createStatement();
            resultSet = statement.executeQuery(sentencia);
            ArrayList<PersonaObjeto> personas = new ArrayList<>();
            while(resultSet.next()){
                personas.add(new PersonaObjeto(resultSet.getString("nombre"), resultSet.getString("telefono"), resultSet.getString("email")));
            }
            return new AgendaObjeto(personas);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexiones();
        }
        return null;
    }
    
    public void insertarPersona(PersonaObjeto p,int idAgenda){
        try {
            String sentencia = "insert into contactos (nombre,email,telefono,idAgenda) values ('"+p.getNombre()+"'"
                    + ",'"+p.getEmail()+"','"+p.getTelefono()+"',"+idAgenda+")";
            c = dataSource.getConnection();
            statement = c.createStatement();
            statement.execute(sentencia);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexiones();
        }
    }
    public ArrayList<PersonaObjeto> obtenerPersona(String nombre,int idAgenda) {
        try {
            String sentencia = "select * from contactos where idAgenda = " + idAgenda +" and nombre like '"+nombre+"'";
            c = dataSource.getConnection();
            statement = c.createStatement();
            resultSet = statement.executeQuery(sentencia);
            ArrayList<PersonaObjeto> personas = new ArrayList<>();
            while(resultSet.next()){
                personas.add(new PersonaObjeto(resultSet.getString("nombre"), resultSet.getString("telefono"), resultSet.getString("email")));
            }
            return personas;
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexiones();
        }
        return null;
    }
    public void borrarPersona(int idPersona){
        try {
            String sentencia = "delete from contactos where id = "+idPersona;
            c = dataSource.getConnection();
            statement = c.createStatement();
            statement.execute(sentencia);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexiones();
        }
    }
    public void actualizarPersona(PersonaObjeto p,int idPersona){
        try {
            String sentencia = "update contactos set nombre='"+p.getNombre()+"', email='"+p.getEmail()+"'"
                    + ", telefono='"+p.getTelefono()+"' where id = "+idPersona;
            c = dataSource.getConnection();
            statement = c.createStatement();
            statement.execute(sentencia);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexiones();
        }
    }
    public void cerrarConexiones() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (c != null) {
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
