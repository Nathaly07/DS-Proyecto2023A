import javax.swing.JOptionPane;

public class TestTour {

    public TestTour() {
    }

    public int initTour() {
        int op = Integer.parseInt(
            JOptionPane.showInputDialog(null, 
            "1. Buscar Tours \n2. Reservar Tour\n3. Modificar Tour\n4. Eliminar Tour\n5. Confirmar Tour\n6. Salir\n ", 
            JOptionPane.QUESTION_MESSAGE));

            return op;
    }

    public
}
