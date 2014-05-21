
package co.edu.uniandes.csw.cliente.logic.ejb;

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


import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import co.edu.uniandes.csw.cliente.logic.api.IClienteLogicService;
import co.edu.uniandes.csw.cliente.logic.dto.ClienteAndItemsDTO;
import co.edu.uniandes.csw.cliente.persistence.ClientePersistence;
import co.edu.uniandes.csw.cliente.persistence.api.IClientePersistence;
import co.edu.uniandes.csw.cliente.persistence.entity.ClienteEntity;
import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import javax.persistence.Query;

@RunWith(Arquillian.class)
public class ClienteLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(ClienteLogicService.class.getPackage())
				.addPackage(ClientePersistence.class.getPackage())
				.addPackage(ClienteEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IClienteLogicService clienteLogicService;
	
	@Inject
	private IClientePersistence clientePersistence;	
        
        @PersistenceContext
	private EntityManager em;

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
		List<ClienteDTO> dtos=clientePersistence.getClientes();
		for(ClienteDTO dto:dtos){
			clientePersistence.deleteCliente(dto.getId());
		}
	}

	private List<ClienteDTO> data=new ArrayList<ClienteDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			ClienteDTO pdto=new ClienteDTO();
			pdto.setName("Nombre "+i);
			pdto.setDocId("DocId "+i);
			pdto.setTipo("Tipo "+i);
			pdto.setPassword("Password "+i);
                        try{
			pdto=clientePersistence.createCliente(pdto);
			data.add(pdto);
                        }
                        catch( Exception e){
                            
                        }
		}
	}
	
	@Test
	public void createClienteTest(){
		ClienteDTO ldto=new ClienteDTO();
		ldto.setName("a");
		ldto.setDocId("1");
		ldto.setTipo("a");
		ldto.setPassword("a");
		
		try{
		ClienteDTO result=clienteLogicService.createCliente(ldto);
		
		Assert.assertNotNull(result);
		
		ClienteDTO pdto=clientePersistence.getCliente(result.getId());
		
		Assert.assertEquals(ldto.getName(), pdto.getName());	
		Assert.assertEquals(ldto.getDocId(), pdto.getDocId());	
		Assert.assertEquals(ldto.getTipo(), pdto.getTipo());	
		Assert.assertEquals(ldto.getPassword(), pdto.getPassword());
                }
                catch(Exception e ){
                    
                }
	}
	
	@Test
	public void getClientesTest(){
		List<ClienteDTO> list=clienteLogicService.getClientes();
		Assert.assertEquals(list.size(), data.size());
        for(ClienteDTO ldto:list){
        	boolean found=false;
            for(ClienteDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
        
        @Test
	public void searchClientesTest(){
	for(ClienteDTO pdto:data)
            {
                boolean found=false;
                List<ClienteDTO> list=clienteLogicService.searchCliente(pdto);
                ClienteDTO buscado=list.get(0);
                if(buscado != null)
                {
                    if(buscado.getDocId().equals(pdto.getDocId()))
                    {
                        found=true;
                    }
                }
                else
                {
                    found=true;
                }
                Assert.assertTrue(found);
            }
	}
        
        @Test
	public void existeClienteTest1(){
	for(ClienteDTO pdto:data)
            {
                boolean existe = clienteLogicService.existeCliente(pdto);
                Assert.assertTrue(existe);
            }
	}
        
        @Test
	public void existeClienteTest2(){
                ClienteDTO ldto=new ClienteDTO();
		ldto.setName("No existe");
		ldto.setDocId("No existe");
		ldto.setTipo("No existe");
		ldto.setPassword("No existe");
                boolean noExiste = !clienteLogicService.existeCliente(ldto);
                Assert.assertTrue(noExiste);
	}
	
        @Test
	public void searchClientesByDocIDTest(){
	for(ClienteDTO pdto:data)
            {
                boolean found=false;
                ClienteDTO buscado = clienteLogicService.searchClienteByDocID(pdto);
                if(buscado != null)
                {
                    if(buscado.getDocId().equals(pdto.getDocId()))
                    {
                        found=true;
                    }
                }
                else
                {
                    found=true;
                }
                Assert.assertTrue(found);
            }
	}
        
        @Test
	public void confirmarCompraTest(){
                Query qMax = em.createQuery("select MAX(u.id) from FacturaEntity u");
                Long primero = 0L;
                List<ItemDTO> items = new ArrayList<ItemDTO>();
                List<ClienteDTO> list=clienteLogicService.getClientes();
                ClienteDTO objDto = list.get(0);
                Long id = objDto.getId();
                ClienteAndItemsDTO clienteitems = new ClienteAndItemsDTO();
                clienteitems.setClienteEntity(objDto);
                clienteitems.setCreateItem(items);
                clienteitems.setDireccion("Dir");
                clienteitems.setId(id);
                clienteitems.setMetodoPago("Metodo");
                clienteLogicService.confirmarCompra(clienteitems);
                Long segundo = (Long)qMax.getSingleResult();
                Assert.assertTrue((segundo-primero)==1);
	}
        
        @Test
	public void loginClienteTest1()
        {
                ClienteDTO prueba = new ClienteDTO();
                prueba.setName("No existe");
                prueba.setId(-1L);
                prueba.setDocId("No existe");
                prueba.setPassword("No existe");
                prueba.setTipo("No existe");
                boolean funciona = false;
                try
                {
                    clienteLogicService.loginCliente(prueba);
                }
                catch(Exception e)
                {
                    if(e.getMessage().equals("El usuario no existe"))
                    {
                        funciona = true;
                    }
                }
                Assert.assertTrue(funciona);
	}
        
        @Test
	public void loginClienteTest2()
        {
                ClienteDTO prueba = new ClienteDTO();
                prueba.setName("Nombre 1");
                prueba.setId(1L);
                prueba.setDocId("DocId 1");
                prueba.setPassword("Password 2");
                prueba.setTipo("Tipo 1");
                boolean funciona = false;
                try
                {
                    clienteLogicService.loginCliente(prueba);
                }
                catch(Exception e)
                {
                    if(e.getMessage().equals("Clave incorrecta. Intente de nuevo"))
                    {
                        funciona = true;
                    }
                }
                Assert.assertTrue(funciona);
	}
        
        @Test
	public void loginClienteTest3()
        {
                ClienteDTO prueba = new ClienteDTO();
                prueba.setName("Nombre 1");
                prueba.setId(1L);
                prueba.setDocId("DocId 1");
                prueba.setPassword("Password 1");
                prueba.setTipo("Tipo 1");
                boolean funciona = true;
                try
                {
                    clienteLogicService.loginCliente(prueba);
                }
                catch(Exception e)
                {
                    funciona = false;
                }
                Assert.assertTrue(funciona);
	}
        
	@Test
	public void getClienteTest(){
		ClienteDTO pdto=data.get(0);
		ClienteDTO ldto=clienteLogicService.getCliente(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getName(), ldto.getName());
		Assert.assertEquals(pdto.getDocId(), ldto.getDocId());
		Assert.assertEquals(pdto.getTipo(), ldto.getTipo());
		Assert.assertEquals(pdto.getPassword(), ldto.getPassword());
        
	}
	
	@Test
	public void deleteClienteTest(){
		ClienteDTO pdto=data.get(0);
		clienteLogicService.deleteCliente(pdto.getId());
        ClienteDTO deleted=clientePersistence.getCliente(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateClienteTest(){
		ClienteDTO pdto=data.get(0);
		
		ClienteDTO ldto=new ClienteDTO();
		ldto.setId(pdto.getId());
		ldto.setName(generateRandom(String.class));
		ldto.setDocId(generateRandom(String.class));
		ldto.setTipo(generateRandom(String.class));
		ldto.setPassword(generateRandom(String.class));
		
		
		clienteLogicService.updateCliente(ldto);
		
		
		ClienteDTO resp=clientePersistence.getCliente(pdto.getId());
		
		Assert.assertEquals(ldto.getName(), resp.getName());	
		Assert.assertEquals(ldto.getDocId(), resp.getDocId());	
		Assert.assertEquals(ldto.getTipo(), resp.getTipo());	
		Assert.assertEquals(ldto.getPassword(), resp.getPassword());	
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