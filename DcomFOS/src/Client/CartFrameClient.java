/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import FOSInterface.MenuInterface;
import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class CartFrameClient extends javax.swing.JFrame {
    int userID;
    int foodID;
    int quantity;
    int modeID;
    int cartID;
    public static void createAndShowGUI(int userID, int modeID) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {              
                try {
                    CartFrameClient frame;
                    frame = new CartFrameClient(userID, modeID);
                    frame.setVisible(true);
                } catch (NotBoundException ex) {
                    Logger.getLogger(CartFrameClient.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(CartFrameClient.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(CartFrameClient.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(CartFrameClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    } 
    public CartFrameClient(int userID, int modeID) throws NotBoundException, MalformedURLException, RemoteException, SQLException {
        initComponents();
        this.userID=userID;
        this.modeID=modeID;
        btnEdit.setVisible(false);
        btnDelete.setVisible(false);
        btnMinus.setVisible(false);
        btnAdd.setVisible(false);
        btnUpdate.setVisible(false);
        lbQuantity.setVisible(false);
        Registry reg = LocateRegistry.getRegistry("localhost", 1072);
        MenuInterface menuService = (MenuInterface) reg.lookup("MenuInterface");
        cartID=menuService.getCartIdOnly(userID);
        List<CartItem> cartItemList = menuService.getCartItem(userID);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setColumnIdentifiers(new Object[]{"FoodID", "FoodName","Quantity", "Price", "Total"});
        model.setRowCount(0);
        try {
            for (CartItem cartItem : cartItemList) {
                double total =0;
                total=cartItem.getQuantity()*cartItem.getPrice();
                model.addRow(new Object[]{cartItem.getFoodId(), cartItem.getFoodname(), cartItem.getQuantity(), cartItem.getPrice(), total});     
            }
        } catch (Exception e) {
            e.printStackTrace();
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lbCart = new javax.swing.JLabel();
        btnMinus = new javax.swing.JButton();
        lbQuantity = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnCheckout = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(626, 626));

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

        btnBack.setText("< Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("McGee's Food Ordering System (FOS)");

        lbCart.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbCart.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCart.setText("Cart");

        btnMinus.setText("-");
        btnMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinusActionPerformed(evt);
            }
        });

        lbQuantity.setText("Quantity in Cart");

        btnAdd.setText("+");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.setMaximumSize(new java.awt.Dimension(64, 28));
        btnEdit.setMinimumSize(new java.awt.Dimension(64, 28));
        btnEdit.setPreferredSize(new java.awt.Dimension(64, 28));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnCheckout.setText("Checkout");
        btnCheckout.setMaximumSize(new java.awt.Dimension(80, 28));
        btnCheckout.setMinimumSize(new java.awt.Dimension(80, 28));
        btnCheckout.setPreferredSize(new java.awt.Dimension(80, 28));
        btnCheckout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckoutActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.setMaximumSize(new java.awt.Dimension(80, 28));
        btnUpdate.setMinimumSize(new java.awt.Dimension(80, 28));
        btnUpdate.setPreferredSize(new java.awt.Dimension(80, 28));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 17, Short.MAX_VALUE)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(108, 108, 108))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(lbCart))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(btnMinus)
                        .addGap(52, 52, 52)
                        .addComponent(lbQuantity)
                        .addGap(33, 33, 33)
                        .addComponent(btnAdd))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(123, 123, 123)
                        .addComponent(btnCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDelete)
                .addGap(202, 202, 202))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbCart, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnMinus)
                            .addComponent(lbQuantity)
                            .addComponent(btnAdd))
                        .addGap(38, 116, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.rowAtPoint(evt.getPoint());
        //highlight the row at jtable
        jTable1.setRowSelectionInterval(row, row);
        int col = jTable1.columnAtPoint(evt.getPoint());
        if (row >= 0 && col >= 0) {
            Object value = jTable1.getValueAt(row, 0);
            foodID = (int) value;
            System.out.println("Value at clicked cell: " + foodID);
            Object qty = jTable1.getValueAt(row, 2);
            quantity = (int) qty;
            System.out.println("Quantity at clicked cell: " + quantity);
            lbQuantity.setText(String.valueOf(quantity));
        }
        btnEdit.setVisible(true);
        btnDelete.setVisible(true);
        //btnMinus.setVisible(true);
        //btnAdd.setVisible(true);
        //lbQuantity.setVisible(true);
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        MenuFrameClient.createAndShowGUI(userID, modeID);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinusActionPerformed
        int quantity = Integer.parseInt(lbQuantity.getText());
        System.out.println("quantity is: "+quantity);
        if (quantity > 1) {
            quantity -= 1;
            System.out.println("Current quantity: " + quantity);
        }
        else
        {
            //delete cart item
            try 
            {
                String[] options = {"Delete", "Cancel"};
                int choice = JOptionPane.showOptionDialog(null, // Parent component
                                                          "Are you sure you want to delete this item?", // Message
                                                          "Confirm Deletion", // Title
                                                          JOptionPane.YES_NO_OPTION, // Option type
                                                          JOptionPane.WARNING_MESSAGE, // Message type
                                                          null, // Icon
                                                          options, // Custom button text
                                                          options[1]); // Default button

                if (choice == JOptionPane.YES_OPTION) 
                {
                    // User chose "Delete"
                    Registry reg = LocateRegistry.getRegistry("localhost", 1072);
                    MenuInterface menuService = (MenuInterface) reg.lookup("MenuInterface");
                    int response = menuService.deleteCartItem(foodID, userID);
                    if(response==1)
                    {
                        //popup successfully add cart, on dismiss, go back to menu
                        Object[] ok = {"OK"};
                        //int popUpResponse = JOptionPane.showConfirmDialog(jPanel1, "Cart successfully updated.", "Confirmation", JOptionPane.YES_OPTION);
                        int popUpResponse = JOptionPane.showOptionDialog(null,
                            "Cart item successfully removed.",
                            "Response Popup",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            ok,
                            ok[0]);
                        if (popUpResponse == JOptionPane.YES_OPTION) 
                        {
                            CartFrameClient.createAndShowGUI(userID, modeID);
                            this.dispose();
                        }
                    }
                    else if(response==2)
                    {
                        JOptionPane.showMessageDialog(null, "Item not found in Cart. Please retry.");    
                    }
                    else
                    {
                        //show error popup
                        JOptionPane.showMessageDialog(null, "Remove cart item fail. Please retry.");                
                    }
                    System.out.println("Item deleted!");
                } else 
                {
                    // User chose "Cancel" or closed the dialog
                    System.out.println("Deletion canceled!");
                }

            } catch (RemoteException ex) {
                Logger.getLogger(CartFrameClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotBoundException ex) {
                Logger.getLogger(CartFrameClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(CartFrameClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        lbQuantity.setText(String.valueOf(quantity));
    }//GEN-LAST:event_btnMinusActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        int quantity = Integer.parseInt(lbQuantity.getText());
        System.out.println("quantity is: "+quantity);
        quantity+=1;
        System.out.println("Current quantity: " + quantity);
        lbQuantity.setText(String.valueOf(quantity));
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        btnMinus.setVisible(true);
        btnAdd.setVisible(true);
        lbQuantity.setVisible(true);
        btnUpdate.setVisible(true);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here: 
        try {
            String[] options = {"Delete", "Cancel"};
            int choice = JOptionPane.showOptionDialog(null, // Parent component
                                                      "Are you sure you want to delete this item?", // Message
                                                      "Confirm Deletion", // Title
                                                      JOptionPane.YES_NO_OPTION, // Option type
                                                      JOptionPane.WARNING_MESSAGE, // Message type
                                                      null, // Icon
                                                      options, // Custom button text
                                                      options[1]); // Default button

            if (choice == JOptionPane.YES_OPTION) 
            {
                // User chose "Delete"
                Registry reg = LocateRegistry.getRegistry("localhost", 1072);
                MenuInterface menuService = (MenuInterface) reg.lookup("MenuInterface");
                int response = menuService.deleteCartItem(foodID, userID);
                if(response==1)
                {
                    //popup successfully add cart, on dismiss, go back to menu
                    Object[] ok = {"OK"};
                    //int popUpResponse = JOptionPane.showConfirmDialog(jPanel1, "Cart successfully updated.", "Confirmation", JOptionPane.YES_OPTION);
                    int popUpResponse = JOptionPane.showOptionDialog(null,
                        "Cart item successfully removed.",
                        "Response Popup",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        ok,
                        ok[0]);
                    if (popUpResponse == JOptionPane.YES_OPTION) 
                    {
                        CartFrameClient.createAndShowGUI(userID, modeID);
                        this.dispose();
                    }
                }
                else if(response==2)
                {
                    JOptionPane.showMessageDialog(null, "Item not found in Cart. Please retry.");    
                }
                else
                {
                    //show error popup
                    JOptionPane.showMessageDialog(null, "Remove cart item fail. Please retry.");                
                }
                System.out.println("Item deleted!");
            } else 
            {
                // User chose "Cancel" or closed the dialog
                System.out.println("Deletion canceled!");
            }
            
        } catch (RemoteException ex) {
            Logger.getLogger(CartFrameClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(CartFrameClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CartFrameClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnCheckoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckoutActionPerformed
        // TODO add your handling code here:
        PlaceOrderClient.createAndShowGUI(userID, modeID, cartID);
        this.dispose();
    }//GEN-LAST:event_btnCheckoutActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        
        try {
            System.out.println("Button Update");
            quantity = Integer.parseInt(lbQuantity.getText());
            Registry reg;
            reg = LocateRegistry.getRegistry("localhost", 1072);
            MenuInterface menuService = (MenuInterface) reg.lookup("MenuInterface");
            int response = menuService.updateCartItem(foodID, userID, quantity);
            if(response==1)
            {
                //popup successfully add cart, on dismiss, go back to menu
                Object[] options = {"OK"};
                //int popUpResponse = JOptionPane.showConfirmDialog(jPanel1, "Cart successfully updated.", "Confirmation", JOptionPane.YES_OPTION);
                int popUpResponse = JOptionPane.showOptionDialog(null,
                    "Cart successfully updated.",
                    "Response Popup",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);
                if (popUpResponse == JOptionPane.YES_OPTION) 
                {
                    CartFrameClient.createAndShowGUI(userID, modeID);
                    this.dispose();
                }
            }
            else
            {
                //show error popup
                JOptionPane.showMessageDialog(null, "Update cart fail. Please retry.");                
            }
        } catch (RemoteException ex) {
            Logger.getLogger(CartFrameClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(CartFrameClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CartFrameClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnUpdateActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCheckout;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnMinus;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbCart;
    private javax.swing.JLabel lbQuantity;
    // End of variables declaration//GEN-END:variables
}
