package com.mycompany.arkanoid;

public class Canvas {
    private int width, height;
    private char[][] matrix;
    
    public Canvas(int width, int height){
        this.width = width;
        this.height = height;
        matrix = new char[width][height];
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
    
    // Отображение одного символа "char c" на канвасе с координатами x,y
    public void setPoint(double x, double y, char c){
        int xx = (int) Math.round(x);
        int yy = (int) Math.round(y);
        
        if (xx<0 || yy<0 || yy>matrix.length || xx>matrix[0].length) return;
        
        matrix[yy][xx] = c;
        
    }
    
    // передаем в массив объект matrix
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
                matrix[i][j] = ' ';
            }
        }
    }
    
    public void print(){
        for (int i = 0; i<matrix.length; i++){
            for (int j = 0; j<matrix[0].length; j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
    
}
