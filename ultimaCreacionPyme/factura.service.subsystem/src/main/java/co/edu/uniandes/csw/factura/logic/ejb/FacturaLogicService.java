
package co.edu.uniandes.csw.factura.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.factura.logic.api.IFacturaLogicService;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import java.util.List;

@Default
@Stateless
@LocalBean
public class FacturaLogicService extends _FacturaLogicService implements IFacturaLogicService {

     public List<FacturaDTO> getFacturasFecha(FacturaDTO d)
        {
            return persistance.getFacturasFecha(d);
        }
}