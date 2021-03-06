/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author weiren
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public Main() {
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

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Data Entry");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Aggregate");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("View Report");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Clean and Back up");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Account");

        jMenuItem1.setText("View Accounts");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("Add Accounts");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Delete Accounts");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem6.setText("Allocation Weights");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Hedge Account");

        jMenuItem4.setText("View Hedge");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem5.setText("Add Hedge");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuItem7.setText("Delete Hedge");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuItem8.setText("Allocation Weights");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        ArrayList<String> tableNames = new ArrayList<String>();
        
        MysqlConnect mysqldbconnect = new MysqlConnect();
        tableNames = mysqldbconnect.getTableNames(); 
                
        if (!tableNames.contains("entries")){
            MysqlConnect mysqldbconnect2 = new MysqlConnect();
            mysqldbconnect2.createEntries(); 
        } 

        DataEntry dataEntry = new DataEntry();
        dataEntry.setTitle("Data Entry");
        dataEntry.setVisible(true);
        dataEntry.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        
        String accDBname = "normal";
        AccountView accWindow = new AccountView(accDBname);
        accWindow.setTitle("Accounts");
        accWindow.setVisible(true);
        accWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        
        String accDBname = "normal";
        JFrame frame = new JFrame("Deleting an account");
        String name = JOptionPane.showInputDialog(frame, "Old Account(Format: FCM,Account Name):");
        JFrame error_frame = new JFrame();

        if (name != null && ("".equals(name))) {
            JOptionPane.showMessageDialog(error_frame, "Please input an account name. It can't be NULL.", "Error in Account Name!",JOptionPane.ERROR_MESSAGE);
        } else if (name == null) {
            JOptionPane.getRootFrame().dispose();   
        } else {
            String[] fcm_account = name.split(",");
            MysqlConnect mysqldbconnect = new MysqlConnect();
            mysqldbconnect.deleteAccount(accDBname, fcm_account);
        }
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

        String accDBname = "normal";
        JFrame frame = new JFrame("Adding a new account");
        String name = JOptionPane.showInputDialog(frame, "New Account(Format: FCM,Account Name):");
        JFrame error_frame = new JFrame();

        if (name != null && ("".equals(name))) {
            JOptionPane.showMessageDialog(error_frame, "Please input an account name. It can't be NULL.", "Error in Account Name!",JOptionPane.ERROR_MESSAGE);
        } else if (name == null) {
            JOptionPane.getRootFrame().dispose();   
        } else {
            String[] fcm_account = name.split(",");
            MysqlConnect mysqldbconnect = new MysqlConnect();
            mysqldbconnect.addNewAccount(accDBname, fcm_account);
        }

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        
        String accDBname = "hedge";
        AccountView accWindow = new AccountView(accDBname);
        accWindow.setTitle("Accounts");
        accWindow.setVisible(true);
        accWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        
        String accDBname = "hedge";
        JFrame frame = new JFrame("Adding a new account");
        String name = JOptionPane.showInputDialog(frame, "New Account(Format: FCM,Account Name):");
        JFrame error_frame = new JFrame();

        if (name != null && ("".equals(name))) {
            JOptionPane.showMessageDialog(error_frame, "Please input an account name. It can't be NULL.", "Error in Account Name!",JOptionPane.ERROR_MESSAGE);
        } else if (name == null) {
            JOptionPane.getRootFrame().dispose();   
        } else {
            String[] fcm_account = name.split(",");
            MysqlConnect mysqldbconnect = new MysqlConnect();
            mysqldbconnect.addNewAccount(accDBname, fcm_account);
            
        }
        
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        
        String accDBname = "hedge";
        JFrame frame = new JFrame("Deleting an account");
        String name = JOptionPane.showInputDialog(frame, "Old Account(Format: FCM,Account Name):");
        JFrame error_frame = new JFrame();

        if (name != null && ("".equals(name))) {
            JOptionPane.showMessageDialog(error_frame, "Please input an account name. It can't be NULL.", "Error in Account Name!",JOptionPane.ERROR_MESSAGE);
        } else if (name == null) {
            JOptionPane.getRootFrame().dispose();   
        } else {
            String[] fcm_account = name.split(",");
            MysqlConnect mysqldbconnect = new MysqlConnect();
            mysqldbconnect.deleteAccount(accDBname, fcm_account);
        }
        
        
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        
        AllocationView allocWeights = new AllocationView("normal");
        allocWeights.setTitle("Allocation Weight");
        allocWeights.setVisible(true);
        allocWeights.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        
        AllocationView allocWeights = new AllocationView("hedge");
        allocWeights.setTitle("Hedge Allocation Weight");
        allocWeights.setVisible(true);
        allocWeights.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
                
        ArrayList<String> tableNames = new ArrayList<String>();
        tableNames = getTablesinDB();
        
        int[] condition = new int[3];
        condition[0] = 0;
        condition[1] = 0;
        condition[2] = 0;
        
        if (tableNames.contains("allcombined")){
            condition[0] = 1;
        } 
        
        if (tableNames.contains("normalcombined")){
            condition[1] = 1;
        } 
                
        if (tableNames.contains("hedgecombined")){
            condition[2] = 1;
        } 
        
        MysqlConnect mysqldbconnect = new MysqlConnect();
        mysqldbconnect.aggregation(condition);
 
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        ReportView reportview = null;
        
        try {
            reportview = new ReportView();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        reportview.setTitle("Report View");
        reportview.setVisible(true);
        reportview.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    // End of variables declaration//GEN-END:variables

    
    public ArrayList<String> getTablesinDB(){
        ArrayList<String> tableNames = new ArrayList<String>();
        MysqlConnect mysqldbconnect = new MysqlConnect();
        tableNames = mysqldbconnect.getTableNames();
        return(tableNames);
    }





}

