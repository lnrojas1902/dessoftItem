
package co.edu.uniandes.csw.producto.persistence;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.producto.persistence.api.IProductoPersistence;
import co.edu.uniandes.csw.producto.persistence.entity.FacturaEntity;
import co.edu.uniandes.csw.producto.persistence.entity.ItemEntity;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.persistence.Query;
import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.producto.persistence.entity.FacturaItemEntity;
import co.edu.uniandes.csw.producto.persistence.entity.PymeFacturaEntity;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import java.util.ArrayList;

@Default
@Stateless 
@LocalBean
public class ProductoPersistence extends _ProductoPersistence  implements IProductoPersistence {
    public void comprar(Long id, List<ItemDTO> items, String direccion, String metodoPago) {
               
        FacturaEntity nueva = new FacturaEntity();
        Query qMax = entityManager.createQuery("select MAX(u.id) from FacturaEntity u");
        
        Long facturaID = 1L + (Long) ((qMax.getSingleResult()== null)? 0L:qMax.getSingleResult());
        System.out.println("Paso1");
        nueva.setId(facturaID);
        nueva.setFechaDeRealizacion(new Date());
        nueva.setFechaEsperadaEntrega(new Date());
        nueva.setClienteId(id);
        nueva.setEstado("En proceso");
        nueva.setTipoDePago(metodoPago);
        nueva.setDereccionDeEntrega(direccion);
        Query qNombreCliente = entityManager.createQuery("select u.name from ClienteEntity u where"
                        + " u.id="+id);
                
        String nombreC = (String) qNombreCliente.getSingleResult();
        nueva.setName(nombreC+" - "+(new Date()));
        entityManager.persist(nueva);
        System.out.println("Paso2");
         
        int costoTotal = 0;    
        //Query q = entityManager.createQuery("select u from ClienteItemEntity u");
        //List <ClienteItemEntity> items = q.getResultList();  
        Query qMax2 = entityManager.createQuery("select MAX(u.id) from ItemEntity u");
        Long sigItemID = 1L + (Long) ((qMax2.getSingleResult()== null)? 0L:qMax2.getSingleResult());
        System.out.println("Paso3");
        for (int i = 0; i < items.size(); i++) {
            //ClienteItemEntity actual = items.get(i);
            //if ( actual.getClienteId() == id){
            ItemDTO itemActual = items.get(i);
            ItemEntity itemPersistence = new ItemEntity();
                    itemPersistence.setCantidad(itemActual.getCantidad());
                    itemPersistence.setEstado(itemActual.getEstado());
                    itemPersistence.setName("Fact: " + facturaID + "-" + (i+1));
                    itemPersistence.setProductoId(itemActual.getProductoId());
                    itemPersistence.setId(sigItemID);
            entityManager.persist(itemPersistence);
            
                Long prodID = itemActual.getProductoId();
//                Query qCostoItem = entityManager.createQuery("select u.costo from ProductoEntity u where"
//                        + " u.id="+prodID);
                
                
                
                ProductoDTO producto = getProducto(prodID);
                int costoItem = producto.getCosto();
                
                costoTotal += costoItem*itemActual.getCantidad();
                FacturaItemEntity facItem = new FacturaItemEntity();
                facItem.setFacturaId(facturaID);
                facItem.setItemId(sigItemID);
                entityManager.persist(facItem);
                sigItemID += 1L;
//                Query q2 = entityManager.createNamedQuery("ClienteItemEntity.deleteClienteItem");
//                q2.setParameter("clienteId", id);
//                q2.setParameter("itemId", SigItemID + i);
//                q2.executeUpdate();
            //}
        }
        nueva.setValor(costoTotal);
       System.out.println("Paso4");
        PymeFacturaEntity pymeFact = new PymeFacturaEntity(new Long(1), facturaID);
        entityManager.persist(pymeFact);
        
        
    }

}