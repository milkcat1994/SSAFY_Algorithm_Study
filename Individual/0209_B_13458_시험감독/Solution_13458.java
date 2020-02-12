import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -시험 감독-
 * 1. 배열에 각 시험장의 사람 수를 입력받아 배열에 저장한다.
 * 2. 총 시험감독관이 볼 수 있는만큼 해당 배열에서 제외시켜주고
 * 3. 아직 부족하다면 부감독관을 더해준다.
 * └──> 사람수/부감독 와 사람수%부감독>0이면 +1을 해주어 총 필요한 감독관 수를 구한다.
 */

// 출처 : https://www.acmicpc.net/problem/13458
public class Solution_13458 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// N(1 ≤ N ≤ 1,000,000)
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());  // Ai (1 ≤ Ai ≤ 1,000,000)
		for(int i = 0; i < N; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());	// (1 ≤ B, C ≤ 1,000,000)
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
	
		// 10^12승까지 저장 필요
		Long result = 0L;
		for(int i = 0; i < N; ++i) {
			//총 시험감독관이 볼 수 있는 만큼 제외
			arr[i] -= B;
			result++;
			//총 시험감독관 만으로도 충분 할경우 다음 시험장 확인
			if(arr[i] <= 0)
				continue;
			
			//부감독관으로 나눈 나머지가 0이 아니라면 +1
			if(arr[i]%C != 0)
				result++;
			//부감독관이 필요한 수만큼 더해주기
			result += arr[i]/C;
		}
		System.out.println(result);
	}
}
