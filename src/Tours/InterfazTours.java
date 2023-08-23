package Tours;

import javax.swing.*;

public class InterfazTours extends JFrame{

    private JPanel panel1;
    private JPanel pnlBusqueda;
    private JTabbedPane tabbedPane1 = new JTabbedPane(JTabbedPane.LEFT);
    private JPanel panelPestana1;
    private JTextField textField1;
    private JButton buscarButton;

    JPanel firstPanel = new JPanel();
    JPanel secondPanel = new JPanel();

    JLabel firstLabel = new JLabel("First!");
    JLabel secondLabel = new JLabel("Second!");

    JTabbedPane tabbedPane = new JTabbedPane();

    public InterfazTours(String s) {
        super(s);

    }
}
