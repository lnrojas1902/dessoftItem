
package co.edu.uniandes.csw.cliente.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class _FacturaItemEntity {

	@Id
	@GeneratedValue(generator = "FacturaItem")
	private Long itemId;
	private Long facturaId;

        public Long getItemId() {
            return itemId;
        }

        public void setItemId(Long itemId) {
            this.itemId = itemId;
        }

        public Long getFacturaID() {
            return facturaId;
        }

        public void setFacturaID(Long facturaID) {
            this.facturaId = facturaID;
        }
	

}