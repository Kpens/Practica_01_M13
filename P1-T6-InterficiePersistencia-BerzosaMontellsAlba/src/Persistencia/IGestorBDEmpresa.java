/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Classes.Categoria;
import Classes.Equip;
import Classes.Jugador;
import Classes.Temporada;
import Classes.Usuari;
import Enums.Cate_enum;
import Enums.Tipus_enum;
import java.util.List;

/**
 *
 * @author Alma
 */
public interface IGestorBDEmpresa {
    
    
    /**
     * Funcions per la classe de Temporada
     * 
     * Es retorna una llista d'equips quins estaràn filtrats per la temporada indicada
     */
    
    List<Equip> mostrar_equips_temp(Temporada t) throws GestorBDEmpresaException ;//FET
    
    
    /**
     * Funcions per la classe de Categoria
     */
    
    /**
     * Es retorna una llista d'equips quins estaràn filtrats per la categoria
     */
    List<Equip> mostrar_equips_cate(String ordre) throws GestorBDEmpresaException;//FET
        
    
    /**
     * Funcions per la classe d'Usuari
     * 
     * Amb totes les dades de l'usuari es podrà entrar en l'aplicació
     * 
     * Ja que en aquesta aplicació només tindrem un usuari
     */
    void login(String login, String contra) throws GestorBDEmpresaException;
    
    /**
     * Funcions per la classe d'Equip
     * 
     * Es crea un nou equip
     */
    
    void crear_equip(String nom, char tipus, int any_eq, String cate) throws GestorBDEmpresaException;//FET
    
    /**
     * Es afegeix un nou jugador a l'equip i es diu si és el titular
     */
    void afegir_jugadors(Equip e, int id_jug, char t) throws GestorBDEmpresaException;//Hauries de tindre en compte que el equip pot no existir
    
    /**
     * Es podràn eliminar els equips
     */
    void eliminar_equips(Equip e) throws GestorBDEmpresaException;
    
    /**
     * Es retorna l'equip que sigui de la temporada especificada, i el nom espec.
     */
    Equip agafar_equip(String nom, int temporada) throws GestorBDEmpresaException;//FET
    
    
    /**
     * Es podràn eliminar els jugadors d'un equip en concret
     */    
    void eliminar_jugadors(Equip e, Jugador j) throws GestorBDEmpresaException;
    /**
     * Per a poder editar la informació de l'equip
     */
    void modificar_equip(Equip e) throws GestorBDEmpresaException;
    
    /**
     * Una llista de jugadors de l'equip en concret
     */
    List<Jugador> mostrar_jugadors_per_equip(Equip e) throws GestorBDEmpresaException;
    
    
    /**
     * Funcions per la classe de Jugador
     * 
     * Crear un jugador
     */
    
    void crear_jugador() throws GestorBDEmpresaException;
    
    /**
     * Una llista de tots els jugadors
     */
    List<Jugador> mostrar_jugadors(String ordre) throws GestorBDEmpresaException;
}

