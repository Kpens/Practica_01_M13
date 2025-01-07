/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Alma
 */
public class Temporada {
    int anny;
    //Map<Integer, Equip> equips = new HashMap<>();

    public Temporada(int anny){
        if(anny >=1900){
            this.anny = anny;
            
        }else{
            throw new IllegalArgumentException("L'any és massa petit");
        }
    }
    
        
    /*public void mostrar_equips() {
        Iterator<Integer> it_equips = equips.keySet().iterator();
        List<Equip> eqs = new ArrayList<>();
        while(it_equips.hasNext()){
            int key = it_equips.next();
            Equip equip = equips.get(key);
            System.out.println(equip.toString());
            eqs.add(equip);
        }
        
        System.out.println("Equips de l'any "+ anny+ ": ");
        for (Equip equi : eqs) {
            System.out.println(equi);
        }
    }*/
    
    public int getAnny() {
        return anny;
    }

    public void setAnny(int anny) {
        this.anny = anny;
    }

    @Override
    public String toString() {
        return "Temporada{" + "anny=" + anny + '}';
    }
    
}
