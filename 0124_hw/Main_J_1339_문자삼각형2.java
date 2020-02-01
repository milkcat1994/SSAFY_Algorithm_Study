package com.ssafy;

import java.util.Scanner;
import java.lang.StringBuilder;

//http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=2075&sca=2010
class Main_J_1339_문자삼각형2_양지용 {
	// 'A'에서 얼마큼 떨어져 있는지 알기 위해 나머지를 이용할 예정
    final static int diffAZ = 'Z' - 'A' + 1;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 바닥 길이
        int N = sc.nextInt();
        // StringBuilder를 이용한 한번에 출력    
        StringBuilder sb = new StringBuilder();
        // 'A'부터 떨어진 거리
        int curChar = 0;
        // 1<= N <= 100 홀수
        if(N<1 || 100<N || N%2==0){
        	System.out.println("INPUT ERROR");
        }
        else{
        	int COL = N/2 +1;
        	int[][] alphaBoard = new int[N][COL];
        	// 한 열씩 순서대로 채워간다.
        	for(int col = N/2; 0 <= col; --col){
        		// 줄의 시작은 열의 값과 같다.
        		for(int row = col; row <= N-1-col; ++row) {
	                alphaBoard[row][col] = 'A' + curChar++%diffAZ;
	            }
	        }
        	
        	for(int row = 0; row < N; row++){
        		if(row <= N/2){
	        		for(int col = 0; col < row+1; col++){
	        			sb.append((char)alphaBoard[row][col] + " ");
	        		}
        		}
        		else{
        			for(int col = 0; col < N-row; col++){
	        			sb.append((char)alphaBoard[row][col] + " ");
	        		}
        		}
        		sb.append("\n");
        	}
	        System.out.println(sb.toString());
        }
        sc.close();
    }
}