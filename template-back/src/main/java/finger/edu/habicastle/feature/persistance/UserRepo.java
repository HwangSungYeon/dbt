package finger.edu.habicastle.feature.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import finger.edu.habicastle.feature.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByUserKey(Integer userKey);
    Optional<User> findByUserId(String userId);
    Optional<User> deleteByUserKey(Integer userKey);
}
