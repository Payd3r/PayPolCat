/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package climatemonitoring;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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

    /**
     * Crea una nuova istanza di SearchResult.
     * <p>
     * Questo costruttore inizializza una nuova istanza di SearchResult,
     * inizializzando i componenti grafici e configurando l'aspetto grafico
     * della finestra.
     */
    public SearchResult() {
        initComponents();
        grafica();
    }

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
        this.setLocation((int) (screenSize.width - this.getWidth()) / 2, (int) (screenSize.height - this.getHeight()) / 2);
    }

    /**
     * Crea una nuova istanza di SearchResult con il nome dell'area di ricerca e
     * il menu associato.
     *
     * @param areaName il nome dell'area di ricerca
     * @param me il menu associato
     * @throws SQLException se si verifica un errore nella connessione al
     * database
     * @throws ClassNotFoundException se si verifica un errore di classe non
     * trovata
     */
    public SearchResult(String areaName, Menu me) throws ClassNotFoundException, SQLException {
        initComponents();
        grafica();
        refreshTable(areaName);
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
     * @param areaName il nome dell'area per la quale visualizzare le
     * rilevazioni
     * @throws ClassNotFoundException se la classe specificata non è trovata
     * @throws SQLException se si verifica un errore SQL durante l'interazione
     * con il database
     */
    private void refreshTable(String areaName) throws ClassNotFoundException, SQLException {
        DefaultTableModel model = (DefaultTableModel) tblRilevazioni.getModel();
        model.setRowCount(0);
        List<Forecast> temp = DatiCondivisi.getInstance().getForecasts(areaName);
        f = new ArrayList<Forecast>();
        for (int i = 0; i < temp.size(); i++) {
            model.addRow(new Object[]{new SimpleDateFormat("dd/MM/yyyy").format(temp.get(i).getData()), new SimpleDateFormat("hh:mm:ss").format(temp.get(i).getOra()), temp.get(i).getVento()[0], temp.get(i).getUmidita()[0], temp.get(i).getPressione()[0], temp.get(i).getTemperatura()[0], temp.get(i).getPrecipitazioni()[0], temp.get(i).getAltitudine()[0], temp.get(i).getMassa()[0]});
            f.add(temp.get(i));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblRilevazioni = new javax.swing.JTable();
        jButton20 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblRilevazioni.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data", "Ora", "Vento", "Umidita", "Pressione", "Temperatura", "Precipitazioni", "Altitudine dei ghiacciai", "Massa dei ghiacciai"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1022, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(432, 432, 432)
                .addComponent(jButton20)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblRilevazioniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRilevazioniMouseClicked
        JTable source = (JTable) evt.getSource();
        int row = source.rowAtPoint(evt.getPoint());
        int column = source.columnAtPoint(evt.getPoint());
        String s = source.getModel().getValueAt(row, column) + "";
        String[] temp;
        String str = "";
        boolean vis = false;
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton20;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblRilevazioni;
    // End of variables declaration//GEN-END:variables
}
