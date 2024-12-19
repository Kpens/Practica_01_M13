/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import Enums.Sexe_enum;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Alma
 */
public class Jugador {
    String adreca, cog, data_naix, foto, iban, id_legal, nom, poblacio, provincia, pais;
    int id_jug, any_fi_rev, codi_postal;
    Sexe_enum sexe;

    public Jugador(int id_jug, String adreca, int any_fi_rev, String cog, String data_naix, String foto, String iban, String id_legal, String nom, Sexe_enum sexe, int codi_postal, String poblacio, String provincia, String pais) throws Exception{        
        if(isValidIban(iban)){
            this.iban = iban;
        }else{
            throw new Exception("El iban és incorrecte");
            
        }
        if(isValidNif(id_legal)){
            this.id_legal = id_legal;
        }else{
            throw new Exception("El NIF/id_legal és incorrecte");
            
        }
        this.id_jug = id_jug;
        this.adreca = adreca;
        this.any_fi_rev = any_fi_rev;
        this.cog = cog;
        this.data_naix = data_naix;
        this.foto = foto;
        this.nom = nom;
        this.sexe = sexe;
        this.codi_postal = codi_postal;
        this.poblacio = poblacio;
        this.provincia = provincia;
        this.pais = pais;
    }

    public int getId_jug() {
        return id_jug;
    }

    public String getId_legal() {
        return id_legal;
    }

    public String getAdreca() {
        return adreca;
    }

    public String getCog() {
        return cog;
    }

    public String getData_naix() {
        return data_naix;
    }

    public String getFoto() {
        return foto;
    }

    public String getIban() {
        return iban;
    }

    public String getNom() {
        return nom;
    }

    public String getPoblacio() {
        return poblacio;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getPais() {
        return pais;
    }

    public int getAny_fi_rev() {
        return any_fi_rev;
    }

    public int getCodi_postal() {
        return codi_postal;
    }

    public Sexe_enum getSexe() {
        return sexe;
    }

    public static boolean isValidIban(String iban) {
        // Validar que el IBAN té una longitud mínima i màxima
        if (iban == null || iban.length() < 15 || iban.length() > 34) {
            return false;
        }
        
        // Regex per validar el format del IBAN (només per verificar la sintaxi)
        String regex = "^[A-Z]{2}[0-9]{2}[A-Z0-9]{1,30}$";
        Pattern pat = Pattern.compile(regex);
        Matcher matcher = pat.matcher(iban.toUpperCase());

        return matcher.matches();
    }
        
     public static boolean isValidNif(String nif) {
        // Comprova que el NIF no sigui nul ni buit
        if (nif == null || nif.isEmpty()) {
            return false;
        }

        // Regex per validar el NIF
        // Format per a persones físiques: 8 dígits + 1 lletra
        // Format per a persones jurídiques: 1 lletra + 8 dígits
        String regexPersonaFisica = "^[0-9]{8}[A-Z]$";
        String regexPersonaJuridica = "^[A-Z]{1}[0-9]{8}$";
        
        Pattern patternFisica = Pattern.compile(regexPersonaFisica);
        Pattern patternJuridica = Pattern.compile(regexPersonaJuridica);

        // Comprova si el NIF coincideix amb algun dels dos formats
        return patternFisica.matcher(nif.toUpperCase()).matches() || patternJuridica.matcher(nif.toUpperCase()).matches();
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
