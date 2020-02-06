package com.ssafy;

import java.util.Scanner;

//http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=2071&sca=2010
class Main_J_1307_문자사각형1 {
	// 'A'에서 얼마큼 떨어져 있는지 알기 위해 나머지를 이용할 예정
    final static int diffAZ = 'Z' - 'A' + 1;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        // StringBuilder를 이용한 한번에 출력    
        StringBuilder sb = new StringBuilder();
        // 현재 문자 저장 변수
        int curChar = 0;
        // 문자 저장 공간 확보
        char[][] alphaBoard = new char[100][100];
        // 각 행마다 문자 저장
    	for(int col = N-1; col >= 0; --col){
            for(int row = N-1; row >= 0; --row) {
        		alphaBoard[row][col] = (char)(((curChar++)%diffAZ)+'A');	
        	}
    	}
    	// 출력 형태 만들기
        for(int row = 0; row < N; ++row){
        	for(int col = 0; col < N; ++col){
        		sb.append(alphaBoard[row][col] + " ");
        	}
        	sb.append("\n");
        }
        System.out.println(sb.toString());
        sc.close();
    }
}