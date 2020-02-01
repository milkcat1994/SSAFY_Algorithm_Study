package com.ssafy;

import java.util.Scanner;

//http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=795&sca=2020
class Main_J_1523_별삼각형1_양지용 {
    public static void main(String[] args) throws Exception {
        
    	Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        // n <=100 자연수 1<= m <= 3 자연수
        int height = sc.nextInt();
        int sort = sc.nextInt();

        if(height<0 || height>100 || sort<1 || sort>3){
            System.out.println("INPUT ERROR!");
        }
        else {
            switch (sort) {
                case 1:
                for(int row = 0; row < height; ++row){
                    for(int col = 0; col <= row; ++col){
                        sb.append("*");
                    }
                    sb.append("\n");
                }
                break;
            
                case 2:
                for(int row = 0; row < height; ++row){
                    for(int col = 0; col < height-row; ++col){
                        sb.append("*");
                    }
                    sb.append("\n");
                }
                break;

                // case 3
                default:
                for(int row = 0; row < height; ++row){
                    for(int col = 0; col < height-row-1; ++col){
                        sb.append(" ");
                    }
                    for(int col = 0; col < 2*row +1; ++col){
                        sb.append("*");
                    }
                    sb.append("\n");
                }
                break;
            }
            System.out.println(sb.toString());
        }
        sc.close();
    }
}