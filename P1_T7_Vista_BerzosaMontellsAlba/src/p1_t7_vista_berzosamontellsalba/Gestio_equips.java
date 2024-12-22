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
import p1_t7_vista_berzosamontellsalba.Funcions;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class Gestio_equips {
    private static List<Equip> llista_equips; 
    static JFrame f = new JFrame("Gestió d'equips");
    static JLabel error = new JLabel("Error", JLabel.CENTER);
    public static JFrame gestio_equips() {
        
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(800, 600);

        JPanel pan_principal = new JPanel(new BorderLayout());

        JPanel barraNavegacio = crearBarraNavegacio();
        pan_principal.add(barraNavegacio, BorderLayout.PAGE_START);

        JPanel pan_central = new JPanel(new BorderLayout());
        JLabel titol = new JLabel("Gestió d'equips", JLabel.CENTER);
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

    private static JPanel crearBarraNavegacio() {
        JPanel barra = new JPanel(new BorderLayout());
        
        JPanel pan_bot_esquerra = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 5));
        pan_bot_esquerra.setBackground(Color.LIGHT_GRAY);

        JButton bCasa = new JButton("Casa");
        JButton bJugadors = new JButton("Jugadors");
        JButton bTemporades = new JButton("Temporades");

        bCasa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                Funcions.agafar_menu_principal();
            }
            
        });
        bJugadors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                Funcions.agafar_gest_jugs();
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
        Funcions.nav_boto_estil(bJugadors);
        Funcions.nav_boto_estil(bTemporades);

        pan_bot_esquerra.add(bCasa);
        pan_bot_esquerra.add(bJugadors);
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

        JLabel titol = new JLabel("Gestió d'equips", SwingConstants.CENTER);
        titol.setFont(new Font("Arial", Font.BOLD, 32));
        panellContingut.add(titol, BorderLayout.NORTH);

        JPanel panellCercaSexe = new JPanel();
        panellCercaSexe.setLayout(new FlowLayout());

        JTextField cercaField = new JTextField(15);
        cercaField.setFont(new Font("Arial", Font.PLAIN, 16));

        JComboBox<String> cercaBox = new JComboBox<>(new String[]{"Cap",Cate_enum.Alevi.toString(), Cate_enum.Benjami.toString(), Cate_enum.Cadet.toString(), Cate_enum.Infantil.toString(), Cate_enum.Juvenil.toString(), Cate_enum.Senior.toString()});
        cercaBox.setFont(new Font("Arial", Font.PLAIN, 16));
        
        
        panellCercaSexe.add(new JLabel("Nom:"));
        panellCercaSexe.add(cercaField);
        panellCercaSexe.add(new JLabel("Categories:"));
        panellCercaSexe.add(cercaBox);
        
        
        String[] columnes = {"Temporada", "ID_jugador", "Nom Jugador", "Categoria", "data_naix"};
        JTable taula = new JTable();
        taula.setFont(new Font("Arial", Font.PLAIN, 14));

        
        taula.setDefaultEditor(Object.class, null);

        
        taula.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        taula.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int filaSeleccionada = taula.getSelectedRow();
                    if (filaSeleccionada != -1) {
                        Equip jugadorSeleccionat = llista_equips.get(filaSeleccionada);

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
        JButton b_crear_eq = new JButton("Crear un equip");
        Funcions.boto_estil(b_crear_eq);
        JButton b_endarrere = new JButton("Endarrere");
        Funcions.boto_estil(b_endarrere);
        JButton b_modificar_jug = new JButton("Modificar un jugador");
        Funcions.boto_estil(b_modificar_jug);
        JButton b_exportar_dades = new JButton("Exportar dades");
        Funcions.boto_estil(b_exportar_dades);
        
        b_crear_eq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //f.dispose();
                //Funcions.crear_equip(); //encara no he fet la classe
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
               //Classe amb les dues op
               //1 op  xml = JDom
               //JOptionPane.showMessageDialog(f, "S'han exportat les dades \n\n");
            }
        });
        
        panellBotons.add(b_crear_eq);
        panellBotons.add(b_endarrere);
        panellBotons.add(b_modificar_jug);

        
        panellContingut.add(panellBotons, BorderLayout.SOUTH);

        
        JButton bFiltrar = new JButton("Filtrar");
        Funcions.boto_estil(bFiltrar);
        bFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String cate_select = cercaBox.getSelectedItem().toString();
                
                if(cercaBox.getSelectedItem().toString().equals("Cap")){
                    cate_select = null;
                }
                
                String nomCerca = cercaField.getText().trim();

                try {
                    System.out.println("abans de filtrar");
                    
                    llista_equips = new GestorBDEmpresaJdbc().mostrar_equips(cate_select, 2024, 'n', "b", "asc");//null, 2025, 'n', "b", "asc"
                    System.out.println("desp de filtrar");
                    
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
            String[] columnes = {"Id_equip", "Nom", "Temporada", "Cate", "Num_jugadors"};
            Object[][] dades = new Object[llista_equips.size()][columnes.length];

            for (int i = 0; i < llista_equips.size(); i++) {
                Equip eq = llista_equips.get(i);
                dades[i][0] = eq.getId_equip();
                dades[i][1] = eq.getNom();
                dades[i][2] = eq.getAny_eq();
                dades[i][3] = eq.getCate();
                dades[i][4] = eq.getNum_jugadors();
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
            TableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            ((DefaultTableCellRenderer) centerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
            taula.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
            taula.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            taula.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

    }
}