/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1_t7_vista_berzosamontellsalba;
import CapaPers.GestorBDEmpresaJdbc;
import Classes.Jugador;
import Enums.Cate_enum;
import Enums.Sexe_enum;
import p1_t7_vista_berzosamontellsalba.Funcions;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import static p1_t7_vista_berzosamontellsalba.Menu_principal.f;
/**
 *
 * @author Alma
 */
public class Gestio_jugs {
    private static List<Jugador> llistaJugadors; 
    static JFrame f = new JFrame("Gestió de jugadors");
    static JLabel error = new JLabel("Error", JLabel.CENTER);
    public static JFrame gestio_jugs() {
        
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(800, 600);

        JPanel pan_principal = new JPanel(new BorderLayout());

        JPanel barraNavegacio = crearBarraNavegacio();
        pan_principal.add(barraNavegacio, BorderLayout.PAGE_START);

        JPanel pan_central = new JPanel(new BorderLayout());
        JLabel titol = new JLabel("Gestió de jugadors", JLabel.CENTER);
        titol.setFont(new Font("Arial", Font.BOLD, 40));

        pan_central.add(titol, BorderLayout.NORTH);
        pan_central.add(crearContingutPrincipal(), BorderLayout.CENTER);
        
        error.setFont(new Font("Arial", Font.BOLD, 20));
        error.setForeground(Color.red);
        error.setVisible(false);
        pan_central.add(error, BorderLayout.PAGE_END);

        pan_principal.add(pan_central, BorderLayout.CENTER);

        f.add(pan_principal);
        f.setVisible(true);
        return f;
        
    }
    /*
    * Uso panells perquè m'ha semblat que seria més útil amb les taules
    */

    private static JPanel crearBarraNavegacio() {
        JPanel barra = new JPanel(new BorderLayout());

        JPanel pan_bot_esquerra = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 5));
        pan_bot_esquerra.setBackground(Color.LIGHT_GRAY);

        JButton bCasa = new JButton("Casa");
        JButton bEquips = new JButton("Equips");
        JButton bTemporades = new JButton("Temporades");

        bCasa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                Funcions.agafar_menu_principal();
            }
            
        });
        bEquips.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                Funcions.gest_equips();
            }
            
        });
        bTemporades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                Funcions.gest_temporades();
            }
            
        });
        
        Funcions.nav_boto_estil(bCasa);
        Funcions.nav_boto_estil(bEquips);
        Funcions.nav_boto_estil(bTemporades);

        pan_bot_esquerra.add(bCasa);
        pan_bot_esquerra.add(bEquips);
        pan_bot_esquerra.add(bTemporades);

        JButton bFiltrar = new JButton("Filtrar");
        Funcions.nav_boto_estil(bFiltrar);

        bFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pestanya filtrar");
            }
            
        });
        barra.add(pan_bot_esquerra, BorderLayout.WEST);
        barra.add(bFiltrar, BorderLayout.EAST);

        return barra;
    }

    private static JPanel crearContingutPrincipal() {
        JPanel panellContingut = new JPanel();
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

        JRadioButton rbHome = new JRadioButton("Home");
        JRadioButton rbDona = new JRadioButton("Dona");
        ButtonGroup grupSexe = new ButtonGroup();
        grupSexe.add(rbHome);
        grupSexe.add(rbDona);
        rbHome.setSelected(true);

        JComboBox<String> cercaBox = new JComboBox<>(new String[]{"Cap",Cate_enum.Alevi.toString(), Cate_enum.Benjami.toString(), Cate_enum.Cadet.toString(), Cate_enum.Infantil.toString(), Cate_enum.Juvenil.toString(), Cate_enum.Senior.toString()});
        cercaBox.setFont(new Font("Arial", Font.PLAIN, 16));
        
        
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

        /*
        * D'aquesta forma puc elegir quin jugador vull modificar
        */
        taula.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int filaSeleccionada = taula.getSelectedRow();
                    if (filaSeleccionada != -1) {
                        Jugador jugadorSeleccionat = llistaJugadors.get(filaSeleccionada);

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
        JButton b_modificar_jug = new JButton("Modificar un jugador");
        JButton b_exportar_dades = new JButton("Exportar dades");
        
        b_crear_jug.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                Funcions.crear_jug();
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
               //JOptionPane.showMessageDialog(f, "S'han exportat les dades \n\n", "Alerta", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        panellBotons.add(b_crear_jug);
        panellBotons.add(b_endarrere);
        panellBotons.add(b_modificar_jug);

        panellContingut.add(panellBotons, BorderLayout.SOUTH);

        JButton bFiltrar = new JButton("Filtrar");
        bFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                llistaJugadors = null;
                Sexe_enum sexeSeleccionat = rbHome.isSelected() ? Sexe_enum.H : Sexe_enum.D;

                Cate_enum cate_select = null;
                
                if(cercaBox.getSelectedItem().equals(Cate_enum.Alevi.toString())){
                    cate_select = Cate_enum.Alevi;
                }else if(cercaBox.getSelectedItem().equals(Cate_enum.Benjami.toString())){
                    cate_select = Cate_enum.Benjami;
                }else if(cercaBox.getSelectedItem().equals(Cate_enum.Cadet.toString())){
                    cate_select = Cate_enum.Cadet;
                }else if(cercaBox.getSelectedItem().equals(Cate_enum.Infantil.toString())){
                    cate_select = Cate_enum.Infantil;
                }else if(cercaBox.getSelectedItem().equals(Cate_enum.Juvenil.toString())){
                    cate_select = Cate_enum.Juvenil;
                }else if(cercaBox.getSelectedItem().equals(Cate_enum.Senior.toString())){
                    cate_select = Cate_enum.Senior;
                } 
                String busc_nom = busca_nom.getText().trim();
                String busc_nif = busca_nif.getText().trim();
                String busc_data_naix = busca_data_naix.getText().trim();

                try {
                    // Es filtra la llista de jugadors
                    llistaJugadors = new GestorBDEmpresaJdbc().llista_jugadors(sexeSeleccionat, busc_nom, busc_nif, busc_data_naix, cate_select);
                    
                    actualitzarTaula(taula);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(f, "Error al filtrar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel panellFiltre = new JPanel(new BorderLayout());
        panellFiltre.add(bFiltrar, BorderLayout.NORTH);

        panellContingut.add(panellFiltre, BorderLayout.EAST);

        return panellContingut;
    }

    /*
    * Anirà actualitzant la taula per cada cambi en els filtres
    */
    
    private static void actualitzarTaula(JTable taula) {
         error.setVisible(false);
            String[] columnes = {"ID_Jug", "NIF", "Nom Jugador", "Sexe", "data_naix", "Edat", "Categoria"};
            Object[][] dades = new Object[llistaJugadors.size()][columnes.length];

            int edat;
            String cate;
            for (int i = 0; i < llistaJugadors.size(); i++) {
                Jugador jug = llistaJugadors.get(i);
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