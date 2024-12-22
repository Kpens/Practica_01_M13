/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1_t7_vista_berzosamontellsalba;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import p1_t7_vista_berzosamontellsalba.Menu_principal;

/**
 *
 * @author Alma
 */
public class Funcions {
    static void agafar_menu_principal(){
        Menu_principal mp = new Menu_principal();
        JFrame mpFrame = mp.menu_principal();
        mpFrame.setVisible(true);
    }
    
    static void nav_boto_estil(JButton boto) {//Els botons de la part superior
        boto.setFocusPainted(false);
        boto.setBorderPainted(false);
        boto.setBackground(Color.LIGHT_GRAY);
        boto.setFont(new Font("Arial", Font.PLAIN, 16));
    }
     static void boto_estil(JButton boto) {//Els altres botons
        boto.setFocusPainted(false);
        boto.setBorderPainted(false);
        boto.setBackground(new Color(0xDFF5F6));
        boto.setBorderPainted(true);
        boto.setFont(new Font("Arial", Font.PLAIN, 16));
    }
    static void agafar_gest_jugs(){
        Gestio_jugs mp = new Gestio_jugs();
        JFrame mpFrame = mp.gestio_jugs();
        mpFrame.setVisible(true);
    }
    static void crear_jug(){
        Crear_jugador mp = new Crear_jugador();
        JFrame mpFrame = mp.Crear_jugador();
        mpFrame.setVisible(true);
    }
    static void gest_equips(){
        Gestio_equips mp = new Gestio_equips();
        JFrame mpFrame = mp.gestio_equips();
        mpFrame.setVisible(true);
    }
    static void gest_temporades(){
        Gestio_temporades mp = new Gestio_temporades();
        JFrame mpFrame = mp.crear_temporada();
        mpFrame.setVisible(true);
    }
}
