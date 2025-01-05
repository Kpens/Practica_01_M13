/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1_t7_vista_berzosamontellsalba;

import CapaPers.GestorBDEmpresaJdbc;
import Classes.Equip;
import Classes.Jugador;
import Persistencia.GestorBDEmpresaException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author Alma
 */
public class Exportar_amb_xml {
    static private GestorBDEmpresaJdbc gestor;
    
    
    public static void exportarJugadors(List<Jugador> jugadors, String rutaFitxer, JFrame f) {
        try {
            System.out.println("Intent de creació de la capa...");
            gestor = new GestorBDEmpresaJdbc();
            System.out.println("Capa de persistència creada");
            System.out.println("");

        } catch (GestorBDEmpresaException ex) {
                JOptionPane.showMessageDialog(f, "Error: En la capa");
        }
        
        Element arrel = new Element("Jugadors");

        for (Jugador jugador : jugadors) {
            try {
                Element jug_elem = new Element("Jugador");
                jug_elem.setAttribute("id", String.valueOf(jugador.getId_jug()));
                jug_elem.addContent(new Element("Nom").setText(jugador.getNom()));
                jug_elem.addContent(new Element("Cognoms").setText(jugador.getCog()));
                jug_elem.addContent(new Element("Adreca").setText(jugador.getAdreca()));
                jug_elem.addContent(new Element("DataNaix").setText(jugador.getData_naix().substring(0, 10)));
                jug_elem.addContent(new Element("Sexe").setText(jugador.getSexe().toString()));
                
                List<Equip> equips = gestor.equips_on_son_els_jug(jugador.getId_jug());
                
                Element equipsElement = new Element("Equips");
                for (Equip equip : equips) {
                    
                    String mem = equip.getJug_mem_titular().get(jugador.getId_jug()).charValue()+"";
                    
                    Element equip_elem = new Element("Equip");
                    equip_elem.addContent(new Element("Nom").setText(equip.getNom()));
                    equip_elem.addContent(new Element("Any").setText(String.valueOf(equip.getAny_eq())));
                    equip_elem.addContent(new Element("Categoria").setText(equip.getCate().toString()));
                    equip_elem.addContent(new Element("Tipus").setText(equip.getTipus().toString()));
                    equip_elem.addContent(new Element("Titular").setText(mem));
                    equipsElement.addContent(equip_elem);
                }
                
                jug_elem.addContent(equipsElement);
                arrel.addContent(jug_elem);
            } catch (GestorBDEmpresaException ex) {
                Logger.getLogger(Exportar_amb_xml.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        Document document = new Document(arrel);

        try (FileWriter writer = new FileWriter(rutaFitxer)) {
            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            xmlOutputter.output(document, writer);
            System.out.println("XML exportat correctament a: " + rutaFitxer);
        } catch (IOException e) {
            System.err.println("Error exportant el fitxer XML: " + e.getMessage());
        }
    }
}
