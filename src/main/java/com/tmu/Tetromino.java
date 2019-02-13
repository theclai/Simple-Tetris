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
    private boolean collision = false;
    private boolean moveX = false;
    private int color;

    public Tetromino(BufferedImage block, int[][] coords, int color, TetrisBoard tetrisBoard){

        this.block = block;
        this.coords = coords;
        this.tetrisBoard = tetrisBoard;
        this.color = color;

        currentSpeed = defaultSpeed;
        time = 0;
        lastTime = System.currentTimeMillis();

        x = 3;
        y = 0;

    }

    public void update(){

        time += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (collision) {

            for (int i = 0; i < coords.length; i++){
                for (int j = 0; j < coords[i].length; j++){

                    if (coords[i][j] != 0){

                        tetrisBoard.getBoards()[y + i][x + j] = color;
                    }
                }
            }

            checkLine();
            tetrisBoard.addScore();
            tetrisBoard.createNewTetromino();
        }

        if(!(x + posX + coords[0].length > 10) && !(x + posX  < 0)){

            for (int i = 0; i < coords.length; i++) {
                for (int j = 0; j < coords[i].length; j++) {

                    if (coords[i][j] != 0) {

                        if(tetrisBoard.getBoards()[i + y][j + x + posX]  != 0){

                            moveX = false;
                        }
                    }
                }
            }

            if(moveX){
                x += posX;
            }

        }

        if(!(y+1+coords.length > 20)) {

            for (int i = 0; i < coords.length; i++) {
                for (int j = 0; j < coords[i].length; j++) {

                    if (coords[i][j] != 0) {

                        if(tetrisBoard.getBoards()[i + y + 1][j + x] != 0){

                            collision = true;
                        }
                    }
                }
            }

            if (time > currentSpeed) {
                y++;
                time = 0;
            }

        }else{
            collision = true;
        }

        posX = 0;
        moveX = true;
    }

    public void render(Graphics g){

        for (int i=0;i<coords.length;i++){
            for(int j=0;j<coords[i].length;j++){

                if(coords[i][j] != 0){
                    g.drawImage(block, j * tetrisBoard.getBlockSize() + x*tetrisBoard.getBlockSize(),
                            i*tetrisBoard.getBlockSize() + y*tetrisBoard.getBlockSize(), null);
                }
            }
        }
    }

    public void rotate(){

       int[][] rotatedMatrix = transpose(coords);
       rotatedMatrix = reverseMatrix(rotatedMatrix);

       if (x + rotatedMatrix[0].length > 10 || y + rotatedMatrix.length > 20)
           return;

       for (int i=0;i<rotatedMatrix.length;i++){
           for (int j=0;j<rotatedMatrix[0].length;j++){

               if(tetrisBoard.getBoards()[y + i][x + j] != 0)
                   return;
           }
       }
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

    private void checkLine(){

        int height = tetrisBoard.getBoards().length - 1;

        for (int i=height;i>0;i--){

            int count = 0;

            for (int j=0;j<tetrisBoard.getBoards()[0].length;j++){

                if (tetrisBoard.getBoards()[i][j] != 0)
                    count++;

                    tetrisBoard.getBoards()[height][j] = tetrisBoard.getBoards()[i][j];
            }

            if(count < tetrisBoard.getBoards()[0].length)
                height--;
        }
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

    public BufferedImage getBlock() {
        return block;
    }

    public void setBlock(BufferedImage block) {
        this.block = block;
    }

    public int[][] getCoords() {
        return coords;
    }

    public void setCoords(int[][] coords) {
        this.coords = coords;
    }

    public int getColor() {
        return color;
    }
}
