/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1_t7_vista_berzosamontellsalba;

import CapaPers.GestorBDEmpresaJdbc;
import Classes.Equip;
import Classes.Jugador;
import Enums.Sexe_enum;
import Persistencia.GestorBDEmpresaException;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
/**
 *
 * @author Alma
 */
public class Exportacio {
    static JFrame f = new JFrame("Exportar dades");
    static private GestorBDEmpresaJdbc gestor;
    static private JLabel lExportar_a, titol;
    static private JTextField txtRuta;
    static private JButton b_Carpeta, b_Exportar, b_endarrere;
    static private JRadioButton rbXML, rbCSV;
    
    public static JFrame exportar_dades() {
        
        f.setLayout(null);
        f.setSize(800, 530);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setResizable(false);
       
        Funcions.crearBarraNavegacio(f, 'n');

        titol = new JLabel("Exportar dades");
        titol.setFont(new Font("Arial", Font.BOLD, 38));
        titol.setBounds(200, 100, 300, 40);
        titol.setHorizontalAlignment(SwingConstants.CENTER);
        f.add(titol);
        
        go(f);
        return f;
    }

    private static void go(JFrame f) {
        
        
        txtRuta = new JTextField("C://");
        txtRuta.setBounds(200, 160, 280, 30);
        txtRuta.setEnabled(false);
        f.add(txtRuta);

        b_Carpeta = new JButton("...");
        b_Carpeta.setBounds(500, 160, 50, 30);
        Funcions.boto_estil(b_Carpeta);
        f.add(b_Carpeta);

        lExportar_a = new JLabel("Exportar a:");
        lExportar_a.setBounds(240, 190, 100, 30);
        f.add(lExportar_a);

        rbXML = new JRadioButton("XML");
        rbXML.setBounds(320, 190, 70, 30);
        rbCSV = new JRadioButton("CSV");
        rbCSV.setBounds(390, 190, 70, 30);

        rbXML.setSelected(true);
        ButtonGroup grupBotons = new ButtonGroup();
        grupBotons.add(rbXML);
        grupBotons.add(rbCSV);

        f.add(rbXML);
        f.add(rbCSV);

        b_Exportar = new JButton("Exportar de dades");
        b_Exportar.setBounds(250, 240, 200, 40);
        Funcions.boto_estil(b_Exportar);
        f.add(b_Exportar);
        
        b_endarrere = new JButton("Endarrere");
        b_endarrere.setBounds(80, 425, 125, 30);
        Funcions.boto_estil(b_endarrere);
        f.add(b_endarrere);
        
        b_Carpeta.addActionListener(e -> {
            JFileChooser selectorCarpeta = new JFileChooser();
            selectorCarpeta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int opcion = selectorCarpeta.showOpenDialog(f);
            if (opcion == JFileChooser.APPROVE_OPTION) {
                txtRuta.setText(selectorCarpeta.getSelectedFile().getAbsolutePath());
            }
        });

        b_endarrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               f.dispose();
                Funcions.agafar_gest_jugs();
            }
        });
        
        b_Exportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                String ruta = txtRuta.getText().trim()+"\\jugadors"+(rbXML.isSelected() ? "_xml.xml":"_csv.csv");
                List<Jugador> jugs = new ArrayList<>();
                try {
                        System.out.println("Intent de creació de la capa...");
                        gestor = new GestorBDEmpresaJdbc();
                        System.out.println("Capa de persistència creada");
                        System.out.println("");

                        jugs=gestor.llista_jugadors_ant("c", "");

                    } catch (GestorBDEmpresaException ex) {
                            JOptionPane.showMessageDialog(f, "Error: En la capa");
                    }
                if(rbXML.isSelected()){

                    Exportar_amb_xml.exportarJugadors(jugs, ruta, f);

                }else{
                   try (FileWriter csvWriter = new FileWriter(ruta)) {

                    csvWriter.append("ID_Jugador,Nom,Cognoms,Adreça,Codi_Postal,Població,Provincia,Pais,Data_Naixament,Sexe,IBAN,ID_Legal,Equips\n");

                    for (Jugador jugador : jugs) {

                        csvWriter.append(jugador.getId_jug() + ",");
                        csvWriter.append(jugador.getNom() + ",");
                        csvWriter.append(jugador.getCog() + ",");
                        csvWriter.append(jugador.getAdreca() + ",");
                        csvWriter.append(jugador.getCodi_postal() + ",");
                        csvWriter.append(jugador.getPoblacio() + ",");
                        csvWriter.append(jugador.getProvincia() + ",");
                        csvWriter.append(jugador.getPais() + ",");
                        csvWriter.append(jugador.getData_naix().substring(0, 10) + ",");
                        csvWriter.append(jugador.getSexe() + ",");
                        csvWriter.append(jugador.getIban() + ",");
                        csvWriter.append(jugador.getId_legal() + ",");

                        List<Equip> equips = gestor.equips_on_son_els_jug(jugador.getId_jug());
                        if (equips.isEmpty()) {
                            csvWriter.append("Sense equips\n");
                        } else {
                            StringBuilder equipsStr = new StringBuilder();
                            for (Equip equip : equips) {
                                equipsStr.append(equip.getNom()).append(" (").append("Any: ").append(equip.getAny_eq()).append(", Categoria: ")
                                        .append(equip.getCate()).append(", Tipus: ").append(equip.getTipus()).append(", Titular: ")
                                        .append(equip.getJug_mem_titular().get(jugador.getId_jug()).charValue()+"").append("), ");
                            }
                            equipsStr.setLength(equipsStr.length() - 2);
                            csvWriter.append("\"").append(equipsStr).append("\"\n");
                        }
                    }
                    System.out.println("Fitxer CSV exportat correctament a: " + ruta);

                } catch (GestorBDEmpresaException ex) {
                    System.err.println("Error en escriure el fitxer CSV: " + ex.getMessage());
                } catch (IOException ex) {
                    System.err.println("Error en escriure el fitxer CSV: " + ex.getMessage());
                }
            }
            
        }
        });

        b_endarrere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               f.dispose();
                Funcions.agafar_gest_jugs();
            }
        });

        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
