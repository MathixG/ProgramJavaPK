/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaprogram;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaprogram.*;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import model.*;
/**
 *
 * @author Danniel
 */
public class DELP extends javax.swing.JFrame {

    /**
     * Creates new form DELP
     */
    public DELP(JTable arg) throws SQLException {
        //data = new Vector<Produkt>();
        this.baza = new DatabaseDriver();
        data = this.baza.selectALLProducts();
        Namedat = new Vector<String>();
        this.Center = new DefaultTableCellRenderer();
        Center.setHorizontalAlignment(SwingConstants.CENTER);
        for( Produkt temp : data)
        {
            Namedat.addElement(temp.productName);
        }
        initComponents();
        tablica = arg;
         //System.out.println(data.get(0).productName);
        
    }
    
    public DatabaseDriver baza;
    private static JTable tablica;
    private Vector<Produkt> data;
    private Vector<String> Namedat;
    private ListTableModel model;
    private ResultSet result;
     private DefaultTableCellRenderer Center;
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox();
        CANCEL = new javax.swing.JButton();
        DELETE = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(276, 148));

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(this.Namedat));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        CANCEL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CANCEL.setText("CANCEL");
        CANCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CANCELActionPerformed(evt);
            }
        });

        DELETE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        DELETE.setText("DELETE");
        DELETE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DELETEActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(DELETE, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                        .addComponent(CANCEL, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DELETE, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CANCEL, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void CANCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CANCELActionPerformed
    this.dispose();            // TODO add your handling code here:
    }//GEN-LAST:event_CANCELActionPerformed

    private void DELETEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DELETEActionPerformed
       int a;
       a = this.jComboBox2.getSelectedIndex();
        try {
           // this.baza.stat.executeQuery("DELETE  FROM product WHERE productName = " + "'" + data.get(a).productName + "'"
            //+ " AND purchaseDate = " + "'" + data.get(a).purchaseDate + "'");
            this.baza.stat.execute("DELETE  FROM product WHERE productID = " + data.get(a).id);
            result = baza.stat.executeQuery("SELECT productID, productName AS Name,productPrice AS Price,purchasePlace AS 'Purchase Place',purchaseDate AS 'Purchase Date',"
                + "expiryDate AS 'Expiry Date',productWarranty AS 'Product Warranty',categories.categoryName AS Category FROM product inner join categories on categories.categoryID = product.categoryID");
            this.model = ListTableModel.createModelFromResultSet(result);
        } catch (SQLException ex) {
            Logger.getLogger(DELP.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.Namedat = new Vector<String>();
        this.data = new Vector<Produkt>();
        try {
            this.data = this.baza.selectALLProducts();
        } catch (SQLException ex) {
            Logger.getLogger(DELP.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(Produkt temp : data)
        {
            this.Namedat.addElement(temp.productName);
        }
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(this.Namedat));
            tablica.setModel(model);
        for(int i = 0;i < this.tablica.getColumnCount();i++)
        {
            this.tablica.getColumnModel().getColumn(i).setCellRenderer(Center);
        }
        this.tablica.removeColumn(this.tablica.getColumnModel().getColumn(0));
    }//GEN-LAST:event_DELETEActionPerformed

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
            java.util.logging.Logger.getLogger(DELP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DELP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DELP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DELP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new DELP(tablica).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(DELP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CANCEL;
    private javax.swing.JButton DELETE;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox2;
    // End of variables declaration//GEN-END:variables
}
