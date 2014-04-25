
package co.edu.uniandes.csw.factura.logic.api;

import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import java.util.List;

public interface IFacturaLogicService extends _IFacturaLogicService {

    public List<FacturaDTO> getFacturasFecha(FacturaDTO d);
}