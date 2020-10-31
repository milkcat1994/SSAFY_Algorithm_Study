import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * -텀 프로젝트-
 * 현재 지나가는 값은 set에 담기
 * set에 담겨있는 값으로 이동했다면 cycle이 있으므로 stack에서 거꾸로 빼면서 cycle 크기 확인
 * 이미 방문 한 값이면 볼 필요 없으므로 set, stack 초기화 이후 다음 값 확인 
 */

//출처 : https://www.acmicpc.net/problem/9466
public class Main_B_G4_9466_텀프로젝트 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int T = Integer.parseInt(st.nextToken());
		for(int tc=0; tc<T; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int[] arr = new int[N];
			boolean[] isVisited = new boolean[N];
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<N; ++i)
				arr[i] = Integer.parseInt(st.nextToken())-1;
			
			int ti, res=0, cnt=0;
			Stack<Integer> stack = new Stack<>();
			Set<Integer> set = new HashSet<>();
			
			for (int idx= 0; idx< N; ++idx) {
				cnt=0;
				ti=idx;
				if(isVisited[ti] || isVisited[arr[ti]]) continue;
				set.add(ti);
				stack.push(ti);
				isVisited[ti]=true;
				
				while(true) {
					ti = arr[ti];
					if(set.contains(ti)) {
						while(!stack.isEmpty()) {
							cnt++;
							if(stack.pop() == ti) {
								res+=cnt;
								break;
							}
						}
						stack.clear();
						set.clear();
						break;
					}

					if(isVisited[ti]) {
						stack.clear();
						set.clear();
						break;
					}
					set.add(ti);
					stack.push(ti);
					isVisited[ti]=true;
				}
			}
			System.out.println(N-res);
		}
	}
}
