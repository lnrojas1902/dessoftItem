
package co.edu.uniandes.csw.producto.logic.api;

import co.edu.uniandes.csw.producto.logic.dto.ClienteAndItemsDTO;
import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import java.util.List;

public interface IProductoLogicService extends _IProductoLogicService {
    public void comprar(ClienteAndItemsDTO clienteAndItems);
    public ProductoDTO buscarProducto(ProductoDTO nombre)throws Exception;

}