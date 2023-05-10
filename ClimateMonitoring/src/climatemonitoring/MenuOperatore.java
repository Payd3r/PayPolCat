/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package climatemonitoring;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ficara Paolo, Mauri Andrea, Luca Cattaneo
 */
public class MenuOperatore extends javax.swing.JFrame {

    /**
     * Creates new form MenuOperatore
     */
    List<Forecast> f;

    public MenuOperatore() {
        initComponents();
        try {
            lblWelcome.setText("Benvenuto: " + DatiCondivisi.getInstance().getOperatore().getNick());
            createComboMonitoringStation();
            refreshTable(cmbAreas.getSelectedItem().toString(), DatiCondivisi.getInstance().getOperatore().getStation());
        } catch (IOException ex) {
            Logger.getLogger(MenuOperatore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(MenuOperatore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public MenuOperatore(User u) throws IOException, ParseException {
//        initComponents();
//        DatiCondivisi.getInstance().setOperatore(u);
//        lblWelcome.setText("Benvenuto: " + DatiCondivisi.getInstance().getOperatore().getNick());
//        createComboMonitoringStation();
//        refreshTable(cmbAreas.getSelectedItem().toString(), DatiCondivisi.getInstance().getOperatore().getStation());
//    }
    private void refreshTable(String area, String stazione) throws IOException, ParseException {
        DefaultTableModel model = (DefaultTableModel) tblRilevazioni.getModel();
        model.setRowCount(0);
        List<Forecast> temp = FileManager.readForecast(Paths.get("Dati/ParametriClimatici.txt"));
        f = new ArrayList<Forecast>();
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).getIdCittà().equals(area) && temp.get(i).getNomeStazione().equals(stazione)) {
                model.addRow(new Object[]{new SimpleDateFormat("dd/MM/yyyy").format(temp.get(i).getData()), new SimpleDateFormat("hh:mm:ss").format(temp.get(i).getOra()), temp.get(i).getVento()[1], temp.get(i).getUmidita()[1], temp.get(i).getPressione()[1], temp.get(i).getTemperatura()[1], temp.get(i).getPrecipitazioni()[1], temp.get(i).getAltitudine()[1], temp.get(i).getMassa()[1]});
                f.add(temp.get(i));
            }
        }
    }

    private void createComboMonitoringStation() throws IOException, ParseException {
        List<MonitoringStation> monitoringStations = DatiCondivisi.getInstance().getMonitoringStations();
        String[] areas = null;
        for (int i = 0; i < monitoringStations.size(); i++) {
            if (DatiCondivisi.getInstance().getOperatore().getStation().equals(monitoringStations.get(i).getName())) {
                areas = monitoringStations.get(i).getInterestingAreas();
                break;
            }
        }
        for (String x : areas) {
            cmbAreas.addItem(x);
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

        lblWelcome = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        sldVento = new javax.swing.JSlider();
        lblVento = new javax.swing.JLabel();
        lblUm = new javax.swing.JLabel();
        sldUm = new javax.swing.JSlider();
        lblUmm = new javax.swing.JLabel();
        lblPress = new javax.swing.JLabel();
        sldPres = new javax.swing.JSlider();
        lblPressi = new javax.swing.JLabel();
        lblTemp = new javax.swing.JLabel();
        sldTemp = new javax.swing.JSlider();
        lblTempe = new javax.swing.JLabel();
        lblPrec = new javax.swing.JLabel();
        sldPrec = new javax.swing.JSlider();
        lblPreci = new javax.swing.JLabel();
        lblAlt = new javax.swing.JLabel();
        sldAlt = new javax.swing.JSlider();
        lblAlti = new javax.swing.JLabel();
        lblMassa = new javax.swing.JLabel();
        sldMassa = new javax.swing.JSlider();
        lblMas = new javax.swing.JLabel();
        cmbAreas = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRilevazioni = new javax.swing.JTable();
        btnInsert = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblWelcome.setText("Bentornato: ");

        jLabel1.setText("Vento");

        sldVento.setMaximum(5);
        sldVento.setMinimum(1);
        sldVento.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sldVentoStateChanged(evt);
            }
        });

        lblVento.setText("5");

        lblUm.setText("Umidità");

        sldUm.setMaximum(5);
        sldUm.setMinimum(1);
        sldUm.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sldUmStateChanged(evt);
            }
        });

        lblUmm.setText("5");

        lblPress.setText("Pressione");

        sldPres.setMaximum(5);
        sldPres.setMinimum(1);
        sldPres.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sldPresStateChanged(evt);
            }
        });

        lblPressi.setText("5");

        lblTemp.setText("Temperatura");

        sldTemp.setMaximum(5);
        sldTemp.setMinimum(1);
        sldTemp.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sldTempStateChanged(evt);
            }
        });

        lblTempe.setText("5");

        lblPrec.setText("Precipitazioni");

        sldPrec.setMaximum(5);
        sldPrec.setMinimum(1);
        sldPrec.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sldPrecStateChanged(evt);
            }
        });

        lblPreci.setText("5");

        lblAlt.setText("<html>\nAltitudine \n<br>\ndei ghiacciai\n</html>");

        sldAlt.setMaximum(5);
        sldAlt.setMinimum(1);
        sldAlt.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sldAltStateChanged(evt);
            }
        });

        lblAlti.setText("5");

        lblMassa.setText("<html> Massa  <br> dei ghiacciai </html>");

        sldMassa.setMaximum(5);
        sldMassa.setMinimum(1);
        sldMassa.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sldMassaStateChanged(evt);
            }
        });

        lblMas.setText("5");

        cmbAreas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAreasActionPerformed(evt);
            }
        });

        jLabel9.setText("Area di interesse");

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

        btnInsert.setText("Inserisci nuova rilevazione");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        jButton1.setText("Torna alla home");
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
                .addGap(89, 89, 89)
                .addComponent(lblWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(998, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(57, 57, 57))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblUm, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPress, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMassa, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTemp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPrec, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                            .addComponent(lblAlt))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sldUm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblUmm, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sldPres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblPressi, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sldTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblTempe, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sldPrec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblPreci, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sldAlt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblAlti, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sldMassa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblMas, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sldVento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblVento, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnInsert)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbAreas, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbAreas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sldVento, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblVento, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sldUm, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUm, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUmm, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sldPres, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPress, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPressi, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sldTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTempe, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sldPrec, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPrec, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPreci, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sldAlt, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAlt, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAlti, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sldMassa, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMas, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMassa, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnInsert)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sldVentoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sldVentoStateChanged
        lblVento.setText(sldVento.getValue() + "");
    }//GEN-LAST:event_sldVentoStateChanged

    private void sldUmStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sldUmStateChanged
        lblUmm.setText(sldUm.getValue() + "");
    }//GEN-LAST:event_sldUmStateChanged

    private void sldPresStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sldPresStateChanged
        lblPressi.setText(sldPres.getValue() + "");
    }//GEN-LAST:event_sldPresStateChanged

    private void sldTempStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sldTempStateChanged
        lblTempe.setText(sldTemp.getValue() + "");
    }//GEN-LAST:event_sldTempStateChanged

    private void sldPrecStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sldPrecStateChanged
        lblPreci.setText(sldPrec.getValue() + "");
    }//GEN-LAST:event_sldPrecStateChanged

    private void sldAltStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sldAltStateChanged
        lblAlti.setText(sldAlt.getValue() + "");
    }//GEN-LAST:event_sldAltStateChanged

    private void sldMassaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sldMassaStateChanged
        lblMas.setText(sldMassa.getValue() + "");
    }//GEN-LAST:event_sldMassaStateChanged

    private void cmbAreasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAreasActionPerformed
        try {
            refreshTable(cmbAreas.getSelectedItem().toString(), DatiCondivisi.getInstance().getOperatore().getStation());
        } catch (IOException ex) {
            Logger.getLogger(MenuOperatore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(MenuOperatore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmbAreasActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        int wind = -1, humidity = -1, pressure = -1, temperature = -1, rainfall = -1, glacierAltitude = -1, massGlaciers = -1;
        wind = sldVento.getValue();
        humidity = sldUm.getValue();
        pressure = sldPres.getValue();
        temperature = sldTemp.getValue();
        rainfall = sldPrec.getValue();
        glacierAltitude = sldAlt.getValue();
        massGlaciers = sldMassa.getValue();
        Date now = new Date();
        String date = new SimpleDateFormat("dd/MM/yyyy").format(now);
        String time = new SimpleDateFormat("hh:mm:ss").format(now);
        //DefaultTableModel model = (DefaultTableModel) tblRilevazioni.getModel();
        //model.addRow(new Object[]{date, time, wind, humidity, pressure, temperature, rainfall, glacierAltitude, massGlaciers});
        try {
            AddNotes a = new AddNotes(DatiCondivisi.getInstance().getOperatore(), cmbAreas.getSelectedItem().toString(), DatiCondivisi.getInstance().getOperatore().getStation(), date, time, wind, humidity, pressure, temperature, rainfall, glacierAltitude, massGlaciers);
            a.setVisible(rootPaneCheckingEnabled);
        } catch (IOException ex) {
            Logger.getLogger(MenuOperatore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(MenuOperatore.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.dispose();
    }//GEN-LAST:event_btnInsertActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
        new Menu().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInsert;
    private javax.swing.JComboBox<String> cmbAreas;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAlt;
    private javax.swing.JLabel lblAlti;
    private javax.swing.JLabel lblMas;
    private javax.swing.JLabel lblMassa;
    private javax.swing.JLabel lblPrec;
    private javax.swing.JLabel lblPreci;
    private javax.swing.JLabel lblPress;
    private javax.swing.JLabel lblPressi;
    private javax.swing.JLabel lblTemp;
    private javax.swing.JLabel lblTempe;
    private javax.swing.JLabel lblUm;
    private javax.swing.JLabel lblUmm;
    private javax.swing.JLabel lblVento;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JSlider sldAlt;
    private javax.swing.JSlider sldMassa;
    private javax.swing.JSlider sldPrec;
    private javax.swing.JSlider sldPres;
    private javax.swing.JSlider sldTemp;
    private javax.swing.JSlider sldUm;
    private javax.swing.JSlider sldVento;
    private javax.swing.JTable tblRilevazioni;
    // End of variables declaration//GEN-END:variables
}
