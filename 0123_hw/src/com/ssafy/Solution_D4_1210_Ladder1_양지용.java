package com.ssafy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution_D4_1210_Ladder1_양지용 {
    // 총 판 크기
    final static int BOARD_SIZE = 100;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        // 사다리 정보 저장
        // 좌 우 1개씩 늘려 좌 우 확인하는 번거로움 없앰
        int[][] board = new int[BOARD_SIZE][BOARD_SIZE+2];
        // 현재 위치 변수
        int currentRow;
        int currentCol = 0;
        String tempStr;
		for(int test_case = 1; test_case <= 10; test_case++)
		{
            T=Integer.parseInt(br.readLine());
            // 입력을 받아 그에 따른 정보 저장하는 곳.
            for(int row = 0; row < BOARD_SIZE; ++row){
                tempStr = br.readLine();
                for(int col = 1; col < BOARD_SIZE+1; ++col){
                    // 해당 위치가 0(빈공간)이라면 false 저장
                    board[row][col] = tempStr.charAt((col-1)*2);
                }
            }
            //end for
            currentRow = BOARD_SIZE - 1;
            for(int col = 1; col < BOARD_SIZE+1; ++col){
                if(board[currentRow][col] == '2'){
                    currentCol = col;
                    break;
                }
            }

            // 이전 이동 방식 기억 필요
            // 반복을 돌아 row가 0일때까지 돔.
            while(currentRow != 0){
                // 갈 수 있는 방향으로 이동
                //가장 왼쪽이 아니며, 왼쪽의 값이 true라면 왼쪽 이동
            	if(board[currentRow][currentCol-1] == '1'){
	            	do{
	            		--currentCol;
	            	}while(board[currentRow][currentCol-1] == '1');
            	}
            	else if(board[currentRow][currentCol+1] == '1'){
            		// 가장 오른쪽이 아니며, 오른쪽의 값이 true라면 오른쪽 이동
            		do{
                		++currentCol;
            		}while(board[currentRow][currentCol+1] == '1');
            	}
                // 이동 마쳤다면 위로 한칸 올라가기
            	--currentRow;
            }
            // 좌 우 공백에 따른 -1
            System.out.println("#"+T+" "+--currentCol);
        }
    }
}