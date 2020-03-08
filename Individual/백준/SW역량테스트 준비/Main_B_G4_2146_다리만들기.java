import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -다리만들기-
 * 1. BFS통한 구역을 숫자로 표시하여 나눔
 * 2. 해당 나눈 구역을 미리 List에 넣어두고 모든 List돌며 BFS진행
 * 3. 가장 짧게 도달하는 시간을 비교, 갱신
 */

//출처 : https://www.acmicpc.net/problem/2146
public class Main_B_G4_2146_다리만들기 {
	static int N;
	static int[][] board;
	static boolean[][] isVisited;
	
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		board = new int[N][N];
		isVisited = new boolean[N][N];
		for (int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < N; ++col) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		int isLandCount = 1,cr,cc,nr,nc;
		Queue<int[]> que = new LinkedList<>();
		ArrayList<Queue<int[]>> isLandList = new ArrayList<Queue<int[]>>();
		isLandList.add(new LinkedList<int[]>());
		
		int[] tIntArr;
		//구역 나누기
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				//방문 하지 않은 곳이며, 섬이라면 해당 섬 BFS통해 다른 숫자로 칠할것임.
				if(board[row][col] == 1 && !isVisited[row][col]) {
					isLandList.add(new LinkedList<int[]>());
					
					que.offer(new int[] {row,col});
					isVisited[row][col] = true;
					board[row][col] = isLandCount;
					isLandList.get(isLandCount).add(new int[] {row,col});
					
					while(!que.isEmpty()) {
						tIntArr = que.poll();
						cr = tIntArr[0];	cc = tIntArr[1];
						for(int dir = 0; dir < 4; ++dir) {
							nr = cr+drow[dir];	nc = cc+dcol[dir];
							if(isOut(nr,nc) || board[nr][nc] != 1 || isVisited[nr][nc]) continue;
							
							que.offer(new int[] {nr,nc});
							isVisited[nr][nc] = true;
							board[nr][nc] = isLandCount;
							isLandList.get(isLandCount).add(new int[] {nr,nc});
						}
					}
					isLandCount++;
				}
			} //end for(col)
		}
		initVisited();
		
		int time,qSize,baseInt;
		int minResult = 10000;
		for(Queue<int[]> tList : isLandList) {
			if(tList.isEmpty()) continue;
			baseInt = board[tList.peek()[0]][tList.peek()[1]];
			time = 0;
			//가장 가까운 섬 만날때까지
			W:while(!tList.isEmpty()) {
				qSize = tList.size();
				while(--qSize >= 0) {
					tIntArr = tList.poll();
					cr = tIntArr[0];	cc = tIntArr[1];
					
					for(int dir = 0; dir < 4; ++dir) {
						nr = cr+drow[dir];	nc = cc+dcol[dir];
						if(isOut(nr,nc) || board[nr][nc] == baseInt || isVisited[nr][nc]) continue;
						//다른 섬을 찾았을 때 이다.
						if(board[nr][nc] != 0) {
							if(!tList.isEmpty())
								tList.remove();
							initVisited();
							minResult = minResult < time ? minResult : time;
							break W;
						}
						tList.offer(new int[] {nr,nc});
						isVisited[nr][nc] = true;
					}
					
				} //end while(--qsize>=0)
				time++;
			}
		}
		System.out.println(minResult);
	}
	
	public static void initVisited() {
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				isVisited[row][col] = false;
			}
		}
	}
	
	public static boolean isOut(int row, int col) {
		if(row<0 || col<0 || row>=N || col>=N)
			return true;
		return false;
	}
}
