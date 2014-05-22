
package co.edu.uniandes.csw.producto.logic.api;

import co.edu.uniandes.csw.cliente.logic.dto.ClienteAndItemsDTO;
import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import java.util.List;

public interface IProductoLogicService extends _IProductoLogicService {
    public void comprar(ClienteAndItemsDTO clienteAndItems);

}