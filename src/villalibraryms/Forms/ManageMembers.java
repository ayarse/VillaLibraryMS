/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package villalibraryms.Forms;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.Function;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import villalibraryms.Models.Role;
import villalibraryms.Models.User;
import villalibraryms.Repositories.UserRepository;

/**
 *
 * @author ayasnasih
 */
public class ManageMembers extends javax.swing.JFrame {

    private User selectedUser;

    /**
     * Creates new form ManageMembers
     */
    public ManageMembers() {
        initComponents();
        loadData();
    }

    private void loadData() {
        ResultSet rs = UserRepository.getAllUsers();
        jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        System.out.println("Data reloaded.");
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
        jTable1 = new javax.swing.JTable();
        btnActivateUser = new javax.swing.JButton();
        btnAddUser = new javax.swing.JButton();
        btnDeleteUser = new javax.swing.JButton();
        btnActivateUser1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnCancelMembership = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblBarcodeVal = new javax.swing.JLabel();
        lblIssuedDateVal = new javax.swing.JLabel();
        lblExpiryDateVal = new javax.swing.JLabel();
        btnIssueMembershipCard = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Manage Users");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btnActivateUser.setText("Toggle Active Status");
        btnActivateUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivateUserActionPerformed(evt);
            }
        });

        btnAddUser.setText("Add New User");
        btnAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserActionPerformed(evt);
            }
        });

        btnDeleteUser.setText("Delete User");
        btnDeleteUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteUserActionPerformed(evt);
            }
        });

        btnActivateUser1.setText("Edit User");
        btnActivateUser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivateUser1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
        jLabel2.setText("Select a user:");

        btnCancelMembership.setText("Cancel Membership Card");
        btnCancelMembership.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelMembershipActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
        jLabel1.setText("Membership Card Details");

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel3.setText("Barcode");

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel4.setText("Issued Date");

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel5.setText("Expiry Date");

        lblBarcodeVal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBarcodeVal.setText("-");

        lblIssuedDateVal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblIssuedDateVal.setText("-");

        lblExpiryDateVal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblExpiryDateVal.setText("-");

        btnIssueMembershipCard.setText("Issue Membership Card");
        btnIssueMembershipCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIssueMembershipCardActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddUser, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(393, 393, 393))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3))
                                        .addGap(112, 112, 112)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblIssuedDateVal)
                                            .addComponent(lblBarcodeVal)
                                            .addComponent(lblExpiryDateVal)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnActivateUser1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnActivateUser, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnDeleteUser, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnCancelMembership, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnIssueMembershipCard, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(43, 43, 43))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(btnAddUser, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActivateUser1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActivateUser, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteUser, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lblBarcodeVal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(lblIssuedDateVal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(lblExpiryDateVal)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCancelMembership, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))
                    .addComponent(btnIssueMembershipCard, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnActivateUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivateUserActionPerformed

        ifSelected(() -> {
            int selectedUserId = getSelectedUserId();
            String currentActiveStatus = jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString();
            int toggleMode = currentActiveStatus == "true" ? 0 : 1;
            UserRepository.updateActiveStatus(selectedUserId, toggleMode);
            loadData();

        });

    }//GEN-LAST:event_btnActivateUserActionPerformed

    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserActionPerformed
        new AddUser().setVisible(true);
    }//GEN-LAST:event_btnAddUserActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        loadData();
    }//GEN-LAST:event_formWindowActivated

    private void btnDeleteUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteUserActionPerformed

        ifSelected(() -> {
            UserRepository.deleteUser(getSelectedUserId());
            loadData();

        });
    }//GEN-LAST:event_btnDeleteUserActionPerformed

    private void btnActivateUser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivateUser1ActionPerformed

        ifSelected(() -> {
            new AddUser(getSelectedUserId()).setVisible(true);

        });

    }//GEN-LAST:event_btnActivateUser1ActionPerformed

    private void btnCancelMembershipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelMembershipActionPerformed
        ifSelected(() -> {
            UserRepository.cancelCard(getSelectedUserId());
            setSelectedUser(UserRepository.find(getSelectedUserId()));
            loadSelectedData();
        });
    }//GEN-LAST:event_btnCancelMembershipActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        ifSelected(() -> {

            setSelectedUser(UserRepository.find(getSelectedUserId()));
        });


    }//GEN-LAST:event_jTable1MouseClicked

    private void btnIssueMembershipCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIssueMembershipCardActionPerformed
        ifSelected(() -> {
            
            UserRepository.newCard(getSelectedUserId());
            setSelectedUser(UserRepository.find(getSelectedUserId()));
            loadSelectedData();
        });
    }//GEN-LAST:event_btnIssueMembershipCardActionPerformed

    private void ifSelected(Runnable runnable) {
        if (!jTable1.getSelectionModel().isSelectionEmpty()) {
            runnable.run();
        } else {
            JOptionPane.showMessageDialog(null, "No user is selected");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManageMembers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageMembers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageMembers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageMembers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageMembers().setVisible(true);
            }
        });
    }

    private int getSelectedUserId() {
        return Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivateUser;
    private javax.swing.JButton btnActivateUser1;
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnCancelMembership;
    private javax.swing.JButton btnDeleteUser;
    private javax.swing.JButton btnIssueMembershipCard;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblBarcodeVal;
    private javax.swing.JLabel lblExpiryDateVal;
    private javax.swing.JLabel lblIssuedDateVal;
    // End of variables declaration//GEN-END:variables

    private void loadSelectedData() {
        if (selectedUser != null && selectedUser.getMembershipCard() != null) {
            lblBarcodeVal.setText(selectedUser.getMembershipCard().getBarcode());
            SimpleDateFormat dateFmt = new SimpleDateFormat("dd-MM-YYYY");
            lblIssuedDateVal.setText(dateFmt.format(selectedUser.getMembershipCard().getIssuedAt()));
            lblExpiryDateVal.setText(dateFmt.format(selectedUser.getMembershipCard().getExpiresAt()));
        } else {
            lblBarcodeVal.setText("-");
            lblIssuedDateVal.setText("-");
            lblExpiryDateVal.setText("-");
        }
    }

    private void setSelectedUser(User user) {
        this.selectedUser = user;
        loadSelectedData();
    }
}
