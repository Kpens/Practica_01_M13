
import CapaPers.GestorBDEmpresaJdbc;
import Classes.Equip;
import Classes.Temporada;
import Classes.Usuari;
import Persistencia.GestorBDEmpresaException;
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
        
        try {
            System.out.println("Equips per temporada: ");
            gBD.mostrar_equips_temp(t);
        } catch (GestorBDEmpresaException ex) {
            throw new GestorBDEmpresaException("Tot mal", ex);
        
        }
        
        try {
            System.out.println("Equips per categoria: ");
            gBD.mostrar_jugadors_cate();
        } catch (GestorBDEmpresaException ex) {
            throw new GestorBDEmpresaException("Tot mal", ex);
        }
        
       
    }
}
