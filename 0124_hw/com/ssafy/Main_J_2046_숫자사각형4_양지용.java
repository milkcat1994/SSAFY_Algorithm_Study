package com.ssafy;

import java.util.Scanner;

//http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1316&sca=2010
class Main_J_2046_숫자사각형4_양지용 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int N = sc.nextInt();
        final int TYPE = sc.nextInt();
        // StringBuilder를 이용한 한번에 출력    
        StringBuilder sb = new StringBuilder();
        
        switch (TYPE) {
		case 1:
			for(int row = 1; row <= N; ++row){
	        	for(int col = 1; col <= N; ++col){
	        		sb.append(row + " ");
	        	}
	        	sb.append("\n");
	        }
			break;
		case 2:
			for(int row = 1; row <= N; ++row){
	        	for(int col = 1; col <= N; ++col){
	        		sb.append(col + (N+1 -2*col)*((row-1)%2) + " ");
	        	}
	        	sb.append("\n");
			}
			break;
		case 3:
			for(int row = 1; row <= N; ++row){
	        	for(int col = 1; col <= N; ++col){
	        		sb.append(col*row + " ");
	        	}
	        	sb.append("\n");
			}
			break;
		default:
			break;
		}
        System.out.println(sb.toString());
        sc.close();
    }
}