package climatemonitoring;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Classe che descrive la finestra dove viene creata una nuova stazione di
 * monitoraggio
 *
 * @author Ficara Paolo
 * @author Mauri Andrea
 * @author Luca Cattaneo
 *
 */
public class CreateMonitoringStation extends javax.swing.JFrame {

    //attributi
    private String partialInfo;
    private List<String> areas;

    //costruttori
    /**
     * Costruttore che crea l'oggetto della finestra dove verrà creata una nuova
     * stazione di monitoraggio e inizializza tutti i tuoi componenti.
     *
     * @throws ParseException Errore nella scrittura della data o dell'ora
     */
    public CreateMonitoringStation() throws IOException, ParseException {
        initComponents();
        grafica();
        List<InterestingAreas> monitoringStations = new ArrayList<>();
        monitoringStations = DatiCondivisi.getInstance().getAreas();
        createComboMonitoringStation(monitoringStations);
        partialInfo = "";
        areas = new ArrayList<>();
    }

    /**
     * Costruttore che crea l'oggetto della finestra dove verrà creata una nuova
     * stazione di monitoraggio e inizializza tutti i tuoi componenti.
     *
     * @param s credenziali dell'operatore che si sta registrando
     * @throws ParseException Errore nella scrittura della data o dell'ora
     * @throws IOException Errore in lettuera o scrittura nel file delle
     * stazioni di monitoraggio
     */
    public CreateMonitoringStation(String s) throws ParseException, IOException {
        initComponents();
        grafica();
        List<InterestingAreas> monitoringStations = new ArrayList<>();
        monitoringStations = DatiCondivisi.getInstance().getAreas();
        createComboMonitoringStation(monitoringStations);
        partialInfo = s;
        areas = new ArrayList<>();
    }

    private void grafica() {
        ImageIcon img = new ImageIcon("../Data/icon.jpg");
        this.setIconImage(img.getImage());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((int) (screenSize.width - this.getWidth()) / 2, (int) (screenSize.height - this.getHeight()) / 2);
    }

    //metodi
    private void createComboMonitoringStation(List<InterestingAreas> monitoringStations) {
        for (InterestingAreas x : monitoringStations) {
            InterestingAreas.addItem(x.getName());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        address = new javax.swing.JTextField();
        InterestingAreas = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        buttonAdd = new javax.swing.JButton();
        buttonAdd1 = new javax.swing.JButton();
        btnAggiungi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Nome");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Indirizzo");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Elenco aree");

        InterestingAreas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---" }));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("Registra centro aree");

        buttonAdd.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        buttonAdd.setText("Registrati");
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });

        buttonAdd1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        buttonAdd1.setText("Cancel");
        buttonAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAdd1ActionPerformed(evt);
            }
        });

        btnAggiungi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAggiungi.setText("Aggiungi area");
        btnAggiungi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAggiungiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(buttonAdd1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAggiungi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(InterestingAreas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(address)
                            .addComponent(name))))
                .addContainerGap(291, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(InterestingAreas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(btnAggiungi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAdd1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed
        if (name.getText().length() < 1 || address.getText().length() < 1 || areas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Errore compilare tutti i campi!");
        } else {
            partialInfo += name.getText() + "\n";
            String a = "";
            for (int i = 0; i < areas.size(); i++) {
                if (i < areas.size() - 1) {
                    a += areas.get(i) + ",";
                } else {
                    a += areas.get(i);
                }
            }
            String s = name.getText() + ";" + address.getText() + ";" + a + "\n";
            try {
                DBManager.write(s, Paths.get("../Data/CentroMonitoraggio.txt"));
                DBManager.write(partialInfo, Paths.get("../Data/OperatoriRegistrati.txt"));
            } catch (IOException ex) {
                Logger.getLogger(CreateMonitoringStation.class.getName()).log(Level.SEVERE, null, ex);
            }
//        MenuOperatore m = new MenuOperatore();
//        m.setVisible(rootPaneCheckingEnabled);
            String[] info = partialInfo.split(";");
            this.dispose();
            try {
                DatiCondivisi.getInstance().setOperatore(new User(info[0], info[1], info[2], info[3], info[4], info[5], name.getText()));
                DatiCondivisi.getInstance().refresh();
                new Menu().setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(CreateMonitoringStation.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(CreateMonitoringStation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_buttonAddActionPerformed

    private void buttonAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdd1ActionPerformed
        try {
            new Menu().setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(CreateMonitoringStation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(CreateMonitoringStation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonAdd1ActionPerformed

    private void btnAggiungiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggiungiActionPerformed
        JOptionPane.showMessageDialog(null, "Area aggiunta con successo");
        areas.add(InterestingAreas.getItemAt(InterestingAreas.getSelectedIndex()));
        InterestingAreas.setSelectedIndex(0);
    }//GEN-LAST:event_btnAggiungiActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> InterestingAreas;
    private javax.swing.JTextField address;
    private javax.swing.JButton btnAggiungi;
    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonAdd1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField name;
    // End of variables declaration//GEN-END:variables
}
