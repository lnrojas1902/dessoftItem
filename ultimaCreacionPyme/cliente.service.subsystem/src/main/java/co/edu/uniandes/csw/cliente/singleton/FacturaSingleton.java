/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.csw.cliente.singleton;

/**
 *
 * @author admin
 */
public class FacturaSingleton {
    
    private static Long id = new Long(-1);
    
    
    public Long getId(){
        
        id++;
        return id;
    }
}
