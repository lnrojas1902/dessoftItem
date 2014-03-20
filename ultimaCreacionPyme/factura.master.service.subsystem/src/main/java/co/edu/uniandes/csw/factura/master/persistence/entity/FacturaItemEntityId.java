package co.edu.uniandes.csw.factura.master.persistence.entity;

import java.io.Serializable;

/**
 *
 * @author cadmilo
 */
public class FacturaItemEntityId implements Serializable{

    private Long facturaId;
    private Long itemId;

    @Override
    public int hashCode() {
        return (int) (facturaId + itemId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof FacturaItemEntityId) {
            FacturaItemEntityId otherId = (FacturaItemEntityId) object;
            return (otherId.facturaId == this.facturaId) && (otherId.itemId == this.itemId);
        }
        return false;
    }

}
