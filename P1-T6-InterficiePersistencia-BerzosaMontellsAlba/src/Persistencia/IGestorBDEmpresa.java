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
import Enums.Sexe_enum;
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
    
    /*List<Equip> mostrar_equips_temp(Temporada t) throws GestorBDEmpresaException;//FET*/
    
    
    /**
     * Funcions per la classe de Categoria
     */
    
    /**
     * Es retorna una llista d'equips quins estaràn filtrats per la categoria
     */
    /*List<Equip> mostrar_equips_cate(String ordre) throws GestorBDEmpresaException;//FET*/
        
    
    /**
     * Es retorna una llista d'equips quins estaràn filtrats per la categoria, temporada, tipus i el nom. ordenats per temporada/cate. i ordre ascendent o descendent
     * Si tipus és n significa que no s'ha seleccionat cap tipus
     * Si temp és 0 significa que no s'ha seleccionat cap temp
     */
    List<Equip> mostrar_equips(String cate, int temp, char tipus, String nom, String ordre) throws GestorBDEmpresaException;//FET
    /**
     * S'actualitza la llista d'equips així no mostrant se en la consola
     */
    List<Equip> actualitzar_equips() throws GestorBDEmpresaException;//FET
       
    
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
    
    Equip crear_equip(String nom, char tipus, int any_eq, String cate) throws GestorBDEmpresaException;//FET (en swing Revisa la cate)
    
    /**
     * Es afegeix un nou jugador a l'equip i es diu si és el titular
     * Com es fa des de la configuració d'equip s'entra directament l'equip que estem creant, i com es selecciona el jugador de la taula també s'entra un Jugador
     */
    void afegir_jugadors(Equip e, Jugador j, boolean t) throws GestorBDEmpresaException;//Arregla-ho
    
    /**
     * Es podràn eliminar l'equip
     */
    void eliminar_equip(Equip e) throws GestorBDEmpresaException;//Edita BBDD
    
    /**
     * Es retorna l'equip que sigui de la temporada especificada, i el nom espec.
     */
    Equip agafar_equip(String nom, int temporada) throws GestorBDEmpresaException;//FET
    
    
    /**
     * Es podràn eliminar els jugadors d'un equip en concret
     */    
    void eliminar_jugadors(Equip e, Jugador j) throws GestorBDEmpresaException;//Eliminar a BBDD
    /**
     * Per a poder editar la informació de l'equip
     */
    void modificar_equip(Equip e) throws GestorBDEmpresaException;//Editar BBDD
    
    /**
     * Una llista de jugadors de l'equip en concret
     */
    List<Jugador> mostrar_jugadors_per_equip(Equip e) throws GestorBDEmpresaException;
    
    
    /**
     * Funcions per la classe de Jugador
     * 
     * Crear un jugador
     */
    
    Jugador crear_jugador(String nom, String cog, Sexe_enum sexe, String data_naix, String id_legal, String iban, int any_fi_revisio, String adreca, int codi_postal, String poblacio, String foto, String provincia,String pais) throws GestorBDEmpresaException;//FET
    
    /**
     * Una llista de tots els jugadors
     */
    List<Jugador> llista_jugadors(String ordre) throws GestorBDEmpresaException;//Revisa el balsamiq
}

