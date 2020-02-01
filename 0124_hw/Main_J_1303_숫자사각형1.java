package com.ssafy;

import java.util.Scanner;

//http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=2069&sca=2010
class Main_J_1303_숫자사각형1_양지용 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        
        int ROW = sc.nextInt();
        int COL = sc.nextInt();
        
        // StringBuilder에 행 별로 저장해준다.
        for(int row = 0; row < ROW; ++row){
        	for(int col = 1; col <= COL; ++col){
        		sb.append((COL*row + col) + " ");
        	}
        	sb.append("\n");
        }
        System.out.println(sb.toString());
        sc.close();
    }
}