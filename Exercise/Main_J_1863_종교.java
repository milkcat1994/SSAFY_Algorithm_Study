import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * -종교-
 * 1. union, findSet 사용 문제
 */

//출처 : http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1136&sca=99&sfl=wr_hit&stx=1863
public class Main_J_1863_종교 {
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		Arrays.fill(parents, -1);
		
		int a,b;
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			union(a,b);
		}
		
		int result = 0;
		for(int i = 1; i <= N; ++i) {
			if(parents[i] < 0)
				result++;
		}
		System.out.println(result);
	}
	
	public static boolean union(int a, int b) {
		int ap = findSet(a);
		int bp = findSet(b);
		
		if(ap == bp)
			return false;
		
		parents[bp] = ap;
		return true;
	}
	
	public static int findSet(int a) {
		if(parents[a] < 0)
			return a;
		return parents[a] = findSet(parents[a]);
	}
	
}