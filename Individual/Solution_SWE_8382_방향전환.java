import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -방향 전환-
 * 1. BFS이용
 * 2. 이전 움직임이 세로, 가로 임에 따라 다음 움직임 결정
 * 3. 방문관리는 가로,세로 따로 관리
 */

/*
 * 메모리 : 97724KB 
 * 시간 : 370ms 
 * 코드길이 : 2409B 
 * 소요시간 : 40M
 */

//100P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWyNQrCahHcDFAVP
public class Solution_SWE_8382_방향전환 {
	public static Point sp, ep;
	//exhv : 세로_0, 가로_1
	public static class Point {
		int row, col, exhv;
		Point(int row, int col, int exhv){
			this.row = row;
			this.col = col;
			this.exhv = exhv;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		//상,좌,하,우,상
		int[] drow = {-1,0,1,0,-1};
		int[] dcol = {0,-1,0,1,0};
		
		boolean[][][] isVisited = new boolean[201][201][2];
		for(int t = 1; t <= T; ++t) {
			st = new StringTokenizer(br.readLine(), " ");
			sp = new Point(Integer.parseInt(st.nextToken())+100,Integer.parseInt(st.nextToken())+100, -1);
			ep = new Point(Integer.parseInt(st.nextToken())+100,Integer.parseInt(st.nextToken())+100, -1);
			
			Queue<Point> que = new LinkedList<>();
			if(isFind(sp.row, sp.col)) {
				sb.append('#').append(t).append(' ').append(0).append('\n');
				continue;
			}
			
			Point tp;
			int cr,cc,chv, nr,nc,hv, qs, time=1;
			cr=sp.row;	cc=sp.col;
			for(int dir = 0; dir <= 4; ++dir){
				nr=cr+drow[dir];	nc=cc+dcol[dir];	hv=dir%2;
				if(isOut(nr,nc)) continue;
				isVisited[nr][nc][hv] = true;
				que.offer(new Point(nr,nc,hv));
			}
			
			W:while(!que.isEmpty()) {
				qs = que.size();
				while(--qs>=0) {
					tp = que.poll();
					//exhv : 세로_0, 가로_1
					cr=tp.row;	cc=tp.col;	chv=tp.exhv;
					if(isFind(cr,cc)) {
						sb.append('#').append(t).append(' ').append(time).append('\n');
						break W;
					}
					//이전 이동이 세로, 가로 임에 따라 다르게 이동
					for(int dir = chv+1; dir <= 4; dir+=2){
						nr=cr+drow[dir];	nc=cc+dcol[dir];	hv=dir%2;
						if(isOut(nr,nc) || isVisited[nr][nc][hv]) continue;
						isVisited[nr][nc][hv] = true;
						que.offer(new Point(nr,nc,hv));
					}
				}
				time++;
			} //end while(!que.isEmpty())
			
			for (int row = 0; row <= 200; ++row) {
				for (int col = 0; col <= 200; ++col) {
					isVisited[row][col][0]=false;
					isVisited[row][col][1]=false;
				}
			}
		} //end TestCase
		System.out.print(sb.toString());
	}
	
	public static boolean isFind(int row, int col) {
		if(row==ep.row && col==ep.col)
			return true;
		return false;
	}
	
	public static boolean isOut(int row, int col) {
		if(row<0 || col<0 || row>200 || col>200)
			return true;
		return false;
	}
}
