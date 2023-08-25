/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

/**
 *
 * @author ASUS
 */

import DatabaseObject.CartItem;
import java.rmi.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;
import FOSInterface.CheckoutInterface;
import java.awt.HeadlessException;

public class PlaceOrderClient extends javax.swing.JFrame {
    
    int userID;
    int modeID;
    int cartID;

    public static void createAndShowGUI(int userID, int modeID, int cartID){
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    PlaceOrderClient frame = new PlaceOrderClient(userID, modeID, cartID);
                    frame.setVisible(true);
                } catch (MalformedURLException | NotBoundException | RemoteException | SQLException ex) {
                    Logger.getLogger(PlaceOrderClient.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PlaceOrderClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    /**
     * Creates new form calculateBills
     */
    public PlaceOrderClient(int userID, int modeID, int cartID) throws MalformedURLException,NotBoundException,RemoteException, SQLException, InterruptedException{
        
        initComponents();
        
        this.userID = userID;
        this.modeID = modeID;
        this.cartID = cartID;
        
        TFmode.setEditable(false);
        TFbeforeTax.setEditable(false);
        TFserviceTax.setEditable(false);
        TFsst.setEditable(false);
        TFafterTax.setEditable(false);
        
        if (modeID == 1) {
            TFmode.setText("Dine-in");
        } else {
            TFmode.setText("Takeaway");
        }
        
        //variable initiation
        double amountBFTax;
        double amountsvTax;
        double amountwithSST;
        double amountafTax;
        
        try {
            Registry reg = LocateRegistry.getRegistry("localhost", 1070);
            CheckoutInterface stub = (CheckoutInterface) reg.lookup("Checkout");


            // Retrieve data and insert table
            // create table
            DefaultTableModel tm = (DefaultTableModel) checkoutTable.getModel();

            //clear existing row
            tm.setRowCount(0); 

            // retrieve data based on cartID
            List<CartItem> itemList = stub.cartItemRetrieval(cartID);

            //insert data from CartItemList into jtable row by row
            for (CartItem ci : itemList) {
                tm.addRow(new Object[]{ci.getFoodname(), ci.getQuantity(), ci.getPrice()});     
            }

            //test calfoodPrice
            for (int record = 0; record < tm.getRowCount(); record++) {
                int Quantity = (int) tm.getValueAt(record, 1);
                double Price = (double) tm.getValueAt(record, 2); 
                double indiFoodPrice = stub.calindifoodPrice(Price, Quantity);
                String formattedfoodPrice = String.format("%.2f", indiFoodPrice);
                tm.setValueAt(formattedfoodPrice, record, 3);
            }

            //test calbeforeTax
            List<Double> pricelist = new ArrayList<>();
             for (int record = 0; record < tm.getRowCount(); record++) {
               int Quantity = (int) tm.getValueAt(record, 1);
               double Price = (double) tm.getValueAt(record, 2);
               double indiFoodPrice = stub.calindifoodPrice(Price, Quantity);
              pricelist.add(indiFoodPrice);
            }
            System.out.println(pricelist);
            amountBFTax = stub.calbeforeTax(pricelist);
            System.out.println("From server calBeforeTax" + amountBFTax);
            String formattedAmountbfTax = String.format("%.2f", amountBFTax);
            TFbeforeTax.setText(String.valueOf(formattedAmountbfTax));

            // Multithreading
            //test calserviceTax
            amountsvTax = stub.calserviceTax(amountBFTax);
            String formattedAmountsvTax = String.format("%.2f", amountsvTax);
            TFserviceTax.setText(formattedAmountsvTax);

            //test calSST
            amountwithSST = stub.calSST(amountBFTax);
            String formattedAmountwithSST = String.format("%.2f", amountwithSST);
            TFsst.setText(formattedAmountwithSST);

            //test calafterTax
            amountafTax = stub.calafterTax(amountBFTax, amountsvTax, amountwithSST);
            String formattedAmountafTax = String.format("%.2f", amountafTax);
            TFafterTax.setText(String.valueOf(formattedAmountafTax));
            
        } catch (InterruptedException | NotBoundException | RemoteException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Oh no! Something went wrong. \n"
                    + "Please proceed to the counter to place your order. \n"
                    + "Thank you very much ^^~", "From McGee:", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
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

        jLabel1 = new javax.swing.JLabel();
        ButtonBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        checkoutTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TFbeforeTax = new javax.swing.JTextField();
        TFserviceTax = new javax.swing.JTextField();
        TFsst = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TFafterTax = new javax.swing.JTextField();
        ButtonOrder = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        TFmode = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("McGee's Food Ordering System (FOS)");

        ButtonBack.setText("< Back");
        ButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonBackActionPerformed(evt);
            }
        });

        checkoutTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Food Name", "Quantity", "Price", "Total"
            }
        ));
        jScrollPane1.setViewportView(checkoutTable);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Place Order");

        jLabel3.setText("Service Tax (10%):");

        jLabel4.setText("SST (6%):");

        jLabel5.setText("Gross Total (RM):");

        jLabel6.setText("Net Total (RM):");

        ButtonOrder.setText("Place Order >");
        ButtonOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonOrderMouseClicked(evt);
            }
        });
        ButtonOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonOrderActionPerformed(evt);
            }
        });

        jLabel7.setText("Dine-in / Takeaway:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(261, 261, 261)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(TFmode, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(99, 99, 99)
                                .addComponent(jLabel1))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(ButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 21, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(104, 104, 104))
            .addGroup(layout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel4)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TFbeforeTax)
                    .addComponent(TFserviceTax)
                    .addComponent(TFsst)
                    .addComponent(TFafterTax, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ButtonOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(ButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TFmode, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TFafterTax, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TFbeforeTax, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TFserviceTax, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TFsst, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)))
                .addGap(29, 29, 29)
                .addComponent(ButtonOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonBackActionPerformed
        // TODO add your handling code here:
        CartFrameClient.createAndShowGUI(userID, modeID);
        this.dispose();
    }//GEN-LAST:event_ButtonBackActionPerformed

    private void ButtonOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonOrderActionPerformed
        // TODO add your handling code here:
        try {
            // fix value
            String price = TFafterTax.getText();
            double totalprice = Double.parseDouble(price);
            String status = "UNPAID";

            // put into db
            Registry reg = LocateRegistry.getRegistry("localhost", 1070);
            CheckoutInterface stub = (CheckoutInterface) reg.lookup("Checkout");

            int orderID = 0;
            orderID = stub.placeOrder(userID, cartID, modeID, totalprice, status);
            
            if (orderID != 0) {
                JOptionPane.showMessageDialog(null, "Place Order Successful! \n"
                        + "Your Order ID is " + orderID + "\n"
                        + "Our chefs will prepare your food soon ^^~", "From McGee:", JOptionPane.INFORMATION_MESSAGE);
                
                // Close the current page, go to next page
                this.dispose();
                MakePaymentClient.createAndShowGUI(userID, modeID, cartID, orderID, totalprice);
            }
            else{
                JOptionPane.showMessageDialog(null, "Oh no! Something went wrong. \n"
                        + "Please proceed to the counter to place your order. \n"
                        + "Thank you very much ^^~", "From McGee:", JOptionPane.INFORMATION_MESSAGE);
                
                CartFrameClient.createAndShowGUI(userID, modeID);
                this.dispose();
            }
        } catch (HeadlessException | NumberFormatException | NotBoundException | RemoteException | SQLException e) {
            Logger.getLogger(PlaceOrderClient.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Oh no! Something went wrong. \n"
                        + "Please proceed to the counter to place your order. \n"
                        + "Thank you very much ^^~", "From McGee:", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
    }//GEN-LAST:event_ButtonOrderActionPerformed

    private void ButtonOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonOrderMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_ButtonOrderMouseClicked

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) throws MalformedURLException,NotBoundException,RemoteException{
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(calculateBills.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(calculateBills.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(calculateBills.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(calculateBills.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new calculateBills().setVisible(true);
//            }
//        });

//    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonBack;
    private javax.swing.JButton ButtonOrder;
    private javax.swing.JTextField TFafterTax;
    private javax.swing.JTextField TFbeforeTax;
    private javax.swing.JTextField TFmode;
    private javax.swing.JTextField TFserviceTax;
    private javax.swing.JTextField TFsst;
    private javax.swing.JTable checkoutTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
