import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -부분합-
 * 
 * 투포인터 문제
 * 
 * 풀이시간 : 25M
 * 메모리 : 24264KB
 * 시간 : 336ms
 */

//출처 : https://www.acmicpc.net/problem/1806
public class Main_B_G4_1806_부분합 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N+1];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; ++i)
			arr[i] = Integer.parseInt(st.nextToken());
		
		int s=0;
		int e=0;
		int minLength = Integer.MAX_VALUE;
		int sum = arr[s];
		while(e<N) {
			// 합이 작다면 크게 만들기 위해 e증가
			if(sum < S) {
				// e가 마지막이라면 중단
				if(e==N)
					break;
				
				// e증가 후 sum 증가
				e++;
				sum+=arr[e];
			}
			// 합이 같거나 크다면 작게 만들기 위해 s증가
			else {
				// 해당 길이 저장
				minLength = Math.min(minLength, e-s);
				
				// 가장 최소 길이이기에 빠져나오기
				if(s==e)
					break;
				
				// sum 빼고 s증가
				sum-=arr[s];
				s++;
			}
		}
		
		System.out.println(minLength == Integer.MAX_VALUE ? 0 : minLength+1);
	}
}
