/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaPers;

import Classes.Equip;
import Classes.Jugador;
import Classes.Temporada;
import Classes.Usuari;
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



/**
 *
 * @author Alma
 */
public class GestorBDEmpresaJdbc implements IGestorBDEmpresa{

    private Connection c;

    public GestorBDEmpresaJdbc()  throws GestorBDEmpresaException {
        Properties p = new Properties();
        String url, user, pwd;
        String nomFitxerPropietats = "ConnexioJDBC.properties";
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
        Statement q = null;
        try {
            q = c.createStatement();
            ResultSet rs = q.executeQuery("SELECT prod_num, descripcio FROM producte");
            while (rs.next()) {
                Integer codi = rs.getInt("prod_num");
                String desc = rs.getString("descripcio");
                Producte p = new Producte(codi, desc);
                llProd.add(p);
//                llProd.add(new Producte(rs.getInt("prod_num"), rs.getString("descripcio")));
            }
            rs.close();
        } catch (SQLException ex) {
            throw new GestorBDEmpresaException("Error en intentar recuperar la llista de productes.", ex);
        } finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEmpresaException("Error en intentar tancar la sentència que ha recuperat la llista de productes.", ex);
                }
            }
        }
        
        return lequips;
    }

    @Override
    public List<Equip> mostrar_equips_cate() throws GestorBDEmpresaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Jugador> mostrar_jugadors_cate() throws GestorBDEmpresaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void login(Usuari u) throws GestorBDEmpresaException {
        Properties p = new Properties();
        String nom_usuari, contra;
        String nomFitxerPropietats = "Login_usuari.properties";
        try {
            p.load(new FileInputStream(nomFitxerPropietats));
            nom_usuari = p.getProperty("user");
            contra = p.getProperty("pwd");
            
            if (!u.getLogin().equals(nom_usuari) || !u.getPassword().equals(contra) ) {
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
