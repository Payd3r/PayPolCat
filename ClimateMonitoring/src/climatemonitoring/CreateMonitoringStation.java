package climatemonitoring;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe che descrive la finestra dove viene creata una nuova stazione di monitoraggio
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
     * Costruttore che crea l'oggetto della finestra dove verrà creata una nuova stazione di monitoraggio
     * e inizializza tutti i tuoi componenti.
     * @throws ParseException 
     */
    public CreateMonitoringStation() throws IOException, ParseException {
        initComponents();
        List<InterestingAreas> monitoringStations = new ArrayList<>();
        monitoringStations = DatiCondivisi.getInstance().getAreas();
        createComboMonitoringStation(monitoringStations);
        partialInfo = "";
        areas = new ArrayList<>();
    }
    
    /**
     * Costruttore che crea l'oggetto della finestra dove verrà creata una nuova stazione di monitoraggio
     * e inizializza tutti i tuoi componenti. 
     * @param s credenziali dell'operatore che si sta registrando
     * @throws ParseException 
     * @throws IOException
     */
    public CreateMonitoringStation(String s) throws ParseException, IOException {
        initComponents();
        List<InterestingAreas> monitoringStations = new ArrayList<>();
        monitoringStations = DatiCondivisi.getInstance().getAreas();
        createComboMonitoringStation(monitoringStations);
        partialInfo = s;
        areas = new ArrayList<>();
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
        buttonRegister = new javax.swing.JButton();
        buttonAdd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nome");

        jLabel2.setText("Indirizzo");

        jLabel3.setText("Elenco aree");

        InterestingAreas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---" }));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel4.setText("Registra centro aree");

        buttonRegister.setText("Registra");
        buttonRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRegisterActionPerformed(evt);
            }
        });

        buttonAdd.setText("Aggiungi");
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonRegister)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(InterestingAreas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(address))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonAdd)
                        .addGap(16, 16, 16))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
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
                    .addComponent(InterestingAreas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAdd))
                .addGap(30, 30, 30)
                .addComponent(buttonRegister)
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegisterActionPerformed
        partialInfo += name.getText();
        String a = "";
        for (int i = 0; i < areas.size(); i++) {
            if (i < areas.size() - 1) {
                a += areas.get(i) + ",";
            } else {
                a += areas.get(i);
            }
        }
        String s = name.getText() + ";" + address.getText() + ";" + a;
        try {
            FileManager.write(s, Paths.get("Dati/CentroMonitoraggio.txt"));
            FileManager.write(partialInfo, Paths.get("Dati/OperatoriRegistrati.txt"));
        } catch (IOException ex) {
            Logger.getLogger(CreateMonitoringStation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        MenuOperatore m = new MenuOperatore();
        m.setVisible(rootPaneCheckingEnabled);
        this.dispose();
    }//GEN-LAST:event_buttonRegisterActionPerformed

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed
        areas.add(InterestingAreas.getItemAt(InterestingAreas.getSelectedIndex()));
    }//GEN-LAST:event_buttonAddActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> InterestingAreas;
    private javax.swing.JTextField address;
    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField name;
    // End of variables declaration//GEN-END:variables
}
