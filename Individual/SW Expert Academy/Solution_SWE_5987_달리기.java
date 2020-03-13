import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -달리기-
 * 1. 앞에 뽑은 사람 번호가 같다면 뒤의 경우의 수는 같다.
 * 2. 뽑은 사람에 대해 bitMasking이용
 * 3. memo이용하여 중복 계산 제거
 * 4. memo의 경우 현재 선택한 사람에 대한 경우의 수를 가지고 있다.
 */

/*
 * 메모리 : 63312KB 
 * 시간 : 172ms 
 * 코드길이 : 1094B 
 * 소요시간 : 1D
 */

//100P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWaJ4g86WA4DFAUQ
public class Solution_SWE_5987_달리기 {
	static int N,M;
	static int[] needNum;
	static long[] memo;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int u,v;
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; ++t) {
			st = new StringTokenizer(br.readLine(), " ");
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());

			needNum=new int[N];
			memo=new long[1<<N];
			for(int m=0; m<M; ++m) {
				st = new StringTokenizer(br.readLine(), " ");
				u=Integer.parseInt(st.nextToken());
				v=Integer.parseInt(st.nextToken());
				needNum[u-1]|=1<<(v-1);
			}
			System.out.println("#"+t+" "+dfs(0));
		} //end TestCase
	}
	
	//앞번호부터 선택이 아니라 뒷번호 부터 선택해 나갈 것이다.
	static long dfs(int sel) {
		//모두 선택 되었다면 1반환
		if(sel == (1<<N)-1)
			return 1;
		//이미 메모 되어있다면 해당 값 호출
		if(memo[sel]>0)
			return memo[sel];
		
		for(int i=0; i<N;++i) {
			//선택 되었거나 해당 숫자를 넣기 위한 전제조건이 만족 되었는지.
			if((sel&1<<i)>0 || (sel&needNum[i])!=needNum[i]) continue;
			//다음 칸 선택 할 예정
			memo[sel]+=dfs(sel | 1<<i);
		}
		return memo[sel];
	}
}
