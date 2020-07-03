package org.apache.syncope.core.provisioning.api.utils;

import org.apache.syncope.core.provisioning.api.utils.RealmUtils;
import org.apache.syncope.core.provisioning.api.utils.entity.RealmsEntity;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;




/*
 * Unit testing per l'effettivo insieme dei realms dei Realms
 * - Andrea Paci
 */

public class RealmUtilsRealmAccessTest {

    private Set<String> allowedRealms;  //Realms permessi per l'accesso
    private String requestedRealms;     //Realms richiesto per l'accesso
    private Set<String> expectedResult; //Realms aspettato come risultato

    public void assignValues (RealmsEntity realmsEntity){

        this.allowedRealms = realmsEntity.getAllowedRealms();
        this.requestedRealms = realmsEntity.getRequestedRealms();
        this.expectedResult = realmsEntity.getExpectedResult();
    }

    private RealmsEntity getInput(int i){

        RealmsEntity realmsEntity = null;

        switch (i) {
            case 0:

                Set<String> realmsRun1 = null;
                String requestRun1 = "AAAA";
                Set<String> expectedResult1 = new HashSet<>();

                realmsEntity = new RealmsEntity(realmsRun1, requestRun1, expectedResult1);

                break;

            //---------------------------------\\

            case 1:

                Set<String> realmsRun2 = new HashSet<>();
                String requestRun2 = null;
                Set<String> expectedResult2 = new HashSet<>();

                realmsRun2.add("AAAA");

                realmsEntity = new RealmsEntity(realmsRun2, requestRun2, expectedResult2);
                break;


            //---------------------------------\\

            case 2:
                Set<String> realmsRun3 = new HashSet<>();
                String requestRun3 = "";
                Set<String> expectedResult3 = new HashSet<>();




                realmsEntity = new RealmsEntity(realmsRun3, requestRun3, expectedResult3);

                break;
            //---------------------------------\\

            case 3:
                Set<String> realmsRun4 = new HashSet<>();
                String requestRun4 = "BBBB";
                Set<String> expectedResult4 = new HashSet<>();

                realmsRun4.add("AAAAA");
                realmsRun4.add("BB");
                realmsRun4.add("A");



                expectedResult4.add("BB");
                expectedResult4.add("BBBB");
                expectedResult4.add("A");
                expectedResult4.add("AAAAA");

                realmsEntity = new RealmsEntity(realmsRun4, requestRun4, expectedResult4);

                break;
        }

        return realmsEntity;
    }


    @Test
    public void getEffective() {

        for(int i = 0; i < 4; i++) {

            assignValues(getInput(i));

            try {
                Set<String> result = RealmUtils.getEffective(allowedRealms, requestedRealms);
                Assert.assertEquals(expectedResult, result);
            } catch (Exception e) {
                e.printStackTrace();
                Assert.assertEquals(expectedResult.size(), 0);
            }
        }
    }
}