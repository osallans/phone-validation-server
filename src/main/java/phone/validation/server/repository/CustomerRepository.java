package phone.validation.server.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import phone.validation.server.models.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

     @Query(value="SELECT c FROM Customer c JOIN c.country d WHERE  (c.valid = :valid or :valid=-1) and (d.id=:country or :country=-1)")
     Page<Customer> findAllCustomersWithBrokenPagination(@Param("valid") int valid,@Param("country") int country,Pageable paging);
}