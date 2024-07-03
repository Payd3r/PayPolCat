/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package climatemonitoring;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

/**
 *
 * @author Payd3r
 */
public class SelectDateRange extends javax.swing.JFrame {

    private String areaName;
    private Menu m;

    private JDatePickerImpl startDatePicker;
    private JDatePickerImpl endDatePicker;
    private JButton confirmButton;

    public SelectDateRange(String area, Menu m) {
        initComponents();
        areaName = area;
        this.m = m;

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JLabel titleLabel = new JLabel("Select Start and End Dates");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Configurazione del modello per il JDatePicker per la data di inizio
        SqlDateModel startModel = new SqlDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl startDatePanel = new JDatePanelImpl(startModel, p);
        startDatePicker = new JDatePickerImpl(startDatePanel, new DateLabelFormatter());

        SqlDateModel endModel = new SqlDateModel();
        JDatePanelImpl endDatePanel = new JDatePanelImpl(endModel, p);
        endDatePicker = new JDatePickerImpl(endDatePanel, new DateLabelFormatter());

        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(new JLabel("Start Date:"));
        mainPanel.add(startDatePicker);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(new JLabel("End Date:"));
        mainPanel.add(endDatePicker);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        confirmButton = new JButton("Confirm");
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(confirmButton);

        startDatePicker.addActionListener(e -> validateDates());
        endDatePicker.addActionListener(e -> validateDates());
        confirmButton.addActionListener(e -> retrieveDates());

        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void validateDates() {
        Calendar today = Calendar.getInstance();
        // Recupera la data di inizio 
        java.sql.Date startDateSql = (java.sql.Date) startDatePicker.getModel().getValue();
        Calendar startDate = null;
        if (startDateSql != null) {
            startDate = Calendar.getInstance();
            startDate.setTimeInMillis(startDateSql.getTime());
        }
        
        // Recupera la data di fine
        java.sql.Date endDateSql = (java.sql.Date) endDatePicker.getModel().getValue();
        Calendar endDate = null;
        if (endDateSql != null) {
            endDate = Calendar.getInstance();
            endDate.setTimeInMillis(endDateSql.getTime());
        }

        // Controllo che la data di inizio sia prima di oggi
        if (startDate != null && !startDate.before(today)) {
            JOptionPane.showMessageDialog(this, "La data d'inizio deve essere prima di oggi!");
            startDatePicker.getModel().setValue(null); // Reset the invalid date
        }

        // Controllo che la data di fine sia dopo la data di inizio
        if (startDate != null && endDate != null && !endDate.after(startDate)) {
            JOptionPane.showMessageDialog(this, "La data di fine deve essere dopo quella d'inizio!");
            endDatePicker.getModel().setValue(null); // Reset the invalid date
        }
    }

    private void retrieveDates() {
        java.sql.Date startDateSql = (java.sql.Date) startDatePicker.getModel().getValue();
        java.sql.Date endDateSql = (java.sql.Date) endDatePicker.getModel().getValue();

        if (startDateSql != null && endDateSql != null) {
            try {
                SearchResult l = new SearchResult(areaName, m, startDateSql, endDateSql);
                l.setVisible(rootPaneCheckingEnabled);
                this.dispose();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(SelectDateRange.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selezionare entrambe le date!");
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 269, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
