
package co.edu.uniandes.csw.factura.logic.ejb;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.*;


import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.factura.logic.api.IFacturaLogicService;
import co.edu.uniandes.csw.factura.persistence.FacturaPersistence;
import co.edu.uniandes.csw.factura.persistence.api.IFacturaPersistence;
import co.edu.uniandes.csw.factura.persistence.entity.FacturaEntity;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RunWith(Arquillian.class)
public class FacturaLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(FacturaLogicService.class.getPackage())
				.addPackage(FacturaPersistence.class.getPackage())
				.addPackage(FacturaEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IFacturaLogicService facturaLogicService;
	
	@Inject
	private IFacturaPersistence facturaPersistence;	

	@Before
	public void configTest() {
		try {
			clearData();
			insertData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearData() {
		List<FacturaDTO> dtos=facturaPersistence.getFacturas();
		for(FacturaDTO dto:dtos){
			facturaPersistence.deleteFactura(dto.getId());
		}
	}

	private List<FacturaDTO> data=new ArrayList<FacturaDTO>();

	private void insertData() throws ParseException {
		for(int i=0;i<3;i++){
			FacturaDTO pdto=new FacturaDTO();
			pdto.setName("Factura "+i);
			pdto.setValor(i*1000);
			pdto.setEstado("En proceso");
			pdto.setTipoDePago("Crédito");
                        DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
                        Date date1 = formatter.parse("0"+1+i+"/29/02");
                        Date date2 = formatter.parse("0"+2+i+"/29/02");
			pdto.setFechaDeRealizacion(date1);
			pdto.setFechaEsperadaEntrega(date2);
			pdto.setDereccionDeEntrega("Direccion "+i);
			pdto.setClienteId(new Long(i));
			pdto=facturaPersistence.createFactura(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createFacturaTest(){
		FacturaDTO ldto=new FacturaDTO();
		ldto.setName(generateRandom(String.class));
		ldto.setValor(generateRandom(Integer.class));
		ldto.setEstado(generateRandom(String.class));
		ldto.setTipoDePago(generateRandom(String.class));
		ldto.setFechaDeRealizacion(generateRandom(Date.class));
		ldto.setFechaEsperadaEntrega(generateRandom(Date.class));
		ldto.setDereccionDeEntrega(generateRandom(String.class));
		ldto.setClienteId(generateRandom(Long.class));
		
		
		FacturaDTO result=facturaLogicService.createFactura(ldto);
		
		Assert.assertNotNull(result);
		
		FacturaDTO pdto=facturaPersistence.getFactura(result.getId());
		
		Assert.assertEquals(ldto.getName(), pdto.getName());	
		Assert.assertEquals(ldto.getValor(), pdto.getValor());	
		Assert.assertEquals(ldto.getEstado(), pdto.getEstado());	
		Assert.assertEquals(ldto.getTipoDePago(), pdto.getTipoDePago());	
		Assert.assertEquals(ldto.getFechaDeRealizacion(), pdto.getFechaDeRealizacion());	
		Assert.assertEquals(ldto.getFechaEsperadaEntrega(), pdto.getFechaEsperadaEntrega());	
		Assert.assertEquals(ldto.getDereccionDeEntrega(), pdto.getDereccionDeEntrega());	
		Assert.assertEquals(ldto.getClienteId(), pdto.getClienteId());	
	}
	
	@Test
	public void getFacturasTest(){
		List<FacturaDTO> list=facturaLogicService.getFacturas();
		Assert.assertEquals(list.size(), data.size());
        for(FacturaDTO ldto:list){
        	boolean found=false;
            for(FacturaDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getFacturaTest(){
		FacturaDTO pdto=data.get(0);
		FacturaDTO ldto=facturaLogicService.getFactura(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getName(), ldto.getName());
		Assert.assertEquals(pdto.getValor(), ldto.getValor());
		Assert.assertEquals(pdto.getEstado(), ldto.getEstado());
		Assert.assertEquals(pdto.getTipoDePago(), ldto.getTipoDePago());
		Assert.assertEquals(pdto.getFechaDeRealizacion(), ldto.getFechaDeRealizacion());
		Assert.assertEquals(pdto.getFechaEsperadaEntrega(), ldto.getFechaEsperadaEntrega());
		Assert.assertEquals(pdto.getDereccionDeEntrega(), ldto.getDereccionDeEntrega());
		Assert.assertEquals(pdto.getClienteId(), ldto.getClienteId());
        
	}
        
	@Test
        public void getFacturasFechaTest() throws ParseException{
            for(int i=0;i<3;i++)
            {
                        boolean found = false;
			FacturaDTO fecha1 = new FacturaDTO();
                        fecha1.setName("");
                        fecha1.setValor(0);
                        fecha1.setEstado("");
                        fecha1.setTipoDePago("");
                        DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
                        Date date1 = formatter.parse("0"+1+i+"/29/02");
                        Date date2 = formatter.parse("0"+1+i+"/29/02");
                        fecha1.setFechaDeRealizacion(date1);
                        fecha1.setFechaEsperadaEntrega(date2);
                        fecha1.setDereccionDeEntrega("");
                        fecha1.setClienteId(1L);
                        List<FacturaDTO> list=facturaLogicService.getFacturasFecha(fecha1);
                        FacturaDTO comp = list.get(0);
                        if(("Factura "+i).equals(comp.getName()))
                        {
                            found = true;
                        } 
                        Assert.assertTrue(found);
            }
	}
        
	@Test
	public void deleteFacturaTest(){
		FacturaDTO pdto=data.get(0);
		facturaLogicService.deleteFactura(pdto.getId());
        FacturaDTO deleted=facturaPersistence.getFactura(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateFacturaTest(){
		FacturaDTO pdto=data.get(0);
		
		FacturaDTO ldto=new FacturaDTO();
		ldto.setId(pdto.getId());
		ldto.setName(generateRandom(String.class));
		ldto.setValor(generateRandom(Integer.class));
		ldto.setEstado(generateRandom(String.class));
		ldto.setTipoDePago(generateRandom(String.class));
		ldto.setFechaDeRealizacion(generateRandom(Date.class));
		ldto.setFechaEsperadaEntrega(generateRandom(Date.class));
		ldto.setDereccionDeEntrega(generateRandom(String.class));
		ldto.setClienteId(generateRandom(Long.class));
		
		
		facturaLogicService.updateFactura(ldto);
		
		
		FacturaDTO resp=facturaPersistence.getFactura(pdto.getId());
		
		Assert.assertEquals(ldto.getName(), resp.getName());	
		Assert.assertEquals(ldto.getValor(), resp.getValor());	
		Assert.assertEquals(ldto.getEstado(), resp.getEstado());	
		Assert.assertEquals(ldto.getTipoDePago(), resp.getTipoDePago());	
		Assert.assertEquals(ldto.getFechaDeRealizacion(), resp.getFechaDeRealizacion());	
		Assert.assertEquals(ldto.getFechaEsperadaEntrega(), resp.getFechaEsperadaEntrega());	
		Assert.assertEquals(ldto.getDereccionDeEntrega(), resp.getDereccionDeEntrega());	
		Assert.assertEquals(ldto.getClienteId(), resp.getClienteId());	
	}
	
	public <T> T generateRandom(Class<T> objectClass){
		Random r=new Random();
		if(objectClass.isInstance(String.class)){
			String s="";
			for(int i=0;i<10;i++){
				char c=(char)(r.nextInt()/('Z'-'A')+'A');
				s=s+c;
			}
			return objectClass.cast(s);
		}else if(objectClass.isInstance(Integer.class)){
			Integer s=r.nextInt();
			return objectClass.cast(s);
		}else if(objectClass.isInstance(Long.class)){
			Long s=r.nextLong();
			return objectClass.cast(s);
		}else if(objectClass.isInstance(java.util.Date.class)){
			java.util.Calendar c=java.util.Calendar.getInstance();
			c.set(java.util.Calendar.MONTH, r.nextInt()/12);
			c.set(java.util.Calendar.DAY_OF_MONTH,r.nextInt()/30);
			c.setLenient(false);
			return objectClass.cast(c.getTime());
		} 
		return null;
	}
	
}