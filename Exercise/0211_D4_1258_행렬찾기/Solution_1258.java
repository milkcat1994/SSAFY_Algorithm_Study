import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * -행렬찾기-
 * 1. 이중 for문 이용하여 배열을 모두 탐색할 것이다.
 * 2. 탐색하던 중 방문하지 않았으며, 해당 좌표값이 1이상이라면 사각형의 정보를 담기 위해 findArea()함수 실행
 * 3. 해당 사각형의 행과 열, 넓이 정보를 저장하였다면 구현한 Comparable을 통해 우선순위 큐에 넣기
 * 4. 해당 구역은 방문했다고 표시해주기
 * 5. 2번 부터 반복
 */

//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV18LoAqItcCFAZN
public class Solution_1258 {
	
	static int N;
	//상하좌우
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	static int[][] board;
	static boolean[][] isVisited;
	
	public static class Area implements Comparable<Area>{
		//행,열, 넓이
		int row,col,sum;
		Area(int row, int col){
			this.row = row;
			this.col = col;
			this.sum = row*col;
		}
		
		@Override
		public int compareTo(Area o) {
			if(this.sum > o.sum)
				return 1;
			else if(this.sum == o.sum) {
				if(this.row > o.row)
					return 1;
				else
					return -1;
			}
			else {
				return -1;
			}
		}
	}
	
	static PriorityQueue<Area> pq = new PriorityQueue<Area>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		
		String tempStr;
		Area ta;
		for(int t = 1; t <= T; ++t) {
			N = Integer.parseInt(br.readLine());
			
			board = new int[N][N];
			isVisited = new boolean[N][N];
			
			for (int row = 0; row < N; ++row) {
				tempStr = br.readLine();
				for (int col = 0; col < N; ++col) {
					board[row][col] = tempStr.charAt(col*2) - '0';
				}
			}
			
			for (int row = 0; row < N; ++row) {
				for (int col = 0; col < N; ++col) {
					// 아직 가보지 않은 곳이라면, 해당 자리 값이 1이상이라면 구역 찾는 함수 실행.
					if(!isVisited[row][col] && board[row][col] > 0) {
						findArea(row,col);
					}
				}
			}
			
			//해당 정보 하나씩 뺄예정
			sb.append("#"+t+" "+pq.size()+" ");
			while(!pq.isEmpty()) {
				ta = pq.poll();
				sb.append(ta.row +" "+ta.col+" ");
			} //end while(!pq.isEmpty())
			sb.append("\n");
			
		} //end TestCase
		
		System.out.println(sb.toString());
	}
	
	//입력받은 row, col값으로 'ㄴ' 자 구간 찾기
	public static void findArea(int r, int c) {
		int br=0,bc=0;
		for (int col = c; col < N; ++col) {
			// 해당 부분 0이라면 row기준 서치로 전환
			if(board[r][col] == 0)
				break;
			//해당 부분 1이상이고 아직 가보지 않은 곳이라면 col기준 서치
			if(!isVisited[r][col]) {
				//끝자리 col값 임시 저장
				bc = col;
			}
		}
		
		for (int row = r; row < N; ++row) {
			// 해당 부분 0이라면 서치 끝
			if(board[row][c] == 0)
				break;
			//해당 부분 1이상이고 아직 가보지 않은 곳이라면 row기준 서치
			if(!isVisited[row][c]) {
				//끝자리 col값 임시 저장
				br = row;
			}
		}
		
		for (int row = r; row <= br; ++row) {
			for (int col = c; col <= bc; ++col) {
				isVisited[row][col] = true;
			}
		}
		
		//해당 행,열, 넓이 정보 넣기
		//br과 r이 같은 경우 1이어야 하기에 +1씩 진행
		pq.offer(new Area(br-r+1,bc-c+1));
	}
}