import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -스타트 택시-
 * 1H 20M
 * 목적지가 같을 수도 있음.
 * ++ 거리가 같다면 행이 작은 것, 행이 같다면 열이 작은 것.
 */

//출처 : https://www.acmicpc.net/problem/19238
public class Main_B_G4_19238_스타트택시 {
	static int N,M,O;
	static Taxi taxi;
	static Board[][] board;
	static int[][] person;
	static boolean[][] isVisited;
	
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.println(find() ? taxi.o : -1);
	}
	
	// 초기화 함수
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		O = Integer.parseInt(st.nextToken());
		
		taxi = new Taxi(O);
		isVisited = new boolean[N][N];
		board = new Board[N][N];
		for (int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < N; ++col) {
				board[row][col] = new Board();
				if(Integer.parseInt(st.nextToken()) == 1)
					board[row][col].wall = true;
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		taxi.r = Integer.parseInt(st.nextToken()) - 1;
		taxi.c = Integer.parseInt(st.nextToken()) - 1;
		
		int cr, cc;
		person = new int[M][4];
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			cr = Integer.parseInt(st.nextToken()) - 1;
			cc = Integer.parseInt(st.nextToken()) - 1;
			person[i][0] = cr;
			person[i][1] = cc;
			board[cr][cc].person.n = i;
			
			cr = Integer.parseInt(st.nextToken()) - 1;
			cc = Integer.parseInt(st.nextToken()) - 1;
			person[i][2] = cr;
			person[i][3] = cc;
		}
	}

	// 모든 사람들의 도착점 찾기
	static boolean find() {
		int pn, time=0;
		while(time < M && taxi.o > 0) {
			pn = findPerson(taxi.r, taxi.c);
			initVisited();
			// 모두 도착하지 않았는데 사람을 찾을 수 없으면 false 반환
			if(pn == N*N)
				return false;
			
			board[person[pn][0]][person[pn][1]].person.n = -1;
			
			taxi.move(person[pn][0], person[pn][1]);
			
			// 목적지를 찾아 줄 수 없거나, 연료가 바닥났다면 false 반환
			if(!findGoal(taxi.r, taxi.c, pn))
				return false;
			
			initVisited();

			taxi.move(person[pn][2], person[pn][3]);
			
			time++;
		}
		
		// 택시 연료가 바닥났다면 false 반환 -> 필요 없을지도
		if(taxi.o < 0)
			return false;
		return true;
	}
	
	// 거리 가장 가까운 사람 반환
	static int findPerson(int tr, int tc) {
		if(isPerson(tr, tc)) return board[tr][tc].person.n;
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[]{tr,tc});
		
		int distance = 0, cr,cc,nr,nc, size, personN = N*N;
		int[] tia;
		boolean isFind = false;

		while(!que.isEmpty()) {
			size = que.size();
			while(--size >= 0) {
				tia = que.poll();
				cr=tia[0]; cc=tia[1];
				for(int d = 0; d < 4; ++d) {
					nr=cr+drow[d]; nc=cc+dcol[d];
					if(isOut(nr, nc) || isWall(nr, nc) || isVisited[nr][nc]) continue;
					
					if(isPerson(nr, nc)) {
						isFind = true;
						personN = getMinPerson(personN, board[nr][nc].person.n);
					}
					que.offer(new int[] {nr, nc});
					isVisited[nr][nc] = true;
				}
			}
			distance++;
			if(isFind)
				break;
		}
		taxi.o -= distance;
		return personN;
	}
	
	// 같은 거리에서 행이 작은 -> 같다면, 열이 작은 사람 선택
	static int getMinPerson(int op, int np) {
		if(op == N*N)
			return np;
		if(person[op][0] > person[np][0]) {
			return np;
		}
		else if(person[op][0] < person[np][0]) {
			return op;
		}
		else {
			if(person[op][1] > person[np][1]) {
				return np;
			}
			else {
				return op;
			}
		}
	}
	
	// 해당 사람의 목적지 찾기
	static boolean findGoal(int tr, int tc, int pn) {
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[]{tr,tc});
		
		int distance = 0, cr,cc,nr,nc, size;
		int[] tia;
		
		while(!que.isEmpty()) {
			size = que.size();
			distance++;
			while(--size >= 0) {
				tia = que.poll();
				cr=tia[0]; cc=tia[1];
				for(int d = 0; d < 4; ++d) {
					nr=cr+drow[d]; nc=cc+dcol[d];
					if(isOut(nr, nc) || isWall(nr, nc) || isVisited[nr][nc]) continue;
					
					if(isGoal(nr, nc, pn)) {
						taxi.o -= distance;
						// 도착시 택시 연료가 없다면 실패 반환
						if(taxi.o < 0)
							return false;
						
						fillOil(distance);
						return true;
					}
					que.offer(new int[] {nr, nc});
					isVisited[nr][nc] = true;
				}
			}
		}
		return false;
	}
	
	// 이동 거리 2배만큼 연료 채우기
	static void fillOil(int distance) {
		taxi.o += distance*2;
	}
	
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=N || c>=N)
			return true;
		return false;
	}
	
	static boolean isWall(int r, int c) {
		if(board[r][c].wall)
			return true;
		return false;
	}
	
	static boolean isPerson(int r, int c) {
		if(board[r][c].person.n != -1)
			return true;
		return false;
	}
	
	// 해당 사람의 목적지와 넘겨받은 행,열 값이 같은지
	static boolean isGoal(int r, int c, int pn) {
		if(person[pn][2] == r && person[pn][3] == c)
			return true;
		return false;
	}
	
	static void initVisited() {
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				isVisited[row][col] = false;
			}
		}
	}
	
	// 택시 행,열, 연료
	static class Taxi {
		int r,c;
		long o;
		
		Taxi(int o){
			this.o = o;
		}
		
		// 해당 좌표로 택시 이동
		void move(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	// 해당 위치의 사람 번호 기억, 벽인지 빈칸인지?
	static class Board {
		Person person;
		boolean wall;
		Board(){
			person = new Person();
			wall = false;
		}
	}
	
	static class Person {
		int n;
		Person(){
			this.n = -1;
		}
	}
}
