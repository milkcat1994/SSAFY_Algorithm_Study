package com.ssafy;

import java.util.Scanner;

//http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1338&sca=2020
class Main_J_2074_홀수마방진_양지용 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int N = sc.nextInt();
        // StringBuilder를 이용한 한번에 출력    
        StringBuilder sb = new StringBuilder();
        // 숫자 저장 공간 확보
        int[][] numsBoard = new int[N][N];
        int num = 0;
        int row = 0;	int col = N/2;
        
        // 초기 숫자 지정
        numsBoard[row][col] = ++num;
        // 현재 숫자에 따라 이동이므로 ++num으로 사용해야한다.
        while(num < N*N) {
	        // N의 배수라 아래의 행으로 이동
        	if(num % N == 0) {
        		numsBoard[++row][col] = ++num;
        	}
        	// 왼쪽 위로 이동하여 다음 숫자 넣기
        	else {
        		row = (N+row-1)%N;
        		col = (N+col-1)%N;
        		numsBoard[row][col] = ++num;
        	}
        }
        
        for(int tRow = 0; tRow < N; ++tRow) {
        	for (int tCol = 0; tCol < N; ++tCol) {
        		sb.append(numsBoard[tRow][tCol] + " ");
        	}
        	sb.append("\n");
        }
        System.out.println(sb.toString());
        sc.close();
    }
}