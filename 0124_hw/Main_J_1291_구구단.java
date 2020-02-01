package com.ssafy;

import java.util.Scanner;
//http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=574&sca=2010

class Main_J_1291_구구단_양지용 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        
        int startP = sc.nextInt();
        int endP = sc.nextInt();
        
        // start, e 는 2~9
        while(startP<2 || 9<startP || endP<2 || 9<endP){
        	System.out.println("INPUT ERROR!");
        	startP = sc.nextInt();
        	endP = sc.nextInt();
        }
        
        // 시작점이 작은 구구단
        if(startP < endP){
        	for(int row = 1; row <= 9; ++row){
            	for(int col = startP; col <= endP; ++col){
            		sb.append(col +" * "+row + " = "+ String.format("%2d", col*row) + "   ");
            	}
            	sb.append("\n");
            }
        }
        else{
        	for(int row = 1; row <= 9; ++row){
            	for(int col = startP; col >= endP; --col){
            		sb.append(col +" * "+row + " = "+ String.format("%2d", col*row) + "   ");
            	}
            	sb.append("\n");
            }
        }
        System.out.println(sb.toString());
        sc.close();
    }
}