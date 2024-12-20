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
        JFrame f = new JFrame("El nostre club");

        f.setLayout(null);
        f.setSize(1000, 530);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setResizable(false);
        
        crearBarraNavegacio(f);
        
        ltitol = new JLabel("Crear un Jugador ");
        ltitol.setBounds(200,50, 600, 30);
        ltitol.setFont(new Font("Arial", Font.BOLD, 40));
        f.add(ltitol);
        
        
        return f;
    }
    
    /*
    * La barra superior on estàn els botons que connecten als diferents gestors
    */

    private static void crearBarraNavegacio(JFrame f) {
        
        JButton bJugadors = new JButton("Jugadors");
        bJugadors.setBounds(10, 10, 100, 30);
        JButton bEquips = new JButton("Equips");
        bEquips.setBounds(120, 10, 100, 30);
        JButton bTemporades = new JButton("Temporades");
        bTemporades.setBounds(270, 10, 140, 30);
        
        bJugadors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                Funcions.agafar_gest_jugs();
            }
            
        });
        bEquips.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                Funcions.gest_equips();
            }
            
        });
        bTemporades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                Funcions.gest_temporades();
            }
            
        });

        Funcions.nav_boto_estil(bJugadors);
        Funcions.nav_boto_estil(bEquips);
        Funcions.nav_boto_estil(bTemporades);

        f.add(bJugadors);
        f.add(bEquips);
        f.add(bTemporades);

        JButton bFiltrar = new JButton("Filtrar");
        bFiltrar.setBounds(f.getWidth()-120, 10, 100, 30);
        Funcions.nav_boto_estil(bFiltrar);
        bFiltrar.addActionListener(e -> System.out.println("Pestanya filtrar"));

        f.add(bFiltrar);
    }

    
}