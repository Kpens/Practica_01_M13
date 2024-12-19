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
    public JFrame menu_principal() {
        JFrame f = new JFrame("El nostre club");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800, 600);

        JPanel pan_principal = new JPanel(new BorderLayout());

        JPanel barraNavegacio = crearBarraNavegacio();
        pan_principal.add(barraNavegacio, BorderLayout.PAGE_START);

        JPanel pan_central = new JPanel(new BorderLayout());
        JLabel titol = new JLabel("El nostre club", JLabel.CENTER);
        titol.setFont(new Font("Arial", Font.BOLD, 40));

        pan_central.add(titol, BorderLayout.NORTH);

        pan_principal.add(pan_central, BorderLayout.CENTER);

        f.add(pan_principal);
        f.setVisible(true);
        
        return f;
    }

    private static JPanel crearBarraNavegacio() {
        JPanel barra = new JPanel(new BorderLayout());
        barra.setBackground(Color.LIGHT_GRAY);

        //Un panell per a poder agrupar els botons de l'esquerra a la barra i poder separar-los del de filtrar
        JPanel pan_bot_esquerra = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 5));
        pan_bot_esquerra.setBackground(Color.LIGHT_GRAY);

        JButton bJugadors = new JButton("Jugadors");
        JButton bEquips = new JButton("Equips");
        JButton bTemporades = new JButton("Temporades");

        bJugadors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pestanya jugs");
            }
            
        });
        bEquips.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pestanya eqs");
            }
            
        });
        bTemporades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pestanya temporades");
            }
            
        });
        
        Funcions.nav_boto_estil(bJugadors);
        Funcions.nav_boto_estil(bEquips);
        Funcions.nav_boto_estil(bTemporades);

        pan_bot_esquerra.add(bJugadors);
        pan_bot_esquerra.add(bEquips);
        pan_bot_esquerra.add(bTemporades);

        JButton bFiltrar = new JButton("Filtrar");
        Funcions.nav_boto_estil(bFiltrar);

        bFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pestanya filtrar");
            }
            
        });
        barra.add(pan_bot_esquerra, BorderLayout.WEST);
        barra.add(bFiltrar, BorderLayout.EAST);

        return barra;
    }

    
}