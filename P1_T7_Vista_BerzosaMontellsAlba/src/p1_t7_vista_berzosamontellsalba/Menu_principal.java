/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1_t7_vista_berzosamontellsalba;
import p1_t7_vista_berzosamontellsalba.Funcions;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Alma
 */
public class Menu_principal {
    static private JLabel ltitol;
    static JFrame f = new JFrame("El nostre club");
    public JFrame menu_principal() {

        f.setLayout(null);
        f.setSize(1000, 530);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setResizable(false);
        
        
        Funcions.crearBarraNavegacio(f, 'm');
        
        ltitol = new JLabel("El nostre club");
        ltitol.setBounds(200,50, 600, 40);
        ltitol.setFont(new Font("Arial", Font.BOLD, 38));
        f.add(ltitol);
        
        
        return f;
    }
    
    /*
    * La barra superior on estàn els botons que connecten als diferents gestors
    */

}