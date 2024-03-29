/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import DatabaseObject.Menu;
import DatabaseObject.CartItem;
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

/**
 *
 * @author user
 */
public class FoodDetailFrameClient extends javax.swing.JFrame {

    /**
     * Creates new form FoodDetailFrameClient
     */
    int foodID;
    int userID;
    int modeID;
    public static void createAndShowGUI(int foodID, int userID, int modeID){
            java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FoodDetailFrameClient frame;
                    frame = new FoodDetailFrameClient(foodID, userID, modeID);
                    frame.setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(FoodDetailFrameClient.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NotBoundException ex) {
                    Logger.getLogger(FoodDetailFrameClient.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(FoodDetailFrameClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    public FoodDetailFrameClient(int foodID, int userID, int modeID) throws RemoteException, NotBoundException, SQLException {
        initComponents();
        this.setLocationRelativeTo(null);
        this.userID=userID;
        this.foodID=foodID;
        this.modeID=modeID;
        System.out.println("FoodID received from menu: " + foodID);
        System.out.println("userid is: " + userID);
        System.out.println("modeid is: " + modeID);
        lbFoodId.setVisible(false);
        tfFoodName.setEditable(false);
        tfPrice.setEditable(false);
        tfDescription.setEditable(false);
        //retrieve food table from server
        Registry reg = LocateRegistry.getRegistry("localhost", 1070);
        MenuInterface menuService = (MenuInterface) reg.lookup("MenuInterface");
        // Thread for task 1
            new Thread(() -> {
                try {
                    List<Menu> foodDetailList = menuService.getFoodDetail(foodID);
                    if (!foodDetailList.isEmpty()) { // Check if the list is not empty
                        Menu menu = foodDetailList.get(0); // Get the first element

                        tfFoodName.setText(menu.getFoodName());
                        tfPrice.setText(String.format("%.2f", menu.getPrice()));
                        tfDescription.setText(menu.getDescription());
                        lbCategory.setText(menu.getCategory() + " Detail");
                        lbFoodId.setText(String.valueOf(foodID));           
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (SQLException ex) {
                Logger.getLogger(FoodDetailFrameClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            }).start();

            // Thread for task 2
            new Thread(() -> {
                try {
                    List<CartItem> cartItemQtyList = menuService.getCartItem(foodID, userID);
                    if (!cartItemQtyList.isEmpty()) { // Check if the list is not empty
                        CartItem cartItem = cartItemQtyList.get(0); // Get the first element
                        int qty = cartItem.getQuantity();
                        System.out.println("Quantity in cart: " + qty);
                        if(qty==0)
                        {
                            lbQuantity.setText("1");   
                            lbQuantity.revalidate();
                            lbQuantity.repaint();
                        }
                        else
                        {
                            lbQuantity.setText(String.valueOf(qty));  
                            lbQuantity.revalidate();
                            lbQuantity.repaint();
                        }
                    }
                    else //empty record. no cart item found
                    {
                        lbQuantity.setText("1");
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (SQLException ex) {
                Logger.getLogger(FoodDetailFrameClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            }).start();
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
        LbName = new javax.swing.JLabel();
        lbDescription = new javax.swing.JLabel();
        lbQuantity = new javax.swing.JLabel();
        lbFoodId = new javax.swing.JLabel();
        btnMinus = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        tfFoodName = new javax.swing.JTextField();
        tfDescription = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        lbCategory = new javax.swing.JLabel();
        tfPrice = new javax.swing.JTextField();
        lbPrice = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LbName.setText("Food Name: ");

        lbDescription.setText("Description: ");

        lbQuantity.setText("Quantity in Cart");

        lbFoodId.setText("FoodId (Hidden)");

        btnMinus.setText("-");
        btnMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinusActionPerformed(evt);
            }
        });

        btnAdd.setText("+");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("McGee's Food Ordering System (FOS)");

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        lbCategory.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbCategory.setText("Food/Drink Detail");

        lbPrice.setText("Price: ");

        btnBack.setText("< Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(99, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDescription)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbPrice))
                        .addComponent(tfDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addGap(101, 101, 101))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(238, 238, 238)
                        .addComponent(lbCategory))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(73, 73, 73)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(LbName)
                                            .addComponent(tfFoodName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lbFoodId)
                                        .addGap(151, 151, 151)
                                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(175, 175, 175)
                                .addComponent(btnMinus)
                                .addGap(52, 52, 52)
                                .addComponent(lbQuantity)))
                        .addGap(33, 33, 33)
                        .addComponent(btnAdd)))
                .addContainerGap(186, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(484, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(LbName)
                        .addGap(7, 7, 7)
                        .addComponent(tfFoodName, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbPrice)
                        .addGap(7, 7, 7)
                        .addComponent(tfPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(lbDescription)
                        .addGap(18, 18, 18)
                        .addComponent(tfDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnMinus)
                            .addComponent(lbQuantity)
                            .addComponent(btnAdd))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(lbFoodId))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(36, 36, 36)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(542, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinusActionPerformed
        int quantity = Integer.parseInt(lbQuantity.getText());
        System.out.println("quantity is: "+quantity);
        if (quantity > 1) {
            quantity -= 1;
            System.out.println("Current quantity: " + quantity);
        }
        lbQuantity.setText(String.valueOf(quantity));
    }//GEN-LAST:event_btnMinusActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            // TODO add your handling code here:
            System.out.println("Button Update");
            int quantity = Integer.parseInt(lbQuantity.getText());
            Registry reg = LocateRegistry.getRegistry("localhost", 1070);
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
                    MenuFrameClient.createAndShowGUI(userID, modeID);
                    this.dispose();
                }
            }
            else
            {
                //show error popup
                JOptionPane.showMessageDialog(null, "Update cart fail. Please retry.");                
            }
        } catch (RemoteException ex) {
            Logger.getLogger(FoodDetailFrameClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FoodDetailFrameClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(FoodDetailFrameClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        int quantity = Integer.parseInt(lbQuantity.getText());
        System.out.println("quantity is: "+quantity);
        quantity+=1;
        System.out.println("Current quantity: " + quantity);
        lbQuantity.setText(String.valueOf(quantity));
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        MenuFrameClient.createAndShowGUI(userID, modeID);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LbName;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnMinus;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbCategory;
    private javax.swing.JLabel lbDescription;
    private javax.swing.JLabel lbFoodId;
    private javax.swing.JLabel lbPrice;
    private javax.swing.JLabel lbQuantity;
    private javax.swing.JTextField tfDescription;
    private javax.swing.JTextField tfFoodName;
    private javax.swing.JTextField tfPrice;
    // End of variables declaration//GEN-END:variables
}
