package co.edu.uniandes.csw.cliente.persistence.entity;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn; 
import org.eclipse.persistence.annotations.JoinFetch;

@Entity
@IdClass(PymeClienteEntityId.class)
@NamedQueries({
    @NamedQuery(name = "PymeClienteEntity.getClienteListForPyme", query = "SELECT  u FROM PymeClienteEntity u WHERE u.pymeId=:pymeId"),
    @NamedQuery(name = "PymeClienteEntity.deletePymeCliente", query = "DELETE FROM PymeClienteEntity u WHERE u.clienteId=:clienteId and  u.pymeId=:pymeId")
})
public class PymeClienteEntity implements Serializable {

    @Id
    @Column(name = "pymeId")
    private Long pymeId;
    @Id
    @Column(name = "clienteId")
    private Long clienteId;
   
    

    public PymeClienteEntity() {
    }

    public PymeClienteEntity(Long pymeId, Long clienteId) {
        this.pymeId = pymeId;
        this.clienteId = clienteId;
    }

    public Long getPymeId() {
        return pymeId;
    }

    public void setPymeId(Long pymeId) {
        this.pymeId = pymeId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    

}
