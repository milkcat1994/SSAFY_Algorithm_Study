package com.ssafy;

import java.util.Scanner;

//http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1335&sca=2020
class Main_J_2071_파스칼삼각형_양지용 {
    public static void main(String[] args) throws Exception {
        
    	Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        // 1 <= n <=30 정수, 1<= m <= 3 자연수
        final int HEIGHT = sc.nextInt();
        int sort = sc.nextInt();
        
        int[][] numBoard = new int[HEIGHT][HEIGHT];
        // 초기값 설정
        numBoard[0][0] = 1;
        
        // 삼각형에 조건 1과 같이 정렬
        for(int row = 1; row < HEIGHT; ++row) {
        	numBoard[row][0] = 1;
        	for (int col = 1; col < row; ++col) {
        		numBoard[row][col] = numBoard[row-1][col-1] + numBoard[row-1][col];
        	}
        	numBoard[row][row] =1;
        }
        
        // 조건 따라 출력 다르게 출력
        switch (sort) {
            case 1:
                for(int row = 0; row < HEIGHT; ++row) {
            		for(int col = 0; col <= row; ++col) {
            			sb.append(numBoard[row][col]+" ");
            		}
                	sb.append("\n");
                }
            break;
        
            case 2:
                for(int row = HEIGHT-1; row >= 0; --row) {
                	for(int col = HEIGHT-1; col > row; --col) {
                		sb.append(" ");
                	}
                	for(int col = 0; col <= row; ++col) {
                		sb.append(numBoard[row][col] + " ");
                	}
                    sb.append("\n");
                }
            break;

            // case 3
            default:
            	for(int col = HEIGHT-1; col >= 0; --col) {
            		for(int row = HEIGHT-1; row >= col; --row) {
                		sb.append(numBoard[row][col] + " ");
                	}
                    sb.append("\n");
                }
            break;
        }
        System.out.println(sb.toString());
        sc.close();
    }
}