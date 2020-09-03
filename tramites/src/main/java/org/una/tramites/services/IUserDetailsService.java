package org.una.tramites.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface IUserDetailsService {
    
       public UserDetails loadUserByUsername(String username);

}

