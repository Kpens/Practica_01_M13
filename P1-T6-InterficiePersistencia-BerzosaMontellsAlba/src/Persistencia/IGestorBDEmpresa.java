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
     * Es retorna una llista d'equips, que contindràn els seus jugadors, quins estaràn filtrats per la categoria, temporada, 
     * tipus i el nom. ordenats per temporada/cate. i ordre ascendent o descendent
     * Si tipus és n significa que no s'ha seleccionat cap tipus
     * Si temp és 0 significa que no s'ha seleccionat cap temp
     * I si cate/nom és null, no s'ha seleccionat
     */
    List<Equip> llistar_equips(String cate, int temp, char tipus, String nom, String ordre) throws GestorBDEmpresaException;//FET
    
    /*
    * Fa una llista de tots els equips d'un jugador en concret
    */
    List<Equip> equips_on_son_els_jug(int id_jug) throws GestorBDEmpresaException;
    /**
     * S'actualitza la llista d'equips, amb els seus propis jugadors dins dels maps
     */
    List<Equip> actualitzar_equips() throws GestorBDEmpresaException;//FET
       
    
    /**    
     * Es recuperen les dades dels usuaris quin podrà entrar a l'aplicació
     * 
     */
    void login(String login, String contra) throws GestorBDEmpresaException;//FET
    
    /**     * 
     * Es crea un nou equip, si ja existeix tira una excepció
     */
    
    Equip crear_equip(String nom, char tipus, int any_eq, String cate) throws GestorBDEmpresaException;//FET (en swing Revisa la inserció de cate)
    
    
    /**
     * Tanca la connexió
     *
     */
    void tancarCapa() throws GestorBDEmpresaException;
    
    /**
     * S'afegeix un nou jugador a l'equip i es s'indica si és el titular, com es
     * fa des de la gestió d'equip, s'entra directament l'equip que 
     * estem creant i el jugador.
     */
    void afegir_jugadors(Equip e, Jugador j, boolean t) throws GestorBDEmpresaException;//FET
    
    /**
     * Es podrà eliminar l'equip, encara que si aquest ja té jugadors no s'eliminarà, retornant un false, ja que 
     * d'aquesta manera es podrà demanar al usuari si vol eliminar tots els jugadors abans dins del JFrame, si 
     * diu que si es fa la funció eliminar_jugadors_de_l_equip(Equip e, Jugador j, boolean tots);, i desp aquesta
     */
    boolean eliminar_equip(Equip e) throws GestorBDEmpresaException;//Edita BBDD, per fer
    
    /**
     * Es retorna l'equip que sigui de la temporada especificada, i el nom espec.
     */
    Equip agafar_equip(String nom, int temporada) throws GestorBDEmpresaException;//FET
    
    /**
     * Retorna un jugador, quin pot ser demanat per la seva idLegal, o pel seu id_jug, el boolean diu que si és cert 
     * serà la id_legal, sinó la id_jug (per treure'l de membre amb menys linees)
     */
    Jugador agafar_jugador(String id_legal, Boolean esLegal) throws GestorBDEmpresaException;
    
    /**
     * Es podrà eliminar el jugador si no està en cap equip
     */
    void eliminar_jugador(int id_jug) throws GestorBDEmpresaException;
    /**
     * Es podràn eliminar els jugadors d'un equip en concret, si selecciones cert en tots, s'eliminaràn 
     * tots els jugadors, sinó el seleccionat
     */
    void eliminar_jugadors_de_l_equip(Equip e, Jugador j,boolean tots) throws GestorBDEmpresaException;
    
    /**
     * Per a poder editar la informació del jugador, tenint en compte les 
     * restriccions que pot tenir en estar en un equip
     */
    Jugador modificar_jugador(Jugador j) throws GestorBDEmpresaException;
    
    /**
     * Una llista de jugadors d'un equip en concret
     */
    List<Jugador> mostrar_jugadors_per_equip(int id_equip) throws GestorBDEmpresaException;  
    
    /**
     * Retorna M si el jugador està en només equips mixtes, D si esta almenys en un de femeni i igual amb H,
     * si no està en cap retorna M. (Així pots saber si podem modificar el sexe d'un jugador)
     */
    Tipus_enum equip_mes_restrictiu_del_jug(int id_jug) throws GestorBDEmpresaException;
    
    /**
     * Crear un jugador
     */
    Jugador crear_jugador(String nom, String cog, Sexe_enum sexe, String data_naix, String id_legal, String iban, int any_fi_revisio, String adreca, String codi_postal, String poblacio, String foto, String provincia,String pais) throws GestorBDEmpresaException;//FET
    
    /**
     * Una llista de tots els jugadors, filtra per sexe i el nom de jugador
     */
    List<Jugador> llista_jugadors_ant(String sexe, String nom) throws GestorBDEmpresaException;//FET 
    
    /**
     * Una llista de tots els jugadors, filtra per sexe, el nom de jugador, id_legal, data naixement, i si la revisió està en false
     *  mostrarà una llista de jugadors que tenen la revisió mèdica caducada, en canvi, si està en cert dirà true
     */
    List<Jugador> llista_jugadors(Sexe_enum sexe, String nom_j, String id_legal, String data_naix, Cate_enum cate, boolean rev_feta) throws GestorBDEmpresaException;//FET
    
    /**
     * Crear una temporada (si no existeix)
     */
    Temporada crear_temporada(int anny) throws GestorBDEmpresaException;//FET
    /**
     * Llista totes les temporades
     */
    List<Temporada> llista_temporades() throws GestorBDEmpresaException;
}

