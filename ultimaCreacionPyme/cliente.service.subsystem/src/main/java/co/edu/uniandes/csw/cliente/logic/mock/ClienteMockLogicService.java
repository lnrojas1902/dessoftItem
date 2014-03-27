
package co.edu.uniandes.csw.cliente.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.cliente.logic.api.IClienteLogicService;
import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import java.util.List;

@Alternative
@Singleton
public class ClienteMockLogicService extends _ClienteMockLogicService implements IClienteLogicService {

    public byte[] getReport() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<ClienteDTO> searchCliente(ClienteDTO cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void comprar(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
}