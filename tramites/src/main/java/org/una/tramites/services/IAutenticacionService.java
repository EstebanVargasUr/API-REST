package org.una.tramites.services;

import org.una.tramites.dto.AuthenticationRequest;
import org.una.tramites.dto.AuthenticationResponse;

public interface IAutenticacionService {


public AuthenticationResponse login(AuthenticationRequest authenticationRequest);
    


}

