package cashflow.demo.repository;

import cashflow.demo.entity.Business;
import cashflow.demo.entity.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepository extends JpaRepository<Business, String> {

}
