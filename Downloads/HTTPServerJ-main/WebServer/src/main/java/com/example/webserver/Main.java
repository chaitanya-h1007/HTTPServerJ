package com.example.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class Main {
    public static void main(String[] args) {
        //request and response are the main communication medium between the browser and server
        /*
                HTTP/1.1 -> is http version used (protocol used)
                Request "GET myfavfile HTTP/1.1" /n
                Responce "HTTP/1.1 STATUS-CODE (200,400,....) OK  /n
                        then -> Content of the file requested
         */

        try(ServerSocket serverSocket = new ServerSocket(8080)) { //building a port 8080
            System.out.println("Server Started \nListening for messages....");
            while (true) {
                //Handling a new incoming messages
                try(Socket client = serverSocket.accept()) {
                    System.out.println("Debug: We got a new message" + client.toString());

                    InputStreamReader isr = new InputStreamReader(client.getInputStream()); //getting the input from the client request
                    BufferedReader br = new BufferedReader(isr);  //  GET / HTTP/1.1 here we are reading the request

                    StringBuilder builder = new StringBuilder();

                    String clientMessage =  br.readLine();
                    while(!clientMessage.isEmpty()) {
                        builder.append(clientMessage);
                    }

                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}