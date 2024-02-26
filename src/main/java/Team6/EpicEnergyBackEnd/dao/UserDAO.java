package Team6.EpicEnergyBackEnd.dao;

import Team6.EpicEnergyBackEnd.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserDAO extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);
}
