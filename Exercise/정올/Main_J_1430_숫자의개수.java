package com.ssafy;

import java.util.Scanner;

//http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=706&sca=2030
class Main_J_1430_숫자의개수 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] numCount = new int[10];
        
        // 세 개의 자연수
        int x = sc.nextInt();
        int y = sc.nextInt();
        int z = sc.nextInt();
        
        // 결과값 string으로 저장
        String resultStr = Integer.toString(x*y*z);
        
        
        
        int numLength = resultStr.length();
        // charAt이용해 한글자씩 추출
        // 해당 위치(숫자) 갯수 더해주기
        for(int index = 0; index < numLength; ++index)
        	numCount[(int)(resultStr.charAt(index) - '0')]++;
        	
        // counting 된 것 그대로 출력
        for(int count : numCount)
        	System.out.println(count);
        sc.close();
    }
}