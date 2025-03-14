package com.example.collectify;

import static com.example.collectify.AddNewAccount.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CollectifyTest {


    @Ignore
    public void loginValidator() {
        //when
        assertEquals("log1" + " powinno być " + false, false, isValid("log1"));
        assertEquals("GFgfgoplporokmn1" + " powinno być " + false, false, isValid("GFgfgoplporokmn1"));
        assertEquals("GFgfgoplporo1rwr%ffaf" + " powinno być " + false, false, isValid("GFgfgoplporo1rwr%ffaf"));
        assertEquals("login1<" + " powinno być " + false, false, isValid("login1<"));
        assertEquals("login1|" + " powinno być " + false, false, isValid("login1|"));
        assertEquals("!ogin1|" + " powinno być " + false, false, isValid("!ogin1|"));

        assertEquals("login" + " powinno być " + true, true, isValid("login"));
        assertEquals("GFgfgoplporokmn" + " powinno być " + true, true, isValid("GFgfgoplporokmn"));
        assertEquals("login_1" + " powinno być " + true, true, isValid("login_1"));
        assertEquals("login1!" + " powinno być " + true, true, isValid("login1!"));
        assertEquals("login1@" + " powinno być " + true, true, isValid("login1@"));
        assertEquals("login1#" + " powinno być " + true, true, isValid("login1#"));
        assertEquals("login1$" + " powinno być " + true, true, isValid("login1$"));
        assertEquals("login1%" + " powinno być " + true, true, isValid("login1%"));
        assertEquals("login1^" + " powinno być " + true, true, isValid("login1^"));
        assertEquals("login1*" + " powinno być " + true, true, isValid("login1*"));
        assertEquals("login1(" + " powinno być " + true, true, isValid("login1("));
        assertEquals("login1)" + " powinno być " + true, true, isValid("login1)"));
        assertEquals("log+in" + " powinno być " + true, true, isValid("log+in"));
        assertEquals(",12345" + " powinno być " + true, true, isValid(",12345"));
        assertEquals("loginn?" + " powinno być " + true, true, isValid("loginn?"));
        assertEquals("log:in1" + " powinno być " + true, true, isValid("log:in1"));
        assertEquals("logi;n1" + " powinno być " + true, true, isValid("logi;n1"));
        assertEquals("logi.n1" + " powinno być " + true, true, isValid("logi.n1"));



    }
    @Ignore
    public void passwordValidator() {
        //when
        assertEquals("log1" + " powinno być " + false, false, isValidPsssword("log1"));
        assertEquals("firpoftorM#1foir" + " powinno być " + false, false, isValidPsssword("firpoftorM#1foir"));
        assertEquals("GFgfgoplporokmn" + " powinno być " + false, false, isValidPsssword("GFgfgoplporokmn"));
        assertEquals("gfgoplporokmn" + " powinno być " + false, false, isValidPsssword("gfgoplporokmn"));
        assertEquals("123456789012345" + " powinno być " + false, false, isValidPsssword("gfgoplporokmn"));
        assertEquals("g1goplporokmn" + " powinno być " + false, false, isValidPsssword("g1goplporokmn"));
        assertEquals("g1Xoplporokmn" + " powinno być " + false, false, isValidPsssword("g1Xoplporokmn"));
        assertEquals("gXoplporokmn" + " powinno być " + false, false, isValidPsssword("gXoplporokmn"));
        assertEquals("gX<oplporokmn" + " powinno być " + false, false, isValidPsssword("gX<oplporokmn"));
        assertEquals("g1#oplporokmn" + " powinno być " + false, false, isValidPsssword("g1<oplporokmn"));
        assertEquals("gX#oplporokmn" + " powinno być " + false, false, isValidPsssword("gX<oplporokmn"));

        assertEquals("P1#sr" + " powinno być " + true, true, isValidPsssword("P1#sr"));
        assertEquals("P1#ser%!4" + " powinno być " + true, true, isValidPsssword("P1#sr"));
        assertEquals("12T4#678901234" + " powinno być " + true, true, isValidPsssword("12T4#678901234"));
    }
    private Accounts accounts;

    @Before
    public void setUp() {
        //giwen
        accounts = new Accounts();
        // Dodaj przykładowe konta do testów
        //when
        Account account = new Account("user1", "Password1@");
        accounts.setAccounts(account);

    }


    @Test
    public void loginUnavailableTest() {
        //then
        assertEquals("login1 powinno być false", false, accounts.loginBusy("login1"));
        assertEquals("user1 powinno być true", true, accounts.loginBusy("user1"));


    }

}


