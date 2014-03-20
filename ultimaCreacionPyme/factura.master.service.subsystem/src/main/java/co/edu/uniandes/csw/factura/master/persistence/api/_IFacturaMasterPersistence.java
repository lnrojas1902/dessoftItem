package co.edu.uniandes.csw.factura.master.persistence.api;

import co.edu.uniandes.csw.factura.master.persistence.entity.FacturaItemEntity;
import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.factura.master.logic.dto.FacturaMasterDTO;
import java.util.List;

public interface _IFacturaMasterPersistence {

    public FacturaItemEntity createFacturaItem(FacturaItemEntity entity);

    public void deleteFacturaItem(Long facturaId, Long itemId);
    
    public List<ItemDTO> getItemListForFactura(Long facturaId);
    
    public FacturaMasterDTO getFactura(Long facturaId);

}
