/*
 * Library Management System - Ayas Nasih, S1600655
 * 
 * 
 */
package villalibraryms.Forms;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import javax.swing.JOptionPane;
import villalibraryms.Models.BookItem;
import villalibraryms.Models.Borrow;
import villalibraryms.Models.User;
import villalibraryms.Repositories.BookRepository;
import villalibraryms.Repositories.MiscRepository;

/**
 *
 * @author ayasnasih
 */
public class ReturnBook extends javax.swing.JFrame {

    /**
     * Creates new form ReturnBook
     */
    Borrow borrowedBook;
    double fineAmt = 0.0;
    long overdue = 0;

    public ReturnBook() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtBarcode = new javax.swing.JTextField();
        btnScan = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblBorrowerName = new javax.swing.JLabel();
        lblMembershipCard = new javax.swing.JLabel();
        lblBorrowerRole = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        lblAuthor = new javax.swing.JLabel();
        lblIsbn = new javax.swing.JLabel();
        lblFineAmtz = new javax.swing.JLabel();
        lblFineAmt = new javax.swing.JLabel();
        btnReturnBook = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        lblBorrowedDate = new javax.swing.JLabel();
        lblFineAmtz1 = new javax.swing.JLabel();
        lblOverdue = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Return Book");

        btnScan.setText("Scan");
        btnScan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScanActionPerformed(evt);
            }
        });

        jLabel1.setText("Book Barcode");

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel2.setText("Title");
        jLabel2.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel3.setText("Author");
        jLabel3.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel4.setText("ISBN");
        jLabel4.setToolTipText("");

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel5.setText("Borrower Name");
        jLabel5.setToolTipText("");

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel6.setText("Membership Card");
        jLabel6.setToolTipText("");

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel7.setText("Borrower Role");
        jLabel7.setToolTipText("");

        lblBorrowerName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBorrowerName.setText("-");
        lblBorrowerName.setToolTipText("");

        lblMembershipCard.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMembershipCard.setText("-");
        lblMembershipCard.setToolTipText("");

        lblBorrowerRole.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBorrowerRole.setText("-");
        lblBorrowerRole.setToolTipText("");

        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTitle.setText("-");
        lblTitle.setToolTipText("");

        lblAuthor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAuthor.setText("-");
        lblAuthor.setToolTipText("");

        lblIsbn.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblIsbn.setText("-");
        lblIsbn.setToolTipText("");

        lblFineAmtz.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        lblFineAmtz.setText("Fine Amout");
        lblFineAmtz.setToolTipText("");

        lblFineAmt.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFineAmt.setText("-");
        lblFineAmt.setToolTipText("");

        btnReturnBook.setText("Confirm Return");
        btnReturnBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnBookActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel8.setText("Borrowed Date");
        jLabel8.setToolTipText("");

        lblBorrowedDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBorrowedDate.setText("-");
        lblBorrowedDate.setToolTipText("");

        lblFineAmtz1.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        lblFineAmtz1.setText("Overdue");
        lblFineAmtz1.setToolTipText("");

        lblOverdue.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblOverdue.setText("-");
        lblOverdue.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2))
                                    .addGap(86, 86, 86)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblAuthor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblIsbn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnScan)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(lblFineAmtz)
                                    .addComponent(jLabel8)
                                    .addComponent(lblFineAmtz1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblBorrowerRole, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblMembershipCard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblBorrowerName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblFineAmt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblBorrowedDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblOverdue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(btnReturnBook, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnScan, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            .addComponent(txtBarcode))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(lblIsbn)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblAuthor)))
                        .addGap(50, 50, 50)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblBorrowerName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMembershipCard, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblBorrowerRole, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblBorrowedDate, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFineAmtz)
                    .addComponent(lblFineAmt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFineAmtz1)
                    .addComponent(lblOverdue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(btnReturnBook, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnScanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScanActionPerformed

        Borrow borrow = BookRepository.findBorrowedByBarcode(txtBarcode.getText());
        if (borrow == null) {
            JOptionPane.showMessageDialog(null, "Borrowed book record not found.");
            clearLabels();
            return;
        }
        if (borrow.getBorrowedDate() != null && borrow.getReturnedDate() == null) {
            borrowedBook = borrow;
            calculateFine();
            populateLabels();
        } else {
            JOptionPane.showMessageDialog(null, "No copies of this book are pending return.");
            clearLabels();
        }

    }//GEN-LAST:event_btnScanActionPerformed

    private void btnReturnBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnBookActionPerformed

        BookRepository.returnBook(borrowedBook, LocalDate.now());
        String msg = "Book has been returned.";
        if (fineAmt > 0.0) {
            BookRepository.createFine(borrowedBook, LocalDate.now(), fineAmt);
            msg = "Book has been returned with fine.";
        }
        JOptionPane.showMessageDialog(null, msg);
        MiscRepository.generateReturnNotifications(borrowedBook);

        this.dispose();
    }//GEN-LAST:event_btnReturnBookActionPerformed

    private void populateLabels() {
        BookItem book = borrowedBook.getBook();
        User user = borrowedBook.getUser();
        lblTitle.setText(book.getTitle());
        lblAuthor.setText(book.getAuthor().getName());
        lblIsbn.setText(book.getIsbn());

        lblBorrowerName.setText(user.getDisplayName());
        lblMembershipCard.setText(user.getMembershipCard().getBarcode());
        lblBorrowerRole.setText(user.getRole().toString());
        lblBorrowedDate.setText(book.getBorrowedDate().toLocalDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));

        lblFineAmt.setText("MVR " + fineAmt + " (MVR 2.50 / day)");
        lblOverdue.setText(overdue + " Day(s)");
    }

    private void calculateFine() {
        LocalDate borrowedDate = borrowedBook.getBorrowedDate().toLocalDate();
        LocalDate dueDate = borrowedDate.plusDays(BookRepository.MAX_BORROW_DAYS);
        LocalDate today = LocalDate.now();
        if (LocalDate.now().isAfter(dueDate)) {
            overdue = Duration.between(dueDate.atStartOfDay(), today.atStartOfDay()).toDays();
            fineAmt = overdue * 2.5;
        } else {
            fineAmt = 0.0;
            overdue = 0;
        }
    }

    private void clearLabels() {
        lblTitle.setText("-");
        lblAuthor.setText("-");
        lblIsbn.setText("-");

        lblBorrowerName.setText("-");
        lblMembershipCard.setText("-");
        lblBorrowerRole.setText("-");
        lblFineAmt.setText("-");
        lblOverdue.setText("-");

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
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReturnBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReturnBook;
    private javax.swing.JButton btnScan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lblAuthor;
    private javax.swing.JLabel lblBorrowedDate;
    private javax.swing.JLabel lblBorrowerName;
    private javax.swing.JLabel lblBorrowerRole;
    private javax.swing.JLabel lblFineAmt;
    private javax.swing.JLabel lblFineAmtz;
    private javax.swing.JLabel lblFineAmtz1;
    private javax.swing.JLabel lblIsbn;
    private javax.swing.JLabel lblMembershipCard;
    private javax.swing.JLabel lblOverdue;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextField txtBarcode;
    // End of variables declaration//GEN-END:variables
}
