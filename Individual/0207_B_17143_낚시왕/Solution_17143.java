import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -낚시왕-
 * 입력받은 상어 정보는 List형태로 입력받으며 각 좌표마다 상어 정보를 가지고있다.
 * 1. ccol(사람 행위치)를 1칸 증가시키고 가장 가까운 상어를 잡는다.
 * 2. 모든 상어 한번에 이동 -> 상어 정보를 담은 List를 모두 돌며 해당 상어를 이동시킨다.
 * └──해당  좌표의 Queue에서 상어를 빼내어 정보이용
 * └  각 상어 이동거리는 [2*((R,C)-1)]로 나눈 나머지로 설정하여 중복되는 반복 삭제
 * └──이동을 마쳤다면 마친 좌표의 Queue에 상어 넣기
 * 3. 모든 좌표를 순회하며 상어가 2마리 이상인 곳을 찾는다.
 * 4. 2마리 이상이라면 가장 큰 상어를 제외하고 좌표의 Queue에서 삭제한다.
 * 5. ccol(사람 행위치)가 밖으로 나갈때까지 반복
 */

//출처 : https://www.acmicpc.net/problem/17143
public class Solution_17143 {
	static int R,C,M;
	static Board[][] board;
	static List<Shark> sharkList = new ArrayList<Shark>();
	static int drow[] = {-1, 1, 0, 0};
	static int dcol[] = {0, 0, 1, -1};

	static int result = 0;
	static Queue<Shark> tq;
	
	public static class Board {
		Queue<Shark> que = new LinkedList<Shark>();
	}
	
	public static class Shark {
		//좌표, 속력, 방향, 크기
		int row, col, speed, dir, size;

		public Shark(int row, int col, int speed, int dir, int size) {
			this.row = row;
			this.col = col;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
		
		//위치 방향 수정
		public void setValue(int row, int col, int dir) {
			this.row = row;
			this.col = col;
			this.dir = dir;
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		//격자판 크기 R,C 상어의 수 M
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new Board[R+1][C+1];
		
		for(int row = 0; row <= R; ++row) {
			for(int col = 0; col <= C; ++col) {
				board[row][col] = new Board();
			}
		}
		
		// M개의 줄에 상어정보
		// r,c(좌표), s(속력), d(방향), z(크기)
		int tr,tc;
		Shark tempS;
		for(int index = 0; index < M; ++index) {
			st = new StringTokenizer(br.readLine(), " ");
			tr = Integer.parseInt(st.nextToken());
			tc = Integer.parseInt(st.nextToken());
			tempS = new Shark(tr, tc,
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
			sharkList.add(tempS);
			board[tr][tc].que.offer(tempS);
		} //end for(index)

		// 현재 사람 '열'정보
		int ccol = 0;
		//열이동, 나갈때까지
		while(++ccol <= C) {
			//해당 col에서 가장 가까운 상어 get
			for(int row = 1; row <= R; ++row) {
				if(board[row][ccol].que.isEmpty())
					continue;
				tempS = board[row][ccol].que.poll();
				sharkList.remove(tempS);
				result += tempS.size;
				break;
			} //for(row)

			//상어들 이동 -> 각 좌표의 상어정보가 아닌 미리 넣어둔 상어정보를 이용하여
			// 한마리가 여러번 움직이는 것 방지
			for(Shark s : sharkList) {
				moveShark(board[s.row][s.col].que.poll());
			} //end for(row)
			
			//곂치는 상어있는지 파악
			for(int row = 1; row <= R; ++row) {
				for(int col = 1; col <=C; ++col) {
					if(board[row][col].que.size() <2)
						continue;
					eatShark(row, col);
				}
			} //end for(row)
			
		} //while(++col > C)
		
		System.out.println(result);
	}
	
	//해당 상어이동
	/**
	 * 
	 * @param shark		이동시킬 상어
	 */
	public static void moveShark(Shark shark) {
		// 상어의좌표,   속력, 방향, 크기
		// row, col, speed, dir, size;
		int crow = shark.row, ccol = shark.col;
		int dir = shark.dir;

		int nrow, ncol;
		int distance = shark.speed;
		
		switch (dir) {
		//위, 아래
		case 1:	case 2:
			//원점으로 돌아온 뒤 남은 이동거리
			distance = (shark.speed)%(2*(R-1));
			
			while(distance-- > 0) {
				//다음 위치 확인
				nrow = crow + drow[dir-1];
				//다음 위치가 벽이라면 반대방향으로 이동
				if(nrow <= 0 || nrow > R) {
					dir = dir == 1 ? 2 : 1;
					nrow = crow + drow[dir-1];
				}
				crow = nrow;
			}
			break;
			
		//오른측, 왼쪽
		case 3:	case 4:
			//원점으로 돌아온 뒤 남은 이동거리
			distance = (shark.speed)%(2*(C-1));
			
			while(distance-- > 0) {
				//다음 위치 확인
				ncol = ccol + dcol[dir-1];
				//다음 위치가 벽이라면 반대방향으로 이동
				if(ncol <= 0 || ncol > C) {
					dir = dir == 3 ? 4 : 3;
					ncol = ccol + dcol[dir-1];
				}
				ccol = ncol;
			}
			break;
		} //end switch
		
		// 상어가 이동을 마쳤으며, 위치 수정
		shark.setValue(crow, ccol, dir);
		// 해당 좌표에 상어 추가
		board[crow][ccol].que.offer(shark);
		
	} //end func
	
	/**
	 * 
	 * @param row	행
	 * @param col	열
	 */
	public static void eatShark(int row, int col) {
		Queue<Shark> tq = board[row][col].que;
		Shark largeS = tq.poll();
		sharkList.remove(largeS);
		Shark tempS;
		while(!tq.isEmpty()) {
			tempS = tq.poll();
			sharkList.remove(tempS);
			// 크기 큰 상어만 저장
			largeS = largeS.size > tempS.size ? largeS: tempS;
		}
		
		// 크기 큰 상어 넣기
		tq.offer(largeS);
		sharkList.add(largeS);
	} //end func
}
