
package co.edu.uniandes.csw.producto.logic.mock;
import co.edu.uniandes.csw.producto.logic.dto.ClienteAndItemsDTO;
import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.producto.logic.api.IProductoLogicService;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import java.util.List;

@Alternative
@Singleton
public class ProductoMockLogicService extends _ProductoMockLogicService implements IProductoLogicService {

    public void comprar(Long id, List<ItemDTO> items, String direccion, String metodoPago) {
        //
    }

    public void comprar(ClienteAndItemsDTO clienteAndItems) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ProductoDTO buscarProducto(ProductoDTO nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
	
}