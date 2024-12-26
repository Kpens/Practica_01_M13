/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1_t7_vista_berzosamontellsalba;
import CapaPers.GestorBDEmpresaJdbc;
import Classes.Jugador;
import Enums.Cate_enum;
import Enums.Sexe_enum;
import com.toedter.calendar.JDateChooser;
import p1_t7_vista_berzosamontellsalba.Funcions;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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
    static private JLabel ltext,lsexe, lnif, ldata_naix, titol;
    static private JTextField ltf_nom, ltf_nif;
    static private JComboBox<String> cb_cate;
    static private JButton b_crear_jug, b_modificar_jug, b_endarrere, b_filtrar, b_exportar_dades;
    static private JTable taula;
    static Jugador jugador_seleccionat;
    static private JDateChooser dch_data_naix;
    static JFrame f = new JFrame("Gestió de jugadors");
    public static JFrame gestio_jugs() {
        f.setLayout(null);
        f.setSize(1200, 800);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setResizable(false);
        
        
        Funcions.crearBarraNavegacio(f, 'j');
        
        titol = new JLabel("Gestió de jugadors");
        titol.setBounds(400,50, 600, 40);
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
        
        JRadioButton rbHome = new JRadioButton("Home");
        rbHome.setBounds(550, 100, 60, 40);
        JRadioButton rbDona = new JRadioButton("Dona");
        rbDona.setBounds(610, 100, 60, 40);
        ButtonGroup grupSexe = new ButtonGroup();
        grupSexe.add(rbHome);
        grupSexe.add(rbDona);
        rbHome.setSelected(true);
        
        f.add(rbHome);
        
        f.add(rbDona);
        
        
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
        b_filtrar.setBounds(f.getWidth()-150, f.getHeight()-200, 100, 30);
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
        
        taula(f);
        
        b_filtrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                llista_de_jugadors = null;
                Sexe_enum sexeSeleccionat = rbHome.isSelected() ? Sexe_enum.H : Sexe_enum.D;

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
                    llista_de_jugadors = new GestorBDEmpresaJdbc().llista_jugadors(sexeSeleccionat, busc_nom, busc_nif, busc_data_naix, cate_select);
                                        
                    actualitzarTaula(taula);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(f, "Error al filtrar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        f.add(b_modificar_jug);
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
        
        JTable taula = new JTable();
        taula.setFont(new Font("Arial", Font.PLAIN, 14));

        //taula.setEnabled(false);
        taula.setDefaultEditor(Object.class, null);

        taula.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        *//*
        * D'aquesta forma puc elegir quin jugador vull modificar
        *//*
        taula.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int filaSeleccionada = taula.getSelectedRow();
                    if (filaSeleccionada != -1) {
                        jugador_seleccionat = llista_de_jugadors.get(filaSeleccionada);

                        b_modificar_jug.setEnabled(true);
                    }else{
                        
                        b_modificar_jug.setEnabled(false);
                    }
                }
            }
        });

        JScrollPane scrollTaula = new JScrollPane(taula);
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

    static JFrame taula(JFrame f){
        taula = new JTable();
        taula.setFont(new Font("Arial", Font.PLAIN, 14));

        //taula.setEnabled(false);
        taula.setDefaultEditor(Object.class, null);

        taula.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        /*
        * D'aquesta forma puc elegir quin jugador vull modificar
        */
        taula.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int filaSeleccionada = taula.getSelectedRow();
                    if (filaSeleccionada != -1) {
                        jugador_seleccionat = llista_de_jugadors.get(filaSeleccionada);

                        b_modificar_jug.setEnabled(true);
                    }else{
                        
                        b_modificar_jug.setEnabled(false);
                    }
                }
            }
        });

        JScrollPane scrollTaula = new JScrollPane(taula);
        scrollTaula.setBounds(300, 170, 600, 500);
        f.add(scrollTaula);
        return f;
    }
    
    /*
    * Anirà actualitzant la taula per cada cambi en els filtres
    */
    
    private static void actualitzarTaula(JTable taula) {
         //error.setVisible(false);
            String[] columnes = {"ID_Jug", "NIF", "Nom Jugador", "Sexe", "data_naix", "Edat", "Categoria"};
            Object[][] dades = new Object[llista_de_jugadors.size()][columnes.length];

            int edat;
            String cate;
            for (int i = 0; i < llista_de_jugadors.size(); i++) {
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
            taula.setModel(model);

            TableColumn column;

            column = taula.getColumnModel().getColumn(0);
            column.setMaxWidth(50); 
            
            column = taula.getColumnModel().getColumn(1);
            column.setMaxWidth(100);             
            column = taula.getColumnModel().getColumn(2);
            column.setMaxWidth(150); 
            column = taula.getColumnModel().getColumn(3);
            column.setMaxWidth(40); 
            
            column = taula.getColumnModel().getColumn(4);
            column.setMaxWidth(150); 
            column = taula.getColumnModel().getColumn(5);
            column.setMaxWidth(60); 
            
            column = taula.getColumnModel().getColumn(6);
            column.setMaxWidth(100);
            TableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            ((DefaultTableCellRenderer) centerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
            taula.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
            taula.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            taula.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
            taula.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
            taula.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

    }
}