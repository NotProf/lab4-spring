package ua.lpnu.lab4spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.lpnu.lab4spring.entity.User;

import java.time.LocalDate;


@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying(clearAutomatically = true)
    @Query("update User u set u.email = :email, u.firstname = :firstname," +
            " u.lastname = :lastname, u.birthday = :birthday where u.id = :userId")
    void update(@Param("email") String email,
                @Param("firstname") String firstname,
                @Param("lastname") String lastname,
                @Param("birthday") LocalDate birthday,
                @Param("userId") long userId);

    User findByEmail(String email);
    User findById(long id);
}
