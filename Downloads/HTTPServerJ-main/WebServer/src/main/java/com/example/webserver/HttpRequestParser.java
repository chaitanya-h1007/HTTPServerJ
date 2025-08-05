package com.example.webserver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class HttpRequestParser {
    public static HttpRequest parse() throws IOException {
        Socket socket = new Socket("localhost", 9090);

        ServerSocket serverReq = new ServerSocket(9090);
        while(true){
                try{
                    serverReq.accept();
                }
        }


        FileReader fileReader = new FileReader("/home/chaitanya-harish/Documents/httpClientRequest.txt");
        BufferedReader bfReader = new BufferedReader(fileReader);


        HttpRequest httpRequest = new HttpRequest();
        String requestLine = bfReader.readLine();

        if(requestLine != null && !requestLine.isEmpty()){
            String[] parts =  requestLine.split(" ");
            httpRequest.method = parts[0];
            httpRequest.path = parts[1];
            httpRequest.version = parts[2];
        }

        String line;
        while ((line = bfReader.readLine()) != null && !line.isEmpty()) {
            String[] headerParts = line.split(": ", 2);
            if (headerParts.length == 2) {
                httpRequest.header.put(headerParts[0], headerParts[1]);
            }
        }

        System.out.println("Method: " + httpRequest.method);
        System.out.println("Path: " + httpRequest.path);
        System.out.println("Version: " + httpRequest.version);
        System.out.println("Headers:");
        for (Map.Entry<String, String> header : httpRequest.header.entrySet()) {
            System.out.println("  " + header.getKey() + ": " + header.getValue());
        }

        bfReader.close();
        return httpRequest;





    }

    public static void main(String[] args) throws IOException {
        parse();
    }


}
