package co.edu.uniandes.csw.factura.master.persistence.entity;

import co.edu.uniandes.csw.item.persistence.entity.ItemEntity;
import co.edu.uniandes.csw.factura.persistence.entity.FacturaEntity;
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
@IdClass(FacturaItemEntityId.class)
@NamedQueries({
    @NamedQuery(name = "FacturaItemEntity.getItemListForFactura", query = "SELECT  u FROM FacturaItemEntity u WHERE u.facturaId=:facturaId"),
    @NamedQuery(name = "FacturaItemEntity.deleteFacturaItem", query = "DELETE FROM FacturaItemEntity u WHERE u.itemId=:itemId and  u.facturaId=:facturaId")
})
public class FacturaItemEntity implements Serializable {

    @Id
    @Column(name = "facturaId")
    private Long facturaId;
    @Id
    @Column(name = "itemId")
    private Long itemId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "itemId", referencedColumnName = "id")
    @JoinFetch
    private ItemEntity itemEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "facturaId", referencedColumnName = "id")
    @JoinFetch
    private FacturaEntity facturaEntity;

    public FacturaItemEntity() {
    }

    public FacturaItemEntity(Long facturaId, Long itemId) {
        this.facturaId = facturaId;
        this.itemId = itemId;
    }

    public Long getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Long facturaId) {
        this.facturaId = facturaId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public ItemEntity getItemEntity() {
        return itemEntity;
    }

    public void setItemEntity(ItemEntity itemEntity) {
        this.itemEntity = itemEntity;
    }

    public FacturaEntity getFacturaEntity() {
        return facturaEntity;
    }

    public void setFacturaEntity(FacturaEntity facturaEntity) {
        this.facturaEntity = facturaEntity;
    }

}
