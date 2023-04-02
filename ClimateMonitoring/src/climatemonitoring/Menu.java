package climatemonitoring;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Menu {
    private JPanel panel1;
    private static JFrame frame;
    private JTextField searchField;
    private JButton registerButton;
    private JButton loginButton;

    public Menu() {
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Login logPage= new Login();
                logPage.show();
            }
        });
    }

    public static void main(String[] args) throws IOException {
        List<User> users = FileManager.readUser(Paths.get("Dati/OperatoriRegistrati.txt"));
        List<MonitoringStation> station = FileManager.readStation(Paths.get("Dati/CentriMonitoraggio.csv"));
        //FileManager.write("Luca;Mauri;MRANDR03P02D416Y;andreamauri2013@gmail.com;Payd3r;prova2;Como" + "\n", Paths.get("Dati/OperatoriRegistrati.txt"));
        frame = new JFrame("Climate Monitoring");
        frame.setContentPane(new Menu().panel1);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}