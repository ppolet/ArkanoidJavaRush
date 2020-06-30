package com.mycompany.arkanoid;

public class Canvas {
    private int width, height;
    private char[][] matrix;
    
    public Canvas(int width, int height){
        this.width = width;
        this.height = height;
        matrix = new char[height][width];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char[][] getMatrix() {
        return matrix;
    }
    
    public void setPoint(double x, double y, char c){
        int xx = (int) Math.round(x);
        int yy = (int) Math.round(y);
        
        if (xx<0 || yy<0 || yy>matrix.length || xx>matrix[0].length) return;
        
        matrix[yy][xx] = c;
        
    }
    
    public void drawMatrix(double x, double y, int[][] matrix, char c){
        for(int i = 0; i<matrix.length; i++){
            for(int j = 0; j<matrix[0].length; j++){
                if (matrix[j][i] != 0){
                    setPoint(x+j, y+i, c);
                }
            }
        }
    }
    
    public void clear(){
        for (int i = 0; i<matrix.length; i++){
            for (int j = 0; j<matrix[0].length; j++){
                matrix[j][i] = ' ';
            }
        }
    }
    
    public void print(){
        for (int i = 0; i<matrix.length; i++){
            for (int j = 0; j<matrix[0].length; j++){
                System.out.print(matrix[j][i]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
    
}
