
package co.edu.uniandes.csw.cliente.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.cliente.logic.api.IClienteLogicService;
import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import java.util.List;

@Default
@Stateless
@LocalBean
public class ClienteLogicService extends _ClienteLogicService implements IClienteLogicService {

    public byte[] getReport() {
        return persistance.getReport();
    }

    public List<ClienteDTO> searchCliente(ClienteDTO cliente) {
        
        return persistance.searchCliente(cliente);
    }

    public void comprar(Long id) {
        
        persistance.comprar(id);
    }

   

}