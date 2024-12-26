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
import java.time.LocalDate;
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
    private static List<Equip> llista_d_equips; 
    static private JLabel ltext,lsexe, lnif, ldata_naix, ltitol;
    static private JTextField ltf_nom, ltf_nif;
    static private JComboBox<String> cb_cate, cb_temp, cb_tipus;
    static private JButton b_crear_equip, b_modificar_equip, b_endarrere, b_filtrar, b_exportar_dades;
    static private JTable taula;
    static Equip equip_seleccionat;
    static private JDateChooser dch_data_naix;
    static JFrame f = new JFrame("Gestió d'equips");
    static private GestorBDEmpresaJdbc gestor;
    
    public static JFrame gestio_equips() {
     
        f.setLayout(null);
        f.setSize(1200, 800);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setResizable(false);
        
        
        Funcions.crearBarraNavegacio(f, 'e');
        
        ltitol = new JLabel("Gestió d'equips");
        ltitol.setBounds((f.getWidth()/2)-120,50, 285, 40);
        ltitol.setFont(new Font("Arial", Font.BOLD, 38));
        f.add(ltitol);
        
        go(f);
        f.setVisible(true);
        return f;
    }
    private static JFrame go(JFrame f) {

        ltext = new JLabel("Nom: ");
        ltext.setBounds(50, 100, 80, 40);
        ltext.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(ltext);

        ltf_nom = new JTextField();
        ltf_nom.setBounds(110, 105, 160, 30);
        ltf_nom.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(ltf_nom);
/*
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
        */
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
        
        String[] tipus = new String[Tipus_enum.values().length+1];
        tipus[0] = "Qualsevol";
        tipus[1] = "Femeni";
        tipus[2] = "Masculi";
        tipus[3] = "Mixte";
        
        cb_tipus = new JComboBox<>(tipus);
        cb_tipus.setFont(new Font("Arial", Font.PLAIN, 16));
        cb_tipus.setBounds(810, 100, 100, 40);
        
        f.add(cb_tipus);
        
                
        if (temp != null) {
            String[] tempo= new String[temp.size()+1];
            int i=1;
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
            cb_temp.setBounds(930, 100, 100, 40);
            if(existeix){
                cb_temp.setSelectedItem(LocalDate.now().getYear());
            }
            
            f.add(cb_temp);
            
        } else {
            System.out.println("La llista de temporades és buida.");
        }        
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
        cb_cate.setBounds(1050, 100, 100, 40);
        
        f.add(cb_cate);
        
        b_filtrar = new JButton("Filtrar");
        Funcions.boto_estil(b_filtrar);
        b_filtrar.setBounds(f.getWidth()-150, f.getHeight()-200, 100, 30);
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
               //Funcions.crear_equip();
            }
        });
        b_modificar_equip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               f.dispose();
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
        
        taula(f);
        
        b_filtrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                llista_d_equips = null;
                //Sexe_enum sexeSeleccionat = rbHome.isSelected() ? Sexe_enum.H : Sexe_enum.D;
                String cate_select = null;
                for (Cate_enum val : Cate_enum.values()) {
                    if(cb_cate.getSelectedItem().equals(val.toString())){
                        cate_select = val.toString();
                    }
                }
                /*String cate_select;
                if(cb_cate.getSelectedItem().equals(Cate_enum.Alevi.toString())){
                    cate_select = Cate_enum.Alevi.toString();
                }else if(cb_cate.getSelectedItem().equals(Cate_enum.Benjami.toString())){
                    cate_select = Cate_enum.Benjami.toString();
                }else if(cb_cate.getSelectedItem().equals(Cate_enum.Cadet.toString())){
                    cate_select = Cate_enum.Cadet.toString();
                }else if(cb_cate.getSelectedItem().equals(Cate_enum.Infantil.toString())){
                    cate_select = Cate_enum.Infantil.toString();
                }else if(cb_cate.getSelectedItem().equals(Cate_enum.Juvenil.toString())){
                    cate_select = Cate_enum.Juvenil.toString();
                }else if(cb_cate.getSelectedItem().equals(Cate_enum.Senior.toString())){
                    cate_select = Cate_enum.Senior.toString();
                }else{
                    cate_select=null;
                }*/
                System.out.println(cate_select);
                String selectedItem = (String) cb_temp.getSelectedItem();
                int temporada;
                System.out.println(cb_temp.getSelectedItem());
                if(cb_temp.getSelectedItem().equals("Qualsevol")){
                    temporada=0;
                }else{
                    temporada = Integer.parseInt(selectedItem.substring(0, 4));
                }
                char tipus_eq;
                if(cb_tipus.getSelectedItem().equals("Femeni")){
                    tipus_eq ='D';
                }else if(cb_tipus.getSelectedItem().equals("Masculi")){
                    tipus_eq ='H';
                }else if(cb_tipus.getSelectedItem().equals("Mixte")){
                    tipus_eq ='M';
                }else{
                    tipus_eq ='n';
                }
                
                /*String busc_nif = ltf_nif.getText().trim();
                
                String busc_data_naix ="";*/
                /*
                * Com es retorna en Date i en un format incorrecte ho passem al format correcte en string
                */
                /*Date data = dch_data_naix.getDate();
                if (data != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    busc_data_naix = sdf.format(data);
                    System.out.println("Data seleccionada: " + busc_data_naix);
                } */
                String busc_nom = ltf_nom.getText().trim();
                try {
                    // Es filtra la llista de jugadors
                    llista_d_equips = new GestorBDEmpresaJdbc().mostrar_equips(cate_select, temporada, tipus_eq, busc_nom, "asc");
                                        
                    actualitzarTaula(taula);
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
                        equip_seleccionat = llista_d_equips.get(filaSeleccionada);
                        b_modificar_equip.setEnabled(true);
                    }else{
                        
                        b_modificar_equip.setEnabled(false);
                    }
                }
            }
        });

        JScrollPane scrollTaula = new JScrollPane(taula);
        scrollTaula.setBounds(300, 170, 600, 500);
        f.add(scrollTaula);
        return f;
    }
    
    private static void actualitzarTaula(JTable taula) {
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
                dades[i][2] = jug.getAny_eq()+"/"+(Integer.toString(jug.getAny_eq()+1).substring(2, 4));
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
            taula.setModel(model);

            TableColumn column;

            column = taula.getColumnModel().getColumn(0);
            column.setMaxWidth(60); 
            
            column = taula.getColumnModel().getColumn(1);
            column.setMaxWidth(160);             
            column = taula.getColumnModel().getColumn(2);
            column.setMaxWidth(120); 
            column = taula.getColumnModel().getColumn(3);
            column.setMaxWidth(90); 
            
            column = taula.getColumnModel().getColumn(4);
            column.setMaxWidth(100); 
            column = taula.getColumnModel().getColumn(5);
            column.setMaxWidth(100); 
            TableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            ((DefaultTableCellRenderer) centerRenderer).setHorizontalAlignment(SwingConstants.CENTER);
            taula.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
            taula.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
            taula.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

    }
}