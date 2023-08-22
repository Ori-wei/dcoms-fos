/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.util.List;
import java.util.function.Function;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author ASUS
 */
public class paymentDetailValidator {
    
    public static void addValidation(JTextField[] textField, List<Function<String, String>> validation, JLabel[] warningLabels, JButton btn) {
        boolean[] validationResults = new boolean[textField.length];
        System.out.println("Hi i am called");
        
        for (int i = 0; i < textField.length; i++) {
            int finalI = i;          
            textField[i].getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent de) {
                    validateInput(finalI);
                }

                @Override
                public void removeUpdate(DocumentEvent de) {
                    validateInput(finalI);
                }

                @Override
                public void changedUpdate(DocumentEvent de) {
                    validateInput(finalI);
                }

                private void validateInput(int index) {
                    String userinput = textField[index].getText();
                    String warningMessage = validation.get(index).apply(userinput);
                    warningLabels[index].setText(warningMessage);
                    validationResults[index] = warningMessage.isEmpty();
                    validationCheck();
                }

                private void validationCheck() {
                    for (boolean validationResult : validationResults) {
                        if (!validationResult) {
                            btn.setEnabled(false);
                            return;
                        }
                    }
                    
                    btn.setEnabled(true);
                }
            });
        }
    }
    
    
    public static String validateCardNo(String cardno){
        // validate cardNo digits
        System.out.println("cardNo");
        if (cardno.length() != 16 || !cardno.matches("\\d{16}")){
        return "Please enter 16 digits.";
        }else{
        return "";
        }
    }
    
    public static String validateExpDate(String expdate){
        // validate expiry date
        System.out.println("expCvv");
        if(expdate.length() != 2 || !expdate.matches("\\d{2}")){
        return "Please enter 2 digits.";
        }else{
        return "";
        }
    }
    
    public static String validateCVV(String cvv){
        // validate cvv digits
        System.out.println("validateCvv");
        if (cvv.length() != 3 || !cvv.matches("\\d{3}")) {
        return "Please enter 3 digits.";
        }else{
        return "";
        }
    }
}
