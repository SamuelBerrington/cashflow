package cashflow.demo.repository;

import cashflow.demo.entity.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealEstateRepository extends JpaRepository<RealEstate, String> {

}
