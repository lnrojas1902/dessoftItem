
package co.edu.uniandes.csw.cliente.persistence.api;

import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import java.util.List;

public interface IClientePersistence extends _IClientePersistence {

    
    public byte[] getReport();
    
    public List<ClienteDTO> searchCliente(ClienteDTO cliente);

    public boolean existeCliente(ClienteDTO cliente);
    
    public void comprar(Long id, List<ItemDTO> items, String direccion, String metodoPago);
}