package co.edu.uniandes.csw.cliente.persistence.entity;

import java.io.Serializable;

/**
 *
 * @author cadmilo
 */
public class PymeFacturaEntityId implements Serializable{

    private Long pymeId;
    private Long facturaId;

    @Override
    public int hashCode() {
        return (int) (pymeId + facturaId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof PymeFacturaEntityId) {
            PymeFacturaEntityId otherId = (PymeFacturaEntityId) object;
            return (otherId.pymeId == this.pymeId) && (otherId.facturaId == this.facturaId);
        }
        return false;
    }

}
