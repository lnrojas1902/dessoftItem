package co.edu.uniandes.csw.cliente.master.logic.ejb;

import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.item.persistence.api.IItemPersistence;
import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import co.edu.uniandes.csw.cliente.master.logic.api._IClienteMasterLogicService;
import co.edu.uniandes.csw.cliente.master.logic.dto.ClienteMasterDTO;
import co.edu.uniandes.csw.cliente.master.persistence.api.IClienteMasterPersistence;
import co.edu.uniandes.csw.cliente.master.persistence.entity.ClienteItemEntity;
import co.edu.uniandes.csw.cliente.persistence.api.IClientePersistence;
import javax.inject.Inject;

public abstract class _ClienteMasterLogicService implements _IClienteMasterLogicService {

    @Inject
    protected IClientePersistence clientePersistance;
    @Inject
    protected IClienteMasterPersistence clienteMasterPersistance;
    @Inject
    protected IItemPersistence itemPersistance;

    public ClienteMasterDTO createMasterCliente(ClienteMasterDTO cliente) {
        ClienteDTO persistedClienteDTO = clientePersistance.createCliente(cliente.getClienteEntity());
        if (cliente.getCreateItem() != null) {
            for (ItemDTO itemDTO : cliente.getCreateItem()) {
                ItemDTO persistedItemDTO = itemPersistance.createItem(itemDTO);
                ClienteItemEntity clienteItemEntity = new ClienteItemEntity(persistedClienteDTO.getId(), persistedItemDTO.getId());
                clienteMasterPersistance.createClienteItem(clienteItemEntity);
            }
        }
        return cliente;
    }

    public ClienteMasterDTO getMasterCliente(Long id) {
        return clienteMasterPersistance.getCliente(id);
    }

    public void deleteMasterCliente(Long id) {
        clientePersistance.deleteCliente(id);
    }

    public void updateMasterCliente(ClienteMasterDTO cliente) {
        clientePersistance.updateCliente(cliente.getClienteEntity());

        //---- FOR RELATIONSHIP
        // persist new item
        if (cliente.getCreateItem() != null) {
            for (ItemDTO itemDTO : cliente.getCreateItem()) {
                ItemDTO persistedItemDTO = itemPersistance.createItem(itemDTO);
                ClienteItemEntity clienteItemEntity = new ClienteItemEntity(cliente.getClienteEntity().getId(), persistedItemDTO.getId());
                clienteMasterPersistance.createClienteItem(clienteItemEntity);
            }
        }
        // update item
        if (cliente.getUpdateItem() != null) {
            for (ItemDTO itemDTO : cliente.getUpdateItem()) {
                itemPersistance.updateItem(itemDTO);
            }
        }
        // delete item
        if (cliente.getDeleteItem() != null) {
            for (ItemDTO itemDTO : cliente.getDeleteItem()) {
                clienteMasterPersistance.deleteClienteItem(cliente.getClienteEntity().getId(), itemDTO.getId());
                itemPersistance.deleteItem(itemDTO.getId());
            }
        }
    }
}
