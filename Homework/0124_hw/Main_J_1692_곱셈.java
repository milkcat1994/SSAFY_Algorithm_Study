package com.ssafy;

import java.util.Scanner;

//http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=706&sca=2030
class Main_J_1692_곱셈 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] numCount = new int[3];
        
        // 두 개의 자연수
        int x = sc.nextInt();
        int y = sc.nextInt();

        // 두번째 숫자 각자릿수 뽑아내기
        // 1의 자리 ~ 100의 자리
        int num1 = y%10;
        int num2 = (y/10)%10;
        int num3 = y/100;

        System.out.println(x*num1);
        System.out.println(x*num2);
        System.out.println(x*num3);
        System.out.println(x*y);

        sc.close();
    }
}