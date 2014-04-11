
package co.edu.uniandes.csw.cliente.persistence;

import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.cliente.persistence.api.IClientePersistence;
import co.edu.uniandes.csw.cliente.persistence.converter.ClienteConverter;
import co.edu.uniandes.csw.cliente.persistence.entity.ClienteEntity;
import co.edu.uniandes.csw.cliente.persistence.entity.ClienteItemEntity;
import co.edu.uniandes.csw.cliente.persistence.entity.ClienteItemEntity;
import co.edu.uniandes.csw.cliente.persistence.entity.FacturaEntity;
import co.edu.uniandes.csw.cliente.persistence.entity.FacturaItemEntity;
import co.edu.uniandes.csw.cliente.persistence.entity.PymeFacturaEntity;
import co.edu.uniandes.csw.cliente.persistence.entity._FacturaItemEntity;
import co.edu.uniandes.csw.cliente.persistence.entity._FacturaEntity;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.persistence.Query;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;



@Default
@Stateless 
@LocalBean
public class ClientePersistence extends _ClientePersistence  implements IClientePersistence {

    /**
     * Metodo que retorna un archivo pdf con la información de los clientes
     * @return 
     */
    public byte[] getReport() {
        try {
            Map parameters = new HashMap();
            JasperReport report = JasperCompileManager.compileReport(
                    "C:\\informes jasper\\JRXML\\Cliente.jrxml");
            
            Connection conn = entityManager.unwrap(java.sql.Connection.class);
            JasperPrint print = JasperFillManager.fillReport(report, parameters, conn);
            // Exporta el informe a PDF
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(print, baos);
            return baos.toByteArray();
        } catch (JRException ex) {
            Logger.getLogger(ClientePersistence.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Busca y retorna un cliente en el sistema que tenga el mismo nombre dado por parámetro
     * @param cliente
     * @return 
     */
    public List<ClienteDTO> searchCliente(ClienteDTO cliente) {
        
        List <ClienteDTO> lista = getClientes();
        ArrayList <ClienteDTO> resp = new ArrayList<ClienteDTO>();
        
        for (int i = 0; i < lista.size(); i++) {
            
            ClienteDTO actual = lista.get(i);
            if ( actual.getName().equalsIgnoreCase(cliente.getName()))
                resp.add(actual);
        }
        
        return resp;
    }

    /**
     * Metodo que recibe un id de un cliente, y busca todos sus items del carrito, luego crea una
     * factura con esta información y con el valor total y la agrega al sistema, por último borra todos
     * los items del carrito.
     * @param id 
     */
    public void comprar(Long id) {
               
        FacturaEntity nueva = new FacturaEntity();
        
        Query qMax = entityManager.createQuery("select MAX(u.id) from FacturaEntity u");
        
        
        Long facturaID = 1L + (Long) ((qMax.getSingleResult()== null)? 0L:qMax.getSingleResult());
        nueva.setId(facturaID);
        nueva.setFechaDeRealizacion(new Date());
        nueva.setFechaEsperadaEntrega(new Date());
        nueva.setClienteId(id);
        nueva.setEstado("En proceso");
        nueva.setTipoDePago("Efectivo");
        nueva.setName(getCliente(id).getName()+" - "+(new Date()));
        entityManager.persist(nueva);
         
        int costoTotal = 0;
                
        Query q = entityManager.createQuery("select u from ClienteItemEntity u");
        
        List <ClienteItemEntity> items = q.getResultList();
        
        for (int i = 0; i < items.size(); i++) {
            
            ClienteItemEntity actual = items.get(i);
            
            if ( actual.getClienteId() == id){
                
                Long prodID = actual.getItemEntity().getProductoId();
                Query qCostoItem = entityManager.createQuery("select u.costo from ProductoEntity u where"
                        + " u.id="+prodID);
                
                int costoItem = (Integer) qCostoItem.getSingleResult();
                
                costoTotal += costoItem*actual.getItemEntity().getCantidad();
                FacturaItemEntity facItem = new FacturaItemEntity();

                facItem.setFacturaID(facturaID);
                facItem.setItemId(actual.getItemId());
                
                entityManager.persist(facItem);
                
                
                
                Query q2 = entityManager.createNamedQuery("ClienteItemEntity.deleteClienteItem");
                q2.setParameter("clienteId", id);
                q2.setParameter("itemId", actual.getItemId());
                q2.executeUpdate();
            }
        }
        nueva.setValor(costoTotal);
       
        PymeFacturaEntity pymeFact = new PymeFacturaEntity(new Long(1), facturaID);
        entityManager.persist(pymeFact);
        
        
    }

    

}