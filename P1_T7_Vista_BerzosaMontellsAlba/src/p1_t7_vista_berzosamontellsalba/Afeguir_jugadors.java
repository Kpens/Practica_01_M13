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
import p1_t7_vista_berzosamontellsalba.Funcions;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Afeguir_jugadors {
    private static List<Jugador> llista_de_jugadors_en_l_equip = null, llista_de_jugadors_possibles = null;
    static private JLabel ltext,lsexe, lnif, ldata_naix, ltitol;
    static private JTextField ltf_nom, ltf_nif;
    static private JComboBox<String> cb_cate, cb_temp, cb_tipus;
    static private JButton b_crear_equip, b_modificar_equip, b_endarrere, b_filtrar, b_exportar_dades, b_afeguir;
    static private JTable taula_jugadors_de_l_equip, taula_jugs_que_es_poden_aff;
    static private JRadioButton rbHome, rbDona, rbNo,rbSi, rbTit, rbAltre;
    static Jugador jug_seleccionat,jug_seleccionat_a_aff;
    static private JDateChooser dch_data_naix;
    static JFrame f = new JFrame("Afeguir Jugadors a l'equip");
    static private GestorBDEmpresaJdbc gestor;
    
    static private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
    
    public JFrame afeguir_jugs(Equip eq) {
     
        f.setLayout(null);
        
        // Configura la mida del JFrame segons les dimensions de la pantalla
        f.setSize(pantalla.width, pantalla.height);
        
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setResizable(false);
        
        
        Funcions.crearBarraNavegacio(f, 'e');
        
        ltitol = new JLabel("Afeguir Jugadors a l'equip");
        ltitol.setBounds((f.getWidth()/2)-295,50, 555, 40);
        ltitol.setFont(new Font("Arial", Font.BOLD, 38));
        f.add(ltitol);
        
        go(f, eq);
        f.setVisible(true);
        return f;
    }
    private static JFrame go(JFrame f, Equip eq) {
        String tipus;
        if(eq.getTipus().toString().equals("D")){
            tipus ="Femeni";
        }else if(eq.getTipus().toString().equals("H")){
            tipus ="Masculi";
        }else{
            tipus = "Mixte";
        }

        ltext = new JLabel("Equip: "+eq.getNom()+"        Categoria: "+eq.getCate().toString()+"        Tipus: "+tipus+"        Temporada: "+eq.getAny_eq()+"/"+Integer.toString(eq.getAny_eq()+1).substring(2));
        ltext.setBounds(100, 110, 1000, 40);
        ltext.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(ltext);
        
        
        
        try {
            System.out.println("Intent de creació de la capa...");
            gestor = new GestorBDEmpresaJdbc();
            System.out.println("Capa de persistència creada");
            System.out.println("");

        } catch (GestorBDEmpresaException ex) {
                JOptionPane.showMessageDialog(f, "Error: En la capa");
        }
        
        ltext = new JLabel("Nom: ");
        ltext.setBounds(50, 150, 80, 40);
        ltext.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(ltext);

        ltf_nom = new JTextField();
        ltf_nom.setBounds(110, 155, 160, 30);
        ltf_nom.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(ltf_nom);

        lnif = new JLabel("NIF: ");
        lnif.setBounds(280, 150, 140, 40);
        lnif.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(lnif);

        
        ltf_nif = new JTextField();
        ltf_nif.setBounds(330, 155, 140, 30);
        ltf_nif.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(ltf_nif);
        
        lsexe = new JLabel("Sexe: ");
        lsexe.setBounds(480, 150, 160, 40);
        lsexe.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(lsexe);
        
        rbHome = new JRadioButton("Home");
        rbHome.setBounds(550, 150, 60, 40);
        rbDona = new JRadioButton("Dona");
        rbDona.setBounds(610, 150, 60, 40);
        ButtonGroup grupSexe = new ButtonGroup();
        grupSexe.add(rbHome);
        grupSexe.add(rbDona);
        if(eq.getTipus().equals(Tipus_enum.D)){
            
            rbHome.setEnabled(false);
            rbDona.setSelected(true);
        }else if(eq.getTipus().equals(Tipus_enum.H)){
            rbDona.setEnabled(false);
            rbHome.setSelected(true);
        }else{
            rbHome.setSelected(true);
        }
        
        f.add(rbHome);
        
        f.add(rbDona);
        
        ltext = new JLabel("Revisió feta: ");
        ltext.setBounds(1180, 150, 160, 40);
        ltext.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(ltext);
        
        
        rbSi = new JRadioButton("Si");
        rbSi.setBounds(1310, 150, 40, 40);
        rbNo = new JRadioButton("No");
        rbNo.setBounds(1350, 150, 60, 40);
        ButtonGroup grup_rev_feta = new ButtonGroup();
        grup_rev_feta.add(rbSi);
        grup_rev_feta.add(rbNo);
        rbSi.setSelected(true);
        
        f.add(rbSi);
        
        f.add(rbNo);
        
        b_afeguir = new JButton("Afeguir jugador");
        Funcions.boto_estil(b_afeguir);
        b_afeguir.setBounds(690,440, 140, 30);
        b_afeguir.setVisible(true);
        b_afeguir.setEnabled(false);
        f.add(b_afeguir);
        
        ldata_naix = new JLabel("Data naix: ");
        ldata_naix.setBounds(680, 150, 160, 40);
        ldata_naix.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(ldata_naix);

        dch_data_naix = new JDateChooser();
        dch_data_naix.setBounds(790, 155, 140, 30);
        dch_data_naix.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(dch_data_naix);
                        
        ldata_naix = new JLabel("Categoria: ");
        ldata_naix.setBounds(940, 150, 160, 40);
        ldata_naix.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(ldata_naix);
        
        String[] cates = new String[Cate_enum.values().length +1];
        int i=1;
        boolean afegit=false;
        cates[0] = "Qualsevol";
        for (Cate_enum cat : Cate_enum.values()) {
            if(!afegit){
                cates[i] = cat+"";
                i++;
            }
            if(cat.equals(eq.getCate())){
                afegit = true;
            }
        }
        cb_cate = new JComboBox<>(cates);
        cb_cate.setFont(new Font("Arial", Font.PLAIN, 16));
        cb_cate.setBounds(1050, 150, 100, 40);
        
        f.add(cb_cate);
        
        
        rbTit = new JRadioButton("Titular");
        rbTit.setBounds(690, 400, 80, 40);
        rbAltre = new JRadioButton("Altre");
        rbAltre.setBounds(770, 400, 60, 40);
        ButtonGroup grup_aff_tipus = new ButtonGroup();
        grup_aff_tipus.add(rbTit);
        grup_aff_tipus.add(rbAltre);
        rbTit.setEnabled(false);
        rbAltre.setEnabled(true);
        if(!cate_de_jug(jug_seleccionat_a_aff).equals("n")){
            for (String cate : cates) {
                if(cate_de_jug(jug_seleccionat_a_aff).equals(cate)){
                    rbTit.setEnabled(true);
                }
            }
        }
        
        f.add(rbTit);
        
        f.add(rbAltre);
        
        
        
        b_filtrar = new JButton("Filtrar");
        Funcions.boto_estil(b_filtrar);
        b_filtrar.setBounds(f.getWidth()-150, f.getHeight()-160, 100, 30);
        b_filtrar.setVisible(true);
        
        b_crear_equip = new JButton("Crear Equip");
        Funcions.boto_estil(b_crear_equip);
        b_crear_equip.setBounds(110, f.getHeight()-100, 180, 30);
        b_crear_equip.setVisible(true);
        
        b_modificar_equip = new JButton("Modificar Equip");
        Funcions.boto_estil(b_modificar_equip);
        b_modificar_equip.setBounds(300, f.getHeight()-100, 180, 30);
        b_modificar_equip.setVisible(true);
        b_modificar_equip.setEnabled(false);
        
        b_endarrere = new JButton("Endarrere");
        b_endarrere.setBounds(f.getWidth()-300, f.getHeight()-100, 125, 30);
        Funcions.boto_estil(b_endarrere);
        
        b_exportar_dades = new JButton("Exportar dades");
        b_exportar_dades.setBounds(f.getWidth()-200, f.getHeight()-100, 125, 30);
        Funcions.boto_estil(b_exportar_dades);
        
        
        b_crear_equip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               f.dispose();
               Funcions.crear_equip();
            }
        });
        b_modificar_equip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               f.dispose();
               //Funcions.modificar_equip(equip_seleccionat);
            }
        });
        
        b_afeguir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //Funcions.modificar_equip(equip_seleccionat);
            }
        });
        b_endarrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               f.dispose();
                Funcions.agafar_menu_principal();
            }
        });
        
        b_exportar_dades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               f.dispose();
               //JDom --> exportar XML
               //JOptionPane.showMessageDialog(f, "S'han exportat les dades \n\n");
            }
        });
        
        taula_jugadors_de_l_equip(f);
        taula_jugs_que_es_poden_aff(f);
        b_filtrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                llista_de_jugadors_possibles = new ArrayList<>();
                List<Jugador> ab_filtrar = new ArrayList<>();
                Sexe_enum sexeSeleccionat = rbHome.isSelected() ? Sexe_enum.H : Sexe_enum.D;

                boolean rev_feta = rbSi.isSelected();
                Cate_enum cate_select = null;
                for (Cate_enum val : Cate_enum.values()) {
                    if(cb_cate.getSelectedItem().equals(val.toString())){
                        cate_select = val;
                    }
                }

                /*if(cb_cate.getSelectedItem().equals(Cate_enum.Alevi.toString())){
                    cate_select = Cate_enum.Alevi;
                }else if(cb_cate.getSelectedItem().equals(Cate_enum.Benjami.toString())){
                    cate_select = Cate_enum.Benjami;
                }else if(cb_cate.getSelectedItem().equals(Cate_enum.Cadet.toString())){
                    cate_select = Cate_enum.Cadet;
                }else if(cb_cate.getSelectedItem().equals(Cate_enum.Infantil.toString())){
                    cate_select = Cate_enum.Infantil;
                }else if(cb_cate.getSelectedItem().equals(Cate_enum.Juvenil.toString())){
                    cate_select = Cate_enum.Juvenil;
                }else if(cb_cate.getSelectedItem().equals(Cate_enum.Senior.toString())){
                    cate_select = Cate_enum.Senior;
                } */
                String busc_nom = ltf_nom.getText().trim();
                String busc_nif = ltf_nif.getText().trim();

                String busc_data_naix ="";
                /*
                * Com es retorna en Date i en un format incorrecte ho passem al format correcte en string
                */
                Date data = dch_data_naix.getDate();
                if (data != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    busc_data_naix = sdf.format(data);
                    System.out.println("Data seleccionada: " + busc_data_naix);
                } 
                try {
                    llista_de_jugadors_en_l_equip = gestor.mostrar_jugadors_per_equip(eq.getId_equip());
                    actualitzar_taula_jugadors_de_l_equip(taula_jugadors_de_l_equip, eq);
                    // Es filtra la llista de jugadors
                    ab_filtrar = gestor.llista_jugadors(sexeSeleccionat, busc_nom, busc_nif, busc_data_naix, cate_select, rev_feta);

                    if(!(ab_filtrar.isEmpty()||ab_filtrar==null)){
                        int edat;
                        for (Jugador jug : ab_filtrar) {
                            edat = LocalDate.now().getYear()- Integer.parseInt(jug.getData_naix().substring(0, 4));

                            if(edat<= eq.getCate().getEdatMaxima()){
                                llista_de_jugadors_possibles.add(jug);
                            }
                        }  
                    }else{
                        llista_de_jugadors_possibles=new ArrayList<>();
                    }
                    
                    
                    actualitzar_taula_jugs_que_es_poden_aff(taula_jugs_que_es_poden_aff);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(f, "Error al filtrar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });
        f.add(b_modificar_equip);
        f.add(b_filtrar);
        f.add(b_endarrere);
        f.add(b_crear_equip);
        
        return f;
    }
    
    static JFrame taula_jugadors_de_l_equip(JFrame f){
        taula_jugadors_de_l_equip = new JTable();
        taula_jugadors_de_l_equip.setFont(new Font("Arial", Font.PLAIN, 14));

        //taula_jugadors_de_l_equip.setEnabled(false);
        taula_jugadors_de_l_equip.setDefaultEditor(Object.class, null);

        taula_jugadors_de_l_equip.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        /*
        * D'aquesta forma puc elegir quin jugador vull modificar
        */
        taula_jugadors_de_l_equip.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    
                    int filaSeleccionada = taula_jugadors_de_l_equip.getSelectedRow();
                    if (filaSeleccionada != -1) {
                        jug_seleccionat = llista_de_jugadors_en_l_equip.get(filaSeleccionada);
                        //////
                        llista_de_jugadors_possibles=new ArrayList<>();
                        /*try {
                            llista_de_jugadors_possibles = gestor.llista_jugadors(Sexe_enum.H, nom_j, nif, data_naix_j, Cate_enum.Cadet, true);
                        } catch (GestorBDEmpresaException ex) {
                            Logger.getLogger(Afeguir_jugadors.class.getName()).log(Level.SEVERE, null, ex);
                        }*/
                        actualitzar_taula_jugs_que_es_poden_aff(taula_jugs_que_es_poden_aff);
                        b_modificar_equip.setEnabled(true);
                    }else{
                        
                        llista_de_jugadors_en_l_equip = new ArrayList<>();
                        actualitzar_taula_jugs_que_es_poden_aff(taula_jugs_que_es_poden_aff); 
                    }
                }
            }
        });

        JScrollPane scroll_taula_eq = new JScrollPane(taula_jugadors_de_l_equip);
        scroll_taula_eq.setBounds(80, 200, 600, 500);//la psocició de la taula_jugadors_de_l_equip
        f.add(scroll_taula_eq);
        return f;
    }
    
    private static void actualitzar_taula_jugadors_de_l_equip(JTable taula_jugs_actuals, Equip eq) {
            //error.setVisible(false);
        String[] columnes = {"ID_Jug", "NIF", "Nom Jugador","Cognoms", "Sexe", "data_naix", "Edat", "Categoria", "Titular"};
        Object[][] dades = new Object[llista_de_jugadors_en_l_equip.size()][columnes.length+1];

        int edat;
        String cate;
        for (int i = 0; i < llista_de_jugadors_en_l_equip.size(); i++) {
            Jugador jug = llista_de_jugadors_en_l_equip.get(i);
            edat = (LocalDate.now().getYear())-Integer.parseInt(jug.getData_naix().substring(0, 4));
            if(edat >= Cate_enum.Alevi.getEdatMinima()&& edat <= Cate_enum.Alevi.getEdatMaxima()){
                cate = "Alevi";
            }else if(edat >= Cate_enum.Benjami.getEdatMinima()&& edat <= Cate_enum.Benjami.getEdatMaxima()){
                cate = "Benjami";
            }else if(edat >= Cate_enum.Cadet.getEdatMinima()&& edat <= Cate_enum.Cadet.getEdatMaxima()){
                cate = "Cadet";
            }else if(edat >= Cate_enum.Infantil.getEdatMinima()&& edat <= Cate_enum.Infantil.getEdatMaxima()){
                cate = "Infantil";
            }else if(edat >= Cate_enum.Juvenil.getEdatMinima()&& edat <= Cate_enum.Juvenil.getEdatMaxima()){
                cate = "Juvenil";
            }else{
                cate = "Senior";
            }
            char titular = 'N';
            for (Map.Entry<Integer, Character> tit : eq.getJug_mem_titular().entrySet()) {
                if (tit.getKey().equals(jug.getId_jug())) {
                    titular = tit.getValue();
                }
            }

            dades[i][0] = jug.getId_jug(); 
            dades[i][1] = jug.getId_legal();
            dades[i][2] = jug.getNom();
            dades[i][3] = jug.getCog();
            dades[i][4] = jug.getSexe().toString();
            dades[i][5] = jug.getData_naix().substring(0, 10);
            dades[i][6] = edat+" anys";
            dades[i][7] = cate;
            dades[i][8] = titular;
        }

        DefaultTableModel model = new DefaultTableModel(dades, columnes) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        taula_jugs_actuals.setModel(model);

        TableColumn column;

        column = taula_jugs_actuals.getColumnModel().getColumn(0);
        column.setMaxWidth(50); 

        column = taula_jugs_actuals.getColumnModel().getColumn(1);
        column.setMaxWidth(150);             
        column = taula_jugs_actuals.getColumnModel().getColumn(2);
        column.setMaxWidth(100); 
        column = taula_jugs_actuals.getColumnModel().getColumn(3);
        column.setMaxWidth(150); 
        column = taula_jugs_actuals.getColumnModel().getColumn(4);
        column.setMaxWidth(40); 

        column = taula_jugs_actuals.getColumnModel().getColumn(5);
        column.setMaxWidth(200); 
        column = taula_jugs_actuals.getColumnModel().getColumn(6);
        column.setMaxWidth(60); 

        column = taula_jugs_actuals.getColumnModel().getColumn(7);
        column.setMaxWidth(100);
        column = taula_jugs_actuals.getColumnModel().getColumn(8);
        column.setMaxWidth(60);
        TableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        ((DefaultTableCellRenderer) centerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        taula_jugs_actuals.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        taula_jugs_actuals.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        taula_jugs_actuals.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        taula_jugs_actuals.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        taula_jugs_actuals.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        taula_jugs_actuals.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);

    


    }
    static JFrame taula_jugs_que_es_poden_aff(JFrame f){
        taula_jugs_que_es_poden_aff = new JTable();
        taula_jugs_que_es_poden_aff.setFont(new Font("Arial", Font.PLAIN, 14));

        //taula_jugadors_de_l_equip.setEnabled(false);
        taula_jugs_que_es_poden_aff.setDefaultEditor(Object.class, null);

        taula_jugs_que_es_poden_aff.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        /*
        * D'aquesta forma puc elegir quin jugador vull modificar
        */
        taula_jugs_que_es_poden_aff.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int filaSeleccionada = taula_jugs_que_es_poden_aff.getSelectedRow();
                    if (filaSeleccionada != -1) {
                        jug_seleccionat_a_aff = llista_de_jugadors_en_l_equip.get(filaSeleccionada);
                        b_afeguir.setEnabled(true);
                    }else{
                        b_afeguir.setEnabled(false);
                    }
                }
            }
        });

        JScrollPane scroll_taula_eq = new JScrollPane(taula_jugs_que_es_poden_aff);
        scroll_taula_eq.setBounds(840, 200, 600, 500);
        f.add(scroll_taula_eq);
        return f;
    }
    
    private static void actualitzar_taula_jugs_que_es_poden_aff(JTable taula) {
        //error.setVisible(false);
        String[] columnes = {"ID_Jug", "NIF", "Nom Jugador","Cognoms", "Sexe", "data_naix", "Edat", "Categoria"};
        Object[][] dades = new Object[llista_de_jugadors_possibles.size()][columnes.length+1];

        int edat;
        String cate;
        for (int i = 0; i < llista_de_jugadors_possibles.size(); i++) {
            Jugador jug = llista_de_jugadors_possibles.get(i);
            edat = (LocalDate.now().getYear())-Integer.parseInt(jug.getData_naix().substring(0, 4));

            cate = cate_de_jug(jug);
            dades[i][0] = jug.getId_jug(); 
            dades[i][1] = jug.getId_legal();
            dades[i][2] = jug.getNom();
            dades[i][3] = jug.getCog();
            dades[i][4] = jug.getSexe().toString();
            dades[i][5] = jug.getData_naix().substring(0, 10);
            dades[i][6] = edat+" anys";
            dades[i][7] = cate;
        }

        DefaultTableModel model = new DefaultTableModel(dades, columnes) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        taula.setModel(model);

        TableColumn column;

        column = taula.getColumnModel().getColumn(0);
        column.setMaxWidth(50); 

        column = taula.getColumnModel().getColumn(1);
        column.setMaxWidth(150);             
        column = taula.getColumnModel().getColumn(2);
        column.setMaxWidth(100); 
        column = taula.getColumnModel().getColumn(3);
        column.setMaxWidth(150); 
        column = taula.getColumnModel().getColumn(4);
        column.setMaxWidth(40); 

        column = taula.getColumnModel().getColumn(5);
        column.setMaxWidth(200); 
        column = taula.getColumnModel().getColumn(6);
        column.setMaxWidth(60); 

        column = taula.getColumnModel().getColumn(7);
        column.setMaxWidth(100);
        TableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        ((DefaultTableCellRenderer) centerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        taula.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        taula.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        taula.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        taula.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        taula.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

    }
    static String cate_de_jug(Jugador jug){
        String cate;
        if(jug!=null){
            int edat = (LocalDate.now().getYear())-Integer.parseInt(jug.getData_naix().substring(0, 4));
            if(edat >= Cate_enum.Alevi.getEdatMinima()&& edat <= Cate_enum.Alevi.getEdatMaxima()){
                cate = "Alevi";
            }else if(edat >= Cate_enum.Benjami.getEdatMinima()&& edat <= Cate_enum.Benjami.getEdatMaxima()){
                cate = "Benjami";
            }else if(edat >= Cate_enum.Cadet.getEdatMinima()&& edat <= Cate_enum.Cadet.getEdatMaxima()){
                cate = "Cadet";
            }else if(edat >= Cate_enum.Infantil.getEdatMinima()&& edat <= Cate_enum.Infantil.getEdatMaxima()){
                cate = "Infantil";
            }else if(edat >= Cate_enum.Juvenil.getEdatMinima()&& edat <= Cate_enum.Juvenil.getEdatMaxima()){
                cate = "Juvenil";
            }else{
                cate = "Senior";
            }
        }else{
            cate="n";
        }
        
        return cate;
    }
}