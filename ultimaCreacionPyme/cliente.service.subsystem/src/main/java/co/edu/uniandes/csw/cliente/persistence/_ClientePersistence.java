
package co.edu.uniandes.csw.cliente.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import co.edu.uniandes.csw.cliente.persistence.api._IClientePersistence;
import co.edu.uniandes.csw.cliente.persistence.converter.ClienteConverter;
import co.edu.uniandes.csw.cliente.persistence.entity.ClienteEntity;
import co.edu.uniandes.csw.cliente.persistence.entity.PymeClienteEntity;
import co.edu.uniandes.csw.cliente.singleton.FacturaSingleton;

public abstract class _ClientePersistence implements _IClientePersistence {

        
    
	@PersistenceContext(unitName="ClientePU")
	protected EntityManager entityManager;
	
	public ClienteDTO createCliente(ClienteDTO cliente) {
            
                if (!existeCliente(cliente) ){
                    ClienteEntity entity=ClienteConverter.persistenceDTO2Entity(cliente);
                    entityManager.persist(entity);
                    PymeClienteEntity pymeClient = new PymeClienteEntity(1L, entity.getId());
                    entityManager.persist(pymeClient);
                    return ClienteConverter.entity2PersistenceDTO(entity);
                }
                
                return null;
	}

	@SuppressWarnings("unchecked")
	public List<ClienteDTO> getClientes() {
		Query q = entityManager.createQuery("select u from ClienteEntity u");
		return ClienteConverter.entity2PersistenceDTOList(q.getResultList());
	}

	public ClienteDTO getCliente(Long id) {
		return ClienteConverter.entity2PersistenceDTO(entityManager.find(ClienteEntity.class, id));
	}

	public void deleteCliente(Long id) {
		ClienteEntity entity=entityManager.find(ClienteEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateCliente(ClienteDTO detail) {
		ClienteEntity entity=entityManager.merge(ClienteConverter.persistenceDTO2Entity(detail));
		ClienteConverter.entity2PersistenceDTO(entity);
	}

        public boolean existeCliente(ClienteDTO cliente) {
        
            List<ClienteDTO> lista = getClientes();

                for (int i = 0; i < lista.size(); i++) {

                    if ( cliente.getId()== lista.get(i).getId() ||
                            cliente.getDocId().equalsIgnoreCase(lista.get(i).getDocId())){
                        return true;
                    }
                }
            return false;
              
    }
}