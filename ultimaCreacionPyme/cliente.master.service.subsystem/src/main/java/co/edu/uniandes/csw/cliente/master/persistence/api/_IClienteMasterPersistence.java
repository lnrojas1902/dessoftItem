package co.edu.uniandes.csw.cliente.master.persistence.api;

import co.edu.uniandes.csw.cliente.master.persistence.entity.ClienteItemEntity;
import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.cliente.master.logic.dto.ClienteMasterDTO;
import java.util.List;

public interface _IClienteMasterPersistence {

    public ClienteItemEntity createClienteItem(ClienteItemEntity entity);

    public void deleteClienteItem(Long clienteId, Long itemId);
    
    public List<ItemDTO> getItemListForCliente(Long clienteId);
    
    public ClienteMasterDTO getCliente(Long clienteId);

}
