package phone.validation.server;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import phone.validation.server.service.CustomerService;

@SpringBootApplication
public class JumiaServiceApplication {

	@Autowired
	private CustomerService customerService;

	public static void main(String[] args) {
		SpringApplication.run(JumiaServiceApplication.class, args);
	}

	@PostConstruct 
	public void init(){ 
		System.out.println("init after the beans has been instansiated");  
		try
		{
		customerService.processData();
		System.out.println("Data is processed ");
		}
		catch(Exception e)
		{
			System.err.println("Data processed failed ");
			throw e;
		}
	} 

}
