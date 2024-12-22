/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1_t7_vista_berzosamontellsalba;

import CapaPers.GestorBDEmpresaJdbc;
import Classes.Jugador;
import Enums.Sexe_enum;
import Persistencia.GestorBDEmpresaException;
import com.toedter.calendar.JDateChooser;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
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
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
/**
 *
 * @author Alma
 */
public class Crear_jugador {
    static private String nomClassePersistencia = null;
    static private JLabel ltext, lcog, lEnllaç,lsexe, lnif, ldata_naix, liban, lany_rev_medica, ladreca, lcod_postal, lpoblacio, lprovincia, lpais, ltitol;
    static private JTextField ltf_nom, ltf_cog, ltf_nif, ltf_iban, ltf_adreca, ltf_cod_postal, ltf_poblacio, ltf_provincia, ltf_pais;
    static private JButton bcrear_jug;
    static private JDateChooser dch_data_naix; 
    static private JComboBox cb_anys; 
    static private JButton bSeleccionar_img, b_endarrere;
    static private File imageFile;
    static private String img_path;
    BufferedImage img = null;
    File outputFile;

    public JFrame Crear_jugador() {
        
        JFrame f = new JFrame("El nostre club");

        f.setLayout(null);
        f.setSize(1000, 530);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setResizable(false);
        
        go(f);
        return f;
    }

    void go(JFrame f) {

        Funcions.crearBarraNavegacio(f, 'j');

               
        ltitol = new JLabel("Crear un Jugador ");
        ltitol.setBounds(200,50, 600, 30);
        ltitol.setFont(new Font("Arial", Font.BOLD, 40));
        f.add(ltitol);

               
        ltext = new JLabel("Nom: ");
        ltext.setBounds(200, 100, 160, 40);
        ltext.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(ltext);

        ltf_nom = new JTextField();
        ltf_nom.setBounds(380, 105, 200, 30);
        ltf_nom.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(ltf_nom);

        lcog = new JLabel("Cognoms: ");
        lcog.setBounds(200, 140, 160, 40);
        lcog.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(lcog);

        
        ltf_cog = new JTextField();
        ltf_cog.setBounds(380, 145, 200, 30);
        ltf_cog.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(ltf_cog);

        liban = new JLabel("IBAN: ");
        liban.setBounds(200, 180, 160, 40);
        liban.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(liban);

        
        ltf_iban = new JTextField();
        ltf_iban.setBounds(380, 185, 300, 30);
        ltf_iban.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(ltf_iban);

        
        lsexe = new JLabel("Sexe: ");
        lsexe.setBounds(200, 220, 160, 40);
        lsexe.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(lsexe);
        
        JRadioButton rbHome = new JRadioButton("Home");
        rbHome.setBounds(260, 220, 60, 40);
        JRadioButton rbDona = new JRadioButton("Dona");
        rbDona.setBounds(350, 220, 60, 40);
        ButtonGroup grupSexe = new ButtonGroup();
        grupSexe.add(rbHome);
        grupSexe.add(rbDona);
        rbHome.setSelected(true);
        
        f.add(rbHome);
        
        f.add(rbDona);
        
        ldata_naix = new JLabel("Data naix: ");
        ldata_naix.setBounds(200, 260, 160, 40);
        ldata_naix.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(ldata_naix);
        
        
        dch_data_naix = new JDateChooser();
        dch_data_naix.setBounds(380, 265, 200, 30);
        dch_data_naix.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(dch_data_naix);
        
        lnif = new JLabel("NIF: ");
        lnif.setBounds(200, 300, 160, 40);
        lnif.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(lnif);

        
        ltf_nif = new JTextField();
        ltf_nif.setBounds(380, 305, 200, 30);
        ltf_nif.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(ltf_nif);
        
        
        lany_rev_medica = new JLabel("Any_revisió mèdica: ");
        lany_rev_medica.setBounds(200, 340, 300, 40);
        lany_rev_medica.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(lany_rev_medica);

        
        int any_act = LocalDate.now().getYear();
        Integer[] anys = {any_act, any_act + 1};

        cb_anys = new JComboBox<>(anys);
        cb_anys.setBounds(420, 345, 100, 30);
        cb_anys.setFont(new Font("Arial", Font.PLAIN, 20));
        
        f.add(cb_anys);
        
        
        ladreca = new JLabel("Adreça: ");
        ladreca.setBounds(200, 380, 160, 40);
        ladreca.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(ladreca);

        
        ltf_adreca = new JTextField();
        ltf_adreca.setBounds(380, 385, 200, 30);
        ltf_adreca.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(ltf_adreca);
        
        lcod_postal = new JLabel("Codi postal: ");
        lcod_postal.setBounds(600, 380, 160, 40);
        lcod_postal.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(lcod_postal);

        
        ltf_cod_postal = new JTextField();
        ltf_cod_postal.setBounds(720, 385, 200, 30);
        ltf_cod_postal.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(ltf_cod_postal);
        
        lpoblacio = new JLabel("Població: ");
        lpoblacio.setBounds(600, 340, 160, 40);
        lpoblacio.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(lpoblacio);

        
        ltf_poblacio = new JTextField();
        ltf_poblacio.setBounds(720, 345, 200, 30);
        ltf_poblacio.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(ltf_poblacio);
        
        lprovincia = new JLabel("Provincia: ");
        lprovincia.setBounds(600, 300, 160, 40);
        lprovincia.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(lprovincia);

        
        ltf_provincia = new JTextField();
        ltf_provincia.setBounds(720, 305, 200, 30);
        ltf_provincia.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(ltf_provincia);
        
        lpais = new JLabel("Pais: ");
        lpais.setBounds(600, 260, 160, 40);
        lpais.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(lpais);

        
        ltf_pais = new JTextField();
        ltf_pais.setBounds(720, 265, 200, 30);
        ltf_pais.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(ltf_pais);
        
        bcrear_jug = new JButton("Crear");
        bcrear_jug.setBounds(860, 425, 125, 30);
        Funcions.boto_estil(bcrear_jug);
        f.add(bcrear_jug);
        
        b_endarrere = new JButton("Endarrere");
        b_endarrere.setBounds(160, 425, 125, 30);
        Funcions.boto_estil(b_endarrere);
        f.add(b_endarrere);
        
        
        bSeleccionar_img = new JButton("Seleccionar Imatge");
        bSeleccionar_img.setBounds(600, 100, 200, 40);
        bSeleccionar_img.setFont(new Font("Arial", Font.PLAIN, 20));
        Funcions.boto_estil(bSeleccionar_img);//Funcions.nav_boto_estil(bSeleccionar_img);

        b_endarrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               f.dispose();
                Funcions.agafar_gest_jugs();
            }
        });
        /*
        * S'agafa la imatge i es guarda a la carpeta amb el nif com a manera de trobar-la, 
        * si no s'ha popsat el nif no es pot seleccionar, i si existeix un jugador amb aquest tampoc
        */
        bSeleccionar_img.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                GestorBDEmpresaJdbc gestor;
                 Jugador j= null;
                try {
                    System.out.println("Intent de creació de la capa...");
                    gestor = new GestorBDEmpresaJdbc();
                    System.out.println("Capa de persistència creada");
                    System.out.println("");

                    j = gestor.agafar_jugador(ltf_nif.getText().trim().toUpperCase(), true);
                    
                    if(j!=null){
                        
                        JOptionPane.showMessageDialog(f, "Error: El jugador ja existeix");
                        return;
                    }
                    
                    if(!"".equals(ltf_nif.getText().trim()) && j==null){
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setDialogTitle("Seleccionar una imagen");
                        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes", "jpg", "png", "jpeg", "gif"));

                        int result = fileChooser.showOpenDialog(f);
                        if (result == JFileChooser.APPROVE_OPTION) {
                            
                            imageFile = fileChooser.getSelectedFile();
                            try {
                                img = ImageIO.read(imageFile);
                                String nif = ltf_nif.getText();
                                outputFile = new File("D:/jugador/" + nif + ".png");
                                img_path = outputFile.getPath();
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(f, "Error: No s'ha pogut guardar la imatge");
                                return;
                            }
                        }
                    }else{
                        JOptionPane.showMessageDialog(f, "Error: NIF incorrecte");

                    }
                    
                } catch (GestorBDEmpresaException ex) {
                        JOptionPane.showMessageDialog(f, "Error: En la capa");
                }
                
                
            }
        });

        f.add(bSeleccionar_img);
        
        
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
                        JOptionPane.showMessageDialog(f, "No s'ha pogut crear el jugador");
                }
            }
        });

        f.setVisible(true);
    }
   
}
