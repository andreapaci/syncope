package org.apache.syncope.core.spring.security;

import org.apache.syncope.common.lib.policy.DefaultPasswordRuleConf;
import org.apache.syncope.core.persistence.api.entity.policy.PasswordPolicy;
import org.apache.syncope.core.provisioning.api.serialization.POJOHelper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.List;


/*
 * Unit testing per la generazione della password usando policies varie
 * - Andrea Paci
 */



@SpringJUnitConfig(locations = { "classpath:springTest.xml" })
public class DefaultPwdGenWPoliciesTest {

    private DefaultPasswordGenerator pwdGenerator = new DefaultPasswordGenerator();


    //Metodo che crea una configurazione per le password generale
    private static DefaultPasswordRuleConf generalPasswordConf(boolean factor, int max, int min){

        //Configuarazione della configurazione del password generator

        DefaultPasswordRuleConf ruleConf = new DefaultPasswordRuleConf();

        ruleConf.setAlphanumericRequired(factor);
        ruleConf.setDigitRequired(factor);
        ruleConf.setLowercaseRequired(factor);
        ruleConf.setMaxLength(max);
        ruleConf.setMinLength(min);

        //Impostazione di parametri sull'ultimo e primo carattere
        ruleConf.setMustEndWithAlpha(factor);
        ruleConf.setMustEndWithDigit(factor);
        ruleConf.setMustEndWithNonAlpha(factor);

        ruleConf.setMustStartWithAlpha(factor);
        ruleConf.setMustStartWithDigit(factor);
        ruleConf.setMustStartWithNonAlpha(factor);

        ruleConf.setMustntEndWithAlpha(factor);
        ruleConf.setMustntEndWithDigit(factor);
        ruleConf.setMustntEndWithNonAlpha(factor);

        ruleConf.setMustntStartWithAlpha(factor);
        ruleConf.setMustntStartWithDigit(factor);
        ruleConf.setMustntStartWithNonAlpha(factor);

        ruleConf.setNonAlphanumericRequired(factor);
        ruleConf.setUppercaseRequired(factor);

        return ruleConf;

    }

    //Genera un oggetto policy
    private static TestPasswordPolicy generatePolicy(DefaultPasswordRuleConf ruleConf){
        TestImplementation passwordRule = new TestImplementation();
        passwordRule.setBody(POJOHelper.serialize(ruleConf));
        TestPasswordPolicy policy = new TestPasswordPolicy();
        policy.add(passwordRule);

        return policy;
    }


    @Test
    public void generate() {

        String password = null;
        List<PasswordPolicy> policies;

        //Proviamo inizialmente a generare la password con test minimali [null, new List<PasswordPolicies>(VUOTA), new List<PasswordPolicies>(VAALORI VALIDI)]

        //Test valido

        policies = new ArrayList<>();
        policies.add(generatePolicy(generalPasswordConf(false, 24, 8)));


        try { password = pwdGenerator.generate(policies); } catch (Exception e) { e.printStackTrace(); }
        Assert.assertNotNull(password);

        System.out.println(password);


        //Test con lista vuota

        policies = new ArrayList<>();


        try { password = pwdGenerator.generate(policies); } catch (Exception e) { e.printStackTrace(); }
        Assert.assertNotNull(password);

        System.out.println(password);


        //test con policies nulle


        password = null;
        policies = null;


        try { password = pwdGenerator.generate(policies); } catch (Exception e) { e.printStackTrace(); }
        Assert.assertNull(password);

        System.out.println(password);


        //TEST EFFETTUATI PER AUMENTARE LA COVERAGE//

        //Test con fattori tutti settati a true (vengono forzate coppie di regole che non possono coesistere

        password = null;
        policies = null;

        policies = new ArrayList<>();
        policies.add(generatePolicy(generalPasswordConf(true, 24, 8)));

        try { password = pwdGenerator.generate(policies); } catch (Exception e) { e.printStackTrace(); }
        Assert.assertNull(password);

        System.out.println(password);


        //Provare una delle proprietà

        password = null;
        policies = null;

        DefaultPasswordRuleConf conf = generalPasswordConf(false, 24, 8);
        conf.setMustStartWithDigit(true);
        policies = new ArrayList<>();
        policies.add(generatePolicy(conf));

        try { password = pwdGenerator.generate(policies); } catch (Exception e) { e.printStackTrace(); }
        Assert.assertNotNull(password);
        Assert.assertTrue(Character.isDigit(password.charAt(0)));



        //Proviamo a mettere due policies che sono discordanti tra loro

        password = null;
        policies = null;

        policies = new ArrayList<>();

        DefaultPasswordRuleConf conf1 = generalPasswordConf(false, 24, 8);
        conf1.setMustntStartWithDigit(true);

        DefaultPasswordRuleConf conf2 = generalPasswordConf(false, 24, 8);
        conf2.setMustStartWithDigit(true);

        policies.add(generatePolicy(conf1));
        policies.add(generatePolicy(conf2));


        try { password = pwdGenerator.generate(policies); } catch (Exception e) { e.printStackTrace(); }
        Assert.assertNull(password);

        System.out.println(password);


        //Proviamo a mettere una policy con dimensioni sbagliate

        password = null;
        policies = null;

        policies = new ArrayList<>();

        policies.add(generatePolicy(generalPasswordConf(false, 10, 11)));


        try { password = pwdGenerator.generate(policies); } catch (Exception e) { e.printStackTrace(); }
        Assert.assertNull(password);

        System.out.println(password);



        //Proviamo a mettere una policy con dimensioni sbagliate

        password = null;
        policies = null;

        policies = new ArrayList<>();

        policies.add(generatePolicy(generalPasswordConf(false, 10, -1)));


        try { password = pwdGenerator.generate(policies); } catch (Exception e) { e.printStackTrace(); }
        Assert.assertNotNull(password);

        System.out.println(password);



        //Proviamo a mettere una policy con dimensioni sbagliate

        password = null;
        policies = null;

        policies = new ArrayList<>();

        policies.add(generatePolicy(generalPasswordConf(false, 2, 2)));


        try { password = pwdGenerator.generate(policies); } catch (Exception e) { e.printStackTrace(); }
        Assert.assertNull(password);

        System.out.println(password);




        //Proviamo a mettere due policy con dimensioni diverse

        password = null;
        policies = null;

        policies = new ArrayList<>();

        policies.add(generatePolicy(generalPasswordConf(false, 12, 4)));
        policies.add(generatePolicy(generalPasswordConf(false, 8, 2)));


        try { password = pwdGenerator.generate(policies); } catch (Exception e) { e.printStackTrace(); }
        Assert.assertNotNull(password);
        Assert.assertTrue(!(password.length() > 8) && !(password.length() < 4) );

        System.out.println(password);





    }
}