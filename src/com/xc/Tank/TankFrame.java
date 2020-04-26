package com.xc.Tank;

import com.xc.Tank.chainofresponsibility.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    static final public TankFrame INSTRANCE = new TankFrame();


    private Image offScreenImage = null;

    private int gameWidth , gameHeight;

    private GameModel gm;

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
        gm = new GameModel();
        gameWidth= Integer.parseInt((String)ConfigMgr.prop.get("GAME_WIDTH"));
        gameHeight = Integer.parseInt((String)ConfigMgr.prop.get("GAME_HEIGHT"));

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
        gm.paint(g);
    }

    private class MyTankListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_Q) {
                saveGM();
            }
            else if (key == KeyEvent.VK_W){
                loadGM();
            }else
                gm.getMyTank().KeyPressed(e);

        }

        @Override
        public void keyReleased(KeyEvent e) {
            gm.getMyTank().keyReleased(e);
        }
    }
    public int getGameWidth(){
        return gameWidth;
    }
    public int getGameHeight(){
        return gameHeight;
    }

    public GameModel getGm() {
        return gm;
    }

    private void saveGM(){
        File t = new File("c:/tank.dat");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(t);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(gm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                oos.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private void loadGM(){
        File t = new File("c:/tank.dat");

        try {
            FileInputStream fos = new FileInputStream(t);
            ObjectInputStream oos = new ObjectInputStream(fos);
            gm = (GameModel)oos.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }




}
