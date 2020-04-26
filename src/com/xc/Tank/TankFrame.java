package com.xc.Tank;

import com.xc.Tank.chainofresponsibility.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    static final public TankFrame INSTRANCE = new TankFrame();
    private int gameWidth , gameHeight;
    Player myTank;
    int enemyNum ;
    private int x = 100, y = 100;
    private Image offScreenImage = null;

    List<AbstractGameObject> gameObjetcs;
    ColliderChain colliderChain ;



    /*List<Tank> enemies = new ArrayList<>();
    List<Bullet> bullets = new ArrayList<>();
    List<Boom> booms = new ArrayList<>();*/

    private TankFrame() {
        initGame();
        this.setLocation(100, 100);
        this.setSize(gameWidth, gameHeight);
        this.addKeyListener(new MyTankListener());



    }

    private void initGame(){
        myTank = new Player(x, y, Dir.U, Group.GOOD);

        try{
            enemyNum = Integer.parseInt((String)ConfigMgr.prop.get("initTankNum"));
            gameWidth= Integer.parseInt((String)ConfigMgr.prop.get("GAME_WIDTH"));
            gameHeight = Integer.parseInt((String)ConfigMgr.prop.get("GAME_HEIGHT"));
        }catch(Exception ex) {
            ex.getStackTrace();
        }

        //init colliders
        colliderChain  = new ColliderChain();

        //init gameObjectes
        gameObjetcs = new ArrayList<>();
        //gameObjetcs.add(new Player(x,y,Dir.U,Group.GOOD));
        for (int i = 0; i < enemyNum; i++) {
            gameObjetcs.add(new Tank(20 + i * 80, 100, Dir.D, Group.BAD));
        }
        gameObjetcs.add(new Wall());

    }


    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = createImage(gameWidth, gameHeight);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.black);
        gOffScreen.fillRect(0, 0, gameWidth, gameHeight);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        myTank.paint(g);

        for (int i = 0;i<gameObjetcs.size();++i){
            if (!gameObjetcs.get(i).getLive())
                gameObjetcs.remove(i);

        }
        for (int i = 0;i<gameObjetcs.size();++i){
            for (int j = 0;j<gameObjetcs.size();++j){
                if (i == j)continue;
                colliderChain.collide(gameObjetcs.get(i),gameObjetcs.get(j));


                    /*if(((Bullet) gameObjetcs.get(j)).collideWith((Tank)gameObjetcs.get(i))){
                        ((Bullet) gameObjetcs.get(j)).setLive(false);
                        ((Tank) gameObjetcs.get(i)).setLive(false);
                    }*/

            }
        }
        for (int i = 0;i<gameObjetcs.size();++i){
            gameObjetcs.get(i).paint(g);
        }

      /*  for (int i = 0; i < bullets.size(); ++i) {
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
        }*/

        //子弹数据标签
        Color colorOld = g.getColor();
        g.setColor(Color.white);
        String labelgameObjects = "Objects：" + gameObjetcs.size();
        g.drawString(labelgameObjects,30,50);

        /*String labelBullet = "子弹数量：" + bullets.size();
        String labelEnemies = "敌人坦克:" + enemies.size();
        String labelBoom = "爆炸烟火"  + booms.size();*//*
        g.drawString(labelBullet, 30, 50);
        g.drawString(labelEnemies, 30, 70);
        g.drawString(labelBoom, 30, 90);*/

        g.setColor(colorOld);

    }

    public void addBullet(Bullet bullet) {
        gameObjetcs.add(bullet);
    }

    public void addBoom(Boom boom){
        gameObjetcs.add(boom);
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
    public int getGameWidth(){
        return gameWidth;
    }
    public int getGameHeight(){
        return gameHeight;
    }

}
