package schule.bbs2.j2023.efi3b.computerroomreservation.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schule.bbs2.j2023.efi3b.computerroomreservation.persistence.repository.RealUserRepository;
import schule.bbs2.j2023.efi3b.computerroomreservation.util.UserHelper;

import java.util.Map;

/**
 * A database-based implementation of the UserDetailsService interface. This how spring receives a UserDetails instance
 * when e.g. authenticating.
 */
@Service
@Transactional
public class JpaUserDetailsService implements UserDetailsService {

    private final RealUserRepository userRepo;

    public JpaUserDetailsService(RealUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * @param username the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Map<String, String> names = UserHelper.getSplitNamesFromUsername(username);
        return userRepo
                .findByUsername(names.get("firstName"), names.get("lastName"))
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
    }
}
