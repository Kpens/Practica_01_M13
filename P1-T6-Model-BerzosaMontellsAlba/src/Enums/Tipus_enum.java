/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Enums;

/**
 *
 * @author Alma
 */
public enum Tipus_enum {
    D('D'),
    H('H'),
    M('M');
    private char value;

    Tipus_enum(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}
