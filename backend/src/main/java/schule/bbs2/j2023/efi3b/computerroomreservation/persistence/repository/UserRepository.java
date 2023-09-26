//package schule.bbs2.j2023.efi3b.computerroomreservation.persistence.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.Repository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//import schule.bbs2.j2023.efi3b.computerroomreservation.persistence.model.User;
//
//import java.util.List;
//import java.util.Optional;
//
//@RepositoryRestResource(exported = false)
//public interface UserRepository extends Repository<User, Long> {
//
//    @Query(value = "SELECT * FROM USERS u " +
//            "WHERE u.first_name = :firstName " +
//            "AND u.middle_name = :middleName " +
//            "AND u.last_name = :lastName",
//            nativeQuery = true)
//    Optional<User> findByUsername(
//            @Param("firstName") String firstName,
//            @Param("middleName") String middleName,
//            @Param("lastName") String lastName
//    );
//
//    @Query(value = "SELECT * FROM USERS u;" +
//            "WHERE u.role = :roleName",
//            nativeQuery = true)
//    List<User> findAllByRole(@Param("roleName") String role);
//
//    @Query(value = "INSERT INTO USERS" +
//            "VALUES(:user.id, " +
//            ":user.firstName, " +
//            ":user.lastName, " +
//            ":user.middleName, " +
//            ":user.password, " +
//            ":user.role, " +
//            ":user.is_locked)",
//            nativeQuery = true)
//    List<User> save(@Param("user") User user);
//
//
//}