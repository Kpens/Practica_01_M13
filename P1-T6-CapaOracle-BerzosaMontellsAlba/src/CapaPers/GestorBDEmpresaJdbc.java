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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;



/**
 *
 * @author Alma
 */
public class GestorBDEmpresaJdbc implements IGestorBDEmpresa{

    //NI IDEA
    @Override
    public List<Equip> mostrar_equips_temp(Temporada t) {
        List<Equip> lequips = new ArrayList<>();
        
        return lequips;
    }

    @Override
    public List<Equip> mostrar_equips_cate() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Jugador> mostrar_jugadors_cate() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void login(Usuari u) throws GestorBDEmpresaException {
        Properties p = new Properties();
        String nom_usuari, contra;
        String nomFitxerPropietats = "Login_usuari.properties";
        try {
            p.load(new FileInputStream(nomFitxerPropietats));
            nom_usuari = p.getProperty("nom_usuari");
            contra = p.getProperty("contrasenya");
            
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
    public void crear_equip(Equip e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void afegir_jugadors(Equip e, Jugador j) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar_equips(Equip e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar_jugadors(Equip e, Jugador j) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void modificar_equip(Equip e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Jugador> mostrar_jugadors_per_equip(Equip e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void crear_jugador() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Jugador> mostrar_jugadors() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
