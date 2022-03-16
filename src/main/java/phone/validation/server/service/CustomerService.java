package phone.validation.server.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import phone.validation.server.dto.CustomerSearchDto;
import phone.validation.server.models.Customer;



public interface CustomerService {
    
        List<Customer> findAll();

        Map<String, Object> findAll(CustomerSearchDto customerSearchDto);
        
        Map<String, Object> findAllCustomersWithBrokenPagination(CustomerSearchDto customerSearchDto);
    
        void save(Customer employee);
    
        Optional <Customer> findById(Long id);
    
        void delete(long id);

        void processData();


}