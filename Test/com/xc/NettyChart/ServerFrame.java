package com.xc.NettyChart;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ServerFrame extends Frame {
    TextArea serArea = new TextArea();
    TextArea cliArea = new TextArea();
    Server server = null;

    public static ServerFrame INSTANCE = new ServerFrame();
    private ServerFrame(){
        this.setLocation(100,80);
        this.setSize(500,400);
        Panel panel = new Panel(new GridLayout(1,2));
        serArea.setFont(new Font("",Font.PLAIN,25));
        cliArea.setFont(new Font("",Font.PLAIN,25));
        panel.add(serArea);
        panel.add(cliArea);
        this.add(panel);


        serAreaUpdateMsg("服务器");
        cliAreaUpdateMsg("客户端");

        server = new Server();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }

    public void serAreaUpdateMsg(String str){
        serArea.setText(serArea.getText() + str+System.getProperty("line.separator"));
    }

    public void cliAreaUpdateMsg(String str){
        cliArea.setText(cliArea.getText() + str+System.getProperty("line.separator"));
    }

    public static void main(String[]argus){
        ServerFrame.INSTANCE.setVisible(true);
        ServerFrame.INSTANCE.server.Init();
    }

}
