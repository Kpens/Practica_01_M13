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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.digest.DigestUtils;



/**
 *
 * @author Alma
 */
public class GestorBDEmpresaJdbc implements IGestorBDEmpresa{

    private Connection c;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
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

    
    @Override
    public void tancarCapa() throws GestorBDEmpresaException {
        if (c != null) {
            try {
                c.rollback();
            } catch (SQLException ex) {
                throw new GestorBDEmpresaException("Error en fer rollback final.", ex);
            }
            try {
                c.close();
            } catch (SQLException ex) {
                throw new GestorBDEmpresaException("Error en tancar la connexió.\n", ex);
            }
        }
    }

    
    @Override
    public Equip agafar_equip(String nom, int temporada) throws GestorBDEmpresaException {
        Equip eq = null;
        boolean existeix=false;
        nom = nom.toUpperCase();
        try {
            ps = c.prepareStatement("select e.id_equip, e.nom, e.tipus, e.any_eq , c.nom as cate\n" +
"from equip e \n" +
"join categoria c on c.id_cat = e.cate\n" +
"where upper(e.nom) like ? and any_eq = ?");
                    
            ps.setString(1, nom);
            
            ps.setInt(2, temporada);
            rs = ps.executeQuery();



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
                String cate = rs.getString("cate").toUpperCase();
                Cate_enum cat =null;
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
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                throw new GestorBDEmpresaException("Error no s'han pogut tancar: ");
            }
        }
        
    }

    @Override
    public List<Jugador> llista_jugadors_ant(Sexe_enum sexe_j, String nom_j) throws GestorBDEmpresaException {
        List<Jugador> ljugs = new ArrayList<>();
        boolean entrat=false;
        char s;
        if(sexe_j == Sexe_enum.D){
            s='D';
        }else if(sexe_j == Sexe_enum.H){
            s='H';
        }else{
            s='%';
        }
        try {
            ps = c.prepareStatement("select ID_JUG ,NOM ,COGNOMS,SEXE,DATA_NAIX ,ID_LEGAL ,IBAN,ANY_FI_REVISIO_MEDICA ,ADRECA ,CODI_POSTAL ,POBLACIO ,FOTO ,PROVINCIA, PAIS from jugador where upper(sexe) like ? and upper(nom) like ? order by id_jug ");
            
            ps.setString(1, "%"+s+"%");
            
            if (nom_j==null||nom_j.equals("") || nom_j.equals(" ") ){
                ps.setString(2, "%%");
            }else{
                ps.setString(2, "%"+nom_j.toUpperCase()+"%");
            }
            rs = ps.executeQuery();

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
                String codi_postal = rs.getString("CODI_POSTAL");
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
        }  finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                throw new GestorBDEmpresaException("Error no s'han pogut tancar: ");
            }
        }
        
        return ljugs;
        
        
    }

    @Override
    public void login(String login, String contra) throws GestorBDEmpresaException {
        boolean correcte = false;
        Statement st = null;
        try {
            st = c.createStatement();

            List<Usuari> usu = new ArrayList<>();
            String query = "SELECT * FROM usuari";
            rs = st.executeQuery(query);

            contra = "SHA-1" + DigestUtils.sha1Hex(contra);

            while (rs.next()) {
                String log = rs.getString("login");
                String nom = rs.getString("nom_usu");
                String pwd = rs.getString("password");

                if (!pwd.contains("SHA-1")) {
                    pwd = "SHA-1" + DigestUtils.sha1Hex(pwd);
                }

                ps = c.prepareStatement("UPDATE usuari SET password = ? WHERE upper(login) LIKE ?");
                ps.setString(1, pwd);
                ps.setString(2, log.toUpperCase());
                ps.executeUpdate(); // Canviem a executeUpdate()

                Usuari u = new Usuari(log, nom, pwd);
                usu.add(u);
            }

            for (Usuari usuari : usu) {
                if (usuari.getLogin().equals(login) && usuari.getPassword().equals(contra)) {
                    correcte = true;
                }
            }
            if (!correcte) {
                throw new GestorBDEmpresaException("Nom d'usuari o contrasenya incorrectes.");
            }
            c.commit();
        } catch (Exception ex) {
            try {
                c.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorBDEmpresaJdbc.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new GestorBDEmpresaException("Nom d'usuari o contrasenya incorrectes.", ex);
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                throw new GestorBDEmpresaException("Error no s'han pogut tancar: ");
            }
        }
    }


    @Override
    public Equip crear_equip(String nom, char t, int any_eq, String cate) throws GestorBDEmpresaException {
        actualitzar_equips();
        
        for (Equip eq : llista_equips) {
            if(eq.getNom().toUpperCase().equals(nom.toUpperCase()) && eq.getAny_eq() == any_eq ){
                throw new GestorBDEmpresaException("Aquest equip ja existeix");
            }
        }
        
        int id_cate=0;
        int id_equip =-1;
        Categoria catee;
        Cate_enum cat =null;
        cate = cate.toUpperCase();
        System.out.println("cate  "+cate);
        PreparedStatement p=null;
        try {
            p = c.prepareStatement("select id_cat from categoria where upper(nom) like ?");
            p.setString(1, "%"+cate+"%");
            
            rs = p.executeQuery();
            while(rs.next()){
                id_cate = rs.getInt("id_cat");
                System.out.println("ID:" + id_cate);
            }
                        
        } catch (SQLException ex) {
            throw new GestorBDEmpresaException("Error no es pot seleccionar la id de la categoria especificada. ", ex);
        }finally {
            try {
                if (p != null) p.close();
                if (rs != null) rs.close();
            } catch (SQLException se) {
                throw new GestorBDEmpresaException("Error no s'han pogut tancar: ");
            }
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
            ps = c.prepareStatement("INSERT INTO Equip (nom, tipus, any_eq, cate) VALUES (?, ?, ?, ?)");
            ps.setString(1, nom);
            ps.setString(2, ""+t);
            ps.setInt(3, any_eq);
            ps.setInt(4, id_cate);
            ps.executeUpdate();
            c.commit();
            System.out.println("Inserit a la BBDD");
        } catch (Exception ex) {
            throw new GestorBDEmpresaException("Error: no es pot inserir l'equip", ex);
        }finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException se) {
                                throw new GestorBDEmpresaException("Error no s'han pogut tancar: ");
            }
        }
            Equip e;
            //select id_equip from equip where lower(nom) like '%ami%' and any_eq = 2024;


            PreparedStatement pss = null;
        try {
            pss  = c.prepareStatement("select id_equip from equip where lower(nom) like ? and any_eq = ?");
            pss.setString(1, nom.toLowerCase());
            pss.setInt(2, any_eq);
            rs = pss.executeQuery();

            while (rs.next()) {
                id_equip = rs.getInt("id_equip");

            }
        }catch(Exception exx){
            throw new GestorBDEmpresaException("Error: No es pot agafar la id de l'equip", exx);
        }finally {
            try {
                if (rs != null) rs.close();
                if (pss != null) pss.close();
            } catch (SQLException se) {
                throw new GestorBDEmpresaException("Error no s'han pogut tancar: ");
            }
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
            System.out.println(e.getJug_mem().size());
            return;
        }else{
        
            if(t){
                tit='S';
            }else{
                tit = 'N';
            }
             try {

                if(agafar_equip(e.getNom(), e.getAny_eq())==null){//Significa que l'equip no existeix
                    return;
                }
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
                    ps = c.prepareStatement("INSERT INTO MEMBRE (Id_equip_mem, Id_jug_mem, titular) VALUES (?, ?, ?)");
                    System.out.println("ID_equip: "+e.getId_equip() +"Jugador"+ j.getId_jug()+"Titular:"+tit);
                    ps.setInt(1, e.getId_equip());
                    ps.setInt(2, j.getId_jug());
                    ps.setString(3, t?"S":"N");
                    ps.executeUpdate();
                    c.commit();
                    System.out.println("Jugador afegit a l'equip");
                }catch(Exception ex){
                    c.rollback();
                    throw new GestorBDEmpresaException("Dades són incorrectes", ex);
                }finally {
                    try {
                        if (ps != null) ps.close();
                    } catch (SQLException se) {
                        throw new GestorBDEmpresaException("Error no s'han pogut tancar: ");
                    }
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
        actualitzar_equips();
        if(!e.getJug_mem().isEmpty()){
            throw new GestorBDEmpresaException("Error: Aquest equip conté jugadors");
        }else{
            try {
                ps = c.prepareStatement("DELETE FROM equip\n" +
                        "WHERE id_equip = ?");
                ps.setInt(1, e.getId_equip());
                ps.executeUpdate();
                c.commit();
                System.out.println("Eliminat");

            } catch (SQLException ex) {
                Logger.getLogger(GestorBDEmpresaJdbc.class.getName()).log(Level.SEVERE, null, ex);
            }finally {
                try {
                    if (ps != null) ps.close();
                } catch (SQLException se) {
                    throw new GestorBDEmpresaException("Error no s'han pogut tancar: ");
                }
            }
        }
    }

    @Override
    public void eliminar_jugadors_de_l_equip(Equip e, Jugador j, boolean tots) throws GestorBDEmpresaException {
        actualitzar_equips();
        try{
            String jug = "";
             if(!tots){
                 jug =" AND Id_jug_mem = ?";
             }
            ps = c.prepareStatement("DELETE FROM MEMBRE\n" +
"WHERE Id_equip_mem = ?"+jug);
            ps.setInt(1, e.getId_equip());
            if(!tots){
                 ps.setInt(2, j.getId_jug());
            }
            
            ps.executeUpdate();
            
            if(!tots){
                e.eliminar_jugador(j.getId_jug());
            }else{
                e.eliminar_jugadors_de_l_equip();
            }            
            c.commit();
            actualitzar_equips();
            System.out.println("Eliminat");
            
        } catch (SQLException ex) {
            throw new GestorBDEmpresaException("Error en preparar sentència psInsertProduct", ex);
        } catch (Exception ex) {
            throw new GestorBDEmpresaException("Error: ", ex);
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException se) {
                throw new GestorBDEmpresaException("Error no s'han pogut tancar: ");
            }
        }      
    }
/*
    @Override
    public Equip modificar_equip(Equip e) throws GestorBDEmpresaException {
        
        Equip ant_eq = null;
        Map <Integer, Jugador> jug_mem = new HashMap<>();
        Map <Integer, Character> jug_mem_titular = new HashMap<>();
        boolean existeix = false, te_jugs = false;
        actualitzar_equips();
        for (Equip eq : llista_equips) {
            if(eq.getId_equip() == e.getId_equip()){
                ant_eq = eq;
            }
        }
        
        existeix= (ant_eq != null);
        te_jugs = (ant_eq.getNum_jugadors()>0);
        if(existeix){
            //UPDATE Jugador SET nom = 'NouNom', cognoms = 'NousCognoms', IBAN = 'ES9121000418450200051332', codi_postal = 08700, poblacio = 'NovaPoblacio', provincia = 'NovaProvincia', pais = 'NouPais', any_fi_revisio_medica = 2026, adreca = 'C/ nova adreça', foto = 'C:\nova_ruta\persona.jpg' WHERE id_legal = 'ID02';
            char t;
            List<Jugador> jugs_eq = null;
            
            if(te_jugs){
                for (Integer key : ant_eq.getJug_mem().keySet()) {
                    jug_mem.put(key, ant_eq.getJug_mem().get(key));
                    jug_mem_titular.put(key, ant_eq.getJug_mem_titular().get(key));
                }
            }
            eliminar_equip(ant_eq);
            actualitzar_equips();
            Equip eee = crear_equip(e.getNom(), e.getTipus().toString().charAt(0), e.getAny_eq(), e.getCate().toString());
            if(te_jugs){
                for (Integer key : jug_mem.keySet()) {
                    boolean tit;
                    if(ant_eq.getJug_mem_titular().get(key).equals('S')){
                        tit = true;
                    }else{
                        tit=false;
                    }
                    afegir_jugadors(eee, ant_eq.getJug_mem().get(key), tit);
                }
            }
            
            //actualitzar_equips();
            return e;
        }else{
            throw new GestorBDEmpresaException("L'equip no existeix");
        }
    
    }

    */
    @Override
    public List<Jugador> mostrar_jugadors_per_equip(int id_equip) throws GestorBDEmpresaException {
        List<Equip> equips = actualitzar_equips();
        List<Jugador> jugs = new ArrayList<>();
        if(id_equip==-1){
            for (Equip equip : equips) {
                System.out.println("\n"+equip);

                for (Jugador value : equip.getJug_mem().values()) {
                    System.out.println("Jug num:"+ value.getId_jug() + "\n\t" + value.toString());
                    jugs.add(value);
                }
            } 
        }else{
            for (Equip equip : equips) {
                if(equip.getId_equip()==id_equip){
                    for (Jugador value : equip.getJug_mem().values()) {
                        //System.out.println("Jug num:"+ value.getId_jug() + "\n\t" + value.toString());
                        jugs.add(value);
                    }
                }
            }
        }
        
        return jugs;
    }

    @Override
    public Jugador crear_jugador(String nom, String cog, Sexe_enum sexe, String data_naix, String id_legal, String iban, int any_fi_revisio, String adreca, String codi_postal, String poblacio, String foto, String provincia,String pais) throws GestorBDEmpresaException {
        Jugador j = null;
        int id_jug =0;
        boolean trobat = false;
        /*
        List<Jugador> llista = llista_jugadors_ant(sexe, nom);
        if(llista_jugadors_ant(sexe, nom) == null){
            llista = new ArrayList<>();
        }*/
        
        
        if(agafar_jugador(id_legal, true)==null){
            
            if(!Jugador.nif_valid(id_legal)){
                throw new GestorBDEmpresaException("NIF incorrecte");
            }
            if(!Jugador.iban_valid(iban)){
                throw new GestorBDEmpresaException("Iban incorrecte");
            }
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

            try {
                ps = c.prepareStatement("INSERT INTO Jugador (nom, cognoms, sexe, data_naix, id_legal, IBAN, codi_postal, poblacio, provincia, pais, any_fi_revisio_medica, adreca, foto) VALUES (?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?, ?, ?, ?, ?, ?, ?)");


                ps.setString(1, nom);
                ps.setString(2, cog);
                ps.setString(3, sex);        
                ps.setString(4, data_naix);
                ps.setString(5, id_legal);
                ps.setString(6, iban);     
                ps.setString(7, codi_postal);  
                ps.setString(8, poblacio);   
                ps.setString(9, provincia); 
                ps.setString(10, pais);
                ps.setInt(11, any_fi_revisio);
                ps.setString(12, adreca);
                ps.setString(13, foto);
                ps.executeUpdate();

                PreparedStatement pss= null;
                try {

                    pss = c.prepareStatement("select id_jug from jugador where upper(id_legal) like ?");

                    pss.setString(1, id_legal.toUpperCase());
                    rs = pss.executeQuery();

                    while (rs.next()) {
                        id_jug = rs.getInt("id_jug");
                    }
                    c.commit();
                }catch(Exception exx){
                    throw new GestorBDEmpresaException("Error: No es pot agafar la id de l'equip", exx);
                }finally {
                    try {
                        if (pss != null) pss.close();
                        if (rs != null) rs.close();
                    } catch (SQLException se) {
                        throw new GestorBDEmpresaException("Error no s'han pogut tancar: ");
                    }
                }   

                try {
                    j = new Jugador(id_jug, adreca, any_fi_revisio, cog, data_naix, foto, iban, id_legal, nom, sexe, codi_postal, poblacio, provincia, pais);
                    System.out.println("Creat");
                } catch (Exception ex) {
                    System.out.println("No es pot afegir el nou jugador");
                }

                //llista.add(j);
            } catch (SQLException ex) {
                System.out.println("Error: no es pot crear el jugador "+ex.getMessage());
            }finally {
                try {
                    if (ps != null) ps.close();
                } catch (SQLException se) {
                    throw new GestorBDEmpresaException("Error no s'han pogut tancar: ");
                }
            }   
        }else{
            System.out.println("Ja existeix");
            j = agafar_jugador(id_legal, true);
        }
        return j;
        
    }
    
    
  
    @Override
    public List<Equip> llistar_equips(String cate, int temp, char tipus, String nom, String ordre) throws GestorBDEmpresaException {
        actualitzar_equips();//Entenc que no fa res
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

            ps = c.prepareStatement(query);

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

            rs = ps.executeQuery();
                
            while (rs.next()) {
                existeix = true;
                Tipus_enum tenum = Tipus_enum.valueOf(rs.getString("tipus"));
                int id_equip = rs.getInt("id_equip");
                String nom_e = rs.getString("nom");
                int any_eq = rs.getInt("any_eq");
                Cate_enum cat = Cate_enum.valueOf(rs.getString("cate"));

                //System.out.println("Id_equip: " + id_equip + " Nom: " + nom_e + " Tipus: " + tenum + " Any de l'equip: " + any_eq + " Categoria: " + cat);
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
        }finally {
            try {
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException se) {
                throw new GestorBDEmpresaException("Error no s'han pogut tancar: ");
            }
        }   

        aff_jugador_al_map_d_equip(lequips);
        return lequips;
    }
    @Override
    public List<Equip> actualitzar_equips() throws GestorBDEmpresaException {
        List<Equip> lequips = new ArrayList<>();
        Statement q = null;
        llista_equips = null;
        try {
            System.out.println("ab statement");
            q = c.createStatement();
            String query = "select id_equip, nom, tipus, any_eq, cate from equip";
            rs = q.executeQuery(query);

            System.out.println("ab rs");
            while (rs.next()) {
                /*int num =0;*/
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
                
                lequips.add(eq);
            }
            System.out.println("desp rs");
            System.out.println("ab aff jug al map");
            aff_jugador_al_map_d_equip(lequips);
            System.out.println("desp aff al map");
            llista_equips = lequips;
            return lequips;
        } catch (SQLException ex) {
            throw new GestorBDEmpresaException("Error en el select d'equips", ex);
        } catch (Exception ex) {
            throw new GestorBDEmpresaException("Es fa una excepció en l'agregació de l'equip: ", ex);
        } finally {
            try {
                if (q != null) q.close();
                if (rs != null) rs.close();
            } catch (SQLException se) {
                System.out.println("Casi");
            }
        }
    }
    /*
    *   Els afegeixo externament perquè és més útil en el gestor d'equips (m'asseguro que tenen tots els jugadors)
    */
    void aff_jugador_al_map_d_equip(List<Equip> equip) throws GestorBDEmpresaException{
        try {
            for (Equip eq : equip) {
                ps = c.prepareStatement("select titular, ID_JUG_MEM, ID_EQUIP_MEM from membre where ID_EQUIP_MEM = ?");
                ps.setInt(1, eq.getId_equip());

                rs = ps.executeQuery();
                int i=0;
                while (rs.next()) {

                    Jugador j = agafar_jugador(rs.getString("id_jug_mem"), false);
                    if(!eq.getJug_mem().containsKey(j.getId_jug())){
                        String titular = rs.getString("titular");
                        eq.afegir_jugador(j, titular.charAt(0));
                        llista_jugs_eq.add(j);
                        i++; 
                    }
                }
            }
        }catch(SQLException ex) {
            throw new GestorBDEmpresaException("Error en el select de jugadors de l'equip", ex);
        } catch (Exception ex) {
            throw new GestorBDEmpresaException("Actualitzar els usuaris dins de l'equip fan una excepció: ", ex);
        }  finally {
            try {
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException se) {
                throw new GestorBDEmpresaException("Error no s'han pogut tancar: ");
            }
        }
        
    }

    @Override
    public List<Equip> equips_on_son_els_jug(int id_jug) throws GestorBDEmpresaException{
        List<Equip> equips = new ArrayList<>();
        /*
        select e.* from equip e 
join membre m on m.id_equip_mem = e.id_equip
where m.id_jug_mem = 1
        
        */
        
         try {
            ps = c.prepareStatement("select e.id_equip,e.nom, e.tipus, e.any_eq, c.nom as cate \n" +
"from equip e\n" +
"join membre m on m.id_equip_mem = e.id_equip\n" +
"join categoria c on c.id_cat = e.cate\n" +
"where m.id_jug_mem =?");
            ps.setInt(1, id_jug);

            rs = ps.executeQuery();
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
                }else{
                    cat = Cate_enum.Senior;
                }
                Equip eq = new Equip(id_equip, nom_e, tenum, any_eq, cat);
                
                equips.add(eq);
                
            }
             aff_jugador_al_map_d_equip(equips);
             
        }catch(SQLException ex) {
            throw new GestorBDEmpresaException("Error en el select dels equips del jugador", ex);
        } catch (Exception ex) {
            throw new GestorBDEmpresaException("Error: ", ex);
        }  finally {
            try {
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException se) {
                throw new GestorBDEmpresaException("Error no s'han pogut tancar: ");
            }
        }
        
        
        return equips;
    }
    
    @Override
    public Tipus_enum equip_mes_restrictiu_del_jug(int id_jug) throws GestorBDEmpresaException{
        
        List<Equip> equips= equips_on_son_els_jug(id_jug);
        for (Equip equip : equips) {
            if(equip.getTipus().getValue() =='D'){
                return equip.getTipus();
            }else if(equip.getTipus().getValue() =='H'){
                return equip.getTipus();
            }
        }
        
        return Tipus_enum.M;
    }
    @Override
    public Jugador modificar_jugador(Jugador j) throws GestorBDEmpresaException {
        if(agafar_jugador(j.getId_legal(), true) != null){
            //UPDATE Jugador SET nom = 'NouNom', cognoms = 'NousCognoms', IBAN = 'ES9121000418450200051332', codi_postal = 08700, poblacio = 'NovaPoblacio', provincia = 'NovaProvincia', pais = 'NouPais', any_fi_revisio_medica = 2026, adreca = 'C/ nova adreça', foto = 'C:\nova_ruta\persona.jpg' WHERE id_legal = 'ID02';
            Jugador jug=null;
            try {
                ps = c.prepareStatement("UPDATE Jugador SET nom = ?, cognoms = ?, IBAN = ?, codi_postal = ?, poblacio = ?, provincia = ?, pais = ?, any_fi_revisio_medica = ?, adreca = ?, foto = ?, sexe=?, data_naix = TO_DATE(?, 'YYYY-MM-DD') WHERE id_legal = ?");

                ps.setString(1, j.getNom());
                ps.setString(2, j.getCog());
                ps.setString(3, j.getIban());   
                ps.setString(4, j.getCodi_postal());  
                ps.setString(5, j.getPoblacio());        
                ps.setString(6, j.getProvincia());
                ps.setString(7, j.getPais());
                ps.setInt(8, j.getAny_fi_rev());  
                ps.setString(9, j.getAdreca()); 
                ps.setString(10, j.getFoto());  
                ps.setString(11, j.getSexe().toString());  
                ps.setString(12, j.getData_naix());  
                ps.setString(13, j.getId_legal());  
                int rowsAffected =ps.executeUpdate();
                
                if (rowsAffected > 0) {
                    c.commit();
                    System.out.println("Jugador modificat");
                } else {
                    System.out.println("No s'ha trobat");
                }
                
            } catch (SQLException ex) {
                System.out.println("Error: no es pot crear el jugador "+ex.getMessage());
            }finally {
                try {
                    if (ps != null) ps.close();
                } catch (SQLException se) {
                    throw new GestorBDEmpresaException("Error no s'han pogut tancar: ");
                }
            }
            
            try {
                jug = new Jugador(j.getId_jug(), j.getAdreca(), j.getAny_fi_rev(), j.getCog(), j.getData_naix(), j.getFoto(), j.getIban(), j.getId_legal(), j.getNom(), j.getSexe(), j.getCodi_postal(), j.getPoblacio(), j.getProvincia(), j.getPais());
            } catch (Exception ex) {
               throw new GestorBDEmpresaException("Les dades són incorrectes");
            }

            actualitzar_equips();
            return jug;
        }else{
            throw new GestorBDEmpresaException("El jugador no existeix");
        }
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
        PreparedStatement pss=null;
        ResultSet rss=null;
        try {
            
            pss = c.prepareStatement("select ID_JUG ,NOM ,COGNOMS,SEXE,DATA_NAIX ,ID_LEGAL ,IBAN,ANY_FI_REVISIO_MEDICA ,ADRECA ,CODI_POSTAL ,POBLACIO ,FOTO ,PROVINCIA, PAIS from jugador "+where+" order by id_jug ");            
            if(esLegal){
                pss.setString(1, idLegal);
            }else{
                pss.setInt(1, id_j);
            }
            
            rss = pss.executeQuery();

            while(rss.next()){
                int id_jug = rss.getInt("ID_JUG");
                String nom = rss.getString("NOM");
                String cog = rss.getString("COGNOMS");
                String sexeStr = rss.getString("SEXE");
                Sexe_enum sexe = Sexe_enum.valueOf(sexeStr); // Assumint que el sexe és D, M o H.
                String data_naix = rss.getString("DATA_NAIX");
                String id_legal = rss.getString("ID_LEGAL");
                String iban = rss.getString("IBAN");
                int any_fi_rev = rss.getInt("ANY_FI_REVISIO_MEDICA");
                String adreca = rss.getString("ADRECA");
                String codi_postal = rss.getString("CODI_POSTAL");
                String poblacio = rss.getString("POBLACIO");
                String foto = rss.getString("FOTO");
                String provincia = rss.getString("PROVINCIA");
                String pais = rss.getString("PAIS");

                try {
                    // Creació del jugador
                    j = new Jugador(
                            id_jug, adreca, any_fi_rev, cog, data_naix, foto, iban, id_legal, nom,
                            sexe, codi_postal, poblacio, provincia, pais
                    );
                } catch (Exception ex) {
                    throw new GestorBDEmpresaException("Dades incorrectes, probablement el iban o el nif");
                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GestorBDEmpresaJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                if (pss != null) pss.close();
                if (rss != null) rss.close();
            } catch (SQLException se) {
                throw new GestorBDEmpresaException("Error no s'han pogut tancar: ");
            }
        }
        return j;
    }

    @Override
    public List<Jugador> llista_jugadors(Sexe_enum sexe_j, String nom_j, String nif, String data_naix_j, Cate_enum cate, boolean rev_feta) throws GestorBDEmpresaException {
        List<Jugador> ljugs = new ArrayList<>();
        boolean entrat=false;
        char s;
        if(sexe_j == Sexe_enum.D){
            s='D';
        }else if(sexe_j == Sexe_enum.H){
            s='H';
        }else{
            s='%';
        }

        
        try {
            
            /*SELECT ID_JUG, NOM, COGNOMS, SEXE, DATA_NAIX, ID_LEGAL, IBAN, ANY_FI_REVISIO_MEDICA, ADRECA, CODI_POSTAL, POBLACIO, FOTO, PROVINCIA, PAIS,EXTRACT(YEAR FROM DATA_NAIX) AS anny FROM jugador
WHERE UPPER(SEXE) LIKE 'H' AND UPPER(NOM) LIKE '%' AND UPPER(ID_LEGAL) LIKE '%'AND UPPER(DATA_NAIX) LIKE '%' ORDER BY ID_JUG;
            
            */
            
            ps = c.prepareStatement("SELECT ID_JUG, NOM, COGNOMS, SEXE, DATA_NAIX, ID_LEGAL, IBAN, ANY_FI_REVISIO_MEDICA, ADRECA, CODI_POSTAL, POBLACIO, FOTO, PROVINCIA, PAIS,EXTRACT(YEAR FROM DATA_NAIX) AS anny FROM jugador\n" +
"WHERE UPPER(SEXE) LIKE ? AND UPPER(NOM) LIKE ? AND UPPER(ID_LEGAL) LIKE ? AND TO_CHAR(data_naix, 'YYYY-MM-DD') LIKE ? AND ANY_FI_REVISIO_MEDICA  "+(rev_feta ? ">= ?" : "< ?")+" ORDER BY cognoms ");
            
            ps.setString(1, "%"+s+"%");
            
            if(!nom_j.equals("") || !nom_j.equals(" ") ){
                ps.setString(2, "%"+nom_j.toUpperCase()+"%");
            }else{
                ps.setString(2, "%%");
                
            }
            if(!nif.equals("") || !nif.equals(" ") ){
                ps.setString(3, "%"+nif.toUpperCase()+"%");
            }else{
                ps.setString(3, "%");
                
            }
            if(!data_naix_j.equals("") || !data_naix_j.equals(" ") ){
                ps.setString(4, "%"+data_naix_j.toUpperCase()+"%");
            }else{
                ps.setString(4, "%");
                
            }
            ps.setInt(5, LocalDate.now().getYear());
            
            
            
            rs = ps.executeQuery();

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
                String codi_postal = rs.getString("CODI_POSTAL");
                String poblacio = rs.getString("POBLACIO");
                String foto = rs.getString("FOTO");
                String provincia = rs.getString("PROVINCIA");
                String pais = rs.getString("PAIS");
                int temp = rs.getInt("anny");
                int edat = (LocalDate.now().getYear())-temp;
        
                System.out.println("L'edat de "+nom+" "+cog+" actual és: " + (edat));
                
                
                if(cate == null){
                    Jugador jug = new Jugador(
                        id_jug, adreca, any_fi_rev, cog, data_naix, foto, iban, id_legal, nom,
                        sexe, codi_postal, poblacio, provincia, pais
                    );

                    ljugs.add(jug);
                }else{
                    if(cate.getEdatMinima()<=edat && edat <= cate.getEdatMaxima()){
                    
                        Jugador jug = new Jugador(
                            id_jug, adreca, any_fi_rev, cog, data_naix, foto, iban, id_legal, nom,
                            sexe, codi_postal, poblacio, provincia, pais
                        );

                        ljugs.add(jug);
                    }
                }
                
            }
            if(entrat==false){
                return null;
            }

        } catch (SQLException ex) {
            throw new GestorBDEmpresaException("Error en preparar sentència select", ex);
        } catch (Exception ex) {
            throw new GestorBDEmpresaException("Error: ", ex);
        }finally {
            try {
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException se) {
                throw new GestorBDEmpresaException("Error no s'han pogut tancar: ");
            }
        }
        
        return ljugs;
        
        
    }

    @Override
    public Temporada crear_temporada(int anny) throws GestorBDEmpresaException {
        Temporada temp=null;
        
        try {
            ps = c.prepareStatement("SELECT * from temporada where anny like ?");
            ps.setInt(1, anny);

            rs = ps.executeQuery();

            if(!rs.next()){
                PreparedStatement pss = c.prepareStatement("INSERT INTO Temporada (anny) VALUES (?)");
                pss.setInt(1, anny);

                pss.executeUpdate();
                c.commit();
                System.out.println("Temporada creada");
            }

        }catch(SQLException ex){
            throw new GestorBDEmpresaException("Error en buscar les temporades");
        }finally {
            try {
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException se) {
                throw new GestorBDEmpresaException("Error no s'han pogut tancar: ");
            }
        }
        return temp;
    }

    @Override
    public List<Temporada> llista_temporades() throws GestorBDEmpresaException {
        Statement st = null;
        try {
            st = c.createStatement();

            List<Temporada> temp = new ArrayList<>();
            String query = "select * from temporada";
            rs = st.executeQuery(query);


            while (rs.next()) {
                temp.add(new Temporada(rs.getInt("anny")));
            }

        return temp;
        } catch (Exception ex) {
            try {
                c.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorBDEmpresaJdbc.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new GestorBDEmpresaException("No es pot trobar la temporada", ex);
        }finally {
            try {
                if (st != null) st.close();
                if (rs != null) rs.close();
            } catch (SQLException se) {
                throw new GestorBDEmpresaException("Error no s'han pogut tancar: ");
            }
        }
    }

    @Override
    public void eliminar_jugador(int id_jug) throws GestorBDEmpresaException {
        actualitzar_equips();
        if(!equips_on_son_els_jug(id_jug).isEmpty()){
            throw new GestorBDEmpresaException("Error: Aquest jugador està en un equip");
        }else{
            try {
                ps = c.prepareStatement("delete from jugador where id_jug = ?");
                ps.setInt(1, id_jug);
                ps.executeUpdate();
                c.commit();
                System.out.println("Eliminat");

            } catch (SQLException ex) {
                Logger.getLogger(GestorBDEmpresaJdbc.class.getName()).log(Level.SEVERE, null, ex);
            }finally {
                try {
                    if (ps != null) ps.close();
                } catch (SQLException se) {
                    throw new GestorBDEmpresaException("Error no s'han pogut tancar: ", se);
                }
            }
        }
    }
}
