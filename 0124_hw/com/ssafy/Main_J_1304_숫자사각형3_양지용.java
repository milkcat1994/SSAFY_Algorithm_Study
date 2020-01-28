package com.ssafy;

import java.util.Scanner;

//http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=2070&sca=2010
class Main_J_1304_숫자사각형3_양지용 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        
        int N = sc.nextInt();
        // StringBuilder에 행 별로 점화식 이용하여 저장해준다.
        for(int row = 1; row <= N; ++row){
        	for(int col = 0; col < N; ++col){
        		sb.append((N*col + row) + " ");
        	}
        	sb.append("\n");
        }
        System.out.println(sb.toString());
        sc.close();
    }
}