/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author Cristian
 */
public interface LoginPhaseInterface {
    String username = new String();
    String password = new String();
    
    Boolean checkLogin(String username, String password);
}
