import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * -아기상어-
 * 1. 아기상어가 먹을 수 있는 물고기를 BFS를 통해 찾았다.
 * 2. 그 중 가장 왼쪽 위에 있는 물고기는 List를 순회하며 찾았다.
 * 3. 먹었다면 해당 자리로 이동하고 원래 자리는 빈곳으로 만들어준다.
 * 4. 방문여부 배열을 초기화 해주지 않고 .clone해 넣어 메모리를 많이 사용한다.
 * 5. 상어의 행동은 class로 표현하였다.
 */

// 출처 : https://www.acmicpc.net/problem/16236
public class Main_B_G5_16236_아기상어 {
	static int N;
	static char board[][];
	// 아기상어의 이동반경 담을 queue
	static Queue<Point> queue = new LinkedList<Point>();
    // 먹을 수 있는 물고기 담을 list
	static List<Point> flist = new ArrayList<Point>();
	
	//탐색 우선순위 -> 상좌우하
	static int[] drow = {-1, 0, 0, 1};
	static int[] dcol = {0, -1, 1, 0};
	static BabyShark bs;
	static int tempLive;
	
	// 현재 좌표 다음 좌표
	static Point curPoint;
	static int nrow, ncol;
	
	public static class Point {
		int row, col;
		Point(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
	
	public static class BabyShark {
		Point p;
		//needFeed가 0이라면 size증가시키기
		public int size, needFeed, live;
		
		BabyShark(int row, int col, int size){
			this.p = new Point(row, col);
			this.size = size;
			this.needFeed = size;
			this.live = 0;
		}
		
		void setPoint(int row, int col) {
			this.p.row = row;
			this.p.col = col;
		}
		
		// 상어크기 커지면 필요 요구량 증가
		void setSize(int size) {
			this.size = size;
			needFeed = size;
		}
		
		void eat() {
			if(--this.needFeed == 0)
				this.setSize(this.size+1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		// 0:빈칸 , 1~6:물고기크기, 9:아기상어위치
		//배열 초기화
		String tempString;
		board = new char[N+2][N+2];
		boolean[] visitable = new boolean[(N+2)*(N+2)];
		
		//배열 초기화
		for(int row = 1; row <= N; ++row) {
			tempString = br.readLine();
			for(int col = 1; col <= N; ++col) {
				board[row][col] = tempString.charAt((col-1)*2);
				if(board[row][col] == '9')
					bs = new BabyShark(row, col, 2);
				visitable[row*(N+2) + col] = true;
			}
		}
		
		//아기상어가 먹이를 찾아 나선다.
		int result = 0;
		//상어가 먹이를 찾지 못할때 까지
		while(goToFeed(bs.p.row, bs.p.col, visitable.clone())) {
			;
		}
		result = bs.live;
		System.out.println(result);
	}
	
	public static boolean goToFeed(int row, int col, boolean[] visitable) {
		if(!visitable[(N+2)*row + col]) {
			return false;
		}
		boolean isFind = false;
		
		// 4방향 순회하는 bfs작성
		visitable[(N+2)*row + col] = false;
		queue.offer(new Point(row, col));
		int queSize;
		//queue가 빌떄까지 만일 중간에 찾으면 함수 true로 빠져나온다.
		while(!queue.isEmpty()) {
			queSize = queue.size();
			// 같은 depth의 물고기 모두 찾기
			while(--queSize >= 0) {
				//현재 선택된 좌표
				curPoint = queue.poll();
				
				//4방향에 대해 일단 추가시킨다.
				for(int dir = 0; dir < 4; ++dir) {
					nrow = curPoint.row + drow[dir];
					ncol = curPoint.col + dcol[dir];
					//해당 자리 방문 할 수 있다면 queue에 추가시켜준다.
					//해당 자리 방문 가능하지만 bs.size<=board[nrow][ncol]이라면 방문못함.
					//만약에 먹을 수 있는거 나오면 일단 찾았다고 하고, 먹을수있는 list에 계속 저장
					if(visitable[(N+2)*nrow + ncol] && bs.size >= board[nrow][ncol]-'0') {
						visitable[(N+2)*nrow + ncol] = false;
						
						//땅일때
						switch (board[nrow][ncol]) {
						case '0':
							queue.offer(new Point(nrow,ncol));
							break;
	
							//나보다 작은 물고기일때
						default:
							if(bs.size > board[nrow][ncol]-'0') {
								flist.add(new Point(nrow, ncol));
								isFind = true;
							}
							else {
								queue.offer(new Point(nrow,ncol));
								break;
							}
						} //end switch()
					} //end if()
				} //end for(dir)
			} //end while(--queSize)
			
			// 만일 먹을 물고기 찾았다면 가장 위의 왼쪽 찾기
			if(isFind) {
				Point tempP = new Point(N+2,N+2);
				
				for(Point var : flist) {
					if(tempP.row > var.row) {
						tempP.row = var.row;
						tempP.col = var.col;
					}
					else if(tempP.row == var.row) {
						if(tempP.col > var.col) {
							tempP.row = var.row;
							tempP.col = var.col;
						}
					}
				}
				
				//먹구 사이즈 키울기
				bs.eat();
				
				//해당자리 9로 바꾸기
				board[tempP.row][tempP.col] = '9';
				
				//현재 상어위치 0으로 만들기
				board[bs.p.row][bs.p.col] = '0';
				//현재 상어위치 이동
				bs.setPoint(tempP.row, tempP.col);
				
				//먹었으니 살아있던 시간 증가시키고 나갈것
				tempLive++;
				bs.live = tempLive;
				queue.clear();
				flist.clear();
				return true;
			} //end if(isFind)
			
			//못찾을 수도 있어서 임시 생존숫자 증가
			tempLive++;
		} //end while(!queue.isEmpty())
		
		//queue가 비었다 -> 모두 다 확인했다 -> 못찾음.
		return false;
	}
}
