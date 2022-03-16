package phone.validation.server.service;


import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import phone.validation.server.models.Country;


    public interface CountryService {
    
        List <Country> findAll(); 

        Slice <Country> findAll(Pageable page); 
    
        void save(Country employee);
    
        Optional <Country> findById(Long id);
    
        void delete(long id);
    }