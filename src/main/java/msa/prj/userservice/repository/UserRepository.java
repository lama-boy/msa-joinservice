package msa.prj.userservice.repository;

import msa.prj.userservice.dto.UserDto;
import msa.prj.userservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    @Query("SELECT count(u) FROM UserEntity u WHERE u.user_id = ?1")
    int userIdVerification(String user_id);

    //int save(UserEntity userEntity);

    @Query("SELECT u FROM UserEntity u WHERE u.user_id = :userId")
    UserEntity findByUserId(@Param("userId") String userId);
}