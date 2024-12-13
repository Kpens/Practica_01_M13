
import CapaPers.GestorBDEmpresaJdbc;
import Classes.Equip;
import Classes.Jugador;
import Classes.Temporada;
import Classes.Usuari;
import Enums.Cate_enum;
import Enums.Sexe_enum;
import Enums.Tipus_enum;
import Persistencia.GestorBDEmpresaException;
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
        Equip e = null;
        Jugador j = null;

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
        
        try {
            gBD.login("usuari", "usuari");
            System.out.println("Usuari creat");
        } catch (GestorBDEmpresaException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Temporada t = new Temporada(2024) ;
        /*
        try {
            System.out.println("Equips per temporada: ");
            gBD.mostrar_equips_temp(t);
        } catch (GestorBDEmpresaException ex) {
            throw new GestorBDEmpresaException("Tot mal", ex);
        
        }
        
        try {
            System.out.println("Equips per categoria: ");
            gBD.mostrar_equips_cate("desc");
        } catch (GestorBDEmpresaException ex) {
            throw new GestorBDEmpresaException("Tot mal: ", ex);
        }
        
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
        
        */
        
        try {
            List<Jugador> llistj = gBD.llista_jugadors(Sexe_enum.D, "");
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
        
       
        //String nom, String tipus, int any_eq, String cate
        try {
            System.out.println("Crear un equip: ");
            e=gBD.crear_equip("Alevi fdsg", 'H', 2024, "Alevi");
            System.out.println(e.toString());
        } catch (GestorBDEmpresaException ex) {
            throw new GestorBDEmpresaException("Tot mal: ", ex);
        }
          /* */
        try {
            System.out.println("Crear jugador: ");
            j= gBD.crear_jugador("Afhjs", "Seguro", Sexe_enum.H, "2014-06-10", "ID300", "ES9121000418450200051330", 2025,  "C/ carrer 6", Integer.parseInt("08700"), "Igualada", "C:\\jugadors\\persona.jpg", "Barcelona", "Espanya");
            if (j == null) {
                throw new GestorBDEmpresaException("No s'ha pogut crear");
            }
            System.out.println("Jugador: " + j.toString());
        } catch (GestorBDEmpresaException ex) {
            System.err.println("Error en crear jugador: " + ex.getMessage());
        }
        try {
            
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
            System.out.println("Afeguir un jugador en un equip: ");
            gBD.afegir_jugadors(e, j, false);
            
        } catch (GestorBDEmpresaException ex) {
            throw new GestorBDEmpresaException("Tot mal: ", ex);
        }
        try {
            System.out.println("Eliminar jugador d'un equip: ");
            gBD.eliminar_jugadors(e, j, false);
            
        } catch (GestorBDEmpresaException ex) {
            throw new GestorBDEmpresaException("Tot mal: ", ex);
        }
       
       /*
        try {
            System.out.println("Mostrar equips: ");
            if(gBD.mostrar_equips(null, 2025, 'n', "b", "asc")==null){
                System.out.println("No existeix aquest equip");
            }
        } catch (GestorBDEmpresaException ex) {
            throw new GestorBDEmpresaException("Tot mal: ", ex);
        }*/
 /*      
        try {
            System.out.println("Eliminar un jugador d'un equip: ");
            gBD.eliminar_jugadors(e, j);
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
            System.out.println("Eliminar un equip: ");
            gBD.eliminar_equip(e);
        } catch (GestorBDEmpresaException ex) {
            throw new GestorBDEmpresaException("Tot mal: ", ex);
        }
        try {
            System.out.println("mostrar_jugadors_per_equip: ");
            gBD.mostrar_jugadors_per_equip();
        } catch (GestorBDEmpresaException ex) {
            throw new GestorBDEmpresaException("Tot mal: ", ex);
        }*/

    }
}
