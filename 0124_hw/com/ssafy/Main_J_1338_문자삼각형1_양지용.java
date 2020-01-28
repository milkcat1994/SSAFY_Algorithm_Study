package com.ssafy;

import java.util.Scanner;
import java.lang.StringBuilder;

//http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=2074&sca=2010
class Main_J_1338_문자삼각형1_양지용 {
	// 'A'에서 얼마큼 떨어져 있는지 알기 위해 나머지를 이용할 예정
    final static int diffAZ = 'Z' - 'A' + 1;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        // StringBuilder를 이용한 한번에 출력    
        StringBuilder sb = new StringBuilder();
        // 현재 문자 저장 변수
        int curChar;
        // 각 행마다 문자 저장
        for(int row = 0; row < N; ++row) {
        	// '/' 모양이 각 알파벳의 시작이므로 그 이전까지는 공백을 채웢ㅁ
            for(int col = 1; col < N-row; ++col){
                sb.append("  ");
            }

            // 각 행의 시작은 'A'에서 row%diffAZ 만큼 떨어진 값이다.
            curChar = (row)%diffAZ + 'A';
            sb.append((char)curChar + " ");
            
            // row가 한칸씩 내려가며 건너뛰는 수가 늘어난다.
            for(int times = 0; times < row; ++times){
            	// 현재 문자가 'A'에서 떨어진 값 에서 N-1부터 N-1-times까지 반복하여
            	// 'A'부터 떨어진 거리를 구한다.
                curChar = ((curChar-'A' + N-1 -times)%diffAZ +'A');
                sb.append((char)curChar + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
        sc.close();
    }
}