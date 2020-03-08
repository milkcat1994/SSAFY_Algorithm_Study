import java.util.Scanner;
import java.io.FileInputStream;

//https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV13zo1KAAACFAYh&categoryId=AV13zo1KAAACFAYh&categoryType=CODE
class Solution_D2_1204_최빈수구하기
{
	final static int STUDENT_NUMS = 1000;
	final static int GRADE = 100;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
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