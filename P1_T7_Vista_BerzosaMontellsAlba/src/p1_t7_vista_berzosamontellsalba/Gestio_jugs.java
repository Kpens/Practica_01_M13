/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1_t7_vista_berzosamontellsalba;
import CapaPers.GestorBDEmpresaJdbc;
import Classes.Equip;
import Classes.Jugador;
import Enums.Cate_enum;
import Enums.Sexe_enum;
import Persistencia.GestorBDEmpresaException;
import com.toedter.calendar.JDateChooser;
import p1_t7_vista_berzosamontellsalba.Funcions;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
public class Gestio_jugs {
    private static List<Jugador> llista_de_jugadors; 
    private static List<Equip> llista_d_equips; 
    static private JLabel ltext,lsexe, lnif, ldata_naix, titol;
    static private JTextField ltf_nom, ltf_nif;
    static private JComboBox<String> cb_cate;
    static private JButton b_crear_jug, b_modificar_jug, b_eliminar_jug, b_endarrere, b_filtrar, b_exportar_dades;
    static private JTable taula_equip, taula_jug;
    static Jugador jugador_seleccionat;
    static private JRadioButton rbHome, rbDona, rbNo,rbSi;
    static private JDateChooser dch_data_naix;
    static JFrame f = new JFrame("Gestió de jugadors");
    static private GestorBDEmpresaJdbc gestor;
    
    static private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
    public static JFrame gestio_jugs() {
        f.setLayout(null);
        f.setSize(pantalla.width, pantalla.height);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setResizable(false);
        
        
        Funcions.crearBarraNavegacio(f, 'j');
        
        titol = new JLabel("Gestió de jugadors");
        titol.setBounds((f.getWidth()/2)-205,50, 405, 40);
        titol.setFont(new Font("Arial", Font.BOLD, 38));
        f.add(titol);
        

        /*JPanel pan_principal = new JPanel(new BorderLayout());

        JPanel barraNavegacio = crearBarraNavegacio();
        pan_principal.add(barraNavegacio, BorderLayout.PAGE_START);

        JPanel pan_central = new JPanel(new BorderLayout());
        JLabel titol = new JLabel("Gestió de jugadors", JLabel.CENTER);
        titol.setFont(new Font("Arial", Font.BOLD, 40));

        pan_central.add(titol, BorderLayout.NORTH);
        pan_central.add(crearContingutPrincipal(), BorderLayout.CENTER);
        
        pan_principal.add(pan_central, BorderLayout.CENTER);

        f.add(pan_principal);*/
        go(f);
        f.setVisible(true);
        return f;
        
    }
    /*
    * Uso panells perquè m'ha semblat que seria més útil amb les taules
    */

    
    private static JFrame go(JFrame f) {
        
        
        /*JPanel panellContingut = new JPanel();
        panellContingut.setLayout(new BorderLayout());

        JLabel titol = new JLabel("Gestió de Jugadors", SwingConstants.CENTER);
        titol.setFont(new Font("Arial", Font.BOLD, 32));
        panellContingut.add(titol, BorderLayout.NORTH);

        JPanel panellCercaSexe = new JPanel();
        panellCercaSexe.setLayout(new FlowLayout());

        JTextField busca_nom = new JTextField(15);
        busca_nom.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField busca_nif = new JTextField(15);
        busca_nif.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField busca_data_naix = new JTextField(15);
        busca_data_naix.setFont(new Font("Arial", Font.PLAIN, 16));

      */
        
        try {
            System.out.println("Intent de creació de la capa...");
            gestor = new GestorBDEmpresaJdbc();
            System.out.println("Capa de persistència creada");
            System.out.println("");

        } catch (GestorBDEmpresaException ex) {
                JOptionPane.showMessageDialog(f, "Error: En la capa");
        }
        
        ltext = new JLabel("Nom: ");
        ltext.setBounds(50, 100, 80, 40);
        ltext.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(ltext);

        ltf_nom = new JTextField();
        ltf_nom.setBounds(110, 105, 160, 30);
        ltf_nom.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(ltf_nom);

        lnif = new JLabel("NIF: ");
        lnif.setBounds(280, 100, 140, 40);
        lnif.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(lnif);

        
        ltf_nif = new JTextField();
        ltf_nif.setBounds(330, 105, 140, 30);
        ltf_nif.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(ltf_nif);
        
        lsexe = new JLabel("Sexe: ");
        lsexe.setBounds(480, 100, 160, 40);
        lsexe.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(lsexe);
        
        rbHome = new JRadioButton("Home");
        rbHome.setBounds(550, 100, 60, 40);
        rbDona = new JRadioButton("Dona");
        rbDona.setBounds(610, 100, 60, 40);
        ButtonGroup grupSexe = new ButtonGroup();
        grupSexe.add(rbHome);
        grupSexe.add(rbDona);
        rbHome.setSelected(true);
        
        f.add(rbHome);
        
        f.add(rbDona);
        
        ltext = new JLabel("Revisió feta: ");
        ltext.setBounds(1180, 100, 160, 40);
        ltext.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(ltext);
        
        
        rbSi = new JRadioButton("Si");
        rbSi.setBounds(1310, 100, 40, 40);
        rbNo = new JRadioButton("No");
        rbNo.setBounds(1350, 100, 60, 40);
        ButtonGroup grup_rev_feta = new ButtonGroup();
        grup_rev_feta.add(rbSi);
        grup_rev_feta.add(rbNo);
        rbSi.setSelected(true);
        
        f.add(rbSi);
        
        f.add(rbNo);
        
        ldata_naix = new JLabel("Data naix: ");
        ldata_naix.setBounds(680, 100, 160, 40);
        ldata_naix.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(ldata_naix);

        dch_data_naix = new JDateChooser();
        dch_data_naix.setBounds(790, 105, 140, 30);
        dch_data_naix.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(dch_data_naix);
                        
        ldata_naix = new JLabel("Categoria: ");
        ldata_naix.setBounds(940, 100, 160, 40);
        ldata_naix.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(ldata_naix);
        
        String[] cates = new String[Cate_enum.values().length +1];
        int i=1;
        cates[0] = "Qualsevol";
        for (Cate_enum cat : Cate_enum.values()) {
            cates[i] = cat+"";
            i++;
        }
        cb_cate = new JComboBox<>(cates);
        cb_cate.setFont(new Font("Arial", Font.PLAIN, 16));
        cb_cate.setBounds(1050, 100, 100, 40);
        
        f.add(cb_cate);
        
        b_filtrar = new JButton("Filtrar");
        Funcions.boto_estil(b_filtrar);
        b_filtrar.setBounds(f.getWidth()-150, f.getHeight()-160, 100, 30);
        b_filtrar.setVisible(true);
        
        b_crear_jug = new JButton("Crear jugador");
        Funcions.boto_estil(b_crear_jug);
        b_crear_jug.setBounds(110, f.getHeight()-100, 180, 30);
        b_crear_jug.setVisible(true);
        
        b_modificar_jug = new JButton("Modificar jugador");
        Funcions.boto_estil(b_modificar_jug);
        b_modificar_jug.setBounds(300, f.getHeight()-100, 180, 30);
        b_modificar_jug.setVisible(true);
        b_modificar_jug.setEnabled(false);
        
        b_eliminar_jug = new JButton("Eliminar jugador");
        Funcions.boto_estil(b_eliminar_jug);
        b_eliminar_jug.setBounds(490, f.getHeight()-100, 180, 30);
        b_eliminar_jug.setVisible(true);
        b_eliminar_jug.setEnabled(false);
        
        b_endarrere = new JButton("Endarrere");
        b_endarrere.setBounds(f.getWidth()-300, f.getHeight()-100, 125, 30);
        Funcions.boto_estil(b_endarrere);
        
        b_exportar_dades = new JButton("Exportar dades");
        b_exportar_dades.setBounds(f.getWidth()-200, f.getHeight()-100, 125, 30);
        Funcions.boto_estil(b_exportar_dades);
        
        
        b_crear_jug.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               f.dispose();
                Funcions.crear_jug();
            }
        });
        b_modificar_jug.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               f.dispose();
                Funcions.modificar_jug(jugador_seleccionat);
            }
        });
        
        b_eliminar_jug.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //f.dispose();
                    //Funcions.eliminar_jug(jugador_seleccionat);
                    List<Equip> eqs = gestor.equips_on_son_els_jug(jugador_seleccionat.getId_jug());
                    if(eqs.isEmpty()){
                        int resposta = JOptionPane.showConfirmDialog(f, "Estàs segur d'eliminar: "+jugador_seleccionat.getNom()+" "+jugador_seleccionat.getCog()+"\n amb nif: "+jugador_seleccionat.getId_legal(), "Confirmar eliminació", JOptionPane.YES_NO_OPTION);

                        if (resposta == JOptionPane.YES_OPTION) {//Si selecciona 'si' s'elimina el jugador i la seva foto
                            gestor.eliminar_jugador(jugador_seleccionat.getId_jug());
                            
                            String rutaFoto = "D:/jugador/" + jugador_seleccionat.getId_legal() + ".png";

                            jugador_seleccionat = null;
                            File foto = new File(rutaFoto);

                            if (foto.exists()) {
                                if (foto.delete()) {
                                    System.out.println("Foto eliminada");
                                } else {
                                    System.out.println("No s'ha eliminat la foto del jugador");
                                }
                            } else {
                                System.out.println("La foto no existeix a la ruta: " + rutaFoto);
                            }
                            JOptionPane.showMessageDialog(f, "Eliminat");
                            filtracio();
                        } else {
                            JOptionPane.showMessageDialog(f, "El jugador no s'ha eliminat");
                        }
                    }else{
                        JOptionPane.showMessageDialog(f, "El jugador esta en "+eqs.size()+" equips");
                        
                    }
                
                } catch (GestorBDEmpresaException ex) {
                    Logger.getLogger(Gestio_jugs.class.getName()).log(Level.SEVERE, null, ex);
                }
                
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
        
        taula_equip(f);
        taula_jug(f);
        
        b_filtrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtracio();
            }
        });
        f.add(b_modificar_jug);
        f.add(b_eliminar_jug);
        f.add(b_filtrar);
        f.add(b_endarrere);
        f.add(b_crear_jug);
        
        /*
        panellCercaSexe.add(new JLabel("Sexe:"));
        panellCercaSexe.add(rbHome);
        panellCercaSexe.add(rbDona);
        panellCercaSexe.add(new JLabel("Nom:"));
        panellCercaSexe.add(busca_nom);
        panellCercaSexe.add(new JLabel("NIF:"));
        panellCercaSexe.add(busca_nif);
        panellCercaSexe.add(new JLabel("Data_naix:"));
        panellCercaSexe.add(busca_data_naix);
        panellCercaSexe.add(new JLabel("Categories:"));
        panellCercaSexe.add(cercaBox);
        
        JTable taula_jug = new JTable();
        taula_jug.setFont(new Font("Arial", Font.PLAIN, 14));

        //taula_jug.setEnabled(false);
        taula_jug.setDefaultEditor(Object.class, null);

        taula_jug.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        *//*
        * D'aquesta forma puc elegir quin jugador vull modificar
        *//*
        taula_jug.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int filaSeleccionada = taula_jug.getSelectedRow();
                    if (filaSeleccionada != -1) {
                        jugador_seleccionat = llista_de_jugadors.get(filaSeleccionada);

                        b_modificar_jug.setEnabled(true);
                    }else{
                        
                        b_modificar_jug.setEnabled(false);
                    }
                }
            }
        });

        JScrollPane scrollTaula = new JScrollPane(taula_jug);
        scrollTaula.setPreferredSize(new Dimension(600, 500));

        JPanel panellTaula = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panellTaula.add(scrollTaula);

        panellContingut.add(panellCercaSexe, BorderLayout.NORTH);
        panellContingut.add(panellTaula, BorderLayout.CENTER);

        JPanel panellBotons = new JPanel();
        panellBotons.setLayout(new FlowLayout());
        JButton b_crear_jug = new JButton("Crear un jugador");
        JButton b_endarrere = new JButton("Endarrere");
        b_modificar_jug.setEnabled(false);
        JButton b_exportar_dades = new JButton("Exportar dades");
        
       

        JPanel panellFiltre = new JPanel(new BorderLayout());
        panellFiltre.add(bFiltrar, BorderLayout.NORTH);

        panellContingut.add(panellFiltre, BorderLayout.EAST);

        return panellContingut;*/
        return f;
    }

    static JFrame taula_jug(JFrame f){
        taula_jug = new JTable();
        taula_jug.setFont(new Font("Arial", Font.PLAIN, 14));

        //taula_jug.setEnabled(false);
        taula_jug.setDefaultEditor(Object.class, null);

        taula_jug.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        /*
        * D'aquesta forma puc elegir quin jugador vull modificar
        */
        taula_jug.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    
                    int filaSeleccionada = taula_jug.getSelectedRow();
                    if (filaSeleccionada != -1) {
                        jugador_seleccionat = llista_de_jugadors.get(filaSeleccionada);
                        try {
                            llista_d_equips = gestor.equips_on_son_els_jug(jugador_seleccionat.getId_jug());
                        } catch (GestorBDEmpresaException ex) {
                            Logger.getLogger(Gestio_equips.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        actualitzar_taula_eq(taula_equip);
                        b_modificar_jug.setEnabled(true);
                        b_eliminar_jug.setEnabled(true);
                    }else{
                        llista_d_equips = new ArrayList<>();
                        actualitzar_taula_eq(taula_equip);
                        
                        b_modificar_jug.setEnabled(false);
                        b_eliminar_jug.setEnabled(false);
                    }
                }
            }
        });

        JScrollPane scrollTaula = new JScrollPane(taula_jug);
        scrollTaula.setBounds(100, 170, 600, 500);
        f.add(scrollTaula);
        return f;
    }
    
    /*
    * Anirà actualitzant la taula_jug per cada cambi en els filtres
    */
    
    private static void actualitzar_taula_jug(JTable taula_jug) {
        //error.setVisible(false);
        int files;
        if (llista_de_jugadors == null || llista_de_jugadors.isEmpty()) {
            files =0;
        }else{
            files =llista_de_jugadors.size();
        }
        String[] columnes = {"ID_Jug", "NIF", "Nom Jugador", "Sexe", "data_naix", "Edat", "Categoria"};
        Object[][] dades = new Object[files][columnes.length];

        int edat;
        String cate;
        for (int i = 0; i < files; i++) {
            Jugador jug = llista_de_jugadors.get(i);
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
            dades[i][0] = jug.getId_jug(); 
            dades[i][1] = jug.getId_legal();
            dades[i][2] = jug.getNom()+" "+jug.getCog();
            dades[i][3] = jug.getSexe().toString();
            dades[i][4] = jug.getData_naix().substring(0, 10);
            dades[i][5] = edat+" anys";
            dades[i][6] = cate;
        }

        DefaultTableModel model = new DefaultTableModel(dades, columnes) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        taula_jug.setModel(model);

        TableColumn column;

        column = taula_jug.getColumnModel().getColumn(0);
        column.setMaxWidth(50); 

        column = taula_jug.getColumnModel().getColumn(1);
        column.setMaxWidth(100);             
        column = taula_jug.getColumnModel().getColumn(2);
        column.setMaxWidth(150); 
        column = taula_jug.getColumnModel().getColumn(3);
        column.setMaxWidth(40); 

        column = taula_jug.getColumnModel().getColumn(4);
        column.setMaxWidth(150); 
        column = taula_jug.getColumnModel().getColumn(5);
        column.setMaxWidth(60); 

        column = taula_jug.getColumnModel().getColumn(6);
        column.setMaxWidth(100);
        TableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        ((DefaultTableCellRenderer) centerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        taula_jug.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        taula_jug.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        taula_jug.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        taula_jug.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        taula_jug.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

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

        JScrollPane scroll_taula_eq = new JScrollPane(taula_equip);
        scroll_taula_eq.setBounds(800, 170, 600, 500);
        f.add(scroll_taula_eq);
        return f;
    }
    
    private static void actualitzar_taula_eq(JTable taula_equip) {
            String[] columnes = {"ID_Equip", "Nom", "Temporada", "Num. jugadors", "Categoria", "Tipus", "És titular?"};
            Object[][] dades = new Object[llista_d_equips.size()][columnes.length];

            String tipus;
            for (int i = 0; i < llista_d_equips.size(); i++) {
                Equip eq = llista_d_equips.get(i);
                
                switch (eq.getTipus().getValue()) {
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
                String titular = "No";
                for (Map.Entry<Integer, Character> tit : eq.getJug_mem_titular().entrySet()) {
                    if (tit.getKey().equals(jugador_seleccionat.getId_jug())) {
                        if(tit.getValue() == 'S'){
                           titular ="Si";
                        }
                    }
                }
                dades[i][0] = eq.getId_equip();
                dades[i][1] = eq.getNom();
                dades[i][2] = eq.getAny_eq()+"/"+(Integer.toString(eq.getAny_eq()+1).substring(2, 4));
                dades[i][3] = eq.getJug_mem().size();
                dades[i][4] = eq.getCate();
                dades[i][5] = tipus;
                dades[i][6] = titular;
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
            column = taula_equip.getColumnModel().getColumn(6);
            column.setMaxWidth(100);
            TableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            ((DefaultTableCellRenderer) centerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
            taula_equip.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
            taula_equip.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
            taula_equip.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
            taula_equip.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

    }
    
    static void filtracio(){
        llista_de_jugadors = new ArrayList<>();
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
            // Es filtra la llista de jugadors
            llista_de_jugadors = gestor.llista_jugadors(sexeSeleccionat, busc_nom, busc_nif, busc_data_naix, cate_select, rev_feta);

            actualitzar_taula_jug(taula_jug);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(f, "Error al filtrar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}