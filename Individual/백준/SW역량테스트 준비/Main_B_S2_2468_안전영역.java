import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * -안전영역-
 * 1. 모든 높이 정보 Set으로 저장하고 해당 높이보다 낮은 구역을 BFS로 확인한다.
 */

//출처 : https://www.acmicpc.net/problem/2468
public class Main_B_S2_2468_안전영역 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N=Integer.parseInt(br.readLine());
		
		int[][] board = new int[N][N];
		boolean[][] isVisited=new boolean[N][N];
		//모든 높이 저장할 Set
		Set<Integer> set = new HashSet<>();
		for (int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < N; ++col) {
				board[row][col] = Integer.parseInt(st.nextToken());
				set.add(board[row][col]);
			}
		}
		
		Queue<int[]> que = new LinkedList<>();
		int[] drow= {-1,1,0,0};
		int[] dcol= {0,0,-1,1};
		int nr,nc, tRes,maxRes=0;
		int[] tia;
		for(Integer h : set) {
			tRes=0;
			for (int row = 0; row < N; ++row) {
				for (int col = 0; col < N; ++col) {
					//방문했거나 잠기는 부분이라면 보지 않는다.
					if(isVisited[row][col] || board[row][col]<h) continue;
					tRes++;
					que.offer(new int[] {row,col});
					isVisited[row][col]=true;
					while(!que.isEmpty()) {
						tia=que.poll();
						for(int dir = 0; dir < 4; ++dir) {
							nr=tia[0]+drow[dir];	nc=tia[1]+dcol[dir];
							//나가거나, 방문했거나 해당 높이보다 낮은 구역은 잠긴다.
							if(nr<0 || nc<0 || nr>=N || nc>=N || isVisited[nr][nc] || board[nr][nc]<h) continue;
							que.offer(new int[] {nr,nc});
							isVisited[nr][nc]=true;
						}
					}
				}
			}
			//visit 배열 초기화
			for(int i=0; i<N; ++i)
				Arrays.fill(isVisited[i], false);
			//최대 구역 개수 갱신
			if(maxRes<tRes) maxRes=tRes;
		}
		System.out.print(maxRes);
	}
}