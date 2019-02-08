/**
 * TMU Inc.
 * Copyright (c) 2019 All Rights Reserved.
 */
package com.tmu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author Faisal_RM754
 * @version $Id: TetrisBoard.java, v 0.1 20190208 9:52 Faisal_RM754 Exp $$
 */
public class TetrisBoard extends JPanel implements KeyListener {

    private static final int boardWidth = 20;
    private static final int boardHeight = 30;
    private final int blockSize = 30;
    private BufferedImage block;
    private int[][] boards = new int[boardWidth][boardHeight];
    private Tetromino[] tetrominos = new Tetromino[7];
    private  Tetromino currentTetromino;
    private Timer timer;
    private final int framePerSecond = 60;
    private  final int delay = 1000 /framePerSecond;

    public TetrisBoard(){

        try{
            block = ImageIO.read(TetrisBoard.class.getResource("/tiles.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

       timer = new Timer(delay, new ActionListener() {

           public void actionPerformed(ActionEvent e) {

               repaint();
               update();
           }
       });

        timer.start();
        //I
        tetrominos[0] = new Tetromino(block.getSubimage(0,0,blockSize,blockSize),
                new int[][]{
                        {1,1,1}
                },this);

        //Z
        tetrominos[1] = new Tetromino(block.getSubimage(blockSize,0,blockSize,blockSize),
                new int[][]{
                        {1,1,0},
                        {0,1,1}
                },this);

        //S
        tetrominos[2] = new Tetromino(block.getSubimage(blockSize*2,0,blockSize,blockSize),
                new int[][]{
                        {0,1,1},
                        {1,1,0}
                },this);

        //J
        tetrominos[3] = new Tetromino(block.getSubimage(blockSize*3,0,blockSize,blockSize),
                new int[][]{
                        {1,1,1},
                        {0,0,1}
                },this);

        //L
        tetrominos[4] = new Tetromino(block.getSubimage(blockSize*4,0,blockSize,blockSize),
                new int[][]{
                        {1,1,1},
                        {1,0,0}
                },this);

        //T
        tetrominos[5] = new Tetromino(block.getSubimage(blockSize*5,0,blockSize,blockSize),
                new int[][]{
                        {1,1,1},
                        {0,1,0}
                },this);

        //O
        tetrominos[6] = new Tetromino(block.getSubimage(blockSize*6,0,blockSize,blockSize),
                new int[][]{
                        {1,1},
                        {1,1}
                },this);

        currentTetromino = tetrominos[2];
    }

    public int getBlockSize() {
        return blockSize;
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        //g.drawRect(100,100, BOARD_WIDTH, BOARD_HEIGHT);
        //g.drawImage(block,0,0,null);

        currentTetromino.render(g);

        for (int i=0;i<boardHeight;i++){
            g.drawLine(0, i*blockSize,boardWidth*blockSize,i*blockSize);
        }

        for (int j=0;j<boardWidth;j++){
            g.drawLine(j*blockSize,0,j*blockSize,boardHeight*blockSize);
        }
    }

    public void update(){

        currentTetromino.update();
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            currentTetromino.setPosX(-1);
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            currentTetromino.setPosX(1);
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            currentTetromino.downSpeed();
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP){
            currentTetromino.rotate();
        }
    }

    public void keyReleased(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            currentTetromino.defaultSpeed();
        }
    }
}
