package org.apache.syncope.core.provisioning.api.utils;

import org.apache.syncope.core.provisioning.api.utils.entity.RealmsEntity;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.*;



/*
 * Unit testing per la normalizzazione dei Realms
 * - Andrea Paci
 */



public class RealmUtilsNormalizeTestCase {

    //Set string di Realms
    private Set<String> realms;
    private Set<String> expectedResult;



    private RealmsEntity getInput(int i){

        RealmsEntity realmsEntity = null;

        switch (i){

            case 0:

                Set<String> realmsRun1 = null;
                Set<String> expectedResult1 = new HashSet<>();

                realmsEntity = new RealmsEntity(realmsRun1, null, expectedResult1);


                break;

            case 1:

                Set<String> realmsRun2 = new HashSet<>();
                Set<String> expectedResult2 = new HashSet<>();

                realmsEntity = new RealmsEntity(realmsRun2, null, expectedResult2);


                break;

            case 2:


                Set<String> realmsRun3 = new HashSet<>();
                Set<String> expectedResult3 = new HashSet<>();

                realmsRun3.add("AAAAA");
                realmsRun3.add("BBBBB");
                realmsRun3.add("A");

                expectedResult3.add("A");
                expectedResult3.add("BBBBB");

                realmsEntity = new RealmsEntity(realmsRun3, null, expectedResult3);

                break;



        }

        return realmsEntity;

    }


    @Test
    public void normalize() {

        for(int i = 0; i < 3; i++){

            realms = getInput(i).getAllowedRealms();
            expectedResult = getInput(i).getExpectedResult();

            try{

                Assert.assertEquals(expectedResult, RealmUtils.normalize(realms));

            }
            catch(Exception e){
                Assert.assertNull(expectedResult);
                e.printStackTrace();
            }
        }
    }
}