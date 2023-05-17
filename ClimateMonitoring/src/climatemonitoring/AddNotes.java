package climatemonitoring;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe che descrive la finestra dove viene aggiunta una nota alla rilevazione selezionata
 *
 * @author Ficara Paolo
 * @author Mauri Andrea 
 * @author Luca Cattaneo
 * 
 */
public class AddNotes extends javax.swing.JFrame {
    
    //attributi
    private Forecast temp;

    //costruttori
    /**
     * Costruttore che crea l'oggetto della finestra dove si andrà ad aggiungere una nota
     * e inizializza i vari componenti di essa.
     * 
     */
    public AddNotes() {
        initComponents();
    }

    /**
     * Costruttore che crea l'oggetto della finestra dove si andrà ad aggiungere una nota,
     * inizializza i vari componenti di essa.
     * @param idCitta l'identificativo della città a cui è stata eseguita la rilevazione
     * @param nomeStazione il nome della stazione di monitoraggio a cui è assegnata la rilevazione
     * @param date la data di quando è stata fatta la rilevazione
     * @param time l'istante di quando è stata fatta la rilevazione
     * @param wind rilevazione del vento
     * @param humidity rilevazione dell'umidità
     * @param pressure rilevazione dell'umidità
     * @param temperature rilevazione della temperatura
     * @param rainfall rilevazione delle precipitazioni
     * @param glacierAltitude rilevazione dell'altitudine dei ghiacciai
     * @param massGlaciers rilevazione della massa dei ghiacciai
     * 
     * @throws ParseException
     */
    public AddNotes(String idCitta, String nomeStazione, String date, String time, int wind, int humidity, int pressure, int temperature, int rainfall, int glacierAltitude, int massGlaciers) throws ParseException {
        initComponents();
        String[] vento = {"Vento", Integer.toString(wind), ""};
        String[] umidita = {"Umidità", Integer.toString(humidity), ""};
        String[] pressione = {"Pressione", Integer.toString(pressure), ""};
        String[] temperatura = {"Temperatura", Integer.toString(temperature), ""};
        String[] precipitazioni = {"Precipitazioni", Integer.toString(rainfall), ""};
        String[] altitudine = {"Altitudine", Integer.toString(glacierAltitude), ""};
        String[] massa = {"Massa", Integer.toString(massGlaciers), ""};
        temp = new Forecast(idCitta, nomeStazione, new SimpleDateFormat("dd/MM/yyyy").parse(date), new SimpleDateFormat("hh:mm:ss").parse(time), vento, umidita, pressione, temperatura, precipitazioni, altitudine, massa);
        
    }
    
    //metodi
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField6 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        lblTemp = new javax.swing.JLabel();
        lblPrec = new javax.swing.JLabel();
        lblAlt = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblUm = new javax.swing.JLabel();
        lblPress = new javax.swing.JLabel();
        lblMassa = new javax.swing.JLabel();
        txtVento = new javax.swing.JTextField();
        txtUmidita = new javax.swing.JTextField();
        txtTemp = new javax.swing.JTextField();
        txtPrec = new javax.swing.JTextField();
        txtPress = new javax.swing.JTextField();
        txtAlt = new javax.swing.JTextField();
        txtMass = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jTextField6.setText("jTextField1");

        jTextField8.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTemp.setText("Temperatura");

        lblPrec.setText("Precipitazioni");

        lblAlt.setText("<html>\nAltitudine \n<br>\ndei ghiacciai\n</html>");

        jLabel1.setText("Vento");

        lblUm.setText("Umidità");

        lblPress.setText("Pressione");

        lblMassa.setText("<html> Massa  <br> dei ghiacciai </html>");

        txtVento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVentoActionPerformed(evt);
            }
        });
        txtVento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtVentoKeyPressed(evt);
            }
        });

        txtUmidita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUmiditaActionPerformed(evt);
            }
        });

        txtTemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTempActionPerformed(evt);
            }
        });

        txtPrec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecActionPerformed(evt);
            }
        });

        txtPress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPressActionPerformed(evt);
            }
        });

        txtAlt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAltActionPerformed(evt);
            }
        });

        txtMass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMassActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Inserisci una nota");

        jButton1.setText("Inserisci la rilevazione");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblPrec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtPrec, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblAlt, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtAlt, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblTemp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblPress, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblUm, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(26, 26, 26)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtUmidita)
                                    .addComponent(txtPress)
                                    .addComponent(txtTemp, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                                    .addComponent(txtVento, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblMassa, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(26, 26, 26)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtMass, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUm, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUmidita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPress, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrec, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAlt, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAlt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMassa, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String[] t = new String[3];
        t = temp.getVento();
        t[2] = txtVento.getText();
        temp.setVento(t);

        t = temp.getUmidita();
        t[2] = txtUmidita.getText();
        temp.setUmidita(t);

        t = temp.getPressione();
        t[2] = txtPress.getText();
        temp.setPressione(t);

        t = temp.getTemperatura();
        t[2] = txtTemp.getText();
        temp.setTemperatura(t);

        t = temp.getPrecipitazioni();
        t[2] = txtPrec.getText();
        temp.setPrecipitazioni(t);

        t = temp.getAltitudine();
        t[2] = txtAlt.getText();
        temp.setAltitudine(t);

        t = temp.getMassa();
        t[2] = txtMass.getText();
        temp.setMassa(t);

        try {
            FileManager.write(temp.toCSV(), Paths.get("Dati/ParametriClimatici.txt"));
        } catch (IOException ex) {
            Logger.getLogger(AddNotes.class.getName()).log(Level.SEVERE, null, ex);
        }
        MenuOperatore m = new MenuOperatore();
        m.setVisible(rootPaneCheckingEnabled);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtVentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVentoKeyPressed
        boolean max = txtVento.getText().length() > 256;
        if (max)
            txtVento.setText(txtVento.getText().substring(0, 256));
    }//GEN-LAST:event_txtVentoKeyPressed

    private void txtVentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVentoActionPerformed
        boolean max = txtVento.getText().length() > 256;
        if (max)
            txtVento.setText(txtVento.getText().substring(0, 256));
    }//GEN-LAST:event_txtVentoActionPerformed

    private void txtUmiditaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUmiditaActionPerformed
        boolean max = txtUmidita.getText().length() > 256;
        if (max)
            txtUmidita.setText(txtUmidita.getText().substring(0, 256));
    }//GEN-LAST:event_txtUmiditaActionPerformed

    private void txtPressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPressActionPerformed
        boolean max = txtPress.getText().length() > 256;
        if (max)
            txtPress.setText(txtPress.getText().substring(0, 256));
    }//GEN-LAST:event_txtPressActionPerformed

    private void txtTempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTempActionPerformed
        boolean max = txtTemp.getText().length() > 256;
        if (max)
            txtTemp.setText(txtTemp.getText().substring(0, 256));
    }//GEN-LAST:event_txtTempActionPerformed

    private void txtPrecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecActionPerformed
        boolean max = txtPrec.getText().length() > 256;
        if (max)
            txtPrec.setText(txtPrec.getText().substring(0, 256));
    }//GEN-LAST:event_txtPrecActionPerformed

    private void txtAltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAltActionPerformed
        boolean max = txtAlt.getText().length() > 256;
        if (max)
            txtAlt.setText(txtAlt.getText().substring(0, 256));
    }//GEN-LAST:event_txtAltActionPerformed

    private void txtMassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMassActionPerformed
        boolean max = txtMass.getText().length() > 256;
        if (max)
            txtMass.setText(txtMass.getText().substring(0, 256));
    }//GEN-LAST:event_txtMassActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JLabel lblAlt;
    private javax.swing.JLabel lblMassa;
    private javax.swing.JLabel lblPrec;
    private javax.swing.JLabel lblPress;
    private javax.swing.JLabel lblTemp;
    private javax.swing.JLabel lblUm;
    private javax.swing.JTextField txtAlt;
    private javax.swing.JTextField txtMass;
    private javax.swing.JTextField txtPrec;
    private javax.swing.JTextField txtPress;
    private javax.swing.JTextField txtTemp;
    private javax.swing.JTextField txtUmidita;
    private javax.swing.JTextField txtVento;
    // End of variables declaration//GEN-END:variables
}
