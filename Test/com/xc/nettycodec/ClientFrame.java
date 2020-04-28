package com.xc.nettycodec;

import com.xc.nettycodec.Client;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientFrame extends Frame {
    public static final ClientFrame INSTANCE = new ClientFrame();
    private TextArea textArea = new TextArea();
    private TextField textField = new TextField();
    private Client client = new Client();

    private ClientFrame(){
        this.setSize(300,600);
        this.setLocation(200,50);
        this.setTitle("QQDemo");
        //this.
        this.add(textArea,BorderLayout.CENTER);
        this.add(textField,BorderLayout.SOUTH);
        textField.addKeyListener(new MyListener());
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                client.send("__byebye__");
                System.exit(0);
            }
        });
    }

    public void Update(String str) {
        textArea.setText(textArea.getText()+str+"\r\n");
    }

    private void connectToServer(){
        client = new Client();
        client.Init();

    }

    private class MyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_ENTER){

                client.send(textField.getText());
                textField.setText(null);


            }
        }
    }

    public static void main(String[]argus){
        ClientFrame clientFrame = ClientFrame.INSTANCE;
        clientFrame.setVisible(true);
        clientFrame.connectToServer();

    }



}
