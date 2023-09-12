package Seguros;

import Principal.Usuario;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.io.*;
import java.util.StringTokenizer;
import java.util.Date;

public class GestorSeguros {
    private ArrayList<Seguro> Seguros;

    //Constructor del gestor
    public GestorSeguros() {
        this.Seguros = new ArrayList();
    }

    //Elimina el seguro de la lista.
    public void eliminarSeguro(Seguro seguroEliminar) {
        if (seguroEliminar != null) {
            this.Seguros.remove(seguroEliminar);
            JOptionPane.showMessageDialog(null, "Seguro eliminado con éxito.", "Adiós", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog((Component)null, "Seguro no eliminado", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    //Busca todos los seguros de un solo cliente.
    public ArrayList<Seguro> buscarSegurosCliente(Usuario cliente) {
        ArrayList<Seguro> segurosCliente = new ArrayList<Seguro>();
        for(Seguro seguro: Seguros){
            if(cliente.getNombre().equalsIgnoreCase(seguro.getPropietario().getNombre())){
                segurosCliente.add(seguro);
            }
        }
        return segurosCliente;
    }

    //Agrega el seguro a la lista.
    public void agregarSeguro(Seguro seguro) {
        this.Seguros.add(seguro);

    }

    //Busca un seguro en específico por medio del indice que tiene en la lista.
    public Seguro buscarSeguroEspecifico(int indice){
        return this.Seguros.get(indice);
    }

    //Solo inserta registros de seguros a el cliente logeado. Sirve para demostración.
    public void insertarSeguros(Usuario cliente) {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            archivo = new File("src/Seguros/DatosSeguros.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                StringTokenizer registro = new StringTokenizer(linea, "\n");
                while (registro.hasMoreTokens()) {
                    StringTokenizer subCadena = new StringTokenizer(registro.nextToken(), ",");
                    if (subCadena.nextToken().equalsIgnoreCase("SV")) {
                        this.agregarSeguro(new SeguroDeVida(cliente, new String[]{subCadena.nextToken()}, new Date(subCadena.nextToken()), new Date(subCadena.nextToken()), Integer.parseInt(subCadena.nextToken()), subCadena.nextToken()));
                    } else {
                        this.agregarSeguro(new SeguroMédico(cliente, new String[]{subCadena.nextToken()}, new Date(subCadena.nextToken()), new Date(subCadena.nextToken()), new String[]{subCadena.nextToken()}, Integer.parseInt(subCadena.nextToken()), subCadena.nextToken()));
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
