package co.edu.uniandes.csw.cliente.master.persistence.entity;

import co.edu.uniandes.csw.item.persistence.entity.ItemEntity;
import co.edu.uniandes.csw.cliente.persistence.entity.ClienteEntity;
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

/**
 *
 * @author admin
 */
@Entity
@IdClass(ClienteItemEntityId.class)
@NamedQueries({
    @NamedQuery(name = "ClienteItemEntity.getItemListForCliente", query = "SELECT  u FROM ClienteItemEntity u WHERE u.clienteId=:clienteId"),
    @NamedQuery(name = "ClienteItemEntity.deleteClienteItem", query = "DELETE FROM ClienteItemEntity u WHERE u.itemId=:itemId and  u.clienteId=:clienteId")
})
public class ClienteItemEntity implements Serializable {

    @Id
    @Column(name = "clienteId")
    private Long clienteId;
    @Id
    @Column(name = "itemId")
    private Long itemId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "itemId", referencedColumnName = "id")
    @JoinFetch
    private ItemEntity itemEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "clienteId", referencedColumnName = "id")
    @JoinFetch
    private ClienteEntity clienteEntity;

    public ClienteItemEntity() {
    }

    public ClienteItemEntity(Long clienteId, Long itemId) {
        this.clienteId = clienteId;
        this.itemId = itemId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
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

    public ClienteEntity getClienteEntity() {
        return clienteEntity;
    }

    public void setClienteEntity(ClienteEntity clienteEntity) {
        this.clienteEntity = clienteEntity;
    }

}
