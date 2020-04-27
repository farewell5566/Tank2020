package com.xc.web;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void  main(String[]argus){
        try {
            Socket soc = new Socket("localhost",8088);
            BufferedWriter sw = new BufferedWriter(new OutputStreamWriter(soc.getOutputStream()));
            sw.write("beier");
            sw.newLine();
            sw.flush();

            System.out.println("------\r\n");

            BufferedReader br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            String str = br.readLine();
            System.out.println(str);
            sw.close();
            soc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
