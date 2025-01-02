/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import Enums.Cate_enum;
import Enums.Tipus_enum;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Alma
 */
public class Equip {
    
    int id_equip;//Potser seria millor que sigués un string eq_01
    String nom;
    Tipus_enum tipus;
    int any_eq;
    Cate_enum cate;//Potser seria millor cambiar el ddl i que no sigui un num
    Map <Integer, Jugador> jug_mem = new HashMap<>();
    Map <Integer, Character> jug_mem_titular = new HashMap<>();

    public Equip(int id_equip, String nom, Tipus_enum tipus, int any_eq, Cate_enum cate) throws Exception {
        this.id_equip = id_equip;
        this.nom = nom;
        this.tipus = tipus;
        this.cate = cate;
        if(any_eq >= 1900){
            this.any_eq = any_eq;            
        }else{
            throw new Exception("L'any ha de ser major o igual 1900");
        }
    }

    
    public void afegir_jugador(Jugador jug, char t) {//El caràcter marca si el jugador és titular
        if (jug == null) {
            System.out.println("El jugador és null. No s'afegeix.");
            return;
        }
        for (Integer id : jug_mem.keySet()) {
            if(jug.getId_jug() == id){
                System.out.println("El jugador ja existeix. No s'afegeix.");
                return;
            }
        }
        jug_mem.put(jug.id_jug, jug);
        jug_mem_titular.put(jug.id_jug, t);
        
    }
    public boolean agafar_jugador(Jugador jug) {
        if (jug == null) {
            System.out.println("El jugador és null. No existeix.");
            return false;
        }
        for (Integer id : jug_mem.keySet()) {
            if(jug.getId_jug() == id){
                System.out.println("El jugador ja existeix. No s'afegeix.");
                return true;
            }
        }
        System.out.println("false");
        return false;
        
        
    }
    
    public void eliminar_jugador(int id_jug) {
        jug_mem.remove(id_jug);
        jug_mem_titular.remove(id_jug);
    }
    public void eliminar_jugadors_de_l_equip(){
        jug_mem.clear();
        jug_mem_titular.clear();
    }
    
    public void mostrar_jugadors(){
        Iterator<Integer> it_jug_mem = jug_mem.keySet().iterator();
        List<Jugador> jugs = new ArrayList<>();
        System.out.println("Jugadors de l'equip "+ nom+ ": ");
        while(it_jug_mem.hasNext()){
            int key = it_jug_mem.next();
            Jugador jugador = jug_mem.get(key);
            char titular = jug_mem_titular.get(key);
            System.out.println(jugador.toString()+" Titular?: "+titular);
            jugs.add(jugador);
        }
    }

    public String getNom() {
        return nom;
    }

    public int getAny_eq() {
        return any_eq;
    }

    public int getId_equip() {
        return id_equip;
    }

    public Map<Integer, Jugador> getJug_mem() {
        return jug_mem;
    }

    public Map<Integer, Character> getJug_mem_titular() {
        return jug_mem_titular;
    }   

   
    public Tipus_enum getTipus() {
        return tipus;
    }

    public Cate_enum getCate() {
        return cate;
    }

        
    @Override
    public String toString() {
        return "Equip{" + "id_equip=" + id_equip + ", nom=" + nom + ", tipus=" + tipus + '}';
    }
    
    
}
