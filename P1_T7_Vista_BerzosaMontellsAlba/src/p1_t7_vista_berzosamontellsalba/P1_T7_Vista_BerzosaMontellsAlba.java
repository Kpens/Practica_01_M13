/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1_t7_vista_berzosamontellsalba;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Alma
 */
public class P1_T7_Vista_BerzosaMontellsAlba extends JFrame {

    static private String nomClassePersistencia = null;
    static private JLabel ltext, lcontra, lEnllaç;
    static private JTextField ltf_nom, ltf_contra;
    static private JButton bNom_usu;

    public static void main(String[] args) {
        if (args.length > 0) {
            nomClassePersistencia = args[0];
        } else {
            System.out.println("No s'ha especificat cap classe de persistència.");
        }
        P1_T7_Vista_BerzosaMontellsAlba mp = new P1_T7_Vista_BerzosaMontellsAlba();
        mp.go();
    }

    void go() {
        JFrame f = new JFrame("El nostre club");

        f.setLayout(null);
        f.setSize(800, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);

        ltext = new JLabel("Nom d'usuari: ");
        ltext.setBounds(200, 100, 160, 40);
        ltext.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(ltext);

        ltf_nom = new JTextField();
        ltf_nom.setBounds(380, 105, 200, 30);
        ltf_nom.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(ltf_nom);

        lcontra = new JLabel("Contrasenya: ");
        lcontra.setBounds(200, 140, 160, 40);
        lcontra.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(lcontra);

        ltf_contra = new JTextField();
        ltf_contra.setBounds(380, 145, 200, 30);
        ltf_contra.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(ltf_contra);

        bNom_usu = new JButton("Accedir");
        bNom_usu.setBounds(380, 245, 200, 30);
        bNom_usu.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(bNom_usu);

        lEnllaç = new JLabel("<HTML><U>Has oblidat la contrasenya?</U></HTML>");
        lEnllaç.setBounds(380, 200, 250, 30);
        lEnllaç.setForeground(Color.BLUE);
        lEnllaç.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        f.add(lEnllaç);

        lEnllaç.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(f, "Si us plau avisa al teu administrador:\n  aberzosa1@milaifontanals.org\n\n", "Alerta", JOptionPane.WARNING_MESSAGE);
            }
        });

        f.setVisible(true);
        System.out.println("El JFrame s'ha mostrat correctament.");
    }
}
