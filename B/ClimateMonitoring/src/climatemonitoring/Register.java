/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package climatemonitoring;

import engine.Engine;
import java.util.logging.Level;
import java.util.logging.Logger;
import engine.Person;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * La classe Register rappresenta una finestra di registrazione utente. Permette
 * all'utente di inserire i propri dati personali e effettuare la registrazione.
 *
 * @author Ficara Paolo
 * @author Mauri Andrea
 * @author Luca Cattaneo
 */
public class Register extends javax.swing.JFrame {

    private DBManager dBManager;

    /**
     * Crea una nuova istanza della classe Register.
     *
     * @throws ClassNotFoundException Errore nel caricamento dei driver jdbc
     * @throws SQLException Errore nella connessione al database o
     * nell'esecuzione della query
     */
    public Register() throws SQLException, ClassNotFoundException, RemoteException {
        initComponents();
        grafica();
        createComboMonitoringStation(ClientHandler.getInstance().getStub().readStation());
        dBManager = new DBManager();
    }

    private void grafica() {
        ImageIcon img = new ImageIcon("../Data/icon.jpg");
        this.setIconImage(img.getImage());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((int) (screenSize.width - this.getWidth()) / 2, (int) (screenSize.height - this.getHeight()) / 2);
    }

    private void createComboMonitoringStation(List<MonitoringStation> monitoringStations) {
        comboMonitoringStation.addItem("---");
        for (MonitoringStation x : monitoringStations) {
            comboMonitoringStation.addItem(x.getName());
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

        Sex = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtPlace = new javax.swing.JTextField();
        txtDBirth = new javax.swing.JTextField();
        txtSurn = new javax.swing.JTextField();
        radioMale = new javax.swing.JRadioButton();
        RadioFemale = new javax.swing.JRadioButton();
        txtProv = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtNick = new javax.swing.JTextField();
        txtPassw = new javax.swing.JPasswordField();
        jLabel10 = new javax.swing.JLabel();
        comboMonitoringStation = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Nome");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Cognome");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Data di nascita");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Comune Nascita");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Provincia");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Sesso");

        txtDBirth.setForeground(new java.awt.Color(153, 153, 153));
        txtDBirth.setText("01/01/2001");
        txtDBirth.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDBirthFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDBirthFocusLost(evt);
            }
        });

        Sex.add(radioMale);
        radioMale.setText("M");

        Sex.add(RadioFemale);
        RadioFemale.setText("F");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("Registrati");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Email");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("NickName");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Password");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Centro di monitoraggio di afferenza");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel11.setText("Register Page");

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(comboMonitoringStation, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3))
                                        .addGap(48, 48, 48)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(radioMale)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(RadioFemale)
                                                .addGap(79, 79, 79)
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtProv, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtSurn)
                                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addGap(18, 18, 18))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(8, 8, 8)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtPlace)
                                            .addComponent(txtDBirth)
                                            .addComponent(txtEmail)
                                            .addComponent(txtNick)
                                            .addComponent(txtPassw, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel10)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(jLabel11))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel11)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSurn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radioMale)
                            .addComponent(RadioFemale)
                            .addComponent(jLabel6)
                            .addComponent(txtProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPlace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtPassw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(14, 14, 14)
                .addComponent(comboMonitoringStation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String controlloCampi() {
        String s = "";
        if (txtName.getText().length() < 1) {
            s += "Nome assente!\n";
        }
        if (txtSurn.getText().length() < 1) {
            s += "Cognome assente!\n";
        }
        if (!radioMale.isSelected() && !RadioFemale.isSelected()) {
            s += "Sesso non selezionato!\n";
        }
        SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = formatter1.parse(txtDBirth.getText());
        } catch (ParseException ex) {
            s += "Data sbagliata!\n";
        }
        if (txtProv.getText().length() != 2) {
            s += "Errore inserimento provincia!\n";
        }
        if (txtPlace.getText().length() < 1) {
            s += "Comune di nascita assente!\n";
        }
        if (txtEmail.getText().length() < 1 && !txtEmail.getText().contains("@")) {
            s += "Errore inserimento mail!\n";
        }
        if (txtNick.getText().length() < 1) {
            s += "Errore inserimento nick!\n";
        }
        if (txtPassw.getText().length() < 1) {
            s += "Errore inserimento pass!\n";
        }
        return s;
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String err = "";
        if ((err = controlloCampi()) != "")
            JOptionPane.showMessageDialog(null, err);
        else {
            try {
                Person person = new Person();
                person.setSurname(txtSurn.getText());
                person.setName(txtName.getText());
                SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
                Date date = formatter1.parse(txtDBirth.getText());
                String[] dateOfBirth = txtDBirth.getText().split("/");
                person.setDay(dateOfBirth[0]);
                person.setMonth(dateOfBirth[1]);
                person.setYear(dateOfBirth[2]);
                person.setBornCity(txtPlace.getText().toUpperCase());
                if (radioMale.isSelected()) {
                    person.setSex("M");
                } else {
                    person.setSex("F");
                }
                Engine engine = null;
                try {
                    engine = new Engine(person);
                } catch (Exception ex) {
                    Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                }

                String s = "";
                if (comboMonitoringStation.getSelectedIndex() != 0) {
                    ClientHandler.getInstance().getStub().writeUser(new User(txtName.getText(), txtSurn.getText(), engine.getCode(), txtEmail.getText(), txtNick.getText(), txtPassw.getText(), comboMonitoringStation.getItemAt(comboMonitoringStation.getSelectedIndex())));
                    JOptionPane.showMessageDialog(null, "Utente registrato", "Operazione andata a buon fine", JOptionPane.INFORMATION_MESSAGE);
                    Menu r = new Menu();
                    r.setVisible(rootPaneCheckingEnabled);
                    ClientHandler.getInstance().getStub().setOperatore(new User(txtName.getText(), txtSurn.getText(), engine.getCode(), txtEmail.getText(), txtNick.getText(), txtPassw.getText(), comboMonitoringStation.getItemAt(comboMonitoringStation.getSelectedIndex())));
                    this.dispose();
                    new Menu().setVisible(true);
                } else {
                    s = txtName.getText() + ";" + txtSurn.getText() + ";" + engine.getCode() + ";" + txtEmail.getText() + ";" + txtNick.getText() + ";" + txtPassw.getText() + ";";
                    CreateMonitoringStation window = null;
                    window = new CreateMonitoringStation(s);
                    window.setVisible(rootPaneCheckingEnabled);
                    this.dispose();
                    //new Menu().setVisible(true);
                }
            } catch (Exception a) {
                JOptionPane.showMessageDialog(null, a);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtDBirthFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDBirthFocusGained
        if (txtDBirth.getText().equals("01/01/2001")) {
            txtDBirth.setText("");
            txtDBirth.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_txtDBirthFocusGained

    private void txtDBirthFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDBirthFocusLost
        if (txtDBirth.getText().equals("")) {
            txtDBirth.setText("01/01/2001");
            txtDBirth.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_txtDBirthFocusLost

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Menu m = null;
        try {
            m = new Menu();
        } catch (ClassNotFoundException | SQLException | RemoteException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        m.setVisible(rootPaneCheckingEnabled);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton RadioFemale;
    private javax.swing.ButtonGroup Sex;
    private javax.swing.JComboBox<String> comboMonitoringStation;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton radioMale;
    private javax.swing.JTextField txtDBirth;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNick;
    private javax.swing.JPasswordField txtPassw;
    private javax.swing.JTextField txtPlace;
    private javax.swing.JTextField txtProv;
    private javax.swing.JTextField txtSurn;
    // End of variables declaration//GEN-END:variables
}
