import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 이분 그래프라면 YES
 * 아니라면 NO를 출력
 * 
 * 1. 임의 정점에서 시작하여 자신의 색과 다르게 주위를 칠해간다.
 * 2. 만약 도착 정점이 이미 칠 해져 있다면 칠해져야할 색과 비교,
 * 3. 칠해져야 할 색과 같다면 괜찮지만 다르다면 이는 이분그래프가 아니다.
 * 4. 모든 정점을 정상적으로 방문하였다면 이는 이분 그래프이다.
 */

//출처 : https://www.acmicpc.net/problem/1707
public class Main_B_G4_1707_이분그래프 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; ++t) {
			boolean isRight = true;
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			//연결 정보 저장
			List<Integer>[] vList = new LinkedList[V+1];
			//초기 색은 0이다.
			int[] color = new int[V+1];
			
			//모든 vertex에 대해 인접 정점 리스트 생성해두기
			for(int i = 0; i <= V; ++i) {
				vList[i] = new LinkedList<Integer>();
			}
			
			//도착 정점 번호 넣기
			for(int i = 0; i < E; ++i) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				//양방향으로 넣어 확실히 해주기
				vList[u].add(v);
				vList[v].add(u);
			}
			
			Queue<Integer> que = new LinkedList<>();
			
			//현재 vertex의 index
			int cvi;
			//방문 정보
			boolean[] isVisited = new boolean[V+1];
			isVisited[0]=true;

			W:for(int i = 1; i <= V; ++i) {
				if(isVisited[i]) continue;
				//방문 하지 않은 곳 1로 칠하기
				color[i] = 1;
				//해당 정점 넣기
				que.offer(i);
				//방문 표시
				isVisited[i] = true;
				
				//연결된 정점에 대해 BFS시작
				while(!que.isEmpty()) {
					//현재 정점 번호
					cvi = que.poll();
					//현재 정점과 연결된 정점 모두 확인
					for(Integer ti : vList[cvi]) {
						//방문 한 곳이라면 색이 현재 정점과 반대인지 확인
						//que에 ti넣을 필요 없음
						if(isVisited[ti]) {
							//현재 정점과 반대가 아니라면 이는 이분그래프가 아니다.
							if(color[ti] != color[cvi]*-1) {
								isRight = false;
								break W;
							}
						}
						//방문 하지 않은 곳이라면 색은 0이다.
						//현재 정점과 반대 색으로 칠하고 que에 해당 정점 넣어주기
						else {
							color[ti] = color[cvi]*-1;
							isVisited[ti] = true;
							que.offer(ti);
						}
					}
				}
			} //W:for()
			System.out.println(isRight ? "YES" : "NO");
		} //end TestCase
	}
}