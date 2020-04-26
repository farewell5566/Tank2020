package com.xc.Tank;

import com.xc.Tank.chainofresponsibility.ColliderChain;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameModel implements Serializable {
    Player myTank;
    List<AbstractGameObject> gameObjetcs;
    ColliderChain colliderChain ;

    int enemyNum ;
    private int x = 100, y = 100;

    public GameModel(){
        InitGameModel();

    }
    private void InitGameModel(){
        //init colliders
        myTank = new Player(x, y, Dir.U, Group.GOOD);
        colliderChain  = new ColliderChain();
        //init gameObjectes
        gameObjetcs = new ArrayList<>();
        //gameObjetcs.add(new Player(x,y,Dir.U,Group.GOOD));
        try{
            enemyNum = Integer.parseInt((String)ConfigMgr.prop.get("initTankNum"));
        }catch(Exception ex) {
            ex.getStackTrace();
        }

        for (int i = 0; i < enemyNum; i++) {
            gameObjetcs.add(new Tank(20 + i * 80, 100, Dir.D, Group.BAD));
        }
        gameObjetcs.add(new Wall());

    }

    public void paint(Graphics g){
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
        g.setColor(colorOld);

    }

    public void addBullet(Bullet bullet) {
        gameObjetcs.add(bullet);
    }

    public void addBoom(Boom boom){
        gameObjetcs.add(boom);
    }

    public Player getMyTank() {
        return myTank;
    }
}
