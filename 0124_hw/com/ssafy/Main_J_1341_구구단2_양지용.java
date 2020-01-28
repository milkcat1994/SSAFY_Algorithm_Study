package com.ssafy;

import java.util.Scanner;

//http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=2076&sca=2010
class Main_J_1341_구구단2_양지용 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        
        int startP = sc.nextInt();
        int endP = sc.nextInt();
        
        // 시작점이 작은 구구단
        if(startP < endP){
        	for(int row = startP; row <= endP; ++row){
            	for(int col = 1; col <= 9; ++col){
            		sb.append(row +" * "+col + " = "+ String.format("%2d", col*row) + "   ");
            		if(col%3 == 0)
            			sb.append("\n");
            	}
            	sb.append("\n");
            }
        }
        else{
        	for(int row = startP; row >= endP; --row){
            	for(int col = 1; col <= 9; ++col){
            		sb.append(row +" * "+col + " = "+ String.format("%2d", col*row) + "   ");
            		if(col%3 == 0)
            			sb.append("\n");
            	}
            	sb.append("\n");
            }
        }
        System.out.println(sb.toString());
        sc.close();
    }
}