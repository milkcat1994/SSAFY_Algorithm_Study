import java.util.Scanner;

/*
 * -설탕 배달-
 * 1. 봉지 종류가 2가지 뿐이므로 가장 큰 5Kg를 먼저 담아본다.
 * 2. 그리고 5로 나누어 떨어지지 않는다면 3을 빼고 다시 반복한다.
 */

// 출처 : https://www.acmicpc.net/problem/2839
public class Main_B_B1_2839_설탕배달 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int t = 0; t<T; ++t) {
			//목표 Mkg
			int M = sc.nextInt();
			//총 사용할 봉지 갯수
			int count = 0;
			//찾을 때까지 반복 할 예정
			while(true) {
				
				//M이 0보다 작아진다면 못찾은것.
				if(M<0) {
					System.out.println(-1);
					break;
				}
				
				//5로 나뉘어 떨어진다면 봉지들로 만들 수 있다.
				if(M%5 == 0) {
					//몫은 5kg 봉지개수
					count += M/5;
					System.out.println(count);
					break;
				}
				//5로 딱 나뉘지 않는다면 3을 빼고 다시 5로 나누어본다.
				else {
					M-=3;
					//3kg 봉지 하나 추가
					count++;
				}
			} //end While
		} //end TestCase
	}
}