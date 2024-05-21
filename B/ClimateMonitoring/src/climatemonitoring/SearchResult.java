/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package climatemonitoring;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Classe che rappresenta la finestra di visualizzazione dei risultati di
 * ricerca.
 *
 * @author Ficara Paolo
 * @author Mauri Andrea
 * @author Luca Cattaneo
 */
public class SearchResult extends javax.swing.JFrame {

    private List<Forecast> f;
    private Menu m;

    /**
     * Crea una nuova istanza di SearchResult.
     */
    public SearchResult() {
        initComponents();
        grafica();
    }
    
    private void grafica() {
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
     * @throws IOException se si verifica un errore di input/output
     * @throws ParseException se si verifica un errore durante il parsing
     */
    public SearchResult(String areaName, Menu me) throws IOException, ParseException {
        initComponents();
        grafica();
        refreshTable(areaName);
        m = me;
    }

    private void refreshTable(String areaName) throws IOException, ParseException {
        DefaultTableModel model = (DefaultTableModel) tblRilevazioni.getModel();
        model.setRowCount(0);
        List<Forecast> temp = DatiCondivisi.getInstance().getForecasts(areaName);
        f = new ArrayList<Forecast>();
        for (int i = 0; i < temp.size(); i++) {
            model.addRow(new Object[]{new SimpleDateFormat("dd/MM/yyyy").format(temp.get(i).getData()), new SimpleDateFormat("hh:mm:ss").format(temp.get(i).getOra()), temp.get(i).getVento()[1], temp.get(i).getUmidita()[1], temp.get(i).getPressione()[1], temp.get(i).getTemperatura()[1], temp.get(i).getPrecipitazioni()[1], temp.get(i).getAltitudine()[1], temp.get(i).getMassa()[1]});
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
                str = "Vento " + temp[1] + "\nNota: ";
                str += (temp.length > 2) ? temp[2] : "Nessuna nota";
                vis = true;
                break;
            case 3:
                temp = f.get(row).getUmidita();
                str = "Umidità " + temp[1] + "\nNota: ";
                str += (temp.length > 2) ? temp[2] : "Nessuna nota";
                vis = true;
                break;
            case 4:
                temp = f.get(row).getPressione();
                str = "Pressione " + temp[1] + "\nNota: ";
                str += (temp.length > 2) ? temp[2] : "Nessuna nota";
                vis = true;
                break;
            case 5:
                temp = f.get(row).getTemperatura();
                str = "Temperatura " + temp[1] + "\nNota: ";
                str += (temp.length > 2) ? temp[2] : "Nessuna nota";
                vis = true;
                break;
            case 6:
                temp = f.get(row).getPrecipitazioni();
                str = "Precipitazioni " + temp[1] + "\nNota: ";
                str += (temp.length > 2) ? temp[2] : "Nessuna nota";
                vis = true;
                break;
            case 7:
                temp = f.get(row).getAltitudine();
                str = "Altitudine " + temp[1] + "\nNota: ";
                str += (temp.length > 2) ? temp[2] : "Nessuna nota";
                vis = true;
                break;
            case 8:
                temp = f.get(row).getMassa();
                str = "Massa " + temp[1] + "\nNota: ";
                str += (temp.length > 2) ? temp[2] : "Nessuna nota";
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
