package com.ssafy;

import java.util.Scanner;

//http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=767&sca=2020
class Main_J_1495_대각선지그재그_양지용 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int N = sc.nextInt();
        // StringBuilder를 이용한 한번에 출력    
        StringBuilder sb = new StringBuilder();
        // 숫자 저장 공간 확보
        int[][] numsBoard = new int[N][N];
        int num = 1;
        int row = 0;	int col = 0;
        // -1 : 왼쪽 아래 , 1 : 오른쪽 위
        int dir = -1;
        
        // 초기 숫자 지정
        numsBoard[0][0] = num++;
        while(row + col +2<= 2*N) {
        	// 맨 왼쪽 줄에 도달하였다면
        	if(col == 0 && row != N-1) {
        		// 아래로 한칸 이동, 그 뒤 오른쪽 위로 이동
    			numsBoard[++row][col] = num++;
        		numsBoard[--row][++col] = num++;
        		dir = 1;
        	}
        	// 아래로 이동하지 못한다면
        	else if( col != N-1 && row == N-1) {
        		// 오른쪽 이동, 그 뒤 오른쪽 위로 이동
        		numsBoard[row][++col] = num++;
        		numsBoard[--row][++col] = num++;
        		dir = 1;
        	}
        	// 맨 위쪽 줄에 도달하였다면
        	else if(row == 0 && col != N-1) {
        		// 우측으로 한칸 이동, 그 뒤 왼쪽 아래로 이동
    			numsBoard[row][++col] = num++;
        		numsBoard[++row][--col] = num++;
        		dir = -1;
        	}
        	// 우측으로 이동 하지 못한 다면 
        	else if(col == N-1) {
        		// 아래로 한칸 이동, 왼쪽 아래로 이동
        		numsBoard[++row][col] = num++;
        		if(row + col +2 == 2*N)
        			break;
        		numsBoard[++row][--col] = num++;
        		dir = -1;
        	}
    		// 해당 방향으로 계속 진행
        	else {
        		// 왼쪽 아래로 이동 중일때
        		if(dir == -1) {
            		numsBoard[++row][--col] = num++;
        		}
        		// 오른쪽 위로 이동 중일때
        		else {
            		numsBoard[--row][++col] = num++;
        		}
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