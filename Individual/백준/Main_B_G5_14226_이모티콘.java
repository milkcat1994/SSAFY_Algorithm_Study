import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * -이모티콘-
 */

//출처 : https://www.acmicpc.net/problem/14226
public class Main_B_G5_14226_이모티콘 {
	static int N, N_2;
	static boolean[][] isVisited;
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.print(bfs());
	}
	
	static int bfs() {
		Queue<Emo> que = new LinkedList<>();
		que.offer(new Emo(1, 0));
		isVisited[1][0] = true;
		
		int size, time=0;
		Emo te;
		while(!que.isEmpty()) {
			size = que.size();
			while(--size >= 0) {
				te = que.poll();
				if(te.cur == N) return time;
				
				if(!isVisited[te.cur][te.cur]) {
					que.offer(new Emo(te.cur, te.cur));
					isVisited[te.cur][te.cur] = true;
				}
				
				if(te.cur+te.clip <= N_2 && !isVisited[te.cur+te.clip][te.clip]) {
					que.offer(new Emo(te.cur+te.clip, te.clip));
					isVisited[te.cur+te.clip][te.clip] = true;
				}
				
				if(te.cur-1 > 0 && !isVisited[te.cur-1][te.clip]) {
					que.offer(new Emo(te.cur-1, te.clip));
					isVisited[te.cur-1][te.clip] = true;
				}
			}
			time++;
		}
		return -1;
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		N_2 = 2*N;
		isVisited = new boolean[N_2+2][N_2+2];
	}
	
	static class Emo {
		int cur, clip;
		Emo(int cur, int clip){
			this.cur = cur;
			this.clip = clip;
		}
	}
}
