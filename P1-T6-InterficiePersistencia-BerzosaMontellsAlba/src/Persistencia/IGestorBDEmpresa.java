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
     * Es retorna una llista d'equips, sense els jugadors, quins estaràn filtrats per la categoria, temporada, 
     * tipus i el nom. ordenats per temporada/cate. i ordre ascendent o descendent
     * Si tipus és n significa que no s'ha seleccionat cap tipus
     * Si temp és 0 significa que no s'ha seleccionat cap temp
     */
    List<Equip> llistar_equips(String cate, int temp, char tipus, String nom, String ordre) throws GestorBDEmpresaException;//FET
    /**
     * S'actualitza la llista d'equips, amb els seus propis jugadors
     */
    List<Equip> actualitzar_equips() throws GestorBDEmpresaException;//FET
       
    
    /**
     * Funcions per la classe d'Usuari
     * 
     * Amb totes les dades de l'usuari es podrà entrar en l'aplicació
     * 
     * Ja que en aquesta aplicació només tindrem un usuari
     */
    void login(String login, String contra) throws GestorBDEmpresaException;//FET
    
    /**
     * Funcions per la classe d'Equip
     * 
     * Es crea un nou equip, si ja existeix retorna l'equip
     */
    
    Equip crear_equip(String nom, char tipus, int any_eq, String cate) throws GestorBDEmpresaException;//FET (en swing Revisa la inserció de cate)
    
    
    /**
     * Tanca la connexió
     *
     */
    void tancarCapa() throws GestorBDEmpresaException;
    
    /**
     * S'afegeix un nou jugador a l'equip i es diu si és el titular
     * Com es fa des de la configuració d'equip s'entra directament l'equip que 
     * estem creant, i com es selecciona el jugador de la taula també s'entra un 
     * Jugador, així que de per si no podrien esser nulls.
     */
    void afegir_jugadors(Equip e, Jugador j, boolean t) throws GestorBDEmpresaException;//FET
    
    /**
     * Es podrà eliminar l'equip, encara que si té jugadors no s'eliminarà, ja que 
     * així es podrà demanar al usuari si vol eliminar tots els jugadors abans, si 
     * diu que si es fa la funció eliminar_jugadors(), i desp aquesta
     */
    void eliminar_equip(Equip e) throws GestorBDEmpresaException;//Edita BBDD, per fer
    
    /**
     * Es retorna l'equip que sigui de la temporada especificada, i el nom espec.
     */
    Equip agafar_equip(String nom, int temporada) throws GestorBDEmpresaException;//FET
    
    /**
     * Es un jugador, quin pot ser demanat per la seva idLegal, o pel seu numero de jugador (per treure'l de membre amb menys linees)
     */
    Jugador agafar_jugador(String idLegal, Boolean esLegal) throws GestorBDEmpresaException;
    
    
    /**
     * Es podràn eliminar els jugadors d'un equip en concret
     */    
    void eliminar_jugadors(Equip e, Jugador j,boolean tots) throws GestorBDEmpresaException;//Eliminar a BBDD, per fer
    
    /**
     * Per a poder editar la informació de l'equip
     */
    //Equip modificar_equip(Equip e) throws GestorBDEmpresaException;//Editar BBDD
    /**
     * Per a poder editar la informació del jugador
     */
    Jugador modificar_jugador(Jugador j) throws GestorBDEmpresaException;//per fer
    
    /**
     * Una llista de jugadors de l'equip en concret
     */
    List<Jugador> mostrar_jugadors_per_equip() throws GestorBDEmpresaException;//per fer    
    
    /**
     * Funcions per la classe de Jugador
     * 
     * Crear un jugador
     */
    
    Jugador crear_jugador(String nom, String cog, Sexe_enum sexe, String data_naix, String id_legal, String iban, int any_fi_revisio, String adreca, String codi_postal, String poblacio, String foto, String provincia,String pais) throws GestorBDEmpresaException;//FET
    
    /**
     * Una llista de tots els jugadors, filtra per sexe i el nom de jugador
     */
    List<Jugador> llista_jugadors_ant(Sexe_enum sexe, String nom) throws GestorBDEmpresaException;//FET 
    
    /**
     * Una llista de tots els jugadors, filtra per sexe i el nom de jugador
     */
    List<Jugador> llista_jugadors(Sexe_enum sexe, String nom_j, String id_legal, String data_naix, Cate_enum cate) throws GestorBDEmpresaException;//FET
    /**
     * Crear una temporada (si no existeix)
     */
    Temporada crear_temporada(int anny) throws GestorBDEmpresaException;//FET
    
    List<Temporada> llista_temporades() throws GestorBDEmpresaException;
}

