/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1_t7_vista_berzosamontellsalba;

import CapaPers.GestorBDEmpresaJdbc;
import Classes.Equip;
import Classes.Jugador;
import Enums.Sexe_enum;
import Enums.Tipus_enum;
import Persistencia.GestorBDEmpresaException;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Alma
 */
public class Modificar_jug {
    static private String nomClassePersistencia = null;
    static private JLabel ltext, lcog, lEnllaç,lsexe, lnif, ldata_naix, liban, lany_rev_medica, ladreca, lcod_postal, lpoblacio, lprovincia, lpais, ltitol;
    static private JTextField ltf_nom, ltf_cog, ltf_nif, ltf_iban, ltf_adreca, ltf_cod_postal, ltf_poblacio, ltf_provincia, ltf_pais;
    static private JButton bcrear_jug;
    static private JDateChooser dch_data_naix; 
    static private JComboBox cb_anys; 
    static private JButton bSeleccionar_img, b_endarrere;
    static private File imageFile;
    static private String img_path;
    static private GestorBDEmpresaJdbc gestor;
    BufferedImage img = null;
    File outputFile = new File("aaa");

    public JFrame Modificar_jug(Jugador j) {
        
        JFrame f = new JFrame("El nostre club");

        f.setLayout(null);
        f.setSize(1000, 530);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setResizable(false);
        
        go(f, j);
        return f;
    }


    void go(JFrame f, Jugador j) {
        
        try {
            try {
                System.out.println("Intent de creació de la capa...");
                gestor = new GestorBDEmpresaJdbc();
                System.out.println("Capa de persistència creada");
                System.out.println(""); 
            } catch (GestorBDEmpresaException ex) {
                JOptionPane.showMessageDialog(f, "Error: En la capa");
            }
            
            Funcions.crearBarraNavegacio(f, 'j');
            
            
            ltitol = new JLabel("Modificar un Jugador ");
            ltitol.setBounds(200,50, 600, 40);
            ltitol.setFont(new Font("Arial", Font.BOLD, 38));
            f.add(ltitol);
            
            
            ltext = new JLabel("Nom: ");
            ltext.setBounds(200, 100, 160, 40);
            ltext.setFont(new Font("Arial", Font.BOLD, 20));
            f.add(ltext);
            
            ltf_nom = new JTextField(j.getNom());
            ltf_nom.setBounds(380, 105, 200, 30);
            ltf_nom.setFont(new Font("Arial", Font.PLAIN, 20));
            f.add(ltf_nom);
            
            lcog = new JLabel("Cognoms: ");
            lcog.setBounds(200, 140, 160, 40);
            lcog.setFont(new Font("Arial", Font.BOLD, 20));
            f.add(lcog);
            
            
            ltf_cog = new JTextField(j.getCog());
            ltf_cog.setBounds(380, 145, 200, 30);
            ltf_cog.setFont(new Font("Arial", Font.PLAIN, 20));
            f.add(ltf_cog);
            
            liban = new JLabel("IBAN: ");
            liban.setBounds(200, 180, 160, 40);
            liban.setFont(new Font("Arial", Font.BOLD, 20));
            f.add(liban);
            
            
            ltf_iban = new JTextField(j.getIban());
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
            
            /*Serveix per a que quan un jugador forma part d'equips femenins i mixtes doncs que només pugui 
            * tindre sexe femeni, igual amb el masculí. En canvi, si només forma part de mixtes/no té equip pot 
            * tindre canvis de sexe            
            */
            try {
                
                Tipus_enum tenum = gestor.equip_mes_restrictiu_del_jug(j.getId_jug());
                System.out.println("tenum"+tenum.toString());
                switch (tenum) {
                    case H:
                        rbHome.setEnabled(true);
                        rbDona.setEnabled(false);
                        break;
                    case D:
                        rbHome.setEnabled(false);
                        rbDona.setEnabled(true);
                        break;
                    default:
                        rbHome.setEnabled(true);
                        rbDona.setEnabled(true);
                        break;
                }
            } catch (GestorBDEmpresaException ex) {
                Logger.getLogger(Modificar_jug.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            grupSexe.add(rbHome);
            grupSexe.add(rbDona);
            
            if(j.getSexe().equals(Sexe_enum.H)){
                rbHome.setSelected(true);
                rbDona.setSelected(false);
            }else{
                rbHome.setSelected(false);
                rbDona.setSelected(true);
            }
            
            f.add(rbHome);
            
            f.add(rbDona);
            
            ldata_naix = new JLabel("Data naix: ");
            ldata_naix.setBounds(200, 260, 160, 40);
            ldata_naix.setFont(new Font("Arial", Font.BOLD, 20));
            f.add(ldata_naix);
            
            
            SimpleDateFormat sdfEntrada = new SimpleDateFormat("yyyy-MM-dd");
            Date data = sdfEntrada.parse(j.getData_naix());
            
            dch_data_naix = new JDateChooser(data);
            dch_data_naix.setBounds(380, 265, 200, 30);
            dch_data_naix.setFont(new Font("Arial", Font.PLAIN, 20));
            f.add(dch_data_naix);
            
            lnif = new JLabel("NIF: ");
            lnif.setBounds(200, 300, 160, 40);
            lnif.setFont(new Font("Arial", Font.BOLD, 20));
            f.add(lnif);
            
            
            ltf_nif = new JTextField(j.getId_legal());
            ltf_nif.setBounds(380, 305, 200, 30);
            ltf_nif.setFont(new Font("Arial", Font.PLAIN, 20));
            ltf_nif.setEnabled(false);
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
            cb_anys.setSelectedItem(j.getAny_fi_rev());
            f.add(cb_anys);
            
            
            ladreca = new JLabel("Adreça: ");
            ladreca.setBounds(200, 380, 160, 40);
            ladreca.setFont(new Font("Arial", Font.BOLD, 20));
            f.add(ladreca);
            
            
            ltf_adreca = new JTextField(j.getAdreca());
            ltf_adreca.setBounds(380, 385, 200, 30);
            ltf_adreca.setFont(new Font("Arial", Font.PLAIN, 20));
            f.add(ltf_adreca);
            
            lcod_postal = new JLabel("Codi postal: ");
            lcod_postal.setBounds(600, 380, 160, 40);
            lcod_postal.setFont(new Font("Arial", Font.BOLD, 20));
            f.add(lcod_postal);
            
            
            ltf_cod_postal = new JTextField(j.getCodi_postal()+"");
            ltf_cod_postal.setBounds(720, 385, 200, 30);
            ltf_cod_postal.setFont(new Font("Arial", Font.PLAIN, 20));
            f.add(ltf_cod_postal);
            
            lpoblacio = new JLabel("Població: ");
            lpoblacio.setBounds(600, 340, 160, 40);
            lpoblacio.setFont(new Font("Arial", Font.BOLD, 20));
            f.add(lpoblacio);
            
            
            ltf_poblacio = new JTextField(j.getPoblacio());
            ltf_poblacio.setBounds(720, 345, 200, 30);
            ltf_poblacio.setFont(new Font("Arial", Font.PLAIN, 20));
            f.add(ltf_poblacio);
            
            lprovincia = new JLabel("Provincia: ");
            lprovincia.setBounds(600, 300, 160, 40);
            lprovincia.setFont(new Font("Arial", Font.BOLD, 20));
            f.add(lprovincia);
            
            
            ltf_provincia = new JTextField(j.getPoblacio());
            ltf_provincia.setBounds(720, 305, 200, 30);
            ltf_provincia.setFont(new Font("Arial", Font.PLAIN, 20));
            f.add(ltf_provincia);
            
            lpais = new JLabel("Pais: ");
            lpais.setBounds(600, 260, 160, 40);
            lpais.setFont(new Font("Arial", Font.BOLD, 20));
            f.add(lpais);
            
            
            ltf_pais = new JTextField(j.getPais());
            ltf_pais.setBounds(720, 265, 200, 30);
            ltf_pais.setFont(new Font("Arial", Font.PLAIN, 20));
            f.add(ltf_pais);
            
            bcrear_jug = new JButton("Modificar");
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
                    
                        
                        if(!"".equals(ltf_nif.getText().trim())){
                            JFileChooser fileChooser = new JFileChooser();
                            fileChooser.setDialogTitle("Seleccionar una imagen");
                            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes", "jpg", "png", "jpeg", "gif"));
                            
                            int result = fileChooser.showOpenDialog(f);
                            if (result == JFileChooser.APPROVE_OPTION) {
                                
                                imageFile = fileChooser.getSelectedFile();                               
                                try {
                                    img = ImageIO.read(imageFile);
                                    String nif = ltf_nif.getText();
                                    outputFile = new File("C:/jugadors/" + nif + ".png");
                                    img_path = outputFile.getPath();
                                } catch (IOException ex) {
                                    JOptionPane.showMessageDialog(f, "Error: No s'ha pogut guardar la imatge");
                                    return;
                                }
                            }
                        }else{
                            JOptionPane.showMessageDialog(f, "Error: NIF incorrecte");
                            
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

                            if(ltf_nom.getText().trim().length()<4){
                                JOptionPane.showMessageDialog(f, "El nom hauria de ser mínim 4 lletres");
                                return;
                            }

                            if(ltf_cog.getText().trim().length()<4){
                                JOptionPane.showMessageDialog(f, "El cognom hauria de ser mínim 4 lletres");
                                return;
                            }

                            if(ltf_adreca.getText().trim().length()<4){
                                JOptionPane.showMessageDialog(f, "L'adreça hauria de ser mínim 4 lletres");
                                return;
                            }

                            if(ltf_poblacio.getText().trim().length()<4){
                                JOptionPane.showMessageDialog(f, "La població hauria de ser mínim 4 lletres");
                                return;
                            }

                            if(ltf_provincia.getText().trim().length()<4){
                                JOptionPane.showMessageDialog(f, "La provincia hauria de ser mínim 4 lletres");
                                return;
                            }

                            if(ltf_pais.getText().trim().length()<4){
                                JOptionPane.showMessageDialog(f, "El pais hauria de ser mínim 4 lletres");
                                return;
                            }

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
                                JOptionPane.showMessageDialog(f, "El codi postal no és un nombre vàlid");
                                return;
                            }
                            if(dch_data_naix.getDate() != null){
                                data_naix = dch_data_naix.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
                                int any_naix= Integer.parseInt(data_naix.substring(0, 4)); 
                               
                                LocalDate dataSeleccionada = dch_data_naix.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                                if (dataSeleccionada.isAfter(LocalDate.now())) {//Si és més gran a la actual
                                    JOptionPane.showMessageDialog(f, "La data naix no pot ser més tard que l'actual!");
                                    return;
                                }else if((LocalDate.now().getYear())-any_naix<7){
                                    JOptionPane.showMessageDialog(f, "L'edat del jugador és massa petita! \n\n Tenim un mínim de 7 anys en aquest club :D");
                                    return;
                                }
                                
                                
                                List<Equip> eqs = gestor.equips_on_son_els_jug(j.getId_jug());
                                
                                /*
                                * Mira en tots els equips on és el jugador quins són els màxims i minims d'edat (tenint en compte si és tiular o no)
                                * Pd: crec que al final no era necessàri combertir-ho en una funció externa, però per si de cas ho deixarè d'aquesta manera
                                */
                                boolean continuar = Funcions.mirar_edat_correcte__si_es_titular_o_no(f, j, eqs, any_naix);
                                
                                
                                if(img_path == null){
                                   img_path = j.getFoto();
                                }
                                Sexe_enum sexe;
                                if(rbHome.isSelected()){
                                    
                                    sexe = Sexe_enum.H;
                                }else{
                                    sexe = Sexe_enum.D;
                                    
                                }
                                Jugador jug;
                                if(continuar){
                                
                                    try {
                                        //System.out.println(outputFile.getPath());
                                        //int id_jug, String adreca, int any_fi_rev, String cog, String data_naix, String foto, String iban, String id_legal, String nom, Sexe_enum sexe, String codi_postal, String poblacio, String provincia, String pais) throws Exception{
                                        //no funciona el tostring usare gettext
                                        jug = new Jugador(j.getId_jug(),ltf_adreca.getText().trim(),Integer.parseInt(cb_anys.getSelectedItem().toString()), ltf_cog.getText().trim(),data_naix, img_path, ltf_iban.getText().trim(), j.getId_legal(), ltf_nom.getText().trim(), sexe, ltf_cod_postal.getText().trim(), ltf_poblacio.getText().trim(), ltf_provincia.getText().trim(),ltf_pais.getText().trim());

                                        System.out.println("juga ab mod");
                                        gestor.modificar_jugador(jug);
                                        System.out.println("jug desp mod");

                                        if(!"aaa".equals(outputFile.getPath())){
                                            try {
                                                ImageIO.write(img, "png", outputFile);
                                            } catch (IOException ex) {
                                                Logger.getLogger(Crear_jugador.class.getName()).log(Level.SEVERE, null, ex);
                                            }

                                        }
                                        JOptionPane.showMessageDialog(f, "Jugador modificat");
                                    } catch (Exception ex) {
                                        Logger.getLogger(Modificar_jug.class.getName()).log(Level.SEVERE, null, ex);
                                    }
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
        } catch (ParseException ex) {
            Logger.getLogger(Modificar_jug.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}