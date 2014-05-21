
package co.edu.uniandes.csw.cliente.logic.api;

import co.edu.uniandes.csw.cliente.logic.dto.ClienteAndItemsDTO;
import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import java.util.List;
import javax.ws.rs.PathParam;

public interface IClienteLogicService extends _IClienteLogicService {

    
    public byte[] getReport();

    public List<ClienteDTO> searchCliente(ClienteDTO cliente);

    public void comprar(Long id);
    
    public boolean existeCliente(ClienteDTO cliente);

    public ClienteDTO loginCliente(ClienteDTO cliente)throws Exception;

    public void confirmarCompra(ClienteAndItemsDTO clienteAndItems);

    public ClienteDTO searchClienteByDocID(ClienteDTO pdto);
}