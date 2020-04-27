package com.xc.web;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class Server {
    public static void  main(String[]argus){
        try {
            ServerSocket ss = new ServerSocket();
            ss.bind(new InetSocketAddress("localhost",8088));

            while(true) {
                Socket socket = ss.accept();
                new Thread(()->{
                    try {
                        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String str = br.readLine();
                        System.out.println(str);
                        br.close();
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                ss.close();

            }
           /* InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String str = br.readLine();*/


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
