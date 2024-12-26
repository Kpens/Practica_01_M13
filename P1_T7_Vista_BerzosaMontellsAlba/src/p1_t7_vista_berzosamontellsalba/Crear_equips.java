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
 */
public class Crear_equips {
    static private String nomClassePersistencia = null;
    static private JLabel ltext, lcog, lEnllaç,lsexe, lnif, ldata_naix, liban, lany_rev_medica, ladreca, lcod_postal, lpoblacio, lprovincia, lpais, ltitol;
    static private JTextField ltf_nom, ltf_cog, ltf_nif, ltf_iban, ltf_adreca, ltf_cod_postal, ltf_poblacio, ltf_provincia, ltf_pais;
    static private JButton bcrear_jug;
    static private JComboBox<String> cb_cate, cb_temp, cb_tipus;
    static private JDateChooser dch_data_naix; 
    static private JComboBox cb_anys; 
    static private JButton bSeleccionar_img, b_endarrere;
    static private File imageFile;
    static private String img_path;
    static private GestorBDEmpresaJdbc gestor;
    static private BufferedImage img = null;
    static private File outputFile;
    
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
        ltf_nom.setBounds(380, 105, 200, 30);
        ltf_nom.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(ltf_nom);
        
        List<Temporada> temp = null;
        try {
            System.out.println("Intent de creació de la capa...");
            gestor = new GestorBDEmpresaJdbc();
            System.out.println("Capa de persistència creada");
            System.out.println("");


            temp = gestor.llista_temporades();
            

        } catch (GestorBDEmpresaException ex) {
                JOptionPane.showMessageDialog(f, "Error: En la capa");
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
            cb_temp.setBounds(630, 100, 100, 40);
            if(existeix){
                cb_temp.setSelectedItem(temp_act);

            }
            
            f.add(cb_temp);
            
        } else {
            System.out.println("La llista de temporades és buida.");
        }    
        
        lsexe = new JLabel("Tipus: ");
        lsexe.setBounds(200, 220, 160, 40);
        lsexe.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(lsexe);
        
        JRadioButton rbHome = new JRadioButton("Home");
        rbHome.setBounds(260, 220, 60, 40);
        JRadioButton rbDona = new JRadioButton("Dona");
        rbDona.setBounds(350, 220, 60, 40);
        JRadioButton rbMixte = new JRadioButton("Mixte");
        rbMixte.setBounds(440, 220, 60, 40);
        ButtonGroup grupSexe = new ButtonGroup();
        grupSexe.add(rbHome);
        grupSexe.add(rbDona);
        grupSexe.add(rbMixte);
        rbHome.setSelected(true);
        
        f.add(rbHome);
        
        f.add(rbDona);
        f.add(rbMixte);
        
        
        
        
        
        bcrear_jug = new JButton("Crear");
        bcrear_jug.setBounds(860, 425, 125, 30);
        Funcions.boto_estil(bcrear_jug);
        f.add(bcrear_jug);
        
        b_endarrere = new JButton("Endarrere");
        b_endarrere.setBounds(160, 425, 125, 30);
        Funcions.boto_estil(b_endarrere);
        f.add(b_endarrere);
        
        
        
        
        bcrear_jug.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestorBDEmpresaJdbc gestor;
                try {
                    String data_naix = null;
                    System.out.println("Intent de creació de la capa...");
                    gestor = new GestorBDEmpresaJdbc();
                    System.out.println("Capa de persistència creada");
                    System.out.println("");
                    
                    /*
                    * Reviso que s'haguin omplert tots els espais abans de revisar si són correctes
                    */
                    if (!ltf_nom.getText().trim().isEmpty() && !ltf_cog.getText().trim().isEmpty() && !ltf_nif.getText().trim().isEmpty() && grupSexe.getSelection() != null && dch_data_naix.getDate() != null && !ltf_iban.getText().trim().isEmpty() && cb_anys.getSelectedItem() != null && !ltf_adreca.getText().trim().isEmpty() && !ltf_cod_postal.getText().trim().isEmpty() && !ltf_poblacio.getText().trim().isEmpty() && !ltf_provincia.getText().trim().isEmpty() && !ltf_pais.getText().trim().isEmpty()) {

                        
                        if(!Jugador.nif_valid(ltf_nif.getText().trim())){
                            JOptionPane.showMessageDialog(f, "NIF incorrecte!");
                            return;
                        }
                        if(!Jugador.iban_valid(ltf_iban.getText().trim())){
                            JOptionPane.showMessageDialog(f, "Iban incorrecte!");
                            return;
                        }
                        
                        try {
                            int cod_postal = Integer.parseInt(ltf_cod_postal.getText().trim());
                        } catch (NumberFormatException nfe) {
                            throw new GestorBDEmpresaException("El codi postal no és un nombre vàlid");
                        }
                        
                        if(dch_data_naix.getDate() != null){
                            data_naix = dch_data_naix.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
                                
                            if(img_path == null){
                                //System.out.println(dch_data_naix.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString());
                                JOptionPane.showMessageDialog(f, "Afegeix una imatge abans de continuar");

                            }else{
                                //System.out.println(outputFile.getPath());
                                gestor.crear_jugador(ltf_nom.getText().trim(), ltf_cog.getText().trim(), Sexe_enum.H, data_naix, ltf_nif.getText().trim(), ltf_iban.getText().trim(), Integer.parseInt(cb_anys.getSelectedItem().toString()), ltf_adreca.getText().trim(), ltf_cod_postal.getText().trim(), ltf_poblacio.getText().trim(), img_path, ltf_provincia.getText().trim(), ltf_pais.getText().trim());
                               
                                try {
                                    ImageIO.write(img, "png", outputFile);
                                } catch (IOException ex) {
                                    Logger.getLogger(Crear_jugador.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                JOptionPane.showMessageDialog(f, "Jugador creat i imatge afegida");
                                
                            }

                        }else{
                            JOptionPane.showMessageDialog(f, "Afegeix una data de naixement abans de continuar");

                        }
                    } else {
                        JOptionPane.showMessageDialog(f, "Omple tots els camps!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    
                } catch (GestorBDEmpresaException ex) {
                        JOptionPane.showMessageDialog(f, "No s'ha pogut crear l'equip");
                }
            }
        });

        f.setVisible(true);
    }
   
}
