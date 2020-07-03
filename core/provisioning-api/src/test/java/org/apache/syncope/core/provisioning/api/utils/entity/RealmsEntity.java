package org.apache.syncope.core.provisioning.api.utils.entity;

import java.util.Set;

/*
 * Entità per fare unit testing sui Realms
 * - Andrea Paci
 */


public class RealmsEntity {


    private Set<String> allowedRealms;  //Realms permessi per l'accesso
    private String requestedRealms;     //Realms richiesto per l'accesso
    private Set<String> expectedResult; //Realms aspettato come result

    public RealmsEntity(Set<String> allowedRealms, String requestedRealms, Set<String> expectedResult){

        this.allowedRealms = allowedRealms;
        this.requestedRealms = requestedRealms;
        this.expectedResult = expectedResult;
    }

    public Set<String> getAllowedRealms() {
        return allowedRealms;
    }

    public String getRequestedRealms() {
        return requestedRealms;
    }

    public Set<String> getExpectedResult() {
        return expectedResult;
    }
}
