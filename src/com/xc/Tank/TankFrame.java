package com.xc.Tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    static final public TankFrame INSTRANCE = new TankFrame();
    static final int GAME_WIDTH = 1080,GAME_HEIGHT = 960;
    Player myTank;
    Tank enermy;
    List<Bullet> bullets = new ArrayList<>();

    private int x = 100,y = 100;
    private Image offScreenImage = null;

    private TankFrame(){
        this.setLocation(100,100);
        this.setSize(GAME_WIDTH,GAME_HEIGHT);
        this.addKeyListener(new MyTankListener());
        myTank = new Player(x,y,Dir.U,Group.GOOD);
        enermy = new Tank(200,200,Dir.D,Group.BAD);
        //Bullet bullet
    }

    @Override
    public void update(Graphics g){
        if (offScreenImage == null){
            offScreenImage = createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.black);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g) {
        myTank.paint(g);
        enermy.paint(g);

        for (int i = 0;i<bullets.size();++i){
            bullets.get(i).isOutBound();
            if (bullets.get(i).collideWith(enermy)) {
                bullets.get(i).setLive(false);
                enermy.setLive(false);
            }

            if(!bullets.get(i).getLive()){
                bullets.remove(i);
            }
            else{
                bullets.get(i).paint(g);
            }

        }

        //子弹数据标签
        Color colorOld = g.getColor();
        g.setColor(Color.white);
        String label = "子弹数量："+bullets.size();
        g.drawString(label,50,50);
        g.setColor(colorOld);

    }

    public void addBullet(Bullet bullet){
        bullets.add(bullet);
    }



    private class MyTankListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            myTank.KeyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e){
            myTank.keyReleased(e);
        }
    }
}
