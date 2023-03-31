package climatemonitoring;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Menu {
    private JPanel panel1;
    private static JFrame frame;
    private JTextField searchField;
    private JButton registerButton;
    private JButton loginButton;

    public static void main(String[] args) throws IOException {
        List<User> users = FileManager.readUser(Paths.get("Dati/OperatoriRegistrati.txt"));
        List<MonitoringStation> station = FileManager.readStation(Paths.get("Dati/CentriMonitoraggio.csv"));
        frame = new JFrame("Climate Monitoring");
        frame.setContentPane(new Menu().panel1);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}