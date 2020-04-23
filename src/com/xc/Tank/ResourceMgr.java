package com.xc.Tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResourceMgr {
    public static BufferedImage goodTankL,goodTankR,goodTankU,goodTankD;
    public static BufferedImage badTankL,badTankR,badTankU,badTankD;
    public static BufferedImage bulletL,bulletR,bulletU,bulletD;
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try{
            //goodTankU = ImageIO.read(new File("images/GoodTank1.png"));
            goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodTankL = ImageUtil.rotate(goodTankU,-90);
            goodTankR = ImageUtil.rotate(goodTankU,90);
            goodTankD = ImageUtil.rotate(goodTankU,180);

            badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/badTank1.png"));
            badTankL = ImageUtil.rotate(badTankU,-90);
            badTankR = ImageUtil.rotate(badTankU,90);
            badTankD = ImageUtil.rotate(badTankU,180);

            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletL = ImageUtil.rotate(bulletU,-90);
            bulletR = ImageUtil.rotate(bulletU,90);
            bulletD = ImageUtil.rotate(bulletU,180);

            for (int i = 0;i<16;i++) {
                explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i+1) + ".gif"));

            }
        }
        catch(IOException ex){
            ex.getStackTrace();
        }
    }
}
