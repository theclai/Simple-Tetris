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

    private static final int boardWidth = 10;
    private static final int boardHeight = 20;
    private final int blockSize = 30;
    private BufferedImage block;
    private int[][] boards = new int[boardHeight][boardWidth];
    private Tetromino[] tetrominos = new Tetromino[7];
    private  Tetromino currentTetromino;
    private Timer timer;
    private final int framePerSecond = 60;
    private  final int delay = 1000 /framePerSecond;
    private boolean gameOver = false;
    private int score = 0;

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
                },1, this);

        //Z
        tetrominos[1] = new Tetromino(block.getSubimage(blockSize,0,blockSize,blockSize),
                new int[][]{
                        {1,1,0},
                        {0,1,1}
                },2,this);

        //S
        tetrominos[2] = new Tetromino(block.getSubimage(blockSize*2,0,blockSize,blockSize),
                new int[][]{
                        {0,1,1},
                        {1,1,0}
                },3,this);

        //J
        tetrominos[3] = new Tetromino(block.getSubimage(blockSize*3,0,blockSize,blockSize),
                new int[][]{
                        {1,1,1},
                        {0,0,1}
                },4,this);

        //L
        tetrominos[4] = new Tetromino(block.getSubimage(blockSize*4,0,blockSize,blockSize),
                new int[][]{
                        {1,1,1},
                        {1,0,0}
                },5,this);

        //T
        tetrominos[5] = new Tetromino(block.getSubimage(blockSize*5,0,blockSize,blockSize),
                new int[][]{
                        {1,1,1},
                        {0,1,0}
                },6,this);

        //O
        tetrominos[6] = new Tetromino(block.getSubimage(blockSize*6,0,blockSize,blockSize),
                new int[][]{
                        {1,1},
                        {1,1}
                },7,this);

        //currentTetromino = tetrominos[4];
        createNewTetromino();
    }

    public int[][] getBoards() {
        return boards;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public void createNewTetromino(){

        int l = tetrominos.length;
        int i = (int)(Math.random() * l);

        Tetromino temp = tetrominos[i];
        Tetromino nextT = new Tetromino(temp.getBlock(),
                temp.getCoords(),temp.getColor(), this);

        currentTetromino = nextT;

        for (int r=0;r<currentTetromino.getCoords().length;r++){
            for (int s=0;s<currentTetromino.getCoords()[r].length;s++){

                if(boards[r][s+3] != 0 ){
                    gameOver = true;
                }
            }
        }
    }

    public void addScore(){
        this.score++;
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        //g.drawRect(100,100, BOARD_WIDTH, BOARD_HEIGHT);
        //g.drawImage(block,0,0,null);

        currentTetromino.render(g);

        for (int i = 0; i < boards.length; i++) {
            for (int j = 0; j < boards[i].length; j++) {

                if (boards[i][j] != 0) {

                    g.drawImage(block.getSubimage((boards[i][j] - 1) * blockSize, 0, blockSize, blockSize),
                            j * blockSize, i * blockSize, null);
                }
            }
        }

//        for (int i=0;i<boardHeight;i++){
//            g.drawLine(0, i*blockSize,boardWidth*blockSize,i*blockSize);
//        }

//        for (int j=0;j<boardWidth;j++){
//            g.drawLine(j*blockSize,0,j*blockSize,boardHeight*blockSize);
//        }

        // show score
        g.setColor(Color.BLACK);
        g.setFont(new Font("Serif", Font.BOLD, 20));
        //g.drawString(String.valueOf(MainBoard.HEIGHT + 150),MainBoard.WIDTH - 125, MainBoard.HEIGHT  / 2 - 250);
        g.drawString("SCORE", MainBoard.WIDTH - 125, MainBoard.HEIGHT / 2 - 250);
        g.drawString(score + "", MainBoard.WIDTH - 125, MainBoard.HEIGHT / 2  - 220);
    }

    public void update(){

        if(gameOver)
            timer.stop();

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
        else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_SPACE){
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
