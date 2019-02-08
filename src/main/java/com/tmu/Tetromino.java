/**
 * TMU Inc.
 * Copyright (c) 2019 All Rights Reserved.
 */
package com.tmu;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Faisal_RM754
 * @version $Id: Tetromino.java, v 0.1 20190208 10:43 Faisal_RM754 Exp $$
 */
public class Tetromino {

    private BufferedImage block;
    private int[][] coords;
    private TetrisBoard tetrisBoard;
    private int posX = 0;
    private int posY = 0;
    private int x=0;
    private  int y=0;
    private int speed = 600;
    private int downSpeed = 10;
    private long time;
    private long lastTime;

    public Tetromino(BufferedImage block, int[][] coords, TetrisBoard tetrisBoard){

        this.block = block;
        this.coords = coords;
        this.tetrisBoard = tetrisBoard;
        x = 2;
        y = 0;

        time = 0;
        lastTime = System.currentTimeMillis();
    }

    public void update(){

        time += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(!(x + posX + coords[0].length > 11) && !(x + posX  < 0)){
            x += posX;
        }

        if(time > speed){
            y++;
            time =0;
        }

        posX = 0;
    }

    public void render(Graphics g){

        for (int i=0;i<coords.length;i++){
            for(int j=0;j<coords[i].length;j++){
                if(coords[i][j] != 0){
                    g.drawImage(block, i * tetrisBoard.getBlockSize() + x*tetrisBoard.getBlockSize(),
                            j*tetrisBoard.getBlockSize() + y*tetrisBoard.getBlockSize(), null);
                }
            }
        }
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
