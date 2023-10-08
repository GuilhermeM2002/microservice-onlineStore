package br.com.onlineStore.authenticationms.adapters.repository;

import br.com.onlineStore.authenticationms.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findUserByEmail(String email);
}
