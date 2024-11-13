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
    
    List<Equip> mostrar_equips_temp(Temporada t) throws GestorBDEmpresaException ;
    
    
    /**
     * Funcions per la classe de Categoria
     */
    
    /**
     * Es retorna una llista d'equips quins estaràn filtrats per la categoria
     */
    List<Equip> mostrar_equips_cate() throws GestorBDEmpresaException;
    
    /**
     * Es retorna una llista de jugadors quins estaràn filtrats per la categoria
     */
    List<Jugador> mostrar_jugadors_cate() throws GestorBDEmpresaException;
    
    
    /**
     * Funcions per la classe d'Usuari
     * 
     * Amb totes les dades de l'usuari es podrà entrar en l'aplicació
     */
    void login(Usuari u) throws GestorBDEmpresaException;
    
    
    /**
     * Funcions per la classe d'Equip
     * 
     * Es crea un nou equip
     */
    
    void crear_equip(Equip e) throws GestorBDEmpresaException;
    
    /**
     * Es afegeix un nou jugador a l'equip
     */
    void afegir_jugadors(Equip e, Jugador j) throws GestorBDEmpresaException;
    
    /**
     * Es podràn eliminar els equips
     */
    void eliminar_equips(Equip e) throws GestorBDEmpresaException;
    
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
    List<Jugador> mostrar_jugadors() throws GestorBDEmpresaException;
}

