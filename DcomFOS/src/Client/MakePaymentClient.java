/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import static Client.paymentDetailValidator.addValidation;
import javax.swing.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import FOSInterface.CheckoutInterface;
import java.awt.event.ItemEvent;

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
    String paymentMethod;

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
     * @param userID
     * @param modeID
     * @param cartID
     * @param orderID
     * @param totalprice
     * @throws java.rmi.RemoteException
     */
    
    
    
    public MakePaymentClient(int userID, int modeID, int cartID, 
            int orderID, double totalprice) throws RemoteException{
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
        
        debitRadio.addItemListener(this::radioButtonChanged);
        creditRadio.addItemListener(this::radioButtonChanged);

        // Set default selection
        debitRadio.setSelected(true);
        paymentMethod = "Debit Card";
        
        // Set amount = TFafterTax
        TFamount.setText(String.format("%.2f", totalprice));
        TFamount.setEditable(false);
        
        // Disable button
        ButtonPay.setEnabled(false);
        
        // create arrays for validation
        JTextField textfields[] = {TFcardNo, TFexipirationDate1, TFexipirationDate2, TFcvv};
        List<Function<String, String>> validation = Arrays.asList(
                paymentDetailValidator::validateCardNo,
                paymentDetailValidator::validateExpMonth,
                paymentDetailValidator::validateExpYear,
                paymentDetailValidator::validateCVV
        );
        JLabel warningLabels[] = {LBwarningCardNo, LBwarningExpMth, LBwarningExpYear, LBwarningCVV};
        
        // call input validation
        addValidation(textfields, validation, warningLabels, ButtonPay);
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
        
        debitRadio.addItemListener(this::radioButtonChanged);
        creditRadio.addItemListener(this::radioButtonChanged);

        // Set default selection
        debitRadio.setSelected(true);
        paymentMethod = "Debit Card";
        
        // Set amount = TFafterTax
        TFamount.setText(String.format("%.2f", totalprice));
        TFamount.setEditable(false);
        
        // Disable button
        ButtonPay.setEnabled(false);
        
        // create arrays for validation
        JTextField textfields[] = {TFcardNo, TFexipirationDate1, TFexipirationDate2, TFcvv};
        List<Function<String, String>> validation = Arrays.asList(
                paymentDetailValidator::validateCardNo,
                paymentDetailValidator::validateExpMonth,
                paymentDetailValidator::validateExpYear,
                paymentDetailValidator::validateCVV
        );
        JLabel warningLabels[] = {LBwarningCardNo, LBwarningExpMth, LBwarningExpYear, LBwarningCVV};

        // call input validation
        addValidation(textfields, validation, warningLabels, ButtonPay);
        
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
        TFexipirationDate2 = new javax.swing.JTextField();
        TFcvv = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TFamount = new javax.swing.JTextField();
        ButtonPay = new javax.swing.JButton();
        LBwarningCardNo = new javax.swing.JLabel();
        LBwarningExpMth = new javax.swing.JLabel();
        LBwarningCVV = new javax.swing.JLabel();
        TFexipirationDate1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        LBwarningExpYear = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

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
        creditRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditRadioActionPerformed(evt);
            }
        });

        jLabel5.setText("Card Number:");

        TFcardNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFcardNoActionPerformed(evt);
            }
        });

        jLabel6.setText("Total amount:");

        jLabel3.setText("Expiration Date:");

        jLabel4.setText("CVV: ");

        ButtonPay.setText("Pay Now >");
        ButtonPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonPayActionPerformed(evt);
            }
        });

        LBwarningCardNo.setForeground(new java.awt.Color(255, 0, 51));
        LBwarningCardNo.setPreferredSize(new java.awt.Dimension(282, 16));

        LBwarningExpMth.setForeground(new java.awt.Color(255, 0, 0));
        LBwarningExpMth.setPreferredSize(new java.awt.Dimension(282, 16));

        LBwarningCVV.setForeground(new java.awt.Color(255, 0, 0));
        LBwarningCVV.setPreferredSize(new java.awt.Dimension(178, 16));

        TFexipirationDate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFexipirationDate1ActionPerformed(evt);
            }
        });

        jLabel7.setText("/");

        LBwarningExpYear.setForeground(new java.awt.Color(255, 0, 0));
        LBwarningExpYear.setPreferredSize(new java.awt.Dimension(282, 16));

        jLabel8.setText("Payment Method:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(23, 23, 23)
                                .addComponent(debitRadio))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(117, 117, 117)
                                        .addComponent(TFcardNo, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(23, 23, 23)
                                        .addComponent(TFexipirationDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TFexipirationDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(LBwarningExpMth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(LBwarningExpYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(LBwarningCardNo, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(118, 118, 118)
                                            .addComponent(ButtonPay, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addGap(18, 18, 18)
                                            .addComponent(TFamount, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(LBwarningCVV, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addGap(18, 18, 18)
                                            .addComponent(TFcvv, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(creditRadio)
                                .addGap(149, 149, 149))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(231, 231, 231)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(jLabel1))
                            .addComponent(ButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(65, Short.MAX_VALUE))
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
                    .addComponent(creditRadio)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TFcardNo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LBwarningCardNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TFexipirationDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(TFexipirationDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LBwarningExpMth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(LBwarningExpYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TFcvv, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(LBwarningCVV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TFamount, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addComponent(ButtonPay, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void radioButtonChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            // obtain the selected payment method
            paymentMethod = ((JRadioButton) e.getSource()).getText();
            System.out.println("Selected: " + paymentMethod);
        }
    }
    
    private void ButtonPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonPayActionPerformed
        // TODO add your handling code here:
        try{
            // payment timestamp
            Timestamp pytimestamp; 
            pytimestamp = new java.sql.Timestamp(System.currentTimeMillis());
            
            // put into db
            Registry reg = LocateRegistry.getRegistry("localhost", 1070);
            CheckoutInterface stub1 = (CheckoutInterface) reg.lookup("Checkout");
   
            boolean paymentSucess = false;
            paymentSucess = stub1.makePayment(orderID, totalprice, paymentMethod, pytimestamp);
            if (paymentSucess) {
                JOptionPane.showMessageDialog(null, "Make Payment Successful! \n"
                    + "Thank you for your payment. \n"
                    + "Please come again ^^~", "From McGee:", JOptionPane.INFORMATION_MESSAGE);

                // Close the current page, open the next page
                this.dispose();
                ModeClient.createAndShowGUI(userID);
            }
            else{
                JOptionPane.showMessageDialog(null, "Oh no! Something went wrong. \n"
                                    + "Please proceed to the counter to make payment. \n"
                                    + "Thank you very much ^^~", "From McGee:", JOptionPane.INFORMATION_MESSAGE);
                OrderDetailFrameClient.createAndShowGUI(userID, orderID, modeID);
                this.dispose();
            }
            
            
        } catch (RemoteException | SQLException | NotBoundException ex) {
            Logger.getLogger(MakePaymentClient.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Oh no! Something went wrong. \n"
                                + "Please proceed to the counter to make payment. \n"
                                + "Thank you very much ^^~", "From McGee:", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
    }//GEN-LAST:event_ButtonPayActionPerformed

    private void ButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonBackActionPerformed
        // TODO add your handling code here:
        MenuFrameClient.createAndShowGUI(userID, modeID);
        this.dispose();
    }//GEN-LAST:event_ButtonBackActionPerformed

    private void TFcardNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFcardNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFcardNoActionPerformed

    private void TFexipirationDate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFexipirationDate1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFexipirationDate1ActionPerformed

    private void creditRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditRadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_creditRadioActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonBack;
    private javax.swing.JButton ButtonPay;
    private javax.swing.JLabel LBwarningCVV;
    private javax.swing.JLabel LBwarningCardNo;
    private javax.swing.JLabel LBwarningExpMth;
    private javax.swing.JLabel LBwarningExpYear;
    private javax.swing.JTextField TFamount;
    private javax.swing.JTextField TFcardNo;
    private javax.swing.JTextField TFcvv;
    private javax.swing.JTextField TFexipirationDate1;
    private javax.swing.JTextField TFexipirationDate2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton creditRadio;
    private javax.swing.JRadioButton debitRadio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    // End of variables declaration//GEN-END:variables

}
