package com.xc.Tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    static final public TankFrame INSTRANCE = new TankFrame();
    static final int GAME_WIDTH = 1080, GAME_HEIGHT = 960;
    Player myTank;

    static final int enemyNum = 10;
    List<Tank> enemies = new ArrayList<>();
    List<Bullet> bullets = new ArrayList<>();
    List<Boom> booms = new ArrayList<>();
    private int x = 100, y = 100;
    private Image offScreenImage = null;

    private TankFrame() {
        this.setLocation(100, 100);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.addKeyListener(new MyTankListener());
        myTank = new Player(x, y, Dir.U, Group.GOOD);
        //enermy = new Tank(200,200,Dir.D,Group.BAD);
        for (int i = 0; i < enemyNum; i++) {
            enemies.add(new Tank(20 + i * 80, 100, Dir.D, Group.BAD));
        }

        //Bullet bullet
    }

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.black);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        myTank.paint(g);


        for (int i = 0; i < bullets.size(); ++i) {
            bullets.get(i).isOutBound();
            for (int j = 0; j < enemies.size(); ++j) {
                if (bullets.get(i).collideWith(enemies.get(j))) {
                    bullets.get(i).setLive(false);
                    enemies.get(j).setLive(false);
                }
            }
        }

        for (int i = 0; i < bullets.size(); ++i) {
            if (!bullets.get(i).getLive()) {
                bullets.remove(i);
            } else {
                bullets.get(i).paint(g);
            }
        }

        for (int j = 0; j < enemies.size(); ++j) {
            if (!enemies.get(j).getLive()) {
                enemies.remove(j);
            } else
                enemies.get(j).paint(g);
        }

        for(int i = 0;i<booms.size();++i){
            if (!booms.get(i).getLive()){
                booms.remove(i);
            }else{
                booms.get(i).paint(g);
            }
        }

        //子弹数据标签
        Color colorOld = g.getColor();
        g.setColor(Color.white);
        String labelBullet = "子弹数量：" + bullets.size();
        String labelEnemies = "敌人坦克:" + enemies.size();
        String labelBoom = "爆炸烟火"  + booms.size();
        g.drawString(labelBullet, 30, 50);
        g.drawString(labelEnemies, 30, 70);
        g.drawString(labelBoom, 30, 90);

        g.setColor(colorOld);

    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public void addBoom(Boom boom){
        booms.add(boom);
    }

    private class MyTankListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            myTank.KeyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            myTank.keyReleased(e);
        }
    }
}
