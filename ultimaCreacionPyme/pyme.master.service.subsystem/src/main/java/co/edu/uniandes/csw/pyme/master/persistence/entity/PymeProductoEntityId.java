package co.edu.uniandes.csw.pyme.master.persistence.entity;

import java.io.Serializable;

/**
 *
 * @author cadmilo
 */
public class PymeProductoEntityId implements Serializable{

    private Long pymeId;
    private Long productoId;

    @Override
    public int hashCode() {
        return (int) (pymeId + productoId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof PymeProductoEntityId) {
            PymeProductoEntityId otherId = (PymeProductoEntityId) object;
            return (otherId.pymeId == this.pymeId) && (otherId.productoId == this.productoId);
        }
        return false;
    }

}
