import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -파일 합치기-
 * 이어진 파일 끼리 합치며 그중 가장 최소비용을 찾아낸다.
 * memo배열을 통해 해당 구간의 합을 미리 저장 시켜둔다.
 * res배열은 해당 구간의 최소 비용이다.
 * 
 * solve 함수 내에서 memo배열과 res배열을 이용하여 구간의 최소 비용을 갱신해나가며,
 * 최종적으로 res[0][N-1]에 총 최소비용이 저장된다.
 * 
 * 메모리 : 29104KB
 * 시간 : 968ms
 * 풀이 시간 : 3H
 */

//출처 : https://www.acmicpc.net/problem/11066
public class Main_B_G3_11066_파일합치기 {
	static int TC, N;
	static int[] arr;
	
	// 구간 최소 합 저장하는 배열
	static int[][] memo;
	// 해당 구간 최소비용 저장 배열
	static int[][] res;
	static BufferedReader br;
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		TC = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while(--TC >= 0) {
			init();
			
			solve(0, N-1);
			
			sb.append(res[0][N-1]+"\n");
		}
		System.out.print(sb.toString()); 
	}
	
	// 반환값은 해당 구간 숫자 합
	static int solve(int s, int e) {
		// 이미 있는 정보라면 가져오기
		if(memo[s][e] != 0)
			return memo[s][e];
		
		// 합치지 않을 경우 구간 숫자 합을 저장하지 않는다.
		if(s == e) 
			return memo[s][e] = arr[s];
		
		// 최소 비용, 왼쪽 숫자 합, 우측 숫자합
		int min=Integer.MAX_VALUE, l,r;
		
		// 구간별 계산 실행
		for(int left=s; left < e; ++left) {
			l = solve(s, left);
			
			r = solve(left+1, e);
			
			// 해당 구간에서의 최소 비용을 골라낸다.
			if(min > res[s][left] + res[left+1][e] + l+r) {
				//갱신 시 res에 최소 비용을 저장한다.
				res[s][e] = res[s][left] + res[left+1][e] + l+r;
				min = res[s][e];
			}
		}
		
		return memo[s][e] = memo[s][s] + memo[s+1][e];
	}
	
	// 변수 초기화 함수
	static void init() throws Exception {
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		memo = new int[N][N];
		res = new int[N][N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; ++i)
			arr[i] = Integer.parseInt(st.nextToken());
	}
}
