/**
 * TMU Inc.
 * Copyright (c) 2019 All Rights Reserved.
 */
package com.tmu;

import javax.swing.*;

/**
 * @author Faisal_RM754
 * @version $Id: MainBoard.java, v 0.1 20190208 9:48 Faisal_RM754 Exp $$
 */
public class MainBoard {

    private JFrame board;
    public static  final int WIDTH = 300;
    public static final int HEIGHT = 630;
    private TetrisBoard tetrisBoard;

    public MainBoard(){
        board = new JFrame("Tetris");
        board.setSize(WIDTH, HEIGHT);
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.setResizable(false);
        board.setLocationRelativeTo(null);

        tetrisBoard = new TetrisBoard();
        board.add(tetrisBoard);
        board.addKeyListener(tetrisBoard);
        board.setVisible(true);
    }

    public static void main(String[] args){
        new MainBoard();
    }
}
