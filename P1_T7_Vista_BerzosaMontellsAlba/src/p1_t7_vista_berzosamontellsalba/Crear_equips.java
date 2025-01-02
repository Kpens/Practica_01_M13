/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1_t7_vista_berzosamontellsalba;

import CapaPers.GestorBDEmpresaJdbc;
import Classes.Equip;
import Classes.Jugador;
import Classes.Temporada;
import Enums.Cate_enum;
import Enums.Sexe_enum;
import Enums.Tipus_enum;
import Persistencia.GestorBDEmpresaException;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
/**
 *
 * @author Alma
 * 
 * He pensat que seria bona idea que després de crear el jugador es torni a la pantalla de Gestió d'equip
 */
public class Crear_equips {
    static private JLabel ltext,ltipus, ltitol;
    static private JTextField ltf_nom;
    static private JButton bcrear_equip;
    static private JComboBox<String> cb_cate, cb_temp;
    static private JButton b_endarrere;
    static private String img_path;
    static private GestorBDEmpresaJdbc gestor;
    
    public static void main(String[] args)  {
        
        JFrame f = new JFrame("El nostre club");

        f.setLayout(null);
        f.setSize(1000, 530);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setResizable(false);
        
        go(f);
    }

    static void go(JFrame f) {

        Funcions.crearBarraNavegacio(f, 'e');
               
        ltitol = new JLabel("Crear un Equip ");
        ltitol.setBounds(200,50, 600, 40);
        ltitol.setFont(new Font("Arial", Font.BOLD, 38));
        f.add(ltitol);

               
        ltext = new JLabel("Nom: ");
        ltext.setBounds(200, 100, 160, 40);
        ltext.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(ltext);

        ltf_nom = new JTextField();
        ltf_nom.setBounds(360, 105, 230, 30);
        ltf_nom.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(ltf_nom);
        
        ltext = new JLabel("Temporada: ");
        ltext.setBounds(200, 140, 160, 40);
        ltext.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(ltext);
        List<Temporada> temp = null;
        try {
            System.out.println("Intent de creació de la capa...");
            gestor = new GestorBDEmpresaJdbc();
            System.out.println("Capa de persistència creada");
            System.out.println("");


            temp = gestor.llista_temporades();
            

        } catch (GestorBDEmpresaException ex) {
                JOptionPane.showMessageDialog(f, "Error: En la capa");
        }try {
            System.out.println("Tancament de la capa");
            gestor.tancarCapa();
            System.out.println("Capa tancada");
        } catch (GestorBDEmpresaException ex) {
            System.out.println("\tError: " + ex.getMessage());
        }
                
        if (temp != null) {
            String[] tempo= new String[temp.size()+1];
            int i=0;
            boolean existeix =false;
            String anny;
            for (Temporada t : temp) {
                anny = Integer.toString(t.getAnny());
                tempo[i] = anny+"/"+(Integer.parseInt(anny.substring(2, 4))+1);
                if(t.getAnny() == LocalDate.now().getYear()){
                    existeix=true;
                }
                
                i++;
            }
            String temp_act = LocalDate.now().getYear()+"/"+(LocalDate.now().getYear()%100+ 1);//el 100 agafa els 2 ultims vals de l'any
            cb_temp = new JComboBox<>(tempo);
            cb_temp.setFont(new Font("Arial", Font.PLAIN, 16));
            cb_temp.setBounds(490, 140, 100, 40);
            if(existeix){
                cb_temp.setSelectedItem(temp_act);
            }
            
            f.add(cb_temp);
            
        } else {
            JOptionPane.showMessageDialog(f, "No existeix cap temporada");
            
        }    
                
        ltipus = new JLabel("Tipus: ");
        ltipus.setBounds(200, 180, 160, 40);
        ltipus.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(ltipus);
        
        JRadioButton rbHome = new JRadioButton("Home");
        rbHome.setBounds(360, 180, 60, 40);
        JRadioButton rbDona = new JRadioButton("Dona");
        rbDona.setBounds(450, 180, 60, 40);
        JRadioButton rbMixte = new JRadioButton("Mixte");
        rbMixte.setBounds(540, 180, 60, 40);
        ButtonGroup grup_tipus = new ButtonGroup();
        grup_tipus.add(rbHome);
        grup_tipus.add(rbDona);
        grup_tipus.add(rbMixte);
        rbHome.setSelected(true);
        
        f.add(rbHome);
        
        f.add(rbDona);
        f.add(rbMixte);
                
        
        ltext = new JLabel("Categoria: ");
        ltext.setBounds(200, 220, 160, 40);
        ltext.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(ltext);
        
        String[] cates = new String[Cate_enum.values().length +1];
        int i=0;
        for (Cate_enum cat : Cate_enum.values()) {
            cates[i] = cat+"";
            i++;
        }
        cb_cate = new JComboBox<>(cates);
        //cb_cate = new JComboBox<>(new String[]{"Qualsevol",Cate_enum.Alevi.toString(), Cate_enum.Benjami.toString(), Cate_enum.Cadet.toString(), Cate_enum.Infantil.toString(), Cate_enum.Juvenil.toString(), Cate_enum.Senior.toString()});
        cb_cate.setFont(new Font("Arial", Font.PLAIN, 16));
        cb_cate.setBounds(490, 220, 100, 40);
        
        f.add(cb_cate);
        
        bcrear_equip = new JButton("Crear");
        bcrear_equip.setBounds(860, 425, 125, 30);
        Funcions.boto_estil(bcrear_equip);
        f.add(bcrear_equip);
        
        b_endarrere = new JButton("Endarrere");
        b_endarrere.setBounds(160, 425, 125, 30);
        Funcions.boto_estil(b_endarrere);
        f.add(b_endarrere);
        
        
        b_endarrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                Funcions.gest_equips();
            }
        });
        
        bcrear_equip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                    
                    char tipus;
                    if(rbHome.isSelected()){
                        tipus = 'H';
                    }else if(rbDona.isSelected()){
                        tipus = 'D';
                    }else{
                        tipus = 'M';
                    }
                    
                    String cate_select = null;
                    for (Cate_enum val : Cate_enum.values()) {
                        if(cb_cate.getSelectedItem().equals(val.toString())){
                            cate_select = val.toString();
                        }
                    }
                    String nom_eq = null;
                    if (!ltf_nom.getText().trim().isEmpty()) {
                        try {
                            
                            System.out.println("Intent de creació de la capa...");
                            gestor = new GestorBDEmpresaJdbc();
                            System.out.println("Capa de persistència creada");
                            System.out.println("");
                            
                            int temp = Integer.parseInt(cb_temp.getSelectedItem().toString().substring(0, 4));
                            nom_eq =ltf_nom.getText().trim();

                            if(gestor.agafar_equip(nom_eq, tipus)==null){//Significa que l'equip no existeix
                                // crear_equip(String nom, char t, int any_eq, String cate) throws GestorBDEmpresaException {
                                gestor.crear_equip(ltf_nom.getText().trim(), tipus, temp, cate_select);
                                gestor.actualitzar_equips();
                                JOptionPane.showMessageDialog(f, "Equip creat");
                            }else{
                                JOptionPane.showMessageDialog(f, "L'equip ja existeix");
                                
                            }
                        } catch (GestorBDEmpresaException ex) {
                            JOptionPane.showMessageDialog(f, "No s'ha pogut crear, revisa les dades: "+ ex);
                            
                        }try {
                            System.out.println("Tancament de la capa");
                            gestor.tancarCapa();
                            System.out.println("Capa tancada");
                        } catch (GestorBDEmpresaException ex) {
                            System.out.println("\tError: " + ex.getMessage());
                        }
                    } else {
                        JOptionPane.showMessageDialog(f, "Omple tots els camps!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                 
            }
        });

        f.setVisible(true);
    }
   
}
