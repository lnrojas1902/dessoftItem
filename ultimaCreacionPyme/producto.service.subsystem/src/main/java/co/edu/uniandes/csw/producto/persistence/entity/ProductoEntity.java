
package co.edu.uniandes.csw.producto.persistence.entity;

import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class ProductoEntity extends _ProductoEntity implements Serializable {
 
    
    private String imagen;

        
        public String getImagen() {
            return imagen;
        }

        
        public void setImagen(String imagen) {
            this.imagen = imagen;
        }
        
}