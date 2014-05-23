
package co.edu.uniandes.csw.factura.persistence.api;

import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import java.util.Date;
import java.util.List;

public interface IFacturaPersistence extends _IFacturaPersistence {
 public List<FacturaDTO> getFacturasFecha(FacturaDTO d);
public void calificar(FacturaDTO fac);
    public void actualizarFacturas();
}