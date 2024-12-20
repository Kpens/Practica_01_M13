/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1_t7_vista_berzosamontellsalba;

import CapaPers.GestorBDEmpresaJdbc;
import Persistencia.GestorBDEmpresaException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alma
 */
public class Login extends JFrame {

    static private String nomClassePersistencia = null;
    static private JLabel ltext, lcontra, lEnllaç;
    static private JTextField ltf_nom;
    static private JButton bNom_usu;
    static private JPasswordField pf_contra;

    public static void main(String[] args) {
        if (args.length > 0) {
            nomClassePersistencia = args[0];
        } else {
            System.out.println("No s'ha especificat cap classe de persistència.");
        }
        Login mp = new Login();
        mp.go();
    }

    void go() {
        JFrame f = new JFrame("El nostre club");

        f.setLayout(null);
        f.setSize(800, 530);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        pf_contra = new JPasswordField();
        pf_contra.setBounds(380, 145, 200, 30);
        pf_contra.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(pf_contra);

        bNom_usu = new JButton("Accedir");
        bNom_usu.setBounds(435, 225, 125, 30);
        bNom_usu.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(bNom_usu);
        
        lEnllaç = new JLabel("<HTML><U>Has oblidat la contrasenya?</U></HTML>");
        lEnllaç.setBounds(210, 200, 250, 30);
        lEnllaç.setForeground(Color.BLUE);
        lEnllaç.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        f.add(lEnllaç);

        lEnllaç.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(f, "Si us plau avisa al teu administrador:\n  aberzosa1@milaifontanals.org\n\n", "Alerta", JOptionPane.WARNING_MESSAGE);
            }
        });

        bNom_usu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestorBDEmpresaJdbc gestor;
                try {
                    System.out.println("Intent de creació de la capa...");
                    gestor = new GestorBDEmpresaJdbc();
                    System.out.println("Capa de persistència creada");
                    System.out.println("");

                    try {
                        gestor.login(ltf_nom.getText(), pf_contra.getText());

                        f.dispose();
                        Funcions.agafar_menu_principal();
                    } catch (GestorBDEmpresaException ex) {
                        pf_contra.setBackground(Color.RED);
                        JOptionPane.showMessageDialog(f, "Error: " + ex.getMessage(), "Error de login", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (GestorBDEmpresaException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        pf_contra.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                pf_contra.setBackground(Color.WHITE);
            }
        });
        
        f.setVisible(true);
        System.out.println("El JFrame s'ha mostrat correctament.");
    }
}
