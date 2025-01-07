
import CapaPers.GestorBDEmpresaJdbc;
import Classes.Equip;
import Classes.Jugador;
import Classes.Temporada;
import Classes.Usuari;
import Enums.Cate_enum;
import Enums.Sexe_enum;
import Enums.Tipus_enum;
import Persistencia.GestorBDEmpresaException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Alma
 */
public class Test {
    private static GestorBDEmpresaJdbc gBD;

    public static void main(String[] args) throws GestorBDEmpresaException {
        List<Equip> eqs = null;
        List<Jugador> jugs = null;

        // 1. Crear gBD invocant el constructor.
        try {
            System.out.println("Intent de creació de la capa...");
            gBD = new GestorBDEmpresaJdbc();
            System.out.println("Capa de persistència creada");
            System.out.println("");
        } catch (GestorBDEmpresaException ex) {
            ex.printStackTrace();
            System.out.println("Problema en crear capa de persistència:");
            System.out.println(ex.getMessage());
            System.out.println("Avortem programa");
            return;
        }
        
        //L'útim ha de tirar una excepció
        List<String> noms = Arrays.asList(new String[]{"usuari", "Alba", ""});
        List<String> contra = Arrays.asList(new String[]{"usuari", "123456789", ""});
        try {
            for (int i=0; i<contra.size(); i++) {
                gBD.login(noms.get(i), contra.get(i));
                System.out.println("Usuari connectat "+ noms.get(i)+" "+ contra.get(i));
            }
        } catch (GestorBDEmpresaException ex) {
            System.out.println("L'usuari no existeix");
        }
        
        Temporada t;
        List<Integer> anys = Arrays.asList(new Integer[]{2024, 2026, 2028});
        try {
            for (int i=0; i<anys.size(); i++) {
                System.out.println("Crear temporada "+anys.get(i)+": ");

                t =gBD.crear_temporada(anys.get(i));
                if(t==null){
                    System.out.println("\tNo s'ha pogut crear la temporada");
                }
            }
            
        } catch (GestorBDEmpresaException ex) {
            System.out.println(ex.getCause());
        
        }
        
        List<Temporada> temps= new ArrayList<>();
        
        try {
            System.out.println("Llista de temporades: ");
            temps =gBD.llista_temporades();
            for (Temporada temp : temps) {
                
                System.out.println("\t"+temp.toString());
            }
        } catch (GestorBDEmpresaException ex) {
            System.out.println(ex.getCause());
        
        }
        List<Integer> ids = Arrays.asList(new Integer[]{1, 2, 32});
        try {
            for (int i = 0; i < ids.size(); i++) {
                System.out.println("Equips del jugador "+ids.get(i)+": ");
                List<Equip> eqps= gBD.equips_on_son_els_jug(ids.get(i));
                System.out.println("Jugador amb id: "+ids.get(i));
                if(eqps.isEmpty()){
                    System.out.println("\tNo està en cap equip");
                }
                for (Equip eq : eqps) {
                    System.out.println("\t"+eq.toString());
                }
            }
        } catch (GestorBDEmpresaException ex) {
            System.out.println(ex.getCause());
        } 
       /* */
        try {
            System.out.println("\nActualitzar llista d'equips\n");
            eqs = gBD.actualitzar_equips();
            System.out.println("Equips recuperats: " + eqs.size());
            for (Equip equip : eqs) {
                System.out.println("\n"+equip);
                for (Jugador value : equip.getJug_mem().values()) {
                    System.out.println("Jug num:"+ value.getId_jug() + "\n\t" + value.toString());
                }
            }
        } catch (Persistencia.GestorBDEmpresaException ex) {
            System.err.println("S'ha produït un error en actualitzar els equips: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        List<String> cates = new ArrayList<>();
        for (Cate_enum cate : Cate_enum.values()) {
            cates.add(cate.toString());
        }
        cates.set(1, null);
        List<Character> tipus = Arrays.asList(new Character[]{'D', 'H', 'M','n'});
        List<String> nom = Arrays.asList(new String[]{ "sieyudgf",null,null, "AEDRF"});
        
        try {
            for (int i = 0; i < tipus.size(); i++) {
                System.out.println("Llista equips: ");
                List<Equip> eq = gBD.llistar_equips(cates.get(i),temps.get(i).getAnny(), tipus.get(i), nom.get(i),"asc");
                for (Equip equip : eq) {
                    System.out.println("\t"+equip.toString());
                }
            }
        } catch (GestorBDEmpresaException ex) {
            System.out.println(ex.getCause());
        }
        //Equip crear_equip(String nom, char tipus, int any_eq, String cate)
        
        for (Cate_enum cate : Cate_enum.values()) {
            cates.add(cate.toString());
        }
        nom = Arrays.asList(new String[]{ "sieyudgf","Benjami Masculi","hdbcv", "AEDRF"});
        
        for (int i = 0; i < nom.size(); i++) {
            try {
                System.out.println("Crear equip: ");
                gBD.crear_equip(nom.get(i), tipus.get(i), temps.get(i).getAnny(), cates.get(i));
                
            } catch (GestorBDEmpresaException ex) {
                if(ex.getCause()==null){
                    System.out.println("Aquest equip ja existeix");
                }else{
                   System.out.println("No s'ha pogut crear l'equip"); 
                }
                
            }
        }        
        
        List<Sexe_enum> sex = new ArrayList<>();
        sex.add(Sexe_enum.H);
        sex.add(Sexe_enum.D);
        List<Boolean> rev_feta = new ArrayList<>();
        rev_feta.add(true);
        rev_feta.add(false);
        try {
            List<Jugador> llistj = new ArrayList<>();
            for (int i = 0; i < sex.size(); i++) {
                llistj = gBD.llista_jugadors(sex.get(i), "", "", "", Cate_enum.Benjami, rev_feta.get(i));
                if(llistj == null){
                    System.out.println("No s'ha trobat cap jugador");
                }else{
                    System.out.println("Jugadors: ");
                    for (Jugador jugador : llistj) {
                        System.out.println(jugador.toString());
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getCause());
        }
        
        
      
        try {
            jugs = gBD.llista_jugadors(Sexe_enum.D, "", "", "", Cate_enum.Benjami, true);
            for (int i = 0; i < eqs.size(); i++) {
                System.out.println("Id de l'equip que li afeguim un jugador: " + eqs.get(i).getId_equip()+" Id jug a afeguir: "+jugs.get(i).getId_legal());
                System.out.println("Afeguir un jugador en un equip: ");
                gBD.afegir_jugadors(eqs.get(i), jugs.get(i), false);
            }
        } catch (GestorBDEmpresaException ex) {
            System.out.println(ex.getCause());
        }
        
        try {
            System.out.println("Eliminar un equip: ");
            gBD.eliminar_equip(eqs.get(32));
        } catch (GestorBDEmpresaException ex) {
            System.out.println(ex.getCause());
        }
        
         /* 
        
        try {
            System.out.println("Jugadors ordenats per id: ");
            gBD.mostrar_jugadors("desc");
        } catch (GestorBDEmpresaException ex) {
            throw new GestorBDEmpresaException("Tot mal: ", ex);
        }
        
        
        
        try {
            e = new Equip("aaa", Tipus_enum.D, 2024, Cate_enum.Alevi);
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        
        try {
            List<Jugador> llistj = gBD.llista_jugadors_ant(Sexe_enum.D, "Rita");
            if(llistj == null){
                System.out.println("Flabergasted :O");
            }else{
                for (Jugador jugador : llistj) {
                    System.out.println(jugador.toString());
                }
            }
            
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
        try {
            List<Jugador> llistj = gBD.llista_jugadors(Sexe_enum.D, "", "", "", Cate_enum.Benjami);
            if(llistj == null){
                System.out.println("Flabergasted :O");
            }else{
                for (Jugador jugador : llistj) {
                    System.out.println(jugador.toString());
                }
            }
            
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }*/
       /*  
        try {
            System.out.println("Agafar jugador: ");
            j= gBD.agafar_jugador("1", false);
            System.out.println(j.getNom());
        } catch (GestorBDEmpresaException ex) {
            throw new GestorBDEmpresaException("Tot mal: ", ex);
        } 
        
        
        try {
            System.out.println("Crear jugador: ");
            j= gBD.crear_jugador("Afhjs", "Seguro", Sexe_enum.H, "2014-06-10", "47121803R", "ES9121000418450200051330", 2025,  "C/ carrer 6", "08700", "Igualada", "C:\\jugadors\\persona.jpg", "Barcelona", "Espanya");
            if (j == null) {
                throw new GestorBDEmpresaException("No s'ha pogut crear");
            }
            System.out.println("Jugador: " + j.toString());
        } catch (GestorBDEmpresaException ex) {
            System.err.println("Error en crear jugador: " + ex.getMessage());
        } 
      //String nom, String tipus, int any_eq, String cate
        *//*
        try {
            Equip q = new Equip(e.getId_equip(), "DUYWEGB fdsg", Tipus_enum.H, 2024, Cate_enum.Alevi);
            System.out.println("Modificar un equip: ");
            System.out.println("ID_equip abans de modificar-lo: "+e.getId_equip());
            e=gBD.modificar_equip(q);
            System.out.println("ID_equip desp de modificar-lo: "+e.getId_equip());
            
            System.out.println(e.toString());
        } catch (GestorBDEmpresaException ex) {
            throw new GestorBDEmpresaException("Tot mal: ", ex);
        } catch(Exception ex){
            throw new GestorBDEmpresaException("Equip és null", ex);
        }
         */  
       /* try {
            
            Jugador jug = new Jugador(j.getId_jug(), "Calle Falsa 123", 2025, "Gómez", "01/01/1990", "foto.jpg", "ES7620770024020000001234", j.getId_legal(), "Juan", Sexe_enum.H, 28080, "Madrid", "Madrid", "España");

            
          System.out.println("Modificar jugador: ");
            j= gBD.modificar_jugador(jug);
            if (j == null) {
                throw new GestorBDEmpresaException("No s'ha pogut modificar");
            }
            System.out.println("Jugador: " + j.toString());
        } catch (GestorBDEmpresaException ex) {
            System.err.println("Error en crear jugador: " + ex.getMessage());
        }
        
       
        
        
        
      try {
            System.out.println("Eliminar jugador d'un equip: ");
            gBD.(e, j, false);
            
        } catch (GestorBDEmpresaException ex) {
            throw new GestorBDEmpresaException("Tot mal: ", ex);
        }
       */
         /*try {
            System.out.println("Mostrar temporada: ");
            List<Temporada> temp;
            if((temp =gBD.llista_temporades())==null){
                System.out.println("No es troba cap temporada");
            }
            for (Temporada temporada : temp) {
                System.out.println(temporada.getAnny());
            }
            
        } catch (GestorBDEmpresaException ex) {
            throw new GestorBDEmpresaException("Tot mal: ", ex);
        }
       try {
            System.out.println("Mostrar equips: ");
            if(gBD.mostrar_equips("Benjami", 0, 'n', "", "asc")==null){
                System.out.println("No existeix aquest equip");
            }
        } catch (GestorBDEmpresaException ex) {
            throw new GestorBDEmpresaException("Tot mal: ", ex);
        }
        
         try {
            System.out.println("Crear una temporada: ");
            gBD.crear_temporada(2026);
        } catch (GestorBDEmpresaException ex) {
            throw new GestorBDEmpresaException("Tot mal: ", ex);
        }
        
        
        
        try {
            System.out.println("Actualitzar llista d'equips");
            List<Equip> equips = gBD.actualitzar_equips();
            System.out.println("Equips recuperats: " + equips.size());
            for (Equip equip : equips) {
                System.out.println("\n"+equip);
                
                for (Jugador value : equip.getJug_mem().values()) {
                    System.out.println("Jug num:"+ value.getId_jug() + "\n\t" + value.toString());
                }
                
            }
            
        } catch (Persistencia.GestorBDEmpresaException ex) {
            System.err.println("S'ha produït un error en actualitzar els equips: " + ex.getMessage());
            ex.printStackTrace();
        }

        try {
            System.out.println("Crear un equip: ");
            e=gBD.crear_equip("Alevi fdsg", 'H', 2024, "Alevi");
            System.out.println(e.toString());
        } catch (GestorBDEmpresaException ex) {
            throw new GestorBDEmpresaException("Tot mal: ", ex);
        }*/
        /*  
        try {
            System.out.println("mostrar_jugadors_per_equip: ");
            gBD.mostrar_jugadors_per_equip();
        } catch (GestorBDEmpresaException ex) {
            throw new GestorBDEmpresaException("Tot mal: ", ex);
        }

        try{
            System.out.println("Eliminar un jugador: ");
            gBD.eliminar_jugador(33);
        }catch(GestorBDEmpresaException ex) {
            throw new GestorBDEmpresaException("Tingues en compte que: ", ex);
        }*/
    }
}
