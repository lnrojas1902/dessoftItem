
package co.edu.uniandes.csw.factura.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.factura.logic.api.IFacturaLogicService;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Alternative
@Singleton
public class FacturaMockLogicService extends _FacturaMockLogicService implements IFacturaLogicService {
	public List<FacturaDTO> getFacturasFecha(FacturaDTO d)
        {
            ArrayList<FacturaDTO> lista = new ArrayList<FacturaDTO>();
            for (FacturaDTO e: data)
            {
                if(e.getFechaDeRealizacion().compareTo(d.getFechaDeRealizacion())> 0 )
                {
                    lista.add(e);
                }
                
            }
            return lista;
        }

    public void actualizarFacturas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void calificar(FacturaDTO fact) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}