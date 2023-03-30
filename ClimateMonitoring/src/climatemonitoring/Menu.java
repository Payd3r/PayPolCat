package climatemonitoring;

import javax.swing.*;

public class Menu {
    private JPanel panel1;
    private static JFrame frame;
    private JTextField searchField;
    private JButton registerButton;
    private JButton loginButton;

    public static void main(String[] args) {
        frame= new JFrame("Climate Monitoring");
        frame.setContentPane(new Menu().panel1);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
