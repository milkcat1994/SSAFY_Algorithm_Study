import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -스타트링크-
 * BFS이용 가장 빠른 도착시간 찾기
 */

//출처 : https://www.acmicpc.net/problem/5014
public class Main_B_G5_5014_스타트링크 {
	static int time;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int F,S,G,U,D;
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		if(bfs(F,S,G,U,D))
			System.out.println(time);
		else
			System.out.println("use the stairs");
	}
	
	static boolean bfs(int F, int S, int G, int U, int D) {
		Queue<Integer> que = new LinkedList<>();
		boolean[] isVisited = new boolean[F+1];
		int ti, ni, qsize;
		
		que.offer(S);
		isVisited[S] = true;
		while(!que.isEmpty()) {
			qsize = que.size();
			while(--qsize>=0) {
				ti = que.poll();
				if(G==ti) {
					return true;
				}

				ni = ti+U;
				if(ni<=F && !isVisited[ni]) {
					que.offer(ni);
					isVisited[ni]=true;
				}
				
				ni=ti-D;
				if(ni>0 && !isVisited[ni]) {
					que.offer(ni);
					isVisited[ni]=true;
				}
			}
			time++;
		}
		return false;
	}
}
