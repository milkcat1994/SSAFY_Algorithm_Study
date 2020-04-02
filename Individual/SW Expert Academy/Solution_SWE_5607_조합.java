import java.util.Scanner;

/*
 * -[Professional] 조합-
 * 1. 페르마의 소정리 이용하여 풀이
 * 2. nCr의 각 팩토리얼 부분을 %mod 한 값을 저장.
 * 3. 분모를 하나의 값으로 정리 -> (n2*r1) % mod
 * 4. 1/a %mod = a^p-2 %mod 이용하여 a^p-2형태로 변형
 * 5. 분할 정복 이용하여 지수승 값 구하기
 */

/*
 * 메모리 : 29208KB 
 * 시간 : 264ms 
 * 코드길이 : 848B 
 * 소요시간 : 1H 30M
 */

//100P

//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGKdbqczEDFAUo
public class Solution_SWE_5607_조합 {
	static final long mod = 1234567891;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t) {
			int N = sc.nextInt();
			int R = sc.nextInt();
			
			long n1=1,n2=1,r1=1;
			//n!, (n-r)!, r! 에 대한 %p 값 구해서 저장하기
			long cur = 1;
			for(long a = 1; a <= N; ++a) {
				cur *= a;
				cur %= mod;
				if(a == N) n1 = cur;
				if(a == N-R) n2 = cur;
				if(a == R) r1 = cur;
			}
			
			// 분모 정리
			long r2 = (n2*r1) % mod;
			//페르마의 소정리 이용하여 값 구하기
			long res  = (n1 * cal(r2, mod-2)) %mod;
			
			System.out.println("#"+t+" "+res);
		}
	}
	
	static long cal(long a, long b) {
		if(b == 1)
			return a;
		// 지수 b가 2로 나누어 떨어질때
		if(b%2 == 0) {
			long temp = cal(a, b/2) % mod;
			return (temp*temp)%mod;
		}
		else {
			long temp = cal(a, (b-1)/2) % mod;
			return (((temp*temp)%mod)*a)%mod;
		}
	}
}
