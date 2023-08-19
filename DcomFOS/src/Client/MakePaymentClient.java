/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import FOSInterface.YWInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class MakePaymentClient extends javax.swing.JFrame {

    int userID;
    int modeID;
    int cartID;
    int orderID;
    double totalprice;

    public static void createAndShowGUI(int userID, int modeID, int cartID, int orderID, double totalprice){
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MakePaymentClient frame = null;
                try {
                    frame = new MakePaymentClient(userID, modeID, cartID, orderID, totalprice);
                } catch (RemoteException ex) {
                    Logger.getLogger(MakePaymentClient.class.getName()).log(Level.SEVERE, null, ex);
                }
                frame.setVisible(true);
            }
        });
    }
    public static void createAndShowGUI(int userID, int orderID, double totalprice){
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MakePaymentClient frame = null;
                try {
                    frame = new MakePaymentClient(userID, orderID, totalprice);
                } catch (RemoteException ex) {
                    Logger.getLogger(MakePaymentClient.class.getName()).log(Level.SEVERE, null, ex);
                }
                frame.setVisible(true);
            }
        });
    }
    
        
    /**
     * Creates new form makePayment
     */
    
    /**
     * Creates new form makePayment
     * @throws java.rmi.RemoteException
     */
            
    /**
     * Creates new form makePayment
     */
    
    /**
     * Creates new form makePayment
     * @throws java.rmi.RemoteException
     */
            
    /**
     * Creates new form makePayment
     */
    
    /**
     * Creates new form makePayment
     * @throws java.rmi.RemoteException
     */
            
    /**
     * Creates new form makePayment
     */
    
    /**
     * Creates new form makePayment
     * @throws java.rmi.RemoteException
     */
    
    public MakePaymentClient(int userID, int modeID, int cartID, int orderID, double totalprice) throws RemoteException{
        initComponents();
        
        this.userID = userID;
        this.modeID = modeID;
        this.cartID = cartID;
        this.orderID = orderID;
        this.totalprice = totalprice;
        
        // Group radio buttons so only one can be selected at a time
        ButtonGroup grp = new ButtonGroup();
        grp.add(debitRadio);
        grp.add(creditRadio);

        // Set default selection
        debitRadio.setSelected(true);
        
        // Set amount = TFafterTax
        TFamount.setText(String.valueOf(totalprice));
        TFamount.setEditable(false);

    }
    public MakePaymentClient(int userID, int orderID, double totalprice) throws RemoteException{
        initComponents();
        
        this.userID = userID;
        this.orderID = orderID;
        this.totalprice = totalprice;
        
        // Group radio buttons so only one can be selected at a time
        ButtonGroup grp = new ButtonGroup();
        grp.add(debitRadio);
        grp.add(creditRadio);

        // Set default selection
        debitRadio.setSelected(true);
        
        // Set amount = TFafterTax
        TFamount.setText(String.valueOf(totalprice));
        TFamount.setEditable(false);

    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ButtonBack = new javax.swing.JButton();
        debitRadio = new javax.swing.JRadioButton();
        creditRadio = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        TFcardNo = new javax.swing.JTextField();
        TFexipirationDate = new javax.swing.JTextField();
        TFcvv = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TFamount = new javax.swing.JTextField();
        ButtonPay = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("McGee's Food Ordering System (FOS)");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Payment Page");

        ButtonBack.setText("< Back");
        ButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonBackActionPerformed(evt);
            }
        });

        debitRadio.setText("Debit Card");

        creditRadio.setText("Credit Card");

        jLabel5.setText("Card Number:");

        jLabel6.setText("Amount to be paid:");

        jLabel3.setText("Expiration Date:");

        jLabel4.setText("CVV:");

        ButtonPay.setText("Pay Now >");
        ButtonPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonPayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ButtonPay, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(231, 231, 231)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(debitRadio)
                        .addGap(32, 32, 32)
                        .addComponent(creditRadio))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(jLabel1))
                            .addComponent(ButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(154, 154, 154)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(71, 71, 71)
                                        .addComponent(jLabel4)
                                        .addGap(28, 28, 28))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6))
                                        .addGap(18, 18, 18))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(170, 170, 170)
                                .addComponent(jLabel5)
                                .addGap(31, 31, 31)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TFcardNo)
                            .addComponent(TFexipirationDate)
                            .addComponent(TFcvv)
                            .addComponent(TFamount, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(126, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(ButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(debitRadio)
                    .addComponent(creditRadio))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TFamount, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TFcardNo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TFexipirationDate, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TFcvv, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(ButtonPay, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonPayActionPerformed
        // TODO add your handling code here:
        
        // validate the textfield
        if (TFcardNo.getText().trim().isEmpty() || TFcvv.getText().trim().isEmpty() || TFexipirationDate.getText().trim().isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Please fill in the required field!", "Reminder", JOptionPane.WARNING_MESSAGE);
        }
        
        // payment method
        String paymentMethod = null;
        Enumeration<AbstractButton> buttons = buttonGroup1.getElements();
        while (buttons.hasMoreElements()) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                paymentMethod = button.getText();
                System.out.println("Selected: " + button.getText());
                break;
            }
        }
        
        
        // payment timestamp
        Timestamp pytimestamp; 
        pytimestamp = new java.sql.Timestamp(System.currentTimeMillis());
        
        // put into db
        YWInterface stub = null;

        try {
            stub = (YWInterface)Naming.lookup("rmi://localhost:1045/Payment");
        } catch(Exception e) {
            System.out.println("Stub error:");
            e.printStackTrace();
        }
        
        boolean paymentSucess = false;
        try {
            paymentSucess = stub.makePayment(orderID, totalprice, paymentMethod, pytimestamp);
        } catch (RemoteException ex) {
            Logger.getLogger(MakePaymentClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MakePaymentClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Payment status:" + paymentSucess);
        
        if (paymentSucess) {
            try {
                boolean updateOrderSuccess = stub.updateOrderPaid(orderID);

                if (updateOrderSuccess) {
                    JOptionPane.showMessageDialog(null, "Make Payment Successful! \n"
                        + "Thank you for your payment. \n"
                        + "Please come again ^^~", "From McGee:", JOptionPane.INFORMATION_MESSAGE);

                    // Close the current frame (if needed)
                    this.dispose();

                    // Open the menu page

                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Oh no! Something went wrong. \n"
                            + "Please proceed to the counter to make payment. \n"
                            + "Thank you very much ^^~", "From McGee:", JOptionPane.INFORMATION_MESSAGE);
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_ButtonPayActionPerformed

    private void ButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonBackActionPerformed
        // TODO add your handling code here:
        MenuFrameClient.createAndShowGUI(userID);
        this.dispose();
    }//GEN-LAST:event_ButtonBackActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonBack;
    private javax.swing.JButton ButtonPay;
    private javax.swing.JTextField TFamount;
    private javax.swing.JTextField TFcardNo;
    private javax.swing.JTextField TFcvv;
    private javax.swing.JTextField TFexipirationDate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton creditRadio;
    private javax.swing.JRadioButton debitRadio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables

}
