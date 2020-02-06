package com.ssafy;

import java.util.Scanner;

//http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=914&sca=2020
class Main_J_1641_숫자삼각형 {
    public static void main(String[] args) throws Exception {
        
    	Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        // n <=100 홀수 1<= m <= 3 자연수
        int height = sc.nextInt();
        int sort = sc.nextInt();
        
        int[][] numBoard;
        int num=0;
        int ROW,COL;
        
        if(height%2==0 || height>100 || sort<1 || sort>3){
            System.out.println("INPUT ERROR!");
        }
        else {
            switch (sort) {
                case 1:
                    ROW = height;	COL = height;
                    numBoard = new int[ROW][COL];
	                for(int row = 0; row < ROW; ++row){
	                	// 홀수 번째 줄은 거꾸로 입력
	                	if(row%2 != 0) {
		                    for(int col = row; col >= 0; --col){
		                    	numBoard[row][col] = ++num;
		                    }
	                	}
	                	else {
		                    for(int col = 0; col <= row; ++col){
		                    	numBoard[row][col] = ++num;
		                    }
	                	}
	                }

                    for(int row = 0; row < ROW; ++row) {
                		for(int col = 0; col <= row; ++col) {
                			sb.append(numBoard[row][col]+" ");
                		}
                    	sb.append("\n");
                    }
                break;
            
                case 2:
                    ROW = height;	COL = height*2-1;
                    for(int row = 0; row < ROW; ++row) {
                    	for(int col = 0; col < row; ++col) {
                    		sb.append("  ");
                    	}
                    	for(int col = row; col < COL -row; ++col) {
                    		sb.append(row+" ");
                    	}
                        sb.append("\n");
                    }
                break;

                // case 3
                default:
                    ROW = height;
                    int midPoint = ROW/2;
                    for(int row = 0; row < ROW; ++row) {
                    	if(row <=midPoint) {
                    		for(int col = 1; col <= row+1; ++col)
                    			sb.append(col+" ");
                    	}
                    	else {
                    		for(int col = 1; col <= ROW -row; ++col)
                    			sb.append(col+" ");
                    	}
                    	sb.append("\n");
                    }
                break;
            }
            System.out.println(sb.toString());
        }
        sc.close();
    }
}