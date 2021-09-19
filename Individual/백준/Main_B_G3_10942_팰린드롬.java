import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -팰린드롬?-
 */

//출처 : https://www.acmicpc.net/problem/10942
public class Main_B_G3_10942_팰린드롬 {
	static final int NONE = 0;
	static final int NOT = 1;
	static final int PALINDROME = 2;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[] nums;
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.print(solve());
	}
	
	public static String solve() throws Exception {
		StringTokenizer st;
		int[][] memo = new int[N+1][N+1];
		
		int S,E, l,r;
		boolean isPalindrome;
		for(int i=0; i<M; ++i) {
			isPalindrome = true;
			st = new StringTokenizer(br.readLine(), " ");
			S = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			// 지정되지 않았다면 해당 구간 확인
			if(memo[S][E] == NONE) {
				l = (int) Math.floor((double)(S+E)/2);
				r = (int) Math.ceil((double)(S+E)/2);
				
				while(S <= l && r <= E) {
					if(nums[l] != nums[r] || memo[l][r] == NOT) {
						memo[l][r] = NOT;
						isPalindrome = false;
						break;
					}
					memo[l][r] = PALINDROME;
					l--; r++;
				}
			}
			else if(memo[S][E] == NOT) {
				isPalindrome = false;
			}
			
			if(isPalindrome)
				sb.append(1).append('\n');
			else
				sb.append(0).append('\n');
		}
		
		return sb.toString();
	}

	static void init() throws Exception {
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine(), " ");
		nums = new int[N+1];
		for(int i=1; i<=N; ++i) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
	}
	
}
