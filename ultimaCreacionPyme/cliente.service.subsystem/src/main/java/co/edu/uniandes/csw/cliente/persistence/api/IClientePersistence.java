
package co.edu.uniandes.csw.cliente.persistence.api;

import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import java.util.List;

public interface IClientePersistence extends _IClientePersistence {

    
    public byte[] getReport();
    
    public List<ClienteDTO> searchCliente(ClienteDTO cliente);

    public void comprar(Long id);
}