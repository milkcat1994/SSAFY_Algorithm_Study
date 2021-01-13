import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -행렬 곱셈 순서-
 * 구하고자 하는 구간을 선정하고 해당 구간 내의 구간을 둘로 나누어 계산한다.
 * left, right를 두고 left는 우측에서 왼쪽으로 진행하고, right는 left에서 우측까지 구간을 산정한다.
 * left, right 사이에 idx를 두어 구하고자 하는 구간을 둘로 나누어 각 구간을 연결하는 비용을 산정하고 전체 구간의 비용을 갱신한다.
 * 
 * 메모리 : 16632KB
 * 시간 : 244ms
 * 풀이 시간 : 2H
 */

//출처 : https://www.acmicpc.net/problem/11049
public class Main_B_G3_11049_행렬곱셈순서 {
	static int N;

	// 행렬 정보 저장 -> 0:행, 1:열
	static int[][] matrix;
	// 최소 횟수 저장
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.println(solve());
	}
	
	static int solve() {
		// 각 행렬 연결 비용
		int chainValue;
		
		// 가장 우측에서부터 왼쪽으로 범위 변경해나가며 진행
		for(int left=N-1; left>=0; --left) {
			// 구하고자 하는 범위를 설정
			for(int right=left+1; right<N; ++right) {
				int min = Integer.MAX_VALUE;
				
				// 범위 내부에서 구간을 설정하는 기준점
				for(int idx=left; idx<right; ++idx) {
					// 각 구간을 연결할 비용 산정
					chainValue = matrix[left][0]* matrix[idx][1] * matrix[right][1];
					// 해당 구간에 되한 최소값 선정
					min = Math.min(min, dp[left][idx] + dp[idx+1][right] + chainValue);
				}
				// 해당 구간의 최소값 입력
				dp[left][right] = min;
			}
		}
		
		return dp[0][N-1];
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		dp = new int[N][N];
		matrix = new int[N][2];
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			matrix[i][0] = Integer.parseInt(st.nextToken());
			matrix[i][1] = Integer.parseInt(st.nextToken());
		}
	}
}
