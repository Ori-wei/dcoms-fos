/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registry;

import FOSInterface.RegisterInterface;
import Server.RegisterServer;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
/**
 *
 * @author User
 */
public class RegisterRegistry {
    public static void main(String[] args) throws RemoteException{
         try {
            Registry reg = LocateRegistry.createRegistry(1072);
            RegisterInterface registerService = new RegisterServer();
            reg.rebind("RegisterInterface", registerService);
            System.out.println("Server is ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
