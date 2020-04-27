package com.xc.NettyChart;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ClientFrame extends Frame {
    private TextArea textArea = new TextArea();
    private TextField textField = new TextField();
    private Client client = new Client();

    public ClientFrame(){
        this.setSize(300,600);
        this.setLocation(100,50);
        this.setTitle("QQDemo");
        //this.
        this.add(textArea,BorderLayout.CENTER);
        this.add(textField,BorderLayout.SOUTH);
        textField.addKeyListener(new MyListener());


    }

    private void connectToServer(){
        client = new Client();
        client.Init();

    }

    public static void main(String[]argus){
        ClientFrame clientFrame = new ClientFrame();
        clientFrame.setVisible(true);
        clientFrame.connectToServer();

    }

    private class MyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_ENTER){
                textArea.setText(textArea.getText()+textField.getText()+"\r\n");
                //textArea.append("\r\n"+textField.getText());
                client.send(textField.getText());
                textField.setText(null);


            }
        }
    }
}
