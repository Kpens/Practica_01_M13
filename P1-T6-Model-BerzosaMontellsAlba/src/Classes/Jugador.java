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
    String adreca, cog, data_naix, foto, iban, id_legal, nom;
    int id_jug, any_fi_rev;
    Sexe_enum sexe;

    public Jugador(String adreca, int any_fi_rev, String cog, String data_naix, String foto, String iban, String id_legal, String nom, Sexe_enum sexe) {
        this.adreca = adreca;
        this.any_fi_rev = any_fi_rev;
        this.cog = cog;
        this.data_naix = data_naix;
        this.foto = foto;
        this.iban = iban;
        this.id_legal = id_legal;
        this.nom = nom;
        this.sexe = sexe;
    }

    @Override
    public String toString() {
        return "Jugador{" + "adreca=" + adreca + ", cog=" + cog + ", data_naix=" + data_naix + ", foto=" + foto + ", iban=" + iban + ", id_legal=" + id_legal + ", nom=" + nom + ", id_jug=" + id_jug + ", any_fi_rev=" + any_fi_rev + ", sexe=" + sexe + '}';
    }
    
}
