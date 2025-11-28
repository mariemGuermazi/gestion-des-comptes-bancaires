package tn.pi.gestiondescomptesbancaires;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tn.pi.gestiondescomptesbancaires.model.Client;
import tn.pi.gestiondescomptesbancaires.repository.ClientRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Rechercher le client par email
        Client client = clientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Client non trouvé avec l'email : " + email));

        // Créer les détails de l'utilisateur avec le code bancaire comme mot de passe
        return User.builder()
                .username(client.getEmail())
                /// Le code bancaire est le mot de passe
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_CLIENT")))
                .build();
    }
}