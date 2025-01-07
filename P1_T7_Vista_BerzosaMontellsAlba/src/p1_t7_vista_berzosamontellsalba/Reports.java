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
import Persistencia.GestorBDEmpresaException;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
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
public class Reports {
    static JFrame f = new JFrame("Jasper Reports");
    private static List<Equip> llista_d_equips; 
    static private GestorBDEmpresaJdbc gestor;
    static private JLabel lExportar_a, titol, ltext;
    static private JTextField txtRuta;
    static private JButton b_equips, b_Exportar, b_endarrere;
    static private JRadioButton rbXML, rbCSV;
    private static List<Jugador> llista_de_jugadors = null;
    static private JLabel lsexe, lnif, ldata_naix, ltitol;
    static private JTextField ltf_nom, ltf_nif;
    static private JComboBox<String> cb_cate, cb_temp, cb_tipus;
    static private JTable taula_equip, taula_jug;
    static Equip equip_seleccionat;
    
    // Variables per dades de connexió amb JRS
    private static String urlJRS;
    private static String userJRS;
    private static String passwordJRS;
    
    
    static private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
    public JFrame jasper_rep() {
    
                
        f.setLayout(null);
        f.setSize(pantalla.width-400, pantalla.height-50);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setResizable(false);
       
        
        Funcions.crearBarraNavegacio(f, 'e');

        titol = new JLabel("Jasper Reports");
        titol.setFont(new Font("Arial", Font.BOLD, 38));
        titol.setBounds(f.getWidth()/2-150, 100, 300, 40);
        titol.setHorizontalAlignment(SwingConstants.CENTER);
        f.add(titol);
        
        go(f);
        return f;
    }

    private static void go(JFrame f) {
        
        ltext = new JLabel("Equip:");
        ltext.setBounds(110, 75, 600, 250);
        ltext.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(ltext);

        String[] cates = new String[Cate_enum.values().length +1];
        int i=1;
        cates[0] = "Qualsevol";
        for (Cate_enum cat : Cate_enum.values()) {
            cates[i] = cat+"";
            i++;
        }
        cb_cate = new JComboBox<>(cates);
        //cb_cate = new JComboBox<>(new String[]{"Qualsevol",Cate_enum.Alevi.toString(), Cate_enum.Benjami.toString(), Cate_enum.Cadet.toString(), Cate_enum.Infantil.toString(), Cate_enum.Juvenil.toString(), Cate_enum.Senior.toString()});
        cb_cate.setFont(new Font("Arial", Font.PLAIN, 16));
        cb_cate.setBounds(f.getWidth()-290, 190, 100, 40);
        f.add(cb_cate);
        
        List<Temporada> temp = null;
        try {
            System.out.println("Intent de creació de la capa...");
            gestor = new GestorBDEmpresaJdbc();
            System.out.println("Capa de persistència creada");
            System.out.println("");
            temp = gestor.llista_temporades();
            

        } catch (GestorBDEmpresaException ex) {
                JOptionPane.showMessageDialog(f, "Error: En la capa");
                return;
        }
               
        if (temp != null) {
            String[] tempo= new String[temp.size()+1];
            i=1;
            tempo[0]="Qualsevol";
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
            cb_temp = new JComboBox<>(tempo);
            cb_temp.setFont(new Font("Arial", Font.PLAIN, 16));
            cb_temp.setBounds(f.getWidth()-160, 190, 100, 40);
            if(existeix){
                cb_temp.setSelectedItem(LocalDate.now().getYear());
            }
            
            f.add(cb_temp);
            
        } else {
            JOptionPane.showConfirmDialog(f, "La llista de temporades és buida.");
        }        
        
        
        b_equips = new JButton("Cargar equips");
        b_equips.setBounds(f.getWidth()-290, 650, 150, 30);
        Funcions.boto_estil(b_equips);
        f.add(b_equips);

        taula_equip(f);
        b_Exportar = new JButton("Crear Jasper Reports");
        b_Exportar.setBounds(f.getWidth()-280, 720, 200, 30);
        Funcions.boto_estil(b_Exportar);
        f.add(b_Exportar);
        
        b_endarrere = new JButton("Endarrere");
        b_endarrere.setBounds(80, 720, 150, 30);
        Funcions.boto_estil(b_endarrere);
        f.add(b_endarrere);
        
        b_equips.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String cate_select = null;
                    for (Cate_enum val : Cate_enum.values()) {
                        if(cb_cate.getSelectedItem().equals(val.toString())){
                            cate_select = val.toString();
                        }
                    }

                    String temp_select = (String) cb_temp.getSelectedItem();
                    int temporada;
                    //System.out.println(cb_temp.getSelectedItem());
                    if(cb_temp.getSelectedItem().equals("Qualsevol")){
                        temporada=0;
                    }else{
                        temporada = Integer.parseInt(temp_select.substring(0, 4));
                    }
                    // Es filtra la llista de jugadors
                    llista_d_equips = gestor.llistar_equips(cate_select, temporada, 'n', null, "asc");
                                        
                    actualitzar_taula_eq(taula_equip);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(f, "Error al filtrar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        b_endarrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               f.dispose();
                Funcions.gest_equips();
            }
        });
        

        b_Exportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //f.dispose();
                    int temp =-1, cate=-1, id_equip;
                    
                    if(equip_seleccionat==null){
                        id_equip = -1;
                        if(cb_temp.getSelectedIndex()!=0){
                            temp =Integer.parseInt(cb_temp.getSelectedItem().toString().substring(0, 4));
                        }
                        if(cb_cate.getSelectedIndex()!=0){
                            cate =cb_cate.getSelectedIndex();
                        }
                    }else{
                        id_equip =equip_seleccionat.getId_equip();
                    }
                    informeJRS(temp, cate, id_equip);
                } catch (IOException ex) {
                    Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        f.setLocationRelativeTo(null);
        f.setVisible(true);
        
        String fitxerConfigJRS = "informesJRS.xml";
        try {
            String info=null;
            Properties props = new Properties();
            props.loadFromXML(new FileInputStream(fitxerConfigJRS));
            String[] claus = {"url", "user", "password"};
            String[] valors = new String[3];
            for (i = 0; i < claus.length; i++) {
                valors[i] = props.getProperty(claus[i]);
                if (valors[i] == null || valors[i].isEmpty()) {
                    info =(info+"\nNo es troba clau " + valors[i] + " en fitxer " + fitxerConfigJRS);

                }
                if(info!=null){
                    JOptionPane.showMessageDialog(f, "Afegeix una data de naixement abans de continuar");
                }
            }
            urlJRS = valors[0];
            userJRS = valors[1];
            passwordJRS = valors[2];
            JOptionPane.showMessageDialog(f, "Paràmetres per connectar amb JRS recuperats.");

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(f, "No es troba fitxer " + fitxerConfigJRS + " - No es podrà executar cap informe");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(f, ex.getMessage() + " - Probablement no es podrà executar cap informe");
        }
        
        
    }
    static JFrame taula_equip(JFrame f){
        taula_equip = new JTable();
        taula_equip.setFont(new Font("Arial", Font.PLAIN, 14));

        //taula_equip.setEnabled(false);
        taula_equip.setDefaultEditor(Object.class, null);

        taula_equip.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        /*
        * D'aquesta forma puc elegir quin jugador vull modificar
        */
        taula_equip.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    
                    int filaSeleccionada = taula_equip.getSelectedRow();
                    if (filaSeleccionada != -1) {
                        equip_seleccionat = llista_d_equips.get(filaSeleccionada);
                        System.out.println(equip_seleccionat.toString());
                    }else{
                        
                    }
                }
            }
        });

        JScrollPane scroll_taula_eq = new JScrollPane(taula_equip);
        scroll_taula_eq.setBounds(210, 190, 600, 500);//la psocició de la taula_equip
        f.add(scroll_taula_eq);
        return f;
    }
    
    private static void actualitzar_taula_eq(JTable taula_equip) {
            String[] columnes = {"ID_Equip", "Nom", "Temporada", "Num. jugadors", "Categoria", "Tipus"};
            Object[][] dades = new Object[llista_d_equips.size()][columnes.length];

            String tipus;
            for (int i = 0; i < llista_d_equips.size(); i++) {
                Equip jug = llista_d_equips.get(i);
                
                switch (jug.getTipus().getValue()) {
                    case 'D':
                        tipus = "Femeni";
                        break;
                    case 'H':
                        tipus = "Masculi";
                        break;
                    default:
                        tipus="Mixte";
                        break;
                }
                
                dades[i][0] = jug.getId_equip();
                dades[i][1] = jug.getNom();
                dades[i][2] = jug.getTemporada();
                dades[i][3] = jug.getJug_mem().size();
                dades[i][4] = jug.getCate();
                dades[i][5] = tipus;
            }

            DefaultTableModel model = new DefaultTableModel(dades, columnes) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            taula_equip.setModel(model);

            TableColumn column;

            column = taula_equip.getColumnModel().getColumn(0);
            column.setMaxWidth(60); 
            
            column = taula_equip.getColumnModel().getColumn(1);
            column.setMaxWidth(160);             
            column = taula_equip.getColumnModel().getColumn(2);
            column.setMaxWidth(120); 
            column = taula_equip.getColumnModel().getColumn(3);
            column.setMaxWidth(90); 
            
            column = taula_equip.getColumnModel().getColumn(4);
            column.setMaxWidth(100); 
            column = taula_equip.getColumnModel().getColumn(5);
            column.setMaxWidth(100); 
            TableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            ((DefaultTableCellRenderer) centerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
            taula_equip.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
            taula_equip.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
            taula_equip.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

    }
    
    private static void informeJRS(int temp, int cate, int id_equip) throws IOException {
        int BUFFER_SIZE = 4096;
        boolean entrat = false;
        String url = urlJRS + "FitxaEquip.pdf";
        //+ "?Temporada="+"&?Categoria="+"&?Equip="
        // Emplenem el paràmetre "codi" de l'informe
        // Si hi ha més paràmetres a passar, cal concatenar-los com "&" com:
        // + "&nomParametre=valor&nomParametre=valor..."
        
        if(temp!=-1){
            if(entrat){
                url = url +"&";
            }
            url = url +"?Temporada="+temp;
            entrat=true;
        }
        if(cate!=-1){
            if(entrat){
                url = url +"&";
            }
            url = url +"?Categoria="+cate;
            entrat=true;
        }
        if(id_equip!=-1){
            if(entrat){
                url = url +"&";
            }
            url = url +"?Equip="+id_equip;
            entrat=true;
        }
        
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        String autenticacio = Base64.getEncoder().encodeToString((userJRS + ":" + passwordJRS).getBytes());
        con.setRequestProperty("Authorization", "Basic " + autenticacio);
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = con.getHeaderField("Content-Disposition");
            String contentType = con.getContentType();
            int contentLength = con.getContentLength();

            if (disposition != null) {
                // Obtenir el nom del fitxer a partir de la capçalera (Content-Disposition)
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
            } else {
                // Obtenir el nom del fitxer de dins la URL
                int posArguments = url.lastIndexOf("?");
                if (posArguments == -1) { // No hi ha arguments
                    fileName = url.substring(url.lastIndexOf("/") + 1,
                            url.length());
                } else { // Hi ha arguments i cal eliminar-los per obtenir el nom del fitxer
                    fileName = url.substring(url.lastIndexOf("/") + 1, posArguments);
                }
            }

//            System.out.println("Content-Type = " + contentType);
//            System.out.println("Content-Disposition = " + disposition);
//            System.out.println("Content-Length = " + contentLength);
//            System.out.println("fileName = " + fileName);
//            System.out.println("url = " + url);

            // Obrim InputStream des de HTTP connection
            InputStream inputStream = con.getInputStream();
            // Obrim OutputStream per enregistrar el fitxer
            FileOutputStream outputStream = new FileOutputStream(fileName);
            // Llegim i escrivim
            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            inputStream.close();

//            System.out.println("Arxiu descarregat");
            // Intentem obrir-lo en alguna aplicació del SO
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().open(new File(fileName));
                } catch (IOException ex) {
                    System.out.println("No hi ha aplicacions disponibles per obrir el fitxer");
                }
            }
        } else {
            JOptionPane.showMessageDialog(f, "Mètode 'GET' : " + url+"\nCodi resposta: " + responseCode+"\nCap fitxer a descarregar");
        }
        con.disconnect();
    }
}
