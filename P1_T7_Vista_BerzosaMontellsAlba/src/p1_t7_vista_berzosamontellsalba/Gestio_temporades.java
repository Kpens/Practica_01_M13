/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1_t7_vista_berzosamontellsalba;

import CapaPers.GestorBDEmpresaJdbc;
import Enums.Cate_enum;
import Persistencia.GestorBDEmpresaException;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Alma
 */
public class Gestio_temporades {
    static JFrame f = new JFrame("Gestio_temporades");
    static JLabel missatge;
    static JLabel ltemp;
    static private JButton bCrear_temp;
    
    JTextField ltf_anny = new JTextField();
    public JFrame crear_temporada() {
        Gestio_temporades mp = new Gestio_temporades();
        f = new JFrame("Crear Temporada");

        f.setLayout(null);
        f.setSize(800, 530);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setResizable(false);

        
        crearBarraNavegacio(f);
        
        ltemp = new JLabel("Escriu l'any de la temporada: ");
        ltemp.setBounds(100, 100, 300, 40);
        ltemp.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(ltemp);
        missatge = new JLabel();
        missatge.setBounds(20, 200, 300, 40);
        missatge.setForeground(Color.red);
        missatge.setVisible(false);
        missatge.setFont(new Font("Arial", Font.BOLD, 15));
        f.add(missatge);

        ltf_anny = new JTextField();
        ltf_anny.setBounds(400, 105, 200, 30);
        ltf_anny.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(ltf_anny);
        
        bCrear_temp= new JButton("Crear");
        bCrear_temp.setBounds(435, 225, 125, 30);
        bCrear_temp.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(bCrear_temp);
        
        bCrear_temp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    missatge.setForeground(Color.red);
                    missatge.setVisible(false); 
                    GestorBDEmpresaJdbc gestor = new GestorBDEmpresaJdbc();
                    
                   try {
                        int anny =Integer.parseInt(ltf_anny.getText().trim());
                        
                        gestor.crear_temporada(anny);
                    } catch (NumberFormatException exx) {
                        missatge.setText("Error: Num invàlid");
                        missatge.setVisible(true);
                        return;
                    }
                    
                    missatge.setForeground(Color.green);
                    missatge.setText("Inserit");
                    missatge.setVisible(true);

                } catch (GestorBDEmpresaException ex) {
                    missatge.setText("Error: "+ex);
                    missatge.setVisible(true);
                }
            }
        });
        
        return f;
    }

    private static void crearBarraNavegacio(JFrame f) {
        
               
        JButton bCasa = new JButton("Casa");
        bCasa.setBounds(10, 10, 100, 30);
        JButton bEquips = new JButton("Equips");
        bEquips.setBounds(120, 10, 100, 30);
        JButton bJugadors = new JButton("Jugadors");
        bJugadors.setBounds(270, 10, 140, 30);


        bCasa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                Funcions.agafar_menu_principal();
            }
            
        });
        bEquips.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                Funcions.gest_equips();
            }
            
        });
        bJugadors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                Funcions.agafar_gest_jugs();
            }
            
        });
        
        
        Funcions.nav_boto_estil(bCasa);
        Funcions.nav_boto_estil(bEquips);
        Funcions.nav_boto_estil(bJugadors);

        f.add(bCasa);
        f.add(bEquips);
        f.add(bJugadors);

        JButton bFiltrar = new JButton("Filtrar");
        bFiltrar.setBounds(f.getWidth()-100, 10, 100, 30);
        Funcions.nav_boto_estil(bFiltrar);
        bFiltrar.addActionListener(e -> System.out.println("Pestanya filtrar"));

        f.add(bFiltrar);
    }
}
