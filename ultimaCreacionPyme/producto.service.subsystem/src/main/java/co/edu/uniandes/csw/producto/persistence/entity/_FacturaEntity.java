
package co.edu.uniandes.csw.producto.persistence.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class _FacturaEntity {

	@Id
	@GeneratedValue(generator = "Factura")
	private Long id;
	private String name;
	private Integer valor;
	private String estado;
	private String tipoDePago;
	@Temporal(TemporalType.DATE)
	private Date fechaDeRealizacion;
	@Temporal(TemporalType.DATE)
	private Date fechaEsperadaEntrega;
	private String dereccionDeEntrega;
	private Long clienteId;

	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public Integer getValor(){
		return valor;
	}
	
	public void setValor(Integer valor){
		this.valor = valor;
	}
	public String getEstado(){
		return estado;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
	public String getTipoDePago(){
		return tipoDePago;
	}
	
	public void setTipoDePago(String tipoDePago){
		this.tipoDePago = tipoDePago;
	}
	public Date getFechaDeRealizacion(){
		return fechaDeRealizacion;
	}
	
	public void setFechaDeRealizacion(Date fechaDeRealizacion){
		this.fechaDeRealizacion = fechaDeRealizacion;
	}
	public Date getFechaEsperadaEntrega(){
		return fechaEsperadaEntrega;
	}
	
	public void setFechaEsperadaEntrega(Date fechaEsperadaEntrega){
		this.fechaEsperadaEntrega = fechaEsperadaEntrega;
	}
	public String getDereccionDeEntrega(){
		return dereccionDeEntrega;
	}
	
	public void setDereccionDeEntrega(String dereccionDeEntrega){
		this.dereccionDeEntrega = dereccionDeEntrega;
	}
	public Long getClienteId(){
		return clienteId;
	}
	
	public void setClienteId(Long clienteId){
		this.clienteId = clienteId;
	}
}