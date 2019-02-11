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
    private int defaultSpeed = 600;
    private int downSpeed = 60;
    private int currentSpeed = 0;
    private long time;
    private long lastTime;

    public Tetromino(BufferedImage block, int[][] coords, TetrisBoard tetrisBoard){

        this.block = block;
        this.coords = coords;
        this.tetrisBoard = tetrisBoard;
        x = 2;
        y = 0;

        currentSpeed = defaultSpeed;
        time = 0;
        lastTime = System.currentTimeMillis();
    }

    public void update(){

        time += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(!(x + posX + coords[0].length > 11) && !(x + posX  < 0)){
            x += posX;
        }

        if(!(y+1+coords.length > 20)){
            if(time > currentSpeed){
                y++;
                time = 0;
            }
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

    public void rotate(){

       int[][] rotatedMatrix = transpose(coords);
       rotatedMatrix = reverseMatrix(rotatedMatrix);
       coords = rotatedMatrix;
    }

    public int[][] transpose(int[][] matrix){

        int[][] newMatrix = new int[matrix[0].length][matrix.length];

        for (int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[0].length;j++){
                newMatrix[j][i] = matrix[i][j];
            }
        }

        return newMatrix;
    }

    private int[][] reverseMatrix(int[][] matrix) {

        int c = matrix.length / 2;
        int l = matrix.length;

        for (int i = 0; i < c; i++) {

            int[] temp = matrix[i];
            matrix[i] = matrix[l - i - 1];
            matrix[l - i - 1] = temp;
        }

        return matrix;
    }

    public void downSpeed(){

        this.currentSpeed = downSpeed;
    }

    public void defaultSpeed(){
        this.currentSpeed = defaultSpeed;
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
