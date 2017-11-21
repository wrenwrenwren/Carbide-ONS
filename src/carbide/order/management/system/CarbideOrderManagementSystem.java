/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carbide.order.management.system;

import Presentation.Login;
import Presentation.Main;

/**
 *
 * @author weiren
 */
public class CarbideOrderManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // log into local mysql database
        // deal with security problem later
        
        Login loginFrame = new Login();
        loginFrame.setTitle("Carbide ONS Login");
        loginFrame.setVisible(true);
        
        

        
        
        
        
        
    }
    
}
