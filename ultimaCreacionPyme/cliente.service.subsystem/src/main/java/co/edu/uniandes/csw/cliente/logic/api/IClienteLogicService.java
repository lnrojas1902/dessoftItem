
package co.edu.uniandes.csw.cliente.logic.api;

import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import java.util.List;

public interface IClienteLogicService extends _IClienteLogicService {

    
    public byte[] getReport();

    public List<ClienteDTO> searchCliente(ClienteDTO cliente);

    
}