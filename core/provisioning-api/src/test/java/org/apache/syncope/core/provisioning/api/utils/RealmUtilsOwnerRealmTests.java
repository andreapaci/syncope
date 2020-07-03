package org.apache.syncope.core.provisioning.api.utils;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


/*
 * Unit testing per ottenere il realm del owner del gruppo
 * - Andrea Paci
 */

public class RealmUtilsOwnerRealmTests {




    @Test
    public void test(){

        getGroupOwnerRealm(null, null, null + "@" + null);
        getGroupOwnerRealm(null, "groupKey", null + "@groupKey");
        getGroupOwnerRealm("realmPath", "groupKey", "realmPath@groupKey");



    }


    public void getGroupOwnerRealm(String realmPath, String groupKey, String expectedResult) {
        Assert.assertEquals(expectedResult, RealmUtils.getGroupOwnerRealm(realmPath, groupKey));
    }
}