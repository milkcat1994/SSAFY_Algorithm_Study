import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -ACM Craft-
 * 1. BFS위상 정렬을 이용한 풀이
 * 2. 해당 건물이 지어지기에 필요한 '전처리'의 개수 저장 필요
 * 3. 해당 건물이 지어짐으로 인해 지어질 수 있는 건물간의 관계 'edge'정보 필요
 * 4. 처음엔 '전처리'가 아예 없는 건물만 Queue에 넣는다.
 * 5. 이후 해당 건물과 이어진 건물에 대한 시간 갱신을 하고
 * 6. '전처리' 개수가 0이 된다면 Queue에 넣는다.
 * 7. 이를 반복하다 목적 건물이 Queue에서 나올때 해당 건물의 시간을 출력한다.
 */

//출처 : https://www.acmicpc.net/problem/1005
public class Main_B_G3_1005_ACMCraft {
	static final int BUILD_MAX = 1001;
	
	static public class Vertex {
		int v,w;
		Vertex(int v, int w){
			this.v = v;
			this.w = w;
		}
	}
			
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		//건물 max
		int[] timeTable = new int[BUILD_MAX];
		int[] needToEx;
		List<Vertex>[] edge;
		Vertex[] vertexArr;
		int N,K;
		for(int t = 0; t < T; ++t) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			needToEx = new int[N+1];
			edge = new ArrayList[N+1];
			vertexArr = new Vertex[N+1];
			
			//건물 개수만큼 해당 건물의 건설시간 get
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 1; i <= N; ++i) {
				vertexArr[i] = new Vertex(i,Integer.parseInt(st.nextToken()));
				timeTable[i] = 0;
				needToEx[i] = 0;
				edge[i] = new ArrayList<>();
			}
			
			//건물의 상관 관계 get (전제건물, 완성건물)
			int s,e;
			Vertex tv;
			for(int i = 0; i < K; ++i) {
				st = new StringTokenizer(br.readLine()," ");
				s = Integer.parseInt(st.nextToken());
				e = Integer.parseInt(st.nextToken());
				tv = new Vertex(s, timeTable[s]);
				needToEx[e]++;
				edge[s].add(vertexArr[e]);
			}
			int endIndex = Integer.parseInt(br.readLine());
			
			Queue<Vertex> que = new LinkedList<>();
			//전처리 없는 거만 que에 담기
			for(int i = 1; i <= N; ++i) {
				if(needToEx[i] == 0) {
					que.offer(vertexArr[i]);
					//해당 위치 소모량 기입해주기
					timeTable[i] = vertexArr[i].w;
				}
			}
			
			//해당 건물 소요 시간이 적은 것 부터 실행된다.
			while(!que.isEmpty()) {
				tv = que.poll();
				//만약 도착지점의 것이라면 해당 timeTable출력
				if(tv.v == endIndex) {
					sb.append(timeTable[tv.v]).append('\n');
					break;
				}
				
				//해당 vertex에 대한 다음 위치를 다 돌아볼것이다.
				for(Vertex v : edge[tv.v]) {
					//해당 위치에서 전처리 갯수 감소
					needToEx[v.v]--;
					//해당 위치 소모량 max값으로 바꾸어 timeTable에 저장
					if(timeTable[v.v] < timeTable[tv.v]+v.w) {
						timeTable[v.v] = timeTable[tv.v] + v.w;
					}
					//전처리 빌경우에 que에 넣어주기_위상정렬
					if(needToEx[v.v] == 0) {
						que.offer(v);
					}
				}
			}
		} //end for Testcase
		
		System.out.print(sb.toString());
	} //end main
}
