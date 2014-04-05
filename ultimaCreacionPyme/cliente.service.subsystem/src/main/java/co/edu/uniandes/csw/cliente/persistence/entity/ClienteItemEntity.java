
package co.edu.uniandes.csw.cliente.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@IdClass(ClienteItemEntityId.class)
@NamedQueries({
    @NamedQuery(name = "ClienteItemEntity.getItemListForCliente", query = "SELECT  u FROM ClienteItemEntity u WHERE u.clienteId=:clienteId"),
    @NamedQuery(name = "ClienteItemEntity.deleteClienteItem", query = "DELETE FROM ClienteItemEntity u WHERE u.itemId=:itemId and  u.clienteId=:clienteId")
})
public class ClienteItemEntity extends _ClienteItemEntity {
 	
}