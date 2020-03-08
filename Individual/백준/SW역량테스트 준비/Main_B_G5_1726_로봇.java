import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * -로봇-
 * 1. 3차원 배열 이용한 위치, 방향의 방문 표시
 * 2. 우선순위 큐 이용한 가장 작은 이동 방법 부터 처리
 * 3. 방향 전환, 기존 방향 변환 함수 이용 변환
 * 4. 방문하지 않은 방법만 넣어주기
 * 5. 벽이거나 나갔다면 해당 방향 진행 취소
 */

//출처 : https://www.acmicpc.net/problem/1726
public class Main_B_G5_1726_로봇 {
	static int ROW,COL;
	static boolean[][][] isVisited;
	static int[][] board;
	static int er,ec;
	
	static class Point implements Comparable<Point> {
		int row,col,dir,count;
		Point(int row, int col, int dir, int count) {
			this.row = row;
			this.col = col;
			this.dir = dir;
			this.count = count;
		}
		@Override
		public int compareTo(Point o) {
			return this.count - o.count;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		ROW = Integer.parseInt(st.nextToken());
		COL = Integer.parseInt(st.nextToken());
		
		isVisited = new boolean[ROW][COL][4];
		board = new int[ROW][COL];
		
		String tempStr;
		for (int row = 0; row < ROW; ++row) {
			tempStr = br.readLine();
			for (int col = 0; col < COL; ++col) {
				switch (tempStr.charAt(col*2)) {
				//벽 표시
				case '1':
					board[row][col] = -1;
					break;
				}
			}
		}
		
		PriorityQueue<Point> que = new PriorityQueue<>();
		//시작 지점
		st = new StringTokenizer(br.readLine(), " ");
		int ed;
		er = Integer.parseInt(st.nextToken())-1;
		ec = Integer.parseInt(st.nextToken())-1;
		ed = conVertDir(Integer.parseInt(st.nextToken()));
		
		que.offer(new Point(er, ec, ed,0));
		isVisited[er][ec][ed] = true;
		board[er][ec] = -1;

		//종료 지점
		st = new StringTokenizer(br.readLine()," ");
		er = Integer.parseInt(st.nextToken())-1;
		ec = Integer.parseInt(st.nextToken())-1;
		ed = conVertDir(Integer.parseInt(st.nextToken()));
		
		//상우하좌
		int[] drow = {-1,0,1,0};
		int[] dcol = {0,1,0,-1};
		
		Point tp;
		int cr,cc,cd,ct, nr,nc,nd,nt;
		int minRes = Integer.MAX_VALUE, tempRes;
		while(!que.isEmpty()){
			tp = que.poll();
			cr = tp.row;	cc = tp.col;	cd = tp.dir;	ct = tp.count;
			if(isFind(cr, cc)) {
				tempRes = ct+getDirCount(cd,ed);
				minRes = minRes < tempRes ? minRes : tempRes;
			}
			
			//4방향 탐색, 1,2,3칸 이동
			for(int i = 0; i < 4; ++i) {
				nd = i;
				for(int j = 1; j <= 3; ++j) {
					nr = cr+drow[nd]*j;	nc = cc+dcol[nd]*j;
					//벽이거나 나갈경우 pass
					if(isOut(nr, nc) || board[nr][nc] == -1) break;
					//현재 방향, 이전 방향 비교해서 count 증가
					//이동은 1번 이동으로 치기
					nt = ct+getDirCount(cd, nd)+1;
					//방문 하지 않은 방법이라면 넣어주기
					if(!isVisited[nr][nc][nd]) {
						isVisited[nr][nc][nd] = true;
						que.offer(new Point(nr,nc,nd,nt));
					}
				}
			}
		} //end while(!que.isEmpty())
		System.out.println(minRes);
	} //end main
	
	public static int conVertDir(int dir) {
		//방향 : 동서남북 -> 우좌하상
		switch (dir) {
		case 1:	//1 : 우
			return 1;
		case 2:	//3 : 좌
			return 3;
		case 3:	//2 : 하
			return 2;
		case 4:	//0 : 상
		default :
			return 0;
		}
	}
	
	//방향 차이 구하는 함수
	public static int getDirCount(int cd, int nd) {
		int res = Math.abs(cd-nd);
		switch (cd) {
		case 0:
		case 3:
			if(res == 3) res = 1;
		}
		return res;
	}
	
	public static boolean isFind(int row, int col) {
		if(row == er && col == ec)
			return true;
		return false;
	}
	
	public static boolean isOut(int row, int col) {
		if(row<0 || col<0 || row>=ROW || col>=COL)
			return true;
		return false;
	}
}