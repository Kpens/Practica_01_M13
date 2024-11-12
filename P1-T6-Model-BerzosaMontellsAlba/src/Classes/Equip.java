/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

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
    int cate;//Potser seria millor cambiar el ddl i que no sigui un num
    Map <String, Jugador> jug_mem = new HashMap<>();
    Map <String, Character> jug_mem_titular = new HashMap<>();

    public Equip(String nom, Tipus_enum tipus, int any_eq, int cate) throws Exception {
        this.nom = nom;
        this.tipus = tipus;
        this.cate = cate;
        if(any_eq >= 1900){
            this.any_eq = any_eq;            
        }else{
            throw new Exception("L'any ha de ser major o igual 1900");
        }
    }

    
    public void afeguir_jugador(String id_jug, Jugador jug, char t) {
        jug_mem.put(id_jug, jug);
        jug_mem_titular.put(id_jug, t);
    }
    
    public void eliminar_jugador(String id_jug) {
        jug_mem.remove(id_jug);
        jug_mem_titular.remove(id_jug);
    }
    public void eliminar_jugadors(){
        jug_mem.clear();
        jug_mem_titular.clear();
    }
    
    public void mostrar_jugadors(){
        Iterator<String> it_jug_mem = jug_mem.keySet().iterator();
        List<Jugador> jugs = new ArrayList<>();
        while(it_jug_mem.hasNext()){
            String key = it_jug_mem.next();
            Jugador jugador = jug_mem.get(key);
            char titular = jug_mem_titular.get(key);
            System.out.println(jugador.toString()+" Titular?: "+titular);
            jugs.add(jugador);
        }
        System.out.println("Jugadors de l'equip "+ nom+ ": ");
        for (Jugador jug : jugs) {
            System.out.println(jugs);
        }
    }

    @Override
    public String toString() {
        return "Equip{" + "id_equip=" + id_equip + ", nom=" + nom + ", tipus=" + tipus + '}';
    }
    
    
}
