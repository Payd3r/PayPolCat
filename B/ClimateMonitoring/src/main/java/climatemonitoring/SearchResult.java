/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package climatemonitoring;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe che rappresenta la finestra di visualizzazione dei risultati di
 * ricerca.
 *
 * @author Ficara Paolo 755155 CO
 * @author Mauri Andrea 755140 CO
 * @author Luca Cattaneo 755083 CO
 */
public class SearchResult extends javax.swing.JFrame {

    /**
     * Lista delle previsioni meteorologiche.
     * <p>
     * Questo attributo contiene una lista delle previsioni meteorologiche.
     */
    private List<Forecast> f;
    /**
     * Menu dell'applicazione.
     * <p>
     * Questo attributo rappresenta il menu dell'applicazione.
     */
    private Menu m;
    private String area;
    private Date dataInizio;
    private Date dataFine;

    /**
     * Imposta l'icona dell'applicazione e posiziona la finestra al centro dello
     * schermo.
     * <p>
     * Questo metodo esegue le seguenti operazioni:
     * <ul>
     * <li>Carica un'immagine da un percorso relativo e la imposta come icona
     * dell'applicazione.</li>
     * <li>Calcola la dimensione dello schermo.</li>
     * <li>Posiziona la finestra corrente al centro dello schermo, basandosi
     * sulle dimensioni attuali della finestra.</li>
     * </ul>
     */
    private void grafica() {
        try {
            ClientHandler.getInstance().getStub().refresh();
        } catch (RemoteException ex) {
            Logger.getLogger(SearchResult.class.getName()).log(Level.SEVERE, null, ex);
        }
        ImageIcon img = new ImageIcon("../Data/icon.jpg");
        this.setIconImage(img.getImage());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - this.getWidth()) / 2, (screenSize.height - this.getHeight()) / 2);
    }

    /**
     * Crea una nuova istanza di SearchResult con il nome dell'area di ricerca e
     * il menu associato.
     *
     * @param areaName il nome dell'area di ricerca
     * @param me       il menu associato
     * @throws SQLException           se si verifica un errore nella connessione al
     *                                database
     * @throws ClassNotFoundException se si verifica un errore di classe non
     *                                trovata
     */
    public SearchResult(String areaName, Menu me, Date i, Date f) throws ClassNotFoundException, SQLException {
        initComponents();
        grafica();
        this.m = me;
        dataInizio = i;
        dataFine = f;
        this.area = areaName;
        refreshTable();
    }

    /**
     * Aggiorna la tabella delle rilevazioni per un'area specifica.
     * <p>
     * Questo metodo aggiorna la tabella delle rilevazioni utilizzando i dati
     * relativi all'area specificata. Prima di aggiornare la tabella, il metodo
     * recupera le previsioni meteorologiche relative all'area specificata
     * utilizzando il singleton
     * DatiCondivisi.getInstance().getForecasts(areaName). Successivamente,
     * popola la tabella con le informazioni sulle previsioni meteorologiche
     * ottenute.
     *
     * @throws ClassNotFoundException se la classe specificata non è trovata
     * @throws SQLException           se si verifica un errore SQL durante l'interazione
     *                                con il database
     */
    private void refreshTable() throws ClassNotFoundException, SQLException {
        DefaultTableModel model = (DefaultTableModel) tblRilevazioni.getModel();
        model.setRowCount(0);
        List<Forecast> temp = DatiCondivisi.getInstance().getForecasts(area);
        f = new ArrayList<>();
        if (dataInizio == null && dataFine == null) {
            dataInizio = temp.get(0).getData();
            dataFine = temp.get(temp.size() - 1).getData();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        jLabel2.setText(dateFormat.format(dataInizio) + " | " + dateFormat.format(dataFine));
        for (Forecast forecast : temp) {
            Date dataPrevisione = forecast.getData();
            if (!dataPrevisione.before(dataInizio) && !dataPrevisione.after(dataFine)) {
                model.addRow(new Object[]{dateFormat.format(dataPrevisione), new SimpleDateFormat("hh:mm:ss").format(forecast.getOra()), forecast.getVento()[0], forecast.getUmidita()[0], forecast.getPressione()[0], forecast.getTemperatura()[0], forecast.getPrecipitazioni()[0], forecast.getAltitudine()[0], forecast.getMassa()[0]});
                f.add(forecast);
            }
        }

        if (!f.isEmpty()) {
            try {
                jLabel4.setText(ClientHandler.getInstance().getStub().avg("vento") + "");
                jLabel5.setText(ClientHandler.getInstance().getStub().avg("umidita") + "");
                jLabel6.setText(ClientHandler.getInstance().getStub().avg("pressione") + "");
                jLabel7.setText(ClientHandler.getInstance().getStub().avg("temperatura") + "");
                jLabel8.setText(ClientHandler.getInstance().getStub().avg("precipitazioni") + "");
                jLabel9.setText(ClientHandler.getInstance().getStub().avg("altitudine") + "");
                jLabel10.setText(ClientHandler.getInstance().getStub().avg("massa") + "");
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblRilevazioni = new javax.swing.JTable();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblRilevazioni.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "Data", "Ora", "Vento", "Umidita", "Pressione", "Temperatura", "Precipitazioni", "Altitudine dei ghiacciai", "Massa dei ghiacciai"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                    true, true, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblRilevazioni.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRilevazioniMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblRilevazioni);

        jButton20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton20.setText("Torna alla home");
        jButton20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton20MouseClicked(evt);
            }
        });
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton21.setText("Cambia Intervallo");
        jButton21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton21MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Tutti i Valori");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("10/02/2024 to 10/05/2024");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Valori Medi");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("0");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("0");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("0");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("0");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("0");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("0");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1022, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(320, 320, 320)
                                                                                .addComponent(jButton21)
                                                                                .addGap(18, 18, 18))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(22, 22, 22)
                                                                                .addComponent(jLabel2)
                                                                                .addGap(83, 83, 83)
                                                                                .addComponent(jLabel4)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(jLabel5)
                                                                                .addGap(99, 99, 99)
                                                                                .addComponent(jLabel6)
                                                                                .addGap(2, 2, 2)))
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jButton20)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(106, 106, 106)
                                                                                .addComponent(jLabel7)
                                                                                .addGap(102, 102, 102)
                                                                                .addComponent(jLabel8)
                                                                                .addGap(94, 94, 94)
                                                                                .addComponent(jLabel9)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(jLabel10))))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(465, 465, 465)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel3)
                                                                        .addComponent(jLabel1))
                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                .addGap(57, 57, 57)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton21)
                                        .addComponent(jButton20))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblRilevazioniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRilevazioniMouseClicked
        JTable source = (JTable) evt.getSource();
        int row = source.rowAtPoint(evt.getPoint());
        int column = source.columnAtPoint(evt.getPoint());
        String[] temp;
        String str = "";
        boolean vis;
        switch (column) {
            case 2:
                temp = f.get(row).getVento();
                str = "Vento " + temp[0] + "\nNota: ";
                str += (temp.length > 1) ? temp[1] : "Nessuna nota";
                vis = true;
                break;
            case 3:
                temp = f.get(row).getUmidita();
                str = "Umidità " + temp[0] + "\nNota: ";
                str += (temp.length > 1) ? temp[1] : "Nessuna nota";
                vis = true;
                break;
            case 4:
                temp = f.get(row).getPressione();
                str = "Pressione " + temp[0] + "\nNota: ";
                str += (temp.length > 1) ? temp[1] : "Nessuna nota";
                vis = true;
                break;
            case 5:
                temp = f.get(row).getTemperatura();
                str = "Temperatura " + temp[0] + "\nNota: ";
                str += (temp.length > 1) ? temp[1] : "Nessuna nota";
                vis = true;
                break;
            case 6:
                temp = f.get(row).getPrecipitazioni();
                str = "Precipitazioni " + temp[0] + "\nNota: ";
                str += (temp.length > 1) ? temp[1] : "Nessuna nota";
                vis = true;
                break;
            case 7:
                temp = f.get(row).getAltitudine();
                str = "Altitudine " + temp[0] + "\nNota: ";
                str += (temp.length > 1) ? temp[1] : "Nessuna nota";
                vis = true;
                break;
            case 8:
                temp = f.get(row).getMassa();
                str = "Massa " + temp[0] + "\nNota: ";
                str += (temp.length > 1) ? temp[1] : "Nessuna nota";
                vis = true;
                break;
            default:
                vis = false;
                break;
        }
        if (vis) {
            JOptionPane.showMessageDialog(null, str);
        }
    }//GEN-LAST:event_tblRilevazioniMouseClicked

    private void jButton20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton20MouseClicked
        dispose();
        m.setVisible(true);
    }//GEN-LAST:event_jButton20MouseClicked

    private void jButton21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton21MouseClicked
        SelectDateRange l = new SelectDateRange(area, m);
        l.setVisible(rootPaneCheckingEnabled);
        this.dispose();
    }//GEN-LAST:event_jButton21MouseClicked

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton20ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblRilevazioni;
    // End of variables declaration//GEN-END:variables
}
