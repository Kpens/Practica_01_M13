/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaPers;

import Classes.Equip;
import Classes.Jugador;
import Classes.Temporada;
import Classes.Usuari;
import Enums.Cate_enum;
import Enums.Sexe_enum;
import Enums.Tipus_enum;
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
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Alma
 */
public class GestorBDEmpresaJdbc implements IGestorBDEmpresa{

    private Connection c;

    public GestorBDEmpresaJdbc()  throws GestorBDEmpresaException {
        Properties p = new Properties();
        String url, user, pwd;
        String nomFitxerPropietats = "Connexio_JDBC.properties";
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
        try {
            PreparedStatement ps = c.prepareStatement("select e.id_equip, e.nom, e.tipus, e.any_eq, c.nom as cate from equip e \n" +
"join categoria c on c.id_cat = e.cate\n" +
"where e.any_eq = ? \n" +
"group by  e.id_equip,e.nom, e.tipus, e.any_eq, c.nom");
            ps.setInt(1, t.getAnny());
            ResultSet rs = ps.executeQuery();


            while(rs.next()){
                Tipus_enum tenum = Tipus_enum.H;
                int id_equip = rs.getInt("id_equip");
                String nom = rs.getString("nom");
                String tipus = rs.getString("tipus");
                if(Tipus_enum.D.toString().equals(tipus)){
                    tenum = Tipus_enum.D;
                }else if(Tipus_enum.M.toString().equals(tipus)){

                    tenum = Tipus_enum.M;
                }else{
                    tenum = Tipus_enum.H;

                }
                int any_eq = rs.getInt("any_eq");
                String cate = rs.getString("cate");
                Cate_enum cat =null;
                if(Cate_enum.Alevi.toString().equals(cate)){
                    cat = Cate_enum.Alevi;
                }else if(Cate_enum.Benjami.toString().equals(cate)){
                    cat = Cate_enum.Benjami;
                }else if(Cate_enum.Cadet.toString().equals(cate)){
                    cat = Cate_enum.Cadet;
                }else if(Cate_enum.Infantil.toString().equals(cate)){
                    cat = Cate_enum.Infantil;
                }else if(Cate_enum.Juvenil.toString().equals(cate)){
                    cat = Cate_enum.Juvenil;
                }else if(Cate_enum.Senior.toString().equals(cate)){
                    cat = Cate_enum.Senior;
                }

                System.out.println("Id_equip: " + id_equip + " Nom: " + nom + " Tipus: "+ tipus + " Any de l'equip: "+ any_eq+ " Categoria: "+cate);
                Equip eq = new Equip(nom, tenum, any_eq, cat);
                lequips.add(eq);
            }


        } catch (SQLException ex) {
            throw new GestorBDEmpresaException("Error en preparar sentència psInsertProduct", ex);
        } catch (Exception ex) {
            throw new GestorBDEmpresaException("Es fa una excepció: ", ex);
        } 
        
        return lequips;
    }

    @Override
    public List<Equip> mostrar_equips_cate(String ordre) throws GestorBDEmpresaException {
        
        List<Equip> lequips = new ArrayList<>();
        try {
            if(!ordre.equals("desc")){
                ordre = "";
            }
            PreparedStatement ps = c.prepareStatement("select e.id_equip, e.nom, e.tipus, e.any_eq, c.nom as cate from equip e \n" +
"join categoria c on c.id_cat = e.cate\n" +
"order by cate " + ordre);
            
            //ps.setString(1, ordre); amb l'order by no es pot
            ResultSet rs = ps.executeQuery();


            while(rs.next()){
                Tipus_enum tenum = Tipus_enum.H;
                int id_equip = rs.getInt("id_equip");
                String nom = rs.getString("nom");
                String tipus = rs.getString("tipus");
                if(Tipus_enum.D.toString().equals(tipus)){
                    tenum = Tipus_enum.D;
                }else if(Tipus_enum.M.toString().equals(tipus)){

                    tenum = Tipus_enum.M;
                }else{
                    tenum = Tipus_enum.H;

                }
                int any_eq = rs.getInt("any_eq");
                String cate = rs.getString("cate");
                Cate_enum cat =null;
                if(Cate_enum.Alevi.toString().equals(cate)){
                    cat = Cate_enum.Alevi;
                }else if(Cate_enum.Benjami.toString().equals(cate)){
                    cat = Cate_enum.Benjami;
                }else if(Cate_enum.Cadet.toString().equals(cate)){
                    cat = Cate_enum.Cadet;
                }else if(Cate_enum.Infantil.toString().equals(cate)){
                    cat = Cate_enum.Infantil;
                }else if(Cate_enum.Juvenil.toString().equals(cate)){
                    cat = Cate_enum.Juvenil;
                }else if(Cate_enum.Senior.toString().equals(cate)){
                    cat = Cate_enum.Senior;
                }

                System.out.println("Id_equip: " + id_equip + " Nom: " + nom + " Tipus: "+ tipus + " Any de l'equip: "+ any_eq+ " Categoria: "+cate);
                Equip eq = new Equip(nom, tenum, any_eq, cat);
                lequips.add(eq);
            }


        } catch (SQLException ex) {
            throw new GestorBDEmpresaException("Error en preparar sentència psInsertProduct", ex);
        } catch (Exception ex) {
            throw new GestorBDEmpresaException("Error: ", ex);
        }
        
        return lequips;
        
    }
    
    @Override
    public Equip agafar_equip(String nom, int temporada) throws GestorBDEmpresaException {
        Equip eq = null;
        nom = nom.toUpperCase();
        try {
            PreparedStatement ps = c.prepareStatement("select e.id_equip, e.nom, e.tipus, e.any_eq , c.nom as cate\n" +
"from equip e \n" +
"join categoria c on c.id_cat = e.cate\n" +
"where upper(e.nom) like ? and any_eq = ?");
                    
            ps.setString(1, "%" + nom.toUpperCase() + "%");
            
            ps.setInt(2, temporada);
            ResultSet rs = ps.executeQuery();



            while(rs.next()){
                Tipus_enum tenum = Tipus_enum.H;
                int id_equip = rs.getInt("id_equip");
                String nom_e = rs.getString("nom");
                String tipus = rs.getString("tipus");
                if(Tipus_enum.D.toString().equals(tipus)){
                    tenum = Tipus_enum.D;
                }else if(Tipus_enum.M.toString().equals(tipus)){

                    tenum = Tipus_enum.M;
                }else{
                    tenum = Tipus_enum.H;

                }
                int any_eq = rs.getInt("any_eq");
                String cate = rs.getString("cate");
                Cate_enum cat =null;
                if(Cate_enum.Alevi.toString().equals(cate)){
                    cat = Cate_enum.Alevi;
                }else if(Cate_enum.Benjami.toString().equals(cate)){
                    cat = Cate_enum.Benjami;
                }else if(Cate_enum.Cadet.toString().equals(cate)){
                    cat = Cate_enum.Cadet;
                }else if(Cate_enum.Infantil.toString().equals(cate)){
                    cat = Cate_enum.Infantil;
                }else if(Cate_enum.Juvenil.toString().equals(cate)){
                    cat = Cate_enum.Juvenil;
                }else if(Cate_enum.Senior.toString().equals(cate)){
                    cat = Cate_enum.Senior;
                }

                eq = new Equip(nom, tenum, any_eq, cat);
                
            }


            return eq;
        } catch (SQLException ex) {
            throw new GestorBDEmpresaException("Error en preparar sentència psInsertProduct", ex);
        } catch (Exception ex) {
            throw new GestorBDEmpresaException("Es fa una excepció: ", ex);
        }
        
    }

    @Override
    public List<Jugador> mostrar_jugadors(String ordre) throws GestorBDEmpresaException {
        List<Jugador> ljugs = new ArrayList<>();
        try {
            if(!ordre.equals("desc")){
                ordre = "";
            }
            PreparedStatement ps = c.prepareStatement("select ID_JUG ,NOM ,COGNOMS,SEXE,DATA_NAIX ,ID_LEGAL ,IBAN  ,ANY_FI_REVISIO_MEDICA ,ADRECA ,CODI_POSTAL ,POBLACIO ,FOTO ,PROVINCIA, PAIS from jugador order by id_jug " + ordre);
            //ps.setString(1, ordre); amb l'order by no es pot
            ResultSet rs = ps.executeQuery();


            while(rs.next()){
                int id_jug = rs.getInt("ID_JUG");
                String nom = rs.getString("NOM");
                String cog = rs.getString("COGNOMS");
                String sexeStr = rs.getString("SEXE");
                Sexe_enum sexe = Sexe_enum.valueOf(sexeStr); // Assumint que el sexe és D, M o H.
                String data_naix = rs.getString("DATA_NAIX");
                String id_legal = rs.getString("ID_LEGAL");
                String iban = rs.getString("IBAN");
                int any_fi_rev = rs.getInt("ANY_FI_REVISIO_MEDICA");
                String adreca = rs.getString("ADRECA");
                int codi_postal = rs.getInt("CODI_POSTAL");
                String poblacio = rs.getString("POBLACIO");
                String foto = rs.getString("FOTO");
                String provincia = rs.getString("PROVINCIA");
                String pais = rs.getString("PAIS");

                // Creació del jugador
                Jugador jug = new Jugador(
                    adreca, any_fi_rev, cog, data_naix, foto, iban, id_legal, nom,
                    sexe, codi_postal, poblacio, provincia, pais
                );

                    System.out.println(jug.toString());
                ljugs.add(jug);


            }


        } catch (SQLException ex) {
            throw new GestorBDEmpresaException("Error en preparar sentència psInsertProduct", ex);
        } catch (Exception ex) {
            throw new GestorBDEmpresaException("Error: ", ex);
        }
        
        return ljugs;
        
        
    }

    @Override
    public void login(String login, String contra) throws GestorBDEmpresaException {
        Properties p = new Properties();
        String user, pwd;
        String nomFitxerPropietats = "Connexio_JDBC.properties";
        try {
            p.load(new FileInputStream(nomFitxerPropietats));
            user = p.getProperty("user");
            pwd = p.getProperty("pwd");
            
            if (!login.equals(user) || !pwd.equals(contra) ) {
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
    public void crear_equip(String nom, char t, int any_eq, String cate) throws GestorBDEmpresaException {
        
        Cate_enum cat =null;
        if(Cate_enum.Alevi.toString().equals(cate)){
            cat = Cate_enum.Alevi;
        }else if(Cate_enum.Benjami.toString().equals(cate)){
            cat = Cate_enum.Benjami;
        }else if(Cate_enum.Cadet.toString().equals(cate)){
            cat = Cate_enum.Cadet;
        }else if(Cate_enum.Infantil.toString().equals(cate)){
            cat = Cate_enum.Infantil;
        }else if(Cate_enum.Juvenil.toString().equals(cate)){
            cat = Cate_enum.Juvenil;
        }else if(Cate_enum.Senior.toString().equals(cate)){
            cat = Cate_enum.Senior;
        }else{
            System.out.println("\n\nCategoria incorrecte\n\n");
            return;
        }
        
        Tipus_enum tipus = null;
        
        if(tipus.D.getValue() == Character.toUpperCase(t)){
            tipus =Tipus_enum.D;
        }else if(tipus.H.getValue() == Character.toUpperCase(t)){
            tipus =Tipus_enum.H;
        }else if(tipus.M.getValue() == Character.toUpperCase(t)) {
            tipus = Tipus_enum.M;
        }else{
            System.out.println("\n\nTipus incorrecte\n\n");
            return;
        }
        
        if(agafar_equip(nom, any_eq) == null){
            System.out.println("L'equip ja existeix");
            return;
        }else{
            try {
                Equip e = new Equip(nom, tipus, any_eq, cat);
                System.out.println("Equip creat");
                return;
            } catch (Exception ex) {
                Logger.getLogger(GestorBDEmpresaJdbc.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void afegir_jugadors(Equip e, int id_jug, char t) throws GestorBDEmpresaException {
        boolean trobat = false;
         try {
            PreparedStatement ps = c.prepareStatement("select ID_JUG ,NOM ,COGNOMS,SEXE,DATA_NAIX ,ID_LEGAL ,IBAN  ,ANY_FI_REVISIO_MEDICA ,ADRECA ,CODI_POSTAL ,POBLACIO ,FOTO ,PROVINCIA, PAIS from jugador order by id_jug");
            //ps.setString(1, ordre); amb l'order by no es pot
            ResultSet rs = ps.executeQuery();


            while(rs.next() && !trobat){
                int id_j = rs.getInt("ID_JUG");
                if(id_j == id_jug){
                    String nom = rs.getString("NOM");
                    String cog = rs.getString("COGNOMS");
                    String sexeStr = rs.getString("SEXE");
                    Sexe_enum sexe = Sexe_enum.valueOf(sexeStr); // Assumint que el sexe és D, M o H.
                    String data_naix = rs.getString("DATA_NAIX");
                    String id_legal = rs.getString("ID_LEGAL");
                    String iban = rs.getString("IBAN");
                    int any_fi_rev = rs.getInt("ANY_FI_REVISIO_MEDICA");
                    String adreca = rs.getString("ADRECA");
                    int codi_postal = rs.getInt("CODI_POSTAL");
                    String poblacio = rs.getString("POBLACIO");
                    String foto = rs.getString("FOTO");
                    String provincia = rs.getString("PROVINCIA");
                    String pais = rs.getString("PAIS");

                    // Creació del jugador
                    Jugador jug = new Jugador(
                        adreca, any_fi_rev, cog, data_naix, foto, iban, id_legal, nom,
                        sexe, codi_postal, poblacio, provincia, pais
                    );
                    e.afegir_jugador(id_jug, jug, t);
                    System.out.println("Afegit");
                    
                    e.mostrar_jugadors();
                    
                }
            
            }


        } catch (SQLException ex) {
            throw new GestorBDEmpresaException("Error en preparar sentència psInsertProduct", ex);
        } catch (Exception ex) {
            throw new GestorBDEmpresaException("Error: ", ex);
        }       
                
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

    
}
