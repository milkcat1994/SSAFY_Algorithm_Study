import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -수영장-
 * 1. 각 월별로 4가지 방법 모두 사용해보는 완전탐색 이용
 * 2. 해당 월의 횟수가 0일경우 다음달로 그냥 넘어가기
 */

//200P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpFQaAQMDFAUq
public class Solution_SWE_1952_수영장 {
	static int[] needMoney;
	static final int DAY	= 0;
	static final int MONTH	= 1;
	static final int TMONTH = 2;
	static final int YEAR	= 3;
	static int[] monthWork;
	static int minResult;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		needMoney = new int[4];		//1일권,1달권,3달권,1년권
		monthWork = new int[13];	//1~12월
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; ++t) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < 4; ++i)
				needMoney[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 1; i < 13; ++i)
				monthWork[i] = Integer.parseInt(st.nextToken());
			
			minResult = needMoney[YEAR];
			dfs(1, 0);
			sb.append('#').append(t).append(' ').append(minResult).append('\n');
		} //end TestCase
		System.out.print(sb.toString());
	}
	
	public static void dfs(int month, int sum) {
		//다 구했을때
		if(month > 12) {
			minResult = minResult < sum ? minResult : sum;
			return;
		}
		
		if(monthWork[month] == 0) dfs(month+1, sum);
		//4개 방식 선택
		else {
			for(int dir = 0; dir < 4; ++dir) {
				switch (dir) {
				case DAY:
					dfs(month+1, sum+monthWork[month]*needMoney[dir]);
					break;
				case MONTH:
					dfs(month+1, sum+needMoney[dir]);
					break;
				case TMONTH:
					dfs(month+3, sum+needMoney[dir]);
					break;
				}
			}
		}
	}
}
