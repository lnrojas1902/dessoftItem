
package co.edu.uniandes.csw.cliente.persistence;

import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.cliente.persistence.api.IClientePersistence;
import co.edu.uniandes.csw.cliente.persistence.converter.ClienteConverter;
import co.edu.uniandes.csw.cliente.persistence.entity.ClienteEntity;
import co.edu.uniandes.csw.cliente.persistence.entity.FacturaEntity;
import co.edu.uniandes.csw.cliente.persistence.entity._FacturaEntity;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.util.ArrayList;
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

    public void comprar(Long id) {
        
        
        
        FacturaEntity nueva = new FacturaEntity();
        nueva.setId(facturaSingleton.getId());
        nueva.setClienteId(id);
        entityManager.persist(nueva);
        
    }

}