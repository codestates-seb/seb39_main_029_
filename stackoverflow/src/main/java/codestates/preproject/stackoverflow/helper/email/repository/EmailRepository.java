package codestates.preproject.stackoverflow.helper.email.repository;

import codestates.preproject.stackoverflow.helper.email.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmailRepository extends JpaRepository<Email,Long> {


    @Query(value = "SELECT * FROM EMAIL WHERE CODE = :code", nativeQuery = true)
    Optional<Email> findByCodes(String code);
}
