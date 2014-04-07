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
    
    private Long id;
    
    private  static FacturaSingleton instancia;
    
    public static FacturaSingleton darInstancia(){
        
        if ( instancia == null){
            
            instancia = new FacturaSingleton();
        }
       
            return instancia;
    } 
    
    private FacturaSingleton (){
        
         id = new Long(0);
    } 
    
    public Long getId(){
        
        id++;
        return id;
    }
}
