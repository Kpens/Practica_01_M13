/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Enums;

/**
 *
 * @author Alma
 */
public enum Cate_enum {
    Benjami(7, 8),
    Alevi(9, 11),
    Infantil(12, 13),
    Cadet(14, 15),
    Juvenil(16, 17),
    Senior(18, 101);

    private final int min;
    private final int max;

    Cate_enum(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getEdatMinima() {
        return min;
    }

    public int getEdatMaxima() {
        return max;
    }
    
    public boolean edatDinsRang(int edat) {
        return edat >= min && edat <= max;
    }
}
