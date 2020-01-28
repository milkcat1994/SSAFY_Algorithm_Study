package p0122;

import java.util.Scanner;
import java.io.FileInputStream;

//https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV13zo1KAAACFAYh&categoryId=AV13zo1KAAACFAYh&categoryType=CODE
/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
//Solution_D2_1204_최빈수 구하기_양지용
//1204-[S/W 문제해결 기본] 1일차 - 최빈수 구하기
class Solution_D2_1204_최빈수구하기_양지용
{
	final static int STUDENT_NUMS = 1000;
	final static int GRADE = 100;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/
		int max;
		int result;
//		점수들의 갯수를 저장할 배열을 선언한다.
//		0~100점이므로 101개의 공간 생성
		int[] array = new int[GRADE+1];
		for(int test_case = 1; test_case <= T; test_case++)
		{
//			학생 
//			숫자들을 입력받아 해당 숫자의 자리에 1을 더해준다.
			for(int i = 0; i <= STUDENT_NUMS; ++i) {
				array[sc.nextInt()]++;
			}

			max = 0;
			result = 0;
			
//			max값과 비교하여 가장 많이 나온 숫자를 찾는다.
			for(int i = 0; i <= GRADE; ++i) {
				if(max <= array[i]) {
					max = array[i];
					result = i;
				}
			}
			
			System.out.printf("#%d %d\n", test_case, result);
//			배열 다시 초기화
			for(int i = 0; i <= GRADE; ++i)
				array[i] = 0;
		}
		sc.close();
    }
}