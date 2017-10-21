package com.hurokuapp;

import com.google.gson.JsonArray;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import com.google.gson.Gson;
import org.junit.Test;



public class HurokuApiTest {

    int i;
    private class Account{
        public String account_id;
    }

    //---------------------------------------------------------------------------
    //по отдельному счету
    @Test
    public void testHurokuApiAcc(){
        Response response_1 = RestAssured.get("http://kn-ktapp.herokuapp.com/apitest/accounts/12345678").andReturn();
        String string_1 = response_1.getBody().asString();

        Gson gson = new Gson();
        Account acc2 = gson.fromJson(string_1, Account.class);
        Assert.assertNotNull(acc2.account_id);

        System.out.println("account_id: " + acc2.account_id);
    }

    //---------------------------------------------------------------------------
    //по счетам
    @Test
    public void testHurokuApiAccs(){
        Response response_2 = RestAssured.get("http://kn-ktapp.herokuapp.com/apitest/accounts").andReturn();
        String string_2 = response_2.getBody().asString();

        Gson gson = new Gson();
        Account[] acc3 = gson.fromJson(string_2, Account[].class);

        for (i=0; i < 6; i++){
            Assert.assertNotNull(acc3[i].account_id);
            System.out.println("account_id"+i+": " + acc3[i].account_id);
        }
    }

}
