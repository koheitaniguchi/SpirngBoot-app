package jp.co.cybermissions.itspj.java.gymapp1.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryUserRepository extends JpaRepository<InquiryUser , Long>{
    List<InquiryUser> findByCommentsIsNull();
}
