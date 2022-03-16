package phone.validation.server.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phone.validation.server.dto.CustomerSearchDto;
import phone.validation.server.models.Country;
import phone.validation.server.models.Customer;
import phone.validation.server.repository.CountryRepository;
import phone.validation.server.repository.CustomerRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;


@Service
public class CustomerServiceImpl implements CustomerService {
    @PersistenceContext
        public EntityManager em; 
    @Autowired
    private CustomerRepository CustomerRepository;
    @Autowired
    private CountryRepository CountryRepository;

    @Override
    public Optional < Customer > findById(Long id) {
        return CustomerRepository.findById(id);
    }

    @Override
    public void save(Customer Customer) {
        CustomerRepository.save(Customer);
    }

    @Override
    public List<Customer> findAll() {
        return CustomerRepository.findAll();
    }
    @Override
    public Map<String, Object> findAll(CustomerSearchDto customerSearchDto) {
        
        /////////////////////////////
        Query query = em.createNativeQuery("SELECT c.id ,c.name, c.phone , c.valid, c.country_id  FROM Customer c , Country d WHERE (d.id=c.country_id) and (c.valid = :valid or :valid=-1) and (d.id=:country or :country=-1) limit :limit offset:offset",Customer.class)
        .setParameter("valid", customerSearchDto.getValid())
        .setParameter("country", customerSearchDto.getCountryId())
        .setParameter("limit", customerSearchDto.getPageSize())
        .setParameter("offset", customerSearchDto.getPageIndex() * customerSearchDto.getPageSize());
          List<Customer> customers=query.getResultList();
         //////////////////////////////
        Object count= em.createNativeQuery("SELECT count(c.id) FROM Customer c , Country d WHERE (d.id=c.country_id) and (c.valid = :valid or :valid=-1) and (d.id=:country or :country=-1)")
        .setParameter("valid", customerSearchDto.getValid()).setParameter("country", customerSearchDto.getCountryId()).getSingleResult();
        //////////////////////////////
        Map<String, Object> response = new HashMap<>();
        response.put("data", customers);
        response.put("count", count);
       return response;

    }
    
  
    @Override
    public Map<String, Object> findAllCustomersWithBrokenPagination(CustomerSearchDto customerSearchDto) {
        //Set pagination parameters
        Pageable paging = PageRequest.of(customerSearchDto.getPageIndex(),customerSearchDto.getPageSize());
        Page<Customer> pagedData=CustomerRepository.findAllCustomersWithBrokenPagination(customerSearchDto.getValid(), customerSearchDto.getCountryId(),paging);
        Map<String, Object> response = new HashMap<>();
        List<Customer> customers = pagedData.getContent();
        response.put("data", customers);
        response.put("currentPage", pagedData.getNumber());
        response.put("count", pagedData.getTotalElements());
        response.put("totalPages", pagedData.getTotalPages());
        return response;
    }



    @Override
    public void delete(long id) {
        CustomerRepository.deleteById(id);
    }

    @Override
    public void processData() {
        //Retrieve data simply because dataset is small , however in terms of scalabiility , another appproach using scheduled jobs and batches of data would be taken in that case
        List<Customer> customers=(List<Customer>)CustomerRepository.findAll();
        List<Country> countries=(List<Country>) CountryRepository.findAll();
        //In this case , the processing complexity is O(n*m), n=length of countries list , m=length of customers list
        // which is acceptable for small lists , however this is not valid in terms of scalibility
        for (Customer customer : customers) {
            for (Country country : countries) {
                
                if(customer.getPhone().startsWith(country.getCode()))
                {
                    customer.setCountry(country);
                    int valid=Pattern.matches(country.getRegex(), customer.getPhone())?1:0;
                    customer.setValid(valid);
                    CustomerRepository.save(customer);
                }
             
            }
        }
    }





}
