
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

    public boolean existeCliente(ClienteDTO cliente) {
        
        return persistance.existeCliente(cliente);
    }
    
    public ClienteDTO loginCliente(ClienteDTO cliente) throws Exception {
        ClienteDTO clienteVerdader = searchClienteByDocID(cliente);
        if ( clienteVerdader == null)
        {
            throw new Exception("El usuario no existe");
        }
        else if ( clienteVerdader.getPassword().equals(cliente.getPassword()))
        {
            return clienteVerdader;
        }
        else
        {
            throw new Exception("Clave incorrecta. Intente de nuevo");
        }
    }
    
    public ClienteDTO searchClienteByDocID(ClienteDTO cliente) {
        List<ClienteDTO> clientesP = getClientes();
        int i = 0;
        while (i != clientesP.size() && !clientesP.get(i).getDocId().equals(cliente.getDocId()))
        {i++;}
        return (i == clientesP.size())? null: clientesP.get(i);
    }
   

}