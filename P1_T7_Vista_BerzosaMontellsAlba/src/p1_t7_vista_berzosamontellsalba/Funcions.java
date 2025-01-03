/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1_t7_vista_berzosamontellsalba;

import Classes.Jugador;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
    static void modificar_jug(Jugador j){
        Modificar_jug mp = new Modificar_jug();
        JFrame mpFrame = mp.Modificar_jug(j);
        mpFrame.setVisible(true);
    }
    
    
    /*
    * si el char temp_eq_o_jug diu 't' es treurà el de temporada i es posarà el d'equip, així amb cada un.
    * Podent ser 't' temporada 'e' equip 'j' jugador  o un altre que comptem que es el menú principal o algún altre que no hi hagui caigut ara
    */
    static void crearBarraNavegacio(JFrame f, char temp_eq_o_jug) {
        
        JPanel barra_nav = new JPanel();
        barra_nav.setBounds(0, 0, f.getWidth(), 45);
        barra_nav.setBackground(Color.LIGHT_GRAY);

        JButton bnum1 = new JButton();
        bnum1.setBounds(10, 10, 100, 30);
        JButton bnum2 = new JButton();
        bnum2.setBounds(120, 10, 100, 30);
        JButton bnum3 = new JButton();
        bnum3.setBounds(270, 10, 140, 30);
        switch (temp_eq_o_jug) {
            case 't':
                bnum1.setText("Casa");
                bnum2.setText("Jugadors");
                bnum3.setText("Equips");
                break;
            case 'e':
                bnum1.setText("Casa");
                bnum2.setText("Jugadors");
                bnum3.setText("Temporades");
                break;
            case 'j':
                bnum1.setText("Casa");
                bnum2.setText("Equips");
                bnum3.setText("Temporades");
                break;
            default:
                bnum1.setText("Jugadors");
                bnum2.setText("Equips");
                bnum3.setText("Temporades");
                break;
        }

        bnum1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                if(temp_eq_o_jug != 'j'&&temp_eq_o_jug != 't'&&temp_eq_o_jug != 'e'){
                    Funcions.agafar_gest_jugs();
                }else{
                    Funcions.agafar_menu_principal();
                }
                
            }
            
        });
        bnum2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                if(temp_eq_o_jug == 't' || temp_eq_o_jug=='e'){
                    Funcions.agafar_gest_jugs();
                }else{
                    Funcions.gest_equips();
                }
            }
            
        });
        bnum3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                if(temp_eq_o_jug == 't'){
                    Funcions.gest_equips();
                }else{
                    Funcions.gest_temporades();
                }
            }
            
        });

        Funcions.nav_boto_estil(bnum1);
        Funcions.nav_boto_estil(bnum2);
        Funcions.nav_boto_estil(bnum3);

        JButton bFiltrar = new JButton("Log out");
        bFiltrar.setBounds(f.getWidth()-120, 10, 100, 30);
        Funcions.nav_boto_estil(bFiltrar);
        bFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                Login mp = new Login();
                JFrame mpFrame = mp.go();
                mpFrame.setVisible(true);
            }
            
        });

        
        barra_nav.setLayout(null);//Així es poden posar manualment :O
        barra_nav.add(bnum1);
        barra_nav.add(bnum2);
        barra_nav.add(bnum3);
        barra_nav.add(bFiltrar);

        f.add(barra_nav);
    }
}
