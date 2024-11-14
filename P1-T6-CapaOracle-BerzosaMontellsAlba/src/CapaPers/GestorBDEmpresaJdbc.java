/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaPers;

import Classes.Equip;
import Classes.Jugador;
import Classes.Temporada;
import Classes.Usuari;
import Enums.Tipus_enum;
import Persistencia.GestorBDEmpresaException;
import Persistencia.IGestorBDEmpresa;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Alma
 */
public class GestorBDEmpresaJdbc implements IGestorBDEmpresa{

    private Connection c;

    public GestorBDEmpresaJdbc()  throws GestorBDEmpresaException {
        Properties p = new Properties();
        String url, user, pwd;
        String nomFitxerPropietats = "Connexio_JDBC.properties";
        try {
            p.load(new FileInputStream(nomFitxerPropietats));
            url = p.getProperty("url");
            user = p.getProperty("user");
            pwd = p.getProperty("pwd");
            
            if (url == null || user ==null ||pwd==null) {
                throw new GestorBDEmpresaException("Al fitxer " +nomFitxerPropietats + " li manca alguna de les propietats url/user/pwd");
                
            }
            c = DriverManager.getConnection(url, user, pwd);
            c.setAutoCommit(false);
        } catch (FileNotFoundException ex) {
            throw new GestorBDEmpresaException("Fitxer no trobat: " + nomFitxerPropietats, ex);
            
        } catch (IOException ex) {
            throw new GestorBDEmpresaException("No es pot recuperar les propietats del fitxer " + nomFitxerPropietats, ex);
            
        }catch (SQLException ex) {
            throw new GestorBDEmpresaException("No es pot establir la connexió.", ex);
        }
        
    }

    
    
    //NI IDEA
    @Override
    public List<Equip> mostrar_equips_temp(Temporada t) throws GestorBDEmpresaException {
        List<Equip> lequips = new ArrayList<>();
        try {
            PreparedStatement ps = c.prepareStatement("select id_equip, nom, tipus, any_eq, cate from equip where any_eq = ?");
            ps.setInt(1, t.getAnny());
            ResultSet rs = ps.executeQuery();


            while(rs.next()){
                Tipus_enum tenum = Tipus_enum.H;
                int id_equip = rs.getInt("id_equip");
                String nom = rs.getString("nom");
                String tipus = rs.getString("tipus");
                if(Tipus_enum.D.toString().equals(tipus)){
                    tenum = Tipus_enum.D;
                }else if(Tipus_enum.M.toString().equals(tipus)){

                    tenum = Tipus_enum.M;
                }else{
                    tenum = Tipus_enum.H;

                }
                int any_eq = rs.getInt("any_eq");
                int cate = rs.getInt("cate");

                System.out.println("Id_equip: " + id_equip + " Nom: " + nom + " Tipus: "+ tipus + " Any de l'equip: "+ any_eq+ " Categoria: "+cate);
                Equip eq = new Equip(nom, tenum, any_eq, cate);
                lequips.add(eq);
            }


        } catch (SQLException ex) {
            throw new GestorBDEmpresaException("Error en preparar sentència psInsertProduct", ex);
        } catch (Exception ex) {
            throw new GestorBDEmpresaException("", ex);
        }
        
        return lequips;
    }

    @Override
    public List<Equip> mostrar_equips_cate() throws GestorBDEmpresaException {
        
        List<Equip> lequips = new ArrayList<>();
        try {
            PreparedStatement ps = c.prepareStatement("select id_equip, nom, tipus, any_eq, cate from equip order by cate");
            ResultSet rs = ps.executeQuery();


            while(rs.next()){
                Tipus_enum tenum = Tipus_enum.H;
                int id_equip = rs.getInt("id_equip");
                String nom = rs.getString("nom");
                String tipus = rs.getString("tipus");
                if(Tipus_enum.D.toString().equals(tipus)){
                    tenum = Tipus_enum.D;
                }else if(Tipus_enum.M.toString().equals(tipus)){

                    tenum = Tipus_enum.M;
                }else{
                    tenum = Tipus_enum.H;

                }
                int any_eq = rs.getInt("any_eq");
                int cate = rs.getInt("cate");

                System.out.println("Id_equip: " + id_equip + " Nom: " + nom + " Tipus: "+ tipus + " Any de l'equip: "+ any_eq+ " Categoria: "+cate);
                Equip eq = new Equip(nom, tenum, any_eq, cate);
                lequips.add(eq);
            }


        } catch (SQLException ex) {
            throw new GestorBDEmpresaException("Error en preparar sentència psInsertProduct", ex);
        } catch (Exception ex) {
            throw new GestorBDEmpresaException("", ex);
        }
        
        return lequips;
        
    }

    @Override
    public List<Jugador> mostrar_jugadors_cate() throws GestorBDEmpresaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void login(String login, String contra) throws GestorBDEmpresaException {
        Properties p = new Properties();
        String user, pwd;
        String nomFitxerPropietats = "Connexio_JDBC.properties";
        try {
            p.load(new FileInputStream(nomFitxerPropietats));
            user = p.getProperty("user");
            pwd = p.getProperty("pwd");
            
            if (!login.equals(user) || !pwd.equals(contra) ) {
                throw new GestorBDEmpresaException("Nom d'usuari o contrasenya incorrectes.");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("No es troba el fitxer " + nomFitxerPropietats);
            throw new GestorBDEmpresaException("Fitxer no trobat: " + nomFitxerPropietats, ex);
        } catch (IOException ex) {
            throw new GestorBDEmpresaException("Error accedint al fitxer: " + nomFitxerPropietats, ex);
        }
    }

    @Override
    public void crear_equip(Equip e) throws GestorBDEmpresaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void afegir_jugadors(Equip e, Jugador j) throws GestorBDEmpresaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar_equips(Equip e) throws GestorBDEmpresaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar_jugadors(Equip e, Jugador j) throws GestorBDEmpresaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void modificar_equip(Equip e) throws GestorBDEmpresaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Jugador> mostrar_jugadors_per_equip(Equip e) throws GestorBDEmpresaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void crear_jugador() throws GestorBDEmpresaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Jugador> mostrar_jugadors() throws GestorBDEmpresaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
