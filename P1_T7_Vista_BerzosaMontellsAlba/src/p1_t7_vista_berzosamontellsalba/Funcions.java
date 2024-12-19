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
}
