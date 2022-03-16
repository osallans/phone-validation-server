package phone.validation.server.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import phone.validation.server.models.Country;
import phone.validation.server.repository.CountryRepository;

@Service
public class CountryServiceImpl implements CountryService {
    
    @Autowired
    private CountryRepository CountryRepository;

    @Override
    public Optional < Country > findById(Long id) {
        return CountryRepository.findById(id);
    }

    @Override
    public void save(Country Country) {
        CountryRepository.save(Country);
    }

    
    @Override
    public Slice<Country> findAll(Pageable paging) {
        return CountryRepository.findAll(paging);

    }

    @Override
    public List < Country > findAll() {
        return (List<Country>) CountryRepository.findAll();
    }

    @Override
    public void delete(long id) {
        CountryRepository.deleteById(id);
    }



}
