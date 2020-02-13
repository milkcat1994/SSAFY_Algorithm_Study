import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * -뱀-
 * 1. 사과 위치정보는 Point좌표만 저장하기 위해 Set으로 관리
 * 2. 지렁이 방향 정보는 해당 시간에 필요한 이동정보 가지기 위해 HashMap으로 관리
 * 3. 지렁이 몸 위치는 사과를 먹지 못할때 꼬리좌표를 삭제 해야하기에 Queue로 관리_FIFO
 * 4. time을 증가시키며 밖으로 나가거나 자신의 몸(Queue)와 곂친다면 게임 중지
 */

//출처 : https://www.acmicpc.net/problem/3190
public class Solution_3190 {
	static int SIZE;
	static int k;
	
	public static class Point {
		int row, col;
		
		Point(int row, int col){
			this.row = row;
			this.col = col;
		}
		
		boolean equals(int row, int col) {
			if(this.row == row && this.col == col)
				return true;
			return false;
		}
	}
	
	static Set<Point> set = new HashSet<Point>();							//사과 위치정보
	static Map<Integer,Character> map = new HashMap<Integer,Character>();	//지렁이 방향정보
	static Queue<Point> queue = new LinkedList<Point>();					//지렁이 몸 위치
	//상우하좌
	static int[] drow = {-1,0,1,0};
	static int[] dcol = {0,1,0,-1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		SIZE = Integer.parseInt(br.readLine());	 //board의 크기(2 ≤ N ≤ 100)
		int K = Integer.parseInt(br.readLine()); //사과의 개수
		
		//사과 입력
		for(int i = 0; i < K; ++i) {
			st = new StringTokenizer(br.readLine());
			set.add(new Point(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1 ));
		}
		
		//지렁이 방향 변환 정보
		int L = Integer.parseInt(br.readLine());
		for(int i = 0; i < L; ++i) {
			st = new StringTokenizer(br.readLine());
			map.put(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
		}
		
		//처음 방향 우측
		//지렁이 첫 좌표는 0,0이다.
		int cr=0,cc=0,nr,nc,dir=1;
		int time = 0;
		queue.offer(new Point(cr,cc));
		while(true) {
			//시간 증가
			time++;
			nr = cr + drow[dir];
			nc = cc + dcol[dir];
			
			//만일 밖으로 나갔다면, 몸이랑 부딪혔다면, 그만움직이기.
			if(isOut(nr,nc) || conflictOwn(nr,nc)) {
				break;
			}

			//이동 가능하므로 다음 좌표 queue에 넣기
			queue.offer(new Point(nr,nc));
			//사과를먹지 못했다면 꼬리 하나 out
			if(!eatApple(nr,nc))
				queue.poll();
			
			//해당 시간 끝나고 이동할 방향 변환이 있다면 시켜주기
			if(map.containsKey(time)) {
				switch (map.get(time)) {
				//오른쪽 90도 : 시계방향
				case 'D':
					dir = (dir+1)%4;
					break;
				//왼쪽 90도 : 반시계방향
				case 'L':
					dir = (dir+3)%4;
					break;
				}
			}
			//머리 위치 이동
			cr = nr;	cc = nc;
		}
		System.out.println(time);
	}
	
	//사과 먹을 경우 해당 set에서 사과 삭제
	public static boolean eatApple(int row, int col) {
		for(Point p : set) {
			if(p.equals(row, col)) {
				set.remove(p);
				return true;
			}
		}
		return false;
	}
	
	//경계선 나갔는지 Check
	public static boolean isOut(int row, int col) {
		if(row < 0 || col < 0 || row >= SIZE || col >= SIZE)
			return true;
		return false;
	}
	
	//자신의 몸과 부딪혔는지 확인
	public static boolean conflictOwn(int row, int col) {
		for(Point p : queue) {
			if(p.equals(row, col))
				return true;
		}
		return false;
	}
}
