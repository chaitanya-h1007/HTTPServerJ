package com.example.webserver;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    String method;
    String path;
    String version;
    Map<String,String> header = new HashMap<>();

    public HttpRequest(){

    }
    public String toString(){
        return method+ " "+path + " "+ version;
    }
}
