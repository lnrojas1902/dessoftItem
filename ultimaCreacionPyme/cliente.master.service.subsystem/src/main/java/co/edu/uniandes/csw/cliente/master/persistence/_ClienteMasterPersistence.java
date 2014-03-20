package co.edu.uniandes.csw.cliente.master.persistence;
import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.cliente.master.persistence.entity.ClienteItemEntity;
import co.edu.uniandes.csw.item.persistence.converter.ItemConverter;
import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import co.edu.uniandes.csw.cliente.master.logic.dto.ClienteMasterDTO;
import co.edu.uniandes.csw.cliente.master.persistence.api._IClienteMasterPersistence;
import co.edu.uniandes.csw.cliente.persistence.api.IClientePersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class _ClienteMasterPersistence implements _IClienteMasterPersistence {

    @PersistenceContext(unitName = "ClienteMasterPU")
    protected EntityManager entityManager;
    
    @Inject
    protected IClientePersistence clientePersistence;  

    public ClienteMasterDTO getCliente(Long clienteId) {
        ClienteMasterDTO clienteMasterDTO = new ClienteMasterDTO();
        ClienteDTO cliente = clientePersistence.getCliente(clienteId);
        clienteMasterDTO.setClienteEntity(cliente);
        clienteMasterDTO.setListItem(getItemListForCliente(clienteId));
        return clienteMasterDTO;
    }

    public ClienteItemEntity createClienteItem(ClienteItemEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteClienteItem(Long clienteId, Long itemId) {
        Query q = entityManager.createNamedQuery("ClienteItemEntity.deleteClienteItem");
        q.setParameter("clienteId", clienteId);
        q.setParameter("itemId", itemId);
        q.executeUpdate();
    }

    public List<ItemDTO> getItemListForCliente(Long clienteId) {
        ArrayList<ItemDTO> resp = new ArrayList<ItemDTO>();
        Query q = entityManager.createNamedQuery("ClienteItemEntity.getItemListForCliente");
        q.setParameter("clienteId", clienteId);
        List<ClienteItemEntity> qResult =  q.getResultList();
        for (ClienteItemEntity clienteItemEntity : qResult) { 
            if(clienteItemEntity.getItemEntity()==null){
                entityManager.refresh(clienteItemEntity);
            }
            resp.add(ItemConverter.entity2PersistenceDTO(clienteItemEntity.getItemEntity()));
        }
        return resp;
    }

}
