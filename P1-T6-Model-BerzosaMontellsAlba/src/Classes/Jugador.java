/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import Enums.Sexe_enum;

/**
 *
 * @author Alma
 */
public class Jugador {
    String adreca, cog, data_naix, foto, iban, id_legal, nom, poblacio, provincia, pais;
    int id_jug, any_fi_rev, codi_postal;
    Sexe_enum sexe;

    public Jugador(int id_jug, String adreca, int any_fi_rev, String cog, String data_naix, String foto, String iban, String id_legal, String nom, Sexe_enum sexe, int codi_postal, String poblacio, String provincia, String pais) {
        this.id_jug = id_jug;
        this.adreca = adreca;
        this.any_fi_rev = any_fi_rev;
        this.cog = cog;
        this.data_naix = data_naix;
        this.foto = foto;
        this.iban = iban;
        this.id_legal = id_legal;
        this.nom = nom;
        this.sexe = sexe;
        this.codi_postal = codi_postal;
        this.poblacio = poblacio;
        this.provincia = provincia;
        this.pais = pais;
    }

        
    @Override
    public String toString() {
        return "Jugador{" +
                "\n\t adreca='" + adreca + '\'' +
                "\n\t cog='" + cog + '\'' +
                "\n\t data_naix='" + data_naix + '\'' +
                "\n\t foto='" + foto + '\'' +
                "\n\t iban='" + iban + '\'' +
                "\n\t id_legal='" + id_legal + '\'' +
                "\n\t nom='" + nom + '\'' +
                "\n\t id_jug=" + id_jug +
                "\n\t any_fi_rev=" + any_fi_rev +
                "\n\t sexe=" + sexe +
                "\n\t codi_postal=" + codi_postal +
                "\n\t poblacio='" + poblacio + '\'' +
                "\n\t provincia='" + provincia + '\'' +
                "\n\t pais='" + pais + '\'' +
                '}';
    }
    
}
