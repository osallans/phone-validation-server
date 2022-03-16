package phone.validation.server.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import phone.validation.server.models.Country;


@Repository
public interface CountryRepository extends JpaRepository<Country, Long>{}