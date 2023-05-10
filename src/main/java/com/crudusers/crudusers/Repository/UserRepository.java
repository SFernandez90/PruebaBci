package com.crudusers.crudusers.Repository;

import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.crudusers.crudusers.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    @Modifying
    @Query(value = "UPDATE USERS AS u"
            + " SET u.name=:name, u.email=:email, u.modified=:modified, u.password=:password, u.last_login=:last_login"
            + " WHERE u.id_user=:id_user", nativeQuery = true)
    void upDate(@Param("id_user") Long id, @Param("name") String nombre, @Param("email") String email,
                @Param("password") String password, @Param("modified") LocalDateTime dateMod,
                @Param("last_login") LocalDateTime dateLast);

    @Modifying
    @Query(value = "UPDATE PHONES AS p"
            + " SET p.number=:number, p.city_code=:city_code, p.country_code=:country_code"
            + " WHERE p.id_phone=:id_phone", nativeQuery = true)
    void updatePhone(@Param("id_phone") Long id, @Param("number") String number, @Param("country_code") String countryCode,
                     @Param("city_code") String cityCode);


    /*
    *******ERROR H2 DATABASE  OK MYSQL DATABASE ********
    @Modifying
    @Query(value = "UPDATE USERS AS u INNER JOIN PHONES AS p ON u.id_user=p.id_user_fk "
            + " SET u.name=:name, u.email=:email, u.modified=:modified, u.password=:password, p.number=:number "
            + " WHERE u.id_user=:id_user", nativeQuery = true)
    void upDate(@Param("id_user") Long id, @Param("name") String nombre, @Param("email")  String email,
                @Param("password") String password, @Param("modified") LocalDateTime modified
    ,@Param("number") String number);
    */

}
