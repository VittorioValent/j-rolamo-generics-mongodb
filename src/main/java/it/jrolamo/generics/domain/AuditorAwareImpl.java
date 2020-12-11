package it.jrolamo.generics.domain;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Vittorio Valent
 * @since 0.0.1
 */
public class AuditorAwareImpl implements AuditorAware<String> {

    /**
     *
     * @return
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        return Optional.of(((UserDetails) authentication.getPrincipal()).getUsername());
    }
}
