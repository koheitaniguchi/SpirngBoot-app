package jp.co.cybermissions.itspj.java.gymapp1.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewUserRepository extends JpaRepository<NewUser, Long>{
    NewUser findByUsername(String username);
    
}
