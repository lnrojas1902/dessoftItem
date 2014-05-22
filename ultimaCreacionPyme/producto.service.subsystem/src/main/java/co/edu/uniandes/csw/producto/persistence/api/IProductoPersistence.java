
package co.edu.uniandes.csw.producto.persistence.api;

import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import java.util.List;

public interface IProductoPersistence extends _IProductoPersistence {

    public void comprar(Long id, List<ItemDTO> items, String direccion, String metodoPago);

}