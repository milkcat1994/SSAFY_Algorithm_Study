package com.ssafy;

import java.util.Scanner;

//http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=2072&sca=2010
class Main_J_1314_문자사각형2 {
	// 'A'에서 얼마큼 떨어져 있는지 알기 위해 나머지를 이용할 예정
    final static int diffAZ = 'Z' - 'A' + 1;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        // StringBuilder를 이용한 한번에 출력    
        StringBuilder sb = new StringBuilder();
        // 문자 저장 공간 확보
        char[][] alphaBoard = new char[N][N];

        for(int col = 0; col < N; ++col){
        	for(int row = 0; row <N; ++row){
        		alphaBoard[row + (N-1 -2*row)*(col%2)][col] = (char)('A'+((row+col*N)%diffAZ));
        	}
        }
        
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