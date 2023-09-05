package Vuelos.Logica;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarritoAsientos {

    private List<Asiento> asientos;
    private  Vuelo vuelo;
public CarritoAsientos(){
    asientos = new ArrayList<>();
}
    public CarritoAsientos(Vuelo vuelo) {
        this.vuelo = vuelo;
        asientos = new ArrayList<>();
    }


    public void añadir(Asiento a) {
        this.asientos.add(a);
    }

    public void eliminar(Asiento a) {
        for(int i = 0; i < this.asientos.size(); i++){
            Asiento aux = this.asientos.get(i);
            if (aux.getNumero() == a.getNumero() && aux.getNumFila() == a.getNumFila()) {
                this.asientos.remove(aux);
            }
        }
    }
    public List<Asiento> getAsientos(){
        return asientos;
    }

    private  class TablaCarrito extends AbstractTableModel {
        private final String[] COLUMNS = {"# Asiento", "# fila"};
        private List<Asiento> asientos;

        public TablaCarrito(List<Asiento> Asientos){
            this.asientos = Asientos;
        }
        @Override
        public int getRowCount() {
            return asientos.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch(columnIndex) {
                case 0 -> asientos.get(rowIndex).getNumero();
                case 1 -> asientos.get(rowIndex).getNumFila();
                default ->  "-";

            };
        }
        @Override
        public boolean isCellEditable(int row,int column){
            return false;
        }
        @Override
        public String getColumnName(int column){
            return COLUMNS[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex){
            if(getValueAt(0,columnIndex) != null){
                return getValueAt(0,columnIndex).getClass();
            }else{
                return Object.class;
            }
        }
    }
    public void mostarCarrito(JTable tabla){
        TablaCarrito modelo = new TablaCarrito(this.asientos);
        tabla.setModel(modelo);
    }

    public void Limpiar(){
        this.asientos = new ArrayList<>();
    }
    public Vuelo getVuelo() {
        return vuelo;
    }
}
