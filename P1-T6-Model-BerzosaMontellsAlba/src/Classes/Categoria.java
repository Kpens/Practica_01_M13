/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import Enums.Cate_enum;

/**
 *
 * @author Alma
 */
public class Categoria {
    int edat_max;
    int edat_min;
    int id_cate;
    Cate_enum nom_cate;

    public Categoria(int edat_max, int edat_min, Cate_enum nom_cate) throws Exception {
        
        if(edat_min >= 0){
            this.edat_min = edat_min;            
        }else{
            throw new Exception("L'edat minima ha de ser positiva");
        }
        if(edat_min <= edat_max){
            this.edat_max = edat_max;            
        }else{
            throw new Exception("L'edat màxima ha de ser major a la mínima");
        }
        this.nom_cate = nom_cate;
    }

    public Categoria(int edat_min, Cate_enum nom_cate) throws Exception {
        if(edat_min >= 0){
            this.edat_min = edat_min;            
        }else{
            throw new Exception("L'edat minima ha de ser positiva");
        }
        this.nom_cate = nom_cate;
    }

    public int getId_cate(Cate_enum nom_cate) {
        return id_cate;
    }

    public void setId_cate(int id_cate) {
        this.id_cate = id_cate;
    }
    
    

}
