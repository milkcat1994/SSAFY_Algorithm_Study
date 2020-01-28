package com.ssafy;

import java.util.Scanner;
//http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1129&sca=2010
class Main_J_1856_숫자사각형2_양지용 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int ROW = sc.nextInt();
        final int COL = sc.nextInt();
        // StringBuilder를 이용한 한번에 출력    
        StringBuilder sb = new StringBuilder();
        // 숫자 저장 공간 확보
        int[][] numsBoard = new int[ROW][COL];
        
        int num = 1;
        for(int row = 0; row < ROW; ++row) {
        	for(int col = 0; col < COL; ++col){
        		numsBoard[row][col + (COL-1 -2*col)*(row%2)] = num++;
        	}
    	}
        
    	// 출력 형태 만들기
        for(int row = 0; row < ROW; ++row){
        	for(int col = 0; col < COL; ++col){
        		sb.append(numsBoard[row][col] + " ");
        	}
        	sb.append("\n");
        }
        System.out.println(sb.toString());
        sc.close();
    }
}