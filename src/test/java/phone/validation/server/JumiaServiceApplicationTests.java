package phone.validation.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import phone.validation.server.dto.CustomerSearchDto;
import phone.validation.server.models.Country;
import phone.validation.server.models.Customer;
import phone.validation.server.service.CountryService;
import phone.validation.server.service.CustomerService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JumiaServiceApplicationTests {

	@Autowired
    private CustomerService customerService;
    @Autowired
    private CountryService countryService;


	//////////////////////////////////// Function -> GetCountries ////////////////////////////////////
	//Testing countries list exists and not empty
	@Test
	void testCountriesList() 
	{
		//Testing by applying pagination of customers results are of same page size
		List<Country> countries=countryService.findAll();
		assertFalse(countries.isEmpty());
	}
	
	//////////////////////////////////// Function -> ProcessData ////////////////////////////////////
	//Testing processing data works
	@Test
	void testProcessData() 
	{
		customerService.processData();
		//Testing by applying pagination of customers results are of same page size
		CustomerSearchDto customerSearchDto=new CustomerSearchDto();
		customerSearchDto.setCountryId(-1);
		customerSearchDto.setValid(-1);
		customerSearchDto.setPageIndex(0);
		customerSearchDto.setPageSize(50);
		Map<String, Object> customers=customerService.findAll(customerSearchDto);
		List<Customer> customerList=((List<Customer>)customers.get("data"));
		assertThat(customerList).extracting("valid").containsOnly(0,1);
	}

	//////////////////////////////////// Function -> GetCustomers ////////////////////////////////////
	//Testing page size works
	@Test
	void testPageSize() 
	{
		//Testing by applying pagination of customers results are of same page size
		CustomerSearchDto customerSearchDto=new CustomerSearchDto();
		customerSearchDto.setCountryId(-1);
		customerSearchDto.setValid(-1);
		customerSearchDto.setPageIndex(0);
		customerSearchDto.setPageSize(10);
		Map<String, Object> customers=customerService.findAll(customerSearchDto);
		assertEquals(((List<Customer>)customers.get("data")).size(), 10);
	}
	//Testing page index works
	@Test
	void testPageIndex() 
	{
		//Testing by applying paging of customers results are of matching ids against full list
		CustomerSearchDto customerSearchDto=new CustomerSearchDto();
		customerSearchDto.setCountryId(-1);
		customerSearchDto.setValid(-1);
		customerSearchDto.setPageIndex(2);
		customerSearchDto.setPageSize(10);
		Map<String, Object> customers=customerService.findAll(customerSearchDto);
		List<Customer> allCustomers=customerService.findAll();
		assertEquals(((List<Customer>)customers.get("data")).get(0).getId(), allCustomers.get(20).getId());
	}
	//Testing filter of phone validity
	@Test
	void testFilterPhoneValidity() 
	{
		//Testing by applying filter of country results are only of same valid value
		CustomerSearchDto customerSearchDto=new CustomerSearchDto();
		customerSearchDto.setCountryId(-1);
		customerSearchDto.setValid(1);
		customerSearchDto.setPageIndex(0);
		customerSearchDto.setPageSize(50);
		Map<String, Object> customers=customerService.findAll(customerSearchDto);
		List<Customer> customerList=((List<Customer>)customers.get("data"));
		assertThat(customerList).extracting("valid").containsOnly(1);
	}
	//Testing filter of country id
	@Test
	void testFilterCountry() 
	{
		//Testing by applying filter of country results are only of same country_id
		CustomerSearchDto customerSearchDto=new CustomerSearchDto();
		customerSearchDto.setCountryId(3);
		customerSearchDto.setValid(-1);
		customerSearchDto.setPageIndex(0);
		customerSearchDto.setPageSize(50);
		Map<String, Object> customers=customerService.findAll(customerSearchDto);
		List<Customer> customerList=((List<Customer>)customers.get("data"));
		assertThat(customerList).extracting("country.id").containsOnly(3);
	}


}
