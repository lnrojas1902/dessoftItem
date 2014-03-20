package co.edu.uniandes.csw.cliente.master.persistence.converter;
import co.edu.uniandes.csw.cliente.master.persistence.entity.ClienteItemEntity;
import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.item.persistence.converter.ItemConverter;
import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import co.edu.uniandes.csw.cliente.master.logic.dto.ClienteMasterDTO;
import co.edu.uniandes.csw.cliente.persistence.converter.ClienteConverter;
import co.edu.uniandes.csw.cliente.persistence.entity.ClienteEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class _ClienteMasterConverter {

    public static ClienteMasterDTO entity2PersistenceDTO(ClienteEntity clienteEntity 
    ,List<ClienteItemEntity> clienteItemEntity 
    ) {
        ClienteDTO clienteDTO = ClienteConverter.entity2PersistenceDTO(clienteEntity);
        ArrayList<ItemDTO> itemEntities = new ArrayList<ItemDTO>(clienteItemEntity.size());
        for (ClienteItemEntity clienteItem : clienteItemEntity) {
            itemEntities.add(ItemConverter.entity2PersistenceDTO(clienteItem.getItemEntity()));
        }
        ClienteMasterDTO respDTO  = new ClienteMasterDTO();
        respDTO.setClienteEntity(clienteDTO);
        respDTO.setListItem(itemEntities);
        return respDTO;
    }

}