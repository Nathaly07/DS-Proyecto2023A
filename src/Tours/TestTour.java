import javax.swing.JOptionPane;

public class TestTour {

    public TestTour() {
    }

    public void initTour() {
        boolean flag = true;

        while (flag == true){
        int op = Integer.parseInt(
            JOptionPane.showInputDialog(null, 
            "1. Buscar Tours \n2. Reservar Tour\n3. Modificar Tour\n4. Eliminar Tour\n5. Confirmar Tour\n6. Salir\n ", 
            "Menu",
            JOptionPane.QUESTION_MESSAGE));

        
            switch (op) {         
                case 1:
                    JOptionPane.showMessageDialog(null, "Buscar Tours");
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Reservar Tour");
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Modificar Tour");
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Eliminar Tour");
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Confirmar Tour");
                    break;
                case 6:
                    JOptionPane.showMessageDialog(null, "Salir");
                    flag = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida");
                    break;
            }
        }
    }
}
