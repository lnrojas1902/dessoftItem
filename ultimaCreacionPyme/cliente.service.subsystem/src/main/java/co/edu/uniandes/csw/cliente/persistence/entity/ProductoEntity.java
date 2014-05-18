
package co.edu.uniandes.csw.cliente.persistence.entity;

import javax.persistence.Entity;

@Entity
public class ProductoEntity extends _ProductoEntity {
 	
    
    private String imagen;

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    
    
}