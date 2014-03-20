package co.edu.uniandes.csw.cliente.master.persistence.entity;

import java.io.Serializable;

/**
 *
 * @author cadmilo
 */
public class ClienteItemEntityId implements Serializable{

    private Long clienteId;
    private Long itemId;

    @Override
    public int hashCode() {
        return (int) (clienteId + itemId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof ClienteItemEntityId) {
            ClienteItemEntityId otherId = (ClienteItemEntityId) object;
            return (otherId.clienteId == this.clienteId) && (otherId.itemId == this.itemId);
        }
        return false;
    }

}
