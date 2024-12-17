package cashflow.demo.repository;

import cashflow.demo.entity.Player;
import java.util.List;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {

  Player findByName(String name);

  List<Player> findAllByName(String name);
}
