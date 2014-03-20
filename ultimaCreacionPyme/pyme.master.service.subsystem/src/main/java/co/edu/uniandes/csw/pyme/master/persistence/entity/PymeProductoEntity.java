package co.edu.uniandes.csw.pyme.master.persistence.entity;

import co.edu.uniandes.csw.producto.persistence.entity.ProductoEntity;
import co.edu.uniandes.csw.pyme.persistence.entity.PymeEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn; 
import org.eclipse.persistence.annotations.JoinFetch;

@Entity
@IdClass(PymeProductoEntityId.class)
@NamedQueries({
    @NamedQuery(name = "PymeProductoEntity.getProductoListForPyme", query = "SELECT  u FROM PymeProductoEntity u WHERE u.pymeId=:pymeId"),
    @NamedQuery(name = "PymeProductoEntity.deletePymeProducto", query = "DELETE FROM PymeProductoEntity u WHERE u.productoId=:productoId and  u.pymeId=:pymeId")
})
public class PymeProductoEntity implements Serializable {

    @Id
    @Column(name = "pymeId")
    private Long pymeId;
    @Id
    @Column(name = "productoId")
    private Long productoId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "productoId", referencedColumnName = "id")
    @JoinFetch
    private ProductoEntity productoEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "pymeId", referencedColumnName = "id")
    @JoinFetch
    private PymeEntity pymeEntity;

    public PymeProductoEntity() {
    }

    public PymeProductoEntity(Long pymeId, Long productoId) {
        this.pymeId = pymeId;
        this.productoId = productoId;
    }

    public Long getPymeId() {
        return pymeId;
    }

    public void setPymeId(Long pymeId) {
        this.pymeId = pymeId;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public ProductoEntity getProductoEntity() {
        return productoEntity;
    }

    public void setProductoEntity(ProductoEntity productoEntity) {
        this.productoEntity = productoEntity;
    }

    public PymeEntity getPymeEntity() {
        return pymeEntity;
    }

    public void setPymeEntity(PymeEntity pymeEntity) {
        this.pymeEntity = pymeEntity;
    }

}
