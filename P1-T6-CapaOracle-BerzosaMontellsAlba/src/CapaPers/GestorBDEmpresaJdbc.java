/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaPers;

import Classes.Categoria;
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
    List<Categoria> cat;
    List<Equip> llista_equips = new ArrayList<>();
    List<Jugador> llista_jugs_eq = new ArrayList<>();

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

    /*
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
        
    }*/
    
    @Override
    public Equip agafar_equip(String nom, int temporada) throws GestorBDEmpresaException {
        Equip eq = null;
        boolean existeix=false;
        nom = nom.toUpperCase();
        try {
            PreparedStatement ps = c.prepareStatement("select e.id_equip, e.nom, e.tipus, e.any_eq , c.nom as cate\n" +
"from equip e \n" +
"join categoria c on c.id_cat = e.cate\n" +
"where upper(e.nom) like ? and any_eq = ?");
                    
            ps.setString(1, "%" + nom + "%");
            
            ps.setInt(2, temporada);
            ResultSet rs = ps.executeQuery();



            while(rs.next()){
                Tipus_enum tenum = Tipus_enum.H;
                existeix = true;
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

                eq = new Equip(id_equip, nom, tenum, any_eq, cat);
                
                
            }
            
                if(existeix){//Existeix i retorna l'equip
                    System.out.println("ID"+eq.getId_equip());
                    return eq;
                }else{
                    return null;
                }
        } catch (SQLException ex) {
            throw new GestorBDEmpresaException("Error en preparar sentència psInsertProduct", ex);
        } catch (Exception ex) {
            throw new GestorBDEmpresaException("Es fa una excepció: ", ex);
        }
        
    }

    @Override
    public List<Jugador> llista_jugadors(Sexe_enum sexe_j, String nom_j) throws GestorBDEmpresaException {
        List<Jugador> ljugs = new ArrayList<>();
        boolean entrat=false;
        char s;
        if(sexe_j == Sexe_enum.D){
            s='D';
        }else{
            s='H';
        }
        try {
            PreparedStatement ps = c.prepareStatement("select ID_JUG ,NOM ,COGNOMS,SEXE,DATA_NAIX ,ID_LEGAL ,IBAN,ANY_FI_REVISIO_MEDICA ,ADRECA ,CODI_POSTAL ,POBLACIO ,FOTO ,PROVINCIA, PAIS from jugador where upper(sexe) like ? and upper(nom) like ? order by id_jug ");
            
            ps.setString(1, "%"+s+"%");
            
            if(nom_j.equals("") || nom_j.equals(" ") ){
                ps.setString(2, "%"+nom_j.toUpperCase()+"%");
            }else{
                ps.setString(2, "%%");
                
            }
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                entrat=true;
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
                    id_jug, adreca, any_fi_rev, cog, data_naix, foto, iban, id_legal, nom,
                    sexe, codi_postal, poblacio, provincia, pais
                );

                ljugs.add(jug);


            }
            if(entrat==false){
                return null;
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
    public Equip crear_equip(String nom, char t, int any_eq, String cate) throws GestorBDEmpresaException {
        actualitzar_equips();
        
        for (Equip eq : llista_equips) {
            if(eq.getNom().equals(nom) && eq.getAny_eq() == any_eq ){
                return eq;
            }
        }
        
        int id_cate=0;
        int id_equip =-1;
        Categoria catee;
        Cate_enum cat =null;
        cate = cate.toUpperCase();
        System.out.println("cate  "+cate);
        PreparedStatement p;
        try {
            p = c.prepareStatement("select id_cat from categoria where upper(nom) like ?");
            p.setString(1, "%"+cate+"%");
            
            ResultSet rs = p.executeQuery();
            while(rs.next()){
                id_cate = rs.getInt("id_cat");
                System.out.println("ID:" + id_cate);
            }
                        
        } catch (SQLException ex) {
            throw new GestorBDEmpresaException("Error no es pot seleccionar la id de la categoria especificada. ", ex);
        }
        
        if(Cate_enum.Alevi.toString().toUpperCase().equals(cate)){
            cat = Cate_enum.Alevi;
        }else if(Cate_enum.Benjami.toString().toUpperCase().equals(cate)){
            cat = Cate_enum.Benjami;
        }else if(Cate_enum.Cadet.toString().toUpperCase().equals(cate)){
            cat = Cate_enum.Cadet;
        }else if(Cate_enum.Infantil.toString().toUpperCase().equals(cate)){
            cat = Cate_enum.Infantil;
        }else if(Cate_enum.Juvenil.toString().toUpperCase().equals(cate)){
            cat = Cate_enum.Juvenil;
        }else if(Cate_enum.Senior.toString().toUpperCase().equals(cate)){
            cat = Cate_enum.Senior;
        }else{
            throw new GestorBDEmpresaException("Error: Categoria incorrecte");
        }
        
        Tipus_enum tipus = null;
        
        if(tipus.D.getValue() == Character.toUpperCase(t)){
            tipus =Tipus_enum.D;
        }else if(tipus.H.getValue() == Character.toUpperCase(t)){
            tipus =Tipus_enum.H;
        }else if(tipus.M.getValue() == Character.toUpperCase(t)) {
            tipus = Tipus_enum.M;
        }else{
            throw new GestorBDEmpresaException("Error: El tipus d'equip és incorrecte");
        }
        
        try {
                PreparedStatement ps = c.prepareStatement("INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES (?, ?, ?, ?)");
                ps.setString(1, nom);
                ps.setString(2, ""+t);
                ps.setInt(3, any_eq);
                ps.setInt(4, id_cate);
                ps.execute();
                c.commit();
                System.out.println("Inserit a la BBDD");
            } catch (Exception ex) {
            throw new GestorBDEmpresaException("Error: no es pot inserir l'equip", ex);
        }
            Equip e;
            //select id_equip from equip where lower(nom) like '%ami%' and any_eq = 2024;


        try {

            PreparedStatement pss = c.prepareStatement("select id_equip from equip where lower(nom) like ? and any_eq = ?");
            pss.setString(1, nom.toLowerCase());
            pss.setInt(2, any_eq);
            ResultSet rs = pss.executeQuery();

            while (rs.next()) {
                id_equip = rs.getInt("id_equip");

            }
        }catch(Exception exx){
            throw new GestorBDEmpresaException("Error: No es pot agafar la id de l'equip", exx);
        }

        try {
            System.out.println("Id_equip: "+id_equip);
            e = new Equip(id_equip, nom, tipus, any_eq, cat);
            System.out.println("Equip creat: "+e.toString());
            actualitzar_equips();
            return e;
        } catch (Exception ex) {
            throw new GestorBDEmpresaException("Error: No es pot afegir l'equip", ex);
        }
        
    }

    @Override
    public void afegir_jugadors(Equip e, Jugador j, boolean t) throws GestorBDEmpresaException {
        if(e==null){
            throw new GestorBDEmpresaException("No hi ha cap equip seleccionat");
        }
        if(j == null){
            throw new GestorBDEmpresaException("No hi ha cap jugador seleccionat");
            
        }
        actualitzar_equips();
        System.out.println(e.getId_equip());
        boolean trobat = false;
        char tit;
        if (e.agafar_jugador(j)) {
            System.out.println("Aquest equip ja conté aquest jugador");
            System.out.println(e.getJug_mem().values());
            System.out.println(e.getNum_jugadors());
            return;
        }else{
            System.out.println("Aquest equip no conté aquest jugador");
            System.out.println(e.getNum_jugadors());
            System.out.println(e.getJug_mem().values());
        
            if(t){
                tit='S';
            }else{
                tit = 'N';
            }
             try {

                if(agafar_equip(e.getNom(), e.getAny_eq())==null){//Significa que l'equip no existeix
                    return;
                }
                 System.out.println("dghjk");
                 /*
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
                        jug = new Jugador(
                                id_j, 
                            adreca, any_fi_rev, cog, data_naix, foto, iban, id_legal, nom,
                            sexe, codi_postal, poblacio, provincia, pais
                        );

                        e.afegir_jugador(id_jug, jug, tit);



                        e.mostrar_jugadors();

                        trobat = true;
                    }

                }*/


                try{
                    PreparedStatement pps = c.prepareStatement("INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (?, ?, ?)");
                    System.out.println("ID_equip: "+e.getId_equip() +"Jugador"+ j.getId_jug()+"Titular:"+tit);
                    pps.setInt(1, e.getId_equip());
                    pps.setInt(2, j.getId_jug());
                    pps.setString(3, t?"S":"N");
                    pps.execute();
                    c.commit();
                    System.out.println("Commit efectuat");
                }catch(Exception ex){
                    c.rollback();
                    throw new GestorBDEmpresaException("Molt probablement les dades són incorrectes", ex);
                }
                e.afegir_jugador(j, tit);
                System.out.println("Afegit");


            } catch (SQLException ex) {
                throw new GestorBDEmpresaException("Error en preparar sentència psInsertProduct", ex);
            } catch (Exception ex) {
                System.err.println("Error: " + ex.getMessage());
                throw new GestorBDEmpresaException("Error: Afegir el jugador a l'equip no va, prob un les altres funcions", ex);
            }       
        }           
    }

    @Override
    public void eliminar_equip(Equip e) throws GestorBDEmpresaException {
        
        if(e.getNum_jugadors()>0){
            throw new GestorBDEmpresaException("Error: Aquest equip conté jugadors");
        }else{
            System.out.println("DFGHJKL");
        }
    }

    @Override
    public void eliminar_jugadors(Equip e, Jugador j) throws GestorBDEmpresaException {
        try{
             
            PreparedStatement pps = c.prepareStatement("DELETE FROM MEMBRE\n" +
"WHERE Id_equip_mem = ? AND Id_jug_mem = ?");
            pps.setInt(1, e.getId_equip());
            pps.setInt(2, j.getId_jug());
            pps.execute();
            
            e.eliminar_jugador(j.getId_jug());
            c.commit();
            System.out.println("Eliminat");
            
        } catch (SQLException ex) {
            throw new GestorBDEmpresaException("Error en preparar sentència psInsertProduct", ex);
        } catch (Exception ex) {
            throw new GestorBDEmpresaException("Error: ", ex);
        }       
    }

    @Override
    public void modificar_equip(Equip e) throws GestorBDEmpresaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Jugador> mostrar_jugadors_per_equip() throws GestorBDEmpresaException {
        List<Equip> equips = actualitzar_equips();
        List<Jugador> jugs = new ArrayList<>();
        for (Equip equip : equips) {
            System.out.println("\n"+equip);

            for (Jugador value : equip.getJug_mem().values()) {
                System.out.println("Jug num:"+ value.getId_jug() + "\n\t" + value.toString());
                jugs.add(value);
            }
        }
        return jugs;
    }

    @Override
    public Jugador crear_jugador(String nom, String cog, Sexe_enum sexe, String data_naix, String id_legal, String iban, int any_fi_revisio, String adreca, int codi_postal, String poblacio, String foto, String provincia,String pais) throws GestorBDEmpresaException {
        Jugador j = null;
        int id_jug =0;
        boolean trobat = false;
        List<Jugador> llista = llista_jugadors(sexe, nom);
        
        
        if(agafar_jugador(id_legal, true)==null){
            String sex;
            switch (sexe) {
                case D:
                    sex = "D";
                    break;
                case H:
                    sex = "H";
                    break;
                default:
                    throw new IllegalArgumentException("El sexe ha de ser 'D' o 'H'");
            }

            PreparedStatement pst;
            try {
                pst = c.prepareStatement("INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto) VALUES (?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?, ?, ?, ?, ?, ?, ?)");


                pst.setString(1, nom);
                pst.setString(2, cog);
                pst.setString(3, sex);        
                pst.setString(4, data_naix);
                pst.setString(5, id_legal);
                pst.setString(6, iban);     
                pst.setInt(7, codi_postal);  
                pst.setString(8, poblacio);   
                pst.setString(9, provincia); 
                pst.setString(10, pais);
                pst.setInt(11, any_fi_revisio);
                pst.setString(12, adreca);
                pst.setString(13, foto);
                pst.execute();

                 try {

                    PreparedStatement pss = c.prepareStatement("select id_jug from jugador where upper(id_legal) like ?");

                    pss.setString(1, id_legal.toUpperCase());
                    ResultSet rs = pss.executeQuery();

                    while (rs.next()) {
                        id_jug = rs.getInt("id_jug");
                    }
                    c.commit();
                }catch(Exception exx){
                    throw new GestorBDEmpresaException("Error: No es pot agafar la id de l'equip", exx);
                }

                j = new Jugador(id_jug, adreca, any_fi_revisio, cog, data_naix, foto, iban, id_legal, nom, sexe, codi_postal, poblacio, provincia, pais);

                llista.add(j);
            } catch (SQLException ex) {
                System.out.println("Error: no es pot crear el jugador "+ex.getMessage());
            }
            System.out.println("dfsghjk");
        }else{
            j = agafar_jugador(id_legal, true);
        }
        return j;
        
    }
    
    
    
    
//CAMBIA HO
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public List<Equip> mostrar_equips(String cate, int temp, char tipus, String nom, String ordre) throws GestorBDEmpresaException {
        boolean entrat = false, existeix = false;
        List<Equip> lequips = new ArrayList<>();
        int i = 0;
        String tip = String.valueOf(tipus);
        String qu = "";
        if (cate != null) {
            qu = qu + (entrat ? " AND " : " WHERE ")+"upper(c.nom) like ?";
            entrat = true;
        }
        if (temp != 0) { // Quan es seleccioni cap temporada retornarà 0
            qu = qu + (entrat ? " AND " : " WHERE ")+" e.any_eq = ?";
            entrat = true;
        }
        if (tipus != 'n') {
            qu = qu + (entrat ? " AND " : " WHERE ")+"upper(e.tipus) like ?";
            entrat = true;
        }
        if (nom != null) {
            qu = qu + (entrat ? " AND " : " WHERE ")+"upper(e.nom) like ?";
        }
        try {
            if (!ordre.equalsIgnoreCase("desc")) {
                ordre = "asc"; // Valor per defecte si no és descendent
            }
            String query = "SELECT e.id_equip, e.nom, e.tipus, e.any_eq, c.nom as cate FROM equip e " +
                           "JOIN categoria c ON c.id_cat = e.cate " +
                           qu +
                           " ORDER BY cate " + ordre;

            PreparedStatement ps = c.prepareStatement(query);

            if (cate != null) {
                i++;
                ps.setString(i, "%" + cate.toUpperCase() + "%");
            }
            if (temp != 0) {
                i++;
                ps.setInt(i, temp);
            }
            if (tipus != 'n') {
                i++;
                ps.setString(i, "%" + tip.toUpperCase() + "%");
            }
            if (nom != null) {
                i++;
                ps.setString(i, "%" + nom.toUpperCase() + "%");
            }

            ResultSet rs = ps.executeQuery();
                
            while (rs.next()) {
                existeix = true;
                Tipus_enum tenum = Tipus_enum.valueOf(rs.getString("tipus"));
                int id_equip = rs.getInt("id_equip");
                String nom_e = rs.getString("nom");
                int any_eq = rs.getInt("any_eq");
                Cate_enum cat = Cate_enum.valueOf(rs.getString("cate"));

                System.out.println("Id_equip: " + id_equip + " Nom: " + nom_e + " Tipus: " + tenum + " Any de l'equip: " + any_eq + " Categoria: " + cat);
                Equip eq = new Equip(id_equip, nom_e, tenum, any_eq, cat);
                lequips.add(eq);
            }
            if(!existeix){
                System.out.println("No existeix");
            }
        } catch (SQLException ex) {
            throw new GestorBDEmpresaException("Error en el prepared statement", ex);
        } catch (Exception ex) {
            throw new GestorBDEmpresaException("Error general", ex);
        }

        return lequips;
    }
    @Override
    public List<Equip> actualitzar_equips() throws GestorBDEmpresaException {
        List<Equip> lequips = new ArrayList<>();
        Statement q = null;
        try {
            q = c.createStatement();
            String query = "select id_equip, nom, tipus, any_eq, cate from equip";
            ResultSet rs = q.executeQuery(query);

            while (rs.next()) {
                int num =0;
                Tipus_enum tenum;
                String tipus = rs.getString("tipus");
                if (Tipus_enum.D.toString().equals(tipus)) {
                    tenum = Tipus_enum.D;
                } else if (Tipus_enum.M.toString().equals(tipus)) {
                    tenum = Tipus_enum.M;
                } else {
                    tenum = Tipus_enum.H;
                }

                int id_equip = rs.getInt("id_equip");
                String nom = rs.getString("nom");
                int any_eq = rs.getInt("any_eq");
                String cate = rs.getString("cate");

                Cate_enum cat = null;
                if (Cate_enum.Alevi.toString().equals(cate)) {
                    cat = Cate_enum.Alevi;
                } else if (Cate_enum.Benjami.toString().equals(cate)) {
                    cat = Cate_enum.Benjami;
                } else if (Cate_enum.Cadet.toString().equals(cate)) {
                    cat = Cate_enum.Cadet;
                } else if (Cate_enum.Infantil.toString().equals(cate)) {
                    cat = Cate_enum.Infantil;
                } else if (Cate_enum.Juvenil.toString().equals(cate)) {
                    cat = Cate_enum.Juvenil;
                } else if (Cate_enum.Senior.toString().equals(cate)) {
                    cat = Cate_enum.Senior;
                }

                Equip eq = new Equip(id_equip, nom, tenum, any_eq, cat);
                
                
                 try {
                    PreparedStatement ps = c.prepareStatement("select * from membre where id_equip_mem = ?");
                    ps.setInt(1, eq.getId_equip());
                    
                    ResultSet rss = ps.executeQuery();

                    while (rss.next()) {
                        

                        Jugador j = agafar_jugador(rss.getString("id_jug_mem"), false);
                        char titular = rss.getString("titular").charAt(0);
                        eq.afegir_jugador(j, titular);
                        llista_jugs_eq.add(j);
                        num++;
                    }
                    if(num >0){
                        eq.setNum_jugadors(num);

                    }
                    lequips.add(eq);
                 }catch(SQLException ex) {
                    throw new GestorBDEmpresaException("Error en el select de jugadors de l'equip", ex);
                } catch (Exception ex) {
                    throw new GestorBDEmpresaException("Actualitzar els usuaris dins de l'equip fan una excepció: ", ex);
                } 
            }
            llista_equips = lequips;
            return lequips;
        } catch (SQLException ex) {
            throw new GestorBDEmpresaException("Error en el select d'equips", ex);
        } catch (Exception ex) {
            throw new GestorBDEmpresaException("Es fa una excepció en l'agregació de l'equip: ", ex);
        } 
    }

    @Override
    public void modificar_jugador(Jugador j) throws GestorBDEmpresaException {
        
    }

    @Override
    public Jugador agafar_jugador(String idLegal, Boolean esLegal) throws GestorBDEmpresaException {
        String where = null;
        int id_j = 0;
        if(esLegal){
            where = "where upper(id_legal) like ?";
        }else{
            where = "where id_jug = ?";
            id_j = Integer.parseInt(idLegal);
            
        }
        
        Jugador j = null;
        try {
            
            PreparedStatement ps = c.prepareStatement("select ID_JUG ,NOM ,COGNOMS,SEXE,DATA_NAIX ,ID_LEGAL ,IBAN,ANY_FI_REVISIO_MEDICA ,ADRECA ,CODI_POSTAL ,POBLACIO ,FOTO ,PROVINCIA, PAIS from jugador "+where+" order by id_jug ");            
            if(esLegal){
                ps.setString(1, idLegal);
            }else{
                ps.setInt(1, id_j);
            }
            
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
                j = new Jugador(
                    id_jug, adreca, any_fi_rev, cog, data_naix, foto, iban, id_legal, nom,
                    sexe, codi_postal, poblacio, provincia, pais
                );
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GestorBDEmpresaJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
            return j;
    }

}
