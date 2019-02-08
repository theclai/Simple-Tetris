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

    public Tetromino(BufferedImage block, int[][] coords, TetrisBoard tetrisBoard){

        this.block = block;
        this.coords = coords;
        this.tetrisBoard = tetrisBoard;
    }

    public void Update(){}

    public void Render(Graphics g){

        for (int i=0;i<coords.length;i++){
            for(int j=0;j<coords[i].length;j++){
                if(coords[i][j] != 0){
                    g.drawImage(block, i * tetrisBoard.getBlockSize(), j*tetrisBoard.getBlockSize(), null);
                }
            }
        }
    }
}
