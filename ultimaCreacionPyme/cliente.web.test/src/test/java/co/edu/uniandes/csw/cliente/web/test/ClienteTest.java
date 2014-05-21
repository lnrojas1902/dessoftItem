/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.csw.cliente.web.test;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
/**
 *
 * @author admin
 */
public class ClienteTest {
    
        // Es la instancia inicial del web driver que controlará el navegador firefox
        private static WebDriver driver;
        
        // url en el cual se aloja la página web (en este caso localhost:8080)
        private static String baseUrl;
        
        // variable que indica si varios alert consecutivos (alert javascript) se tomarán
        private static boolean acceptNextAlert = true;
        
        private static StringBuffer verificationErrors = new StringBuffer();
        
        @PersistenceContext
	private EntityManager em;
        
        /*La anotación ‘@BeforeClass’ indica lo que se debe ejecutar ANTES de correr el archivo de pruebas. Este método instancia un nuevo driver firefox (causando la apertura de una ventana física de firefox).*/
        @Inject
	UserTransaction utx;
        
        @BeforeClass
        public static void setUp() throws Exception 
        {
            driver = new FirefoxDriver();
            // se define el url base del proyecto web
            baseUrl = "http://localhost:8080";
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
        
        
        // La anotación ‘@AfterClass’ indica lo que se debe ejecutar DESPUÉS de ejecutar
        // el archivo de pruebas. Este método cierra la ventana de firefox 
        // abierta por @BeforeClass que se utilizó para la prueba.
        @AfterClass
        public static void tearDown() throws Exception 
        {
            // Se cierra el navegador.
            driver.quit();
           // Se verifica que se haya cerrado efectivamente el navegador.
            String verificationErrorString = verificationErrors.toString();
            if (!"".equals(verificationErrorString)) {
                fail(verificationErrorString);
            }
        }
        
        @Before
        public void setUpUrl() 
        {
            driver.get(baseUrl + "/cliente.master.service.subsystem/");
            //configTest();
        }
        
	public void configTest() {
		System.out.println("em: " + em);
		try {
			utx.begin();
			clearData();
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				utx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void clearData() {
		em.createQuery("delete from ClienteEntity").executeUpdate();
	}
        
        @Test
        public void testRegistrar() throws Exception{
            String cadena = "pqpq11111111111111";
            driver.findElement(By.xpath("//button[contains(@id,'button3')]")).click();
            Thread.sleep(2000);
            driver.findElement(By.id("name")).clear();
            driver.findElement(By.id("name")).sendKeys(cadena);
            driver.findElements(By.id("docId")).get(1).clear();
            driver.findElements(By.id("docId")).get(1).sendKeys(cadena);
            driver.findElement(By.id("tipo")).clear();
            driver.findElement(By.id("tipo")).sendKeys(cadena);
            driver.findElements(By.id("password")).get(1).clear();
            driver.findElements(By.id("password")).get(1).sendKeys(cadena);
            driver.findElements(By.xpath("//button[contains(@id,'cliente-loginButton')]")).get(1).click();
            Thread.sleep(2000);
//            List<WebElement> table = driver.findElements(By.xpath("//table[contains(@class,'table striped')]/tbody/tr"));
//            boolean sucess = false;
//            for (WebElement webElement : table) 
//            {
//                List<WebElement> elems = webElement.findElements(By.xpath("td"));
//                if (elems.get(0).getText().equals("aaa") && elems.get(1).getText().equals("aaa")
//                        && elems.get(2).getText().equals("aaa") && elems.get(2).getText().equals("aaa")) 
//                {
//                    sucess = true;
//                }
//            }
            boolean sucess = false;
            String texto1 = driver.findElement(By.id("name")).getAttribute("value");
            if(texto1.equals(cadena))
            {
                sucess = true;
            }
            assertTrue(sucess);
            sucess = false;
            String texto2 = driver.findElement(By.id("docId")).getAttribute("value");
            if(texto2.equals(cadena))
            {
                sucess = true;
            }
            assertTrue(sucess);
            sucess = false;
            String texto3 = driver.findElement(By.id("tipo")).getAttribute("value");
            if(texto3.equals(cadena))
            {
                sucess = true;
            }
            assertTrue(sucess);
            sucess = false;
            String texto4 = driver.findElement(By.id("password")).getAttribute("value");
            if(texto4.equals(cadena))
            {
                sucess = true;
            }
            assertTrue(sucess);
        }
        
        @Test
        public void testLogin() throws Exception{
            String cadena = "pqpq22222222222222";
            driver.findElement(By.xpath("//button[contains(@id,'button3')]")).click();
            Thread.sleep(2000);
            driver.findElement(By.id("name")).clear();
            driver.findElement(By.id("name")).sendKeys(cadena);
            driver.findElements(By.id("docId")).get(1).clear();
            driver.findElements(By.id("docId")).get(1).sendKeys(cadena);
            driver.findElement(By.id("tipo")).clear();
            driver.findElement(By.id("tipo")).sendKeys(cadena);
            driver.findElements(By.id("password")).get(1).clear();
            driver.findElements(By.id("password")).get(1).sendKeys(cadena);
            driver.findElements(By.xpath("//button[contains(@id,'cliente-loginButton')]")).get(1).click();
            Thread.sleep(2000);
//            List<WebElement> table = driver.findElements(By.xpath("//table[contains(@class,'table striped')]/tbody/tr"));
//            boolean sucess = false;
//            for (WebElement webElement : table) 
//            {
//                List<WebElement> elems = webElement.findElements(By.xpath("td"));
//                if (elems.get(0).getText().equals("aaa") && elems.get(1).getText().equals("aaa")
//                        && elems.get(2).getText().equals("aaa") && elems.get(2).getText().equals("aaa")) 
//                {
//                    sucess = true;
//                }
//            }
            boolean sucess = false;
            String texto1 = driver.findElement(By.id("name")).getAttribute("value");
            if(texto1.equals(cadena))
            {
                sucess = true;
            }
            assertTrue(sucess);
            sucess = false;
            String texto2 = driver.findElement(By.id("docId")).getAttribute("value");
            if(texto2.equals(cadena))
            {
                sucess = true;
            }
            assertTrue(sucess);
            sucess = false;
            String texto3 = driver.findElement(By.id("tipo")).getAttribute("value");
            if(texto3.equals(cadena))
            {
                sucess = true;
            }
            assertTrue(sucess);
            sucess = false;
            String texto4 = driver.findElement(By.id("password")).getAttribute("value");
            if(texto4.equals(cadena))
            {
                sucess = true;
            }
            assertTrue(sucess);
            driver.findElement(By.xpath("//button[contains(@id,'button7')]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[contains(@id,'button3')]")).click();
            Thread.sleep(2000);
            driver.findElements(By.id("docId")).get(0).clear();
            driver.findElements(By.id("docId")).get(0).sendKeys(cadena);
            driver.findElements(By.id("password")).get(0).clear();
            driver.findElements(By.id("password")).get(0).sendKeys(cadena);
            driver.findElements(By.xpath("//button[contains(@id,'cliente-loginButton')]")).get(0).click();
            Thread.sleep(2000);
            sucess = false;
            texto1 = driver.findElement(By.id("name")).getAttribute("value");
            if(texto1.equals(cadena))
            {
                sucess = true;
            }
            assertTrue(sucess);
            sucess = false;
            texto2 = driver.findElement(By.id("docId")).getAttribute("value");
            if(texto2.equals(cadena))
            {
                sucess = true;
            }
            assertTrue(sucess);
            sucess = false;
            texto3 = driver.findElement(By.id("tipo")).getAttribute("value");
            if(texto3.equals(cadena))
            {
                sucess = true;
            }
            assertTrue(sucess);
            sucess = false;
            texto4 = driver.findElement(By.id("password")).getAttribute("value");
            if(texto4.equals(cadena))
            {
                sucess = true;
            }
            assertTrue(sucess);
        }
        
        @Test
        public void testVerInfoProducto() throws Exception{
            String nombreProducto = "Queso";
            int costoProducto = 1000;
            int pesoProducto = 1000;
            driver.findElements(By.xpath("//button[contains(@id,'ver-producto')]")).get(0).click();
            Thread.sleep(2000);
            WebElement cad1 = driver.findElement(By.xpath("//*[contains(.,'"+nombreProducto+"')]"));
            if(cad1!=null)
            {
                assertTrue(true);
            }
            else
            {
                assertTrue(false);
            }
            WebElement cad2 = driver.findElement(By.xpath("//*[contains(.,'Costo: $"+costoProducto+"')]"));
            if(cad2!=null)
            {
                assertTrue(true);
            }
            else
            {
                assertTrue(false);
            }
            WebElement cad3 = driver.findElement(By.xpath("//*[contains(.,'Peso: "+pesoProducto+"')]"));
            if(cad3!=null)
            {
                assertTrue(true);
            }
            else
            {
                assertTrue(false);
            }
        }
        
        @Test
        public void testAgregarProductoACarrito() throws Exception{
            String nombreProducto = "Queso";
            int costoProducto = 1000;
            int pesoProducto = 1000;
            driver.findElements(By.xpath("//button[contains(@id,'ver-producto')]")).get(0).click();
            Thread.sleep(2000);
            WebElement cad1 = driver.findElement(By.xpath("//*[contains(.,'"+nombreProducto+"')]"));
            if(cad1!=null)
            {
                assertTrue(true);
            }
            else
            {
                assertTrue(false);
            }
            WebElement cad2 = driver.findElement(By.xpath("//*[contains(.,'Costo: $"+costoProducto+"')]"));
            if(cad2!=null)
            {
                assertTrue(true);
            }
            else
            {
                assertTrue(false);
            }
            WebElement cad3 = driver.findElement(By.xpath("//*[contains(.,'Peso: "+pesoProducto+"')]"));
            if(cad3!=null)
            {
                assertTrue(true);
            }
            else
            {
                assertTrue(false);
            }
            driver.findElement(By.xpath("//button[contains(@id,'comprar-producto')]")).click();
            Thread.sleep(2000);
            cad1 = driver.findElement(By.xpath("//*[contains(.,'"+nombreProducto+"')]"));
            if(cad1!=null)
            {
                assertTrue(true);
            }
            else
            {
                assertTrue(false);
            }
            cad2 = driver.findElement(By.xpath("//*[contains(.,'$"+costoProducto+"')]"));
            if(cad2!=null)
            {
                assertTrue(true);
            }
            else
            {
                assertTrue(false);
            }
        }
        
        @Test
        public void testComprar() throws Exception{
            String cadena = "ww";
            driver.findElement(By.xpath("//button[contains(@id,'button3')]")).click();
            Thread.sleep(2000);
            driver.findElement(By.id("name")).clear();
            driver.findElement(By.id("name")).sendKeys(cadena);
            driver.findElements(By.id("docId")).get(1).clear();
            driver.findElements(By.id("docId")).get(1).sendKeys(cadena);
            driver.findElement(By.id("tipo")).clear();
            driver.findElement(By.id("tipo")).sendKeys(cadena);
            driver.findElements(By.id("password")).get(1).clear();
            driver.findElements(By.id("password")).get(1).sendKeys(cadena);
            driver.findElements(By.xpath("//button[contains(@id,'cliente-loginButton')]")).get(1).click();
            Thread.sleep(2000);
//            List<WebElement> table = driver.findElements(By.xpath("//table[contains(@class,'table striped')]/tbody/tr"));
//            boolean sucess = false;
//            for (WebElement webElement : table) 
//            {
//                List<WebElement> elems = webElement.findElements(By.xpath("td"));
//                if (elems.get(0).getText().equals("aaa") && elems.get(1).getText().equals("aaa")
//                        && elems.get(2).getText().equals("aaa") && elems.get(2).getText().equals("aaa")) 
//                {
//                    sucess = true;
//                }
//            }
            boolean sucess = false;
            String texto1 = driver.findElement(By.id("name")).getAttribute("value");
            if(texto1.equals(cadena))
            {
                sucess = true;
            }
            assertTrue(sucess);
            sucess = false;
            String texto2 = driver.findElement(By.id("docId")).getAttribute("value");
            if(texto2.equals(cadena))
            {
                sucess = true;
            }
            assertTrue(sucess);
            sucess = false;
            String texto3 = driver.findElement(By.id("tipo")).getAttribute("value");
            if(texto3.equals(cadena))
            {
                sucess = true;
            }
            assertTrue(sucess);
            sucess = false;
            String texto4 = driver.findElement(By.id("password")).getAttribute("value");
            if(texto4.equals(cadena))
            {
                sucess = true;
            }
            assertTrue(sucess);
            driver.findElement(By.xpath("//button[contains(@id,'button7')]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[contains(@id,'button3')]")).click();
            Thread.sleep(2000);
            driver.findElements(By.id("docId")).get(0).clear();
            driver.findElements(By.id("docId")).get(0).sendKeys(cadena);
            driver.findElements(By.id("password")).get(0).clear();
            driver.findElements(By.id("password")).get(0).sendKeys(cadena);
            driver.findElements(By.xpath("//button[contains(@id,'cliente-loginButton')]")).get(0).click();
            Thread.sleep(2000);
            sucess = false;
            texto1 = driver.findElement(By.id("name")).getAttribute("value");
            if(texto1.equals(cadena))
            {
                sucess = true;
            }
            assertTrue(sucess);
            sucess = false;
            texto2 = driver.findElement(By.id("docId")).getAttribute("value");
            if(texto2.equals(cadena))
            {
                sucess = true;
            }
            assertTrue(sucess);
            sucess = false;
            texto3 = driver.findElement(By.id("tipo")).getAttribute("value");
            if(texto3.equals(cadena))
            {
                sucess = true;
            }
            assertTrue(sucess);
            sucess = false;
            texto4 = driver.findElement(By.id("password")).getAttribute("value");
            if(texto4.equals(cadena))
            {
                sucess = true;
            }
            assertTrue(sucess);
            String nombreProducto = "Queso";
            int costoProducto = 1000;
            int pesoProducto = 1000;
            driver.findElements(By.xpath("//button[contains(@id,'button1')]")).get(0).click();
            Thread.sleep(2000);
            driver.findElements(By.xpath("//button[contains(@id,'ver-producto')]")).get(0).click();
            Thread.sleep(2000);
            WebElement cad1 = driver.findElement(By.xpath("//*[contains(.,'"+nombreProducto+"')]"));
            if(cad1!=null)
            {
                assertTrue(true);
            }
            else
            {
                assertTrue(false);
            }
            WebElement cad2 = driver.findElement(By.xpath("//*[contains(.,'Costo: $"+costoProducto+"')]"));
            if(cad2!=null)
            {
                assertTrue(true);
            }
            else
            {
                assertTrue(false);
            }
            WebElement cad3 = driver.findElement(By.xpath("//*[contains(.,'Peso: "+pesoProducto+"')]"));
            if(cad3!=null)
            {
                assertTrue(true);
            }
            else
            {
                assertTrue(false);
            }
            driver.findElement(By.xpath("//button[contains(@id,'comprar-producto')]")).click();
            Thread.sleep(2000);
            cad1 = driver.findElement(By.xpath("//*[contains(.,'"+nombreProducto+"')]"));
            if(cad1!=null)
            {
                assertTrue(true);
            }
            else
            {
                assertTrue(false);
            }
            cad2 = driver.findElement(By.xpath("//*[contains(.,'$"+costoProducto+"')]"));
            if(cad2!=null)
            {
                assertTrue(true);
            }
            else
            {
                assertTrue(false);
            }
            driver.findElements(By.id("pago")).get(0).clear();
            driver.findElements(By.id("pago")).get(0).sendKeys("Tarjeta débito");
            driver.findElements(By.id("direccion")).get(0).clear();
            driver.findElements(By.id("direccion")).get(0).sendKeys("Cra 23 no 45");
            driver.findElements(By.xpath("//button[contains(@id,'confirmar-compra')]")).get(0).click();
            Thread.sleep(5000);
            driver.findElement(By.xpath("//button[contains(@id,'button5')]")).click();
//            cad1 = driver.findElement(By.xpath("//*[contains(.,'Cra 23 no 45')]"));
//            if(cad1!=null)
//            {
//                assertTrue(true);
//            }
//            else
//            {
//                assertTrue(false);
//            }
//            cad2 = driver.findElement(By.xpath("//*[contains(.,'Tarjeta débito')]"));
//            if(cad2!=null)
//            {
//                assertTrue(true);
//            }
//            else
//            {
//                assertTrue(false);
//            }
        }
}
