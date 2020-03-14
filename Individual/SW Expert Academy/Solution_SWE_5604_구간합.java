import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -구간합-
 * 백준 책 페이지와 비슷한 유형
 * 1. 앞의 숫자는 뒷자리를 0으로, 뒤의 숫자는 뒷자리를 9로 만들어 주기.
 * Math.pow()를 이용하였더니 double형이라 숫자가 커질경우 오차가 발생함을 알 수 있었음
 * └그리하여 Math.pow()대신 x*=10사용
 */

/*
 * 메모리 : 19692KB 
 * 시간 : 97ms 
 * 코드길이 : 976B 
 * 소요시간 : 1H
 */

//100P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGGNB6cnEDFAUo&
public class Solution_SWE_5604_구간합 {
	static long A,B,sum;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; ++t) {
			st = new StringTokenizer(br.readLine(), " ");
			A = Long.parseLong(st.nextToken());
			B = Long.parseLong(st.nextToken());
			sum=0;
			long x=1;
			while(true) {
				trans(x);
				if(A==B) break;
				
				//	0~9의 합은 45;
				sum+=(B/10-A/10+1)*45*x;
				x*=10;
				A/=10;	B/=10;
			}
			System.out.println("#"+t+" "+ sum);
		} //end TestCase
	}
	
	//숫자 입력받아 해당 자릿수의 합 구하기
	//x는 Math.pow(10,자릿수)이다.
	static void trans(long x) {
		//1의 자리가 0이라면 끝
		while(A%10 != 0) {
			calc(A,x);
			//만약 같아지면 더 볼 필요 없음
			if(A==B)
				return;
			A++;
		}
		
		//1의 자리가 9라면 끝
		while(B%10 != 9) {
			calc(B,x);
			//만약 같아지면 더 볼 필요 없음
			if(A==B)
				return;
			B--;
		}
	}
	
	//A는 늘리기, B는 줄이기
	static void calc(long num, long x) {
		while(num >0) {
			sum+=(num%10)*x;
			num/=10;
		}
		return;
	}
}
