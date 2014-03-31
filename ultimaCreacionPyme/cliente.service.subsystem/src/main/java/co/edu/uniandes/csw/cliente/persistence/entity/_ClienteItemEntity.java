
package co.edu.uniandes.csw.cliente.persistence.entity;

import java.util.Date;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class _ClienteItemEntity {

	@Id
	@GeneratedValue(generator = "ClienteItem")
	private Long itemId;
	private Long clienteId;

        public Long getItemId() {
            return itemId;
        }

        public void setItemId(Long itemId) {
            this.itemId = itemId;
        }

        public Long getClienteId() {
            return clienteId;
        }

        public void setFacturaID(Long clienteId) {
            this.clienteId = clienteId;
        }
	

}