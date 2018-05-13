package com.example.omid.firstexam;

public class Common {
    private static final String BASE_URL = "https://api.whichapp.com/v1/";
    public static RestInterface getRestInterface(){
return RetrofitClass .getClient(BASE_URL).create(RestInterface.class);
    }

}
