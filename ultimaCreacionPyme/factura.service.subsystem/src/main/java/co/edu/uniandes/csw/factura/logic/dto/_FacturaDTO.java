
package co.edu.uniandes.csw.factura.logic.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public abstract class _FacturaDTO {

	private Long id;
	private String name;
	private Integer valor;
	private String estado;
	private String tipoDePago;
	private Date fechaDeRealizacion;
	private Date fechaEsperadaEntrega;
	private String dereccionDeEntrega;
	private Long clienteId;

	public Long getId() {
		return id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
	public Integer getValor() {
		return valor;
	}
 
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	public String getEstado() {
		return estado;
	}
 
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getTipoDePago() {
		return tipoDePago;
	}
 
	public void setTipoDePago(String tipodepago) {
		this.tipoDePago = tipodepago;
	}
	public Date getFechaDeRealizacion() {
		return fechaDeRealizacion;
	}
 
	public void setFechaDeRealizacion(Date fechaderealizacion) {
		this.fechaDeRealizacion = fechaderealizacion;
	}
	public Date getFechaEsperadaEntrega() {
		return fechaEsperadaEntrega;
	}
 
	public void setFechaEsperadaEntrega(Date fechaesperadaentrega) {
		this.fechaEsperadaEntrega = fechaesperadaentrega;
	}
	public String getDereccionDeEntrega() {
		return dereccionDeEntrega;
	}
 
	public void setDereccionDeEntrega(String derecciondeentrega) {
		this.dereccionDeEntrega = derecciondeentrega;
	}
	public Long getClienteId() {
		return clienteId;
	}
 
	public void setClienteId(Long clienteid) {
		this.clienteId = clienteid;
	}
	
}