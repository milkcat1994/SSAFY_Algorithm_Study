import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * -백조의 호수-
 * 1. 백조 먼저 BFS이용해 녹는 얼음 담기
 * 2. 일반 물에서 BFS이용해 녹는 얼음들 담기
 * 3. 물 먼저 BFS이용해 현재 얼음 녹이고 다음 얼음 담기
 * 4. 다음으로 백조 BFS이용해 현재 얼음 녹이고 다음 얼음 담기
 * └──백조 BFS도중 물을 만날 경우 인자값 비교하여 다른 백조를 만난것인지 판단한다.
 * 그리고 다른 백조가 아닌 그냥 물이라면 해당 부분의 BFS를 이어나간다.
 */

//출처 : https://www.acmicpc.net/problem/3197
public class Main_B_G2_3197_백조의호수 {
	static class Point{
		int row, col;
		int swan;
		boolean ice;
		
		public Point(int row, int col, int swan, boolean ice) {
			this.row = row;
			this.col = col;
			this.swan = swan;
			this.ice = ice;
		}
		
	}
	
	static Point[][] board;
	static boolean[][] isVisited;
	static int ROW,COL;
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		ROW = Integer.parseInt(st.nextToken());
		COL = Integer.parseInt(st.nextToken());
		
		board = new Point[ROW][COL];
		isVisited = new boolean[ROW][COL];
		Deque<Point> que = new LinkedList<>();
		String str;
		char tc;
		int dn=1;
		for (int row = 0; row < ROW; ++row) {
			str = br.readLine();
			for (int col = 0; col < COL; ++col) {
				tc = str.charAt(col);
				switch (tc) {
				case '.':
					board[row][col] = new Point(row,col,0,false);
					break;
				case 'X':
					board[row][col] = new Point(row,col,0,true);
					break;
				case 'L':
					board[row][col] = new Point(row,col,dn++,false);
					que.offer(board[row][col]);
					isVisited[row][col] = true;
				}
			}
		}
		
		Point tp, np;
		int cr,cc, nr,nc, qs,time=0;
		qs=que.size();
		while(--qs>=0) {
			tp = que.poll();
			cr=tp.row;	cc=tp.col;
			for(int dir = 0; dir<4; ++dir) {
				nr=cr+drow[dir];	nc=cc+dcol[dir];
				//나간 것 먼저 체크
				if(isOut(nr,nc) || isVisited[nr][nc]) continue;
				np = board[nr][nc];
				
				//해당 자리에 백조 번호 갱신
				np.swan=tp.swan;
				//방문 처리 해주고 해당 자리 넣기
				isVisited[nr][nc]=true;
				//물이라면 앞에 넣기 얼음이라면 뒤에넣기
				if(np.ice) {
					que.offerLast(np);
				}
				//만일 물이라면 한번 더 볼것이다.
				else {
					que.offerFirst(np);
					qs++;
				}
			} //end for(dir)
		}
		
		//위까지 진행하면 백조 있는 물은 BFS완료
		//나머지 부분 BFS진행
		Deque<Point> tq = new LinkedList<>();
		for (int r = 0; r < ROW; ++r) {
			for (int c = 0; c < COL; ++c) {
				//얼음이거나 방문했다면 보지 않을것.
				if(board[r][c].ice || isVisited[r][c]) continue;
				tq.offerFirst(board[r][c]);
				isVisited[r][c]=true;
				qs=1;
				while(--qs>=0) {
					tp = tq.poll();
					cr=tp.row;	cc=tp.col;
					for(int dir = 0; dir<4; ++dir) {
						nr=cr+drow[dir];	nc=cc+dcol[dir];
						if(isOut(nr,nc) || isVisited[nr][nc]) continue;
						np = board[nr][nc];
						
						isVisited[nr][nc]=true;
						if(np.ice) {
							tq.offerLast(np);
						}
						//만일 물이라면 한번 더 볼것이다.
						else {
							tq.offerFirst(np);
							qs++;
						}
					} //end for(dir)
				}
			}
		}
		
		time++;
		while(true) {
			//해당 얼음 다 돌면서 주위 얼음 다시 넣기
			qs=tq.size();
			while(--qs>=0) {
				tp = tq.poll();
				tp.ice=false;
				cr=tp.row;	cc=tp.col;
				//현재 자리 백조 BFS에 담겼다면 백조가 봐줄 것이다.
				if(tp.swan>0) continue;
				for(int dir = 0; dir<4; ++dir) {
					nr=cr+drow[dir];	nc=cc+dcol[dir];
					//나간 것 먼저 체크
					if(isOut(nr,nc)) continue;
					np = board[nr][nc];
					
					//얼음이라면 방문 안했는지 보고 넣기
					if(np.ice) {
						if(isVisited[nr][nc]) {
							continue;
						}
						//방문 처리 해주고 해당 자리 넣기
						isVisited[nr][nc]=true;
						//얼음이라면 뒤에넣기
						if(np.ice) {
							tq.offerLast(np);
						}
					}
				} //end for(dir)
			}

			//그다음은 백조로 덮으면서 돌기
			qs=que.size();
			while(--qs>=0) {
				tp = que.poll();
				tp.ice=false;
				
				cr=tp.row;	cc=tp.col;
				for(int dir = 0; dir<4; ++dir) {
					nr=cr+drow[dir];	nc=cc+dcol[dir];
					//나간 것 먼저 체크
					if(isOut(nr,nc)) continue;
					np = board[nr][nc];
					//방문했지만
					if(isVisited[nr][nc]) {
						//나와 다른 양수의 duck을 가진거면 만난 것이다.
						if(np.swan!=tp.swan && np.swan>0 && !np.ice) {
							System.out.println(time);
							return;
						}
						//내 오리가 방문했던 곳이면 방문했던곳이다.
						if(np.swan==tp.swan)
							continue;
					}
					//해당 자리에 백조 번호 갱신
					np.swan=tp.swan;
					//방문 처리 해주고 해당 자리 넣기
					isVisited[nr][nc]=true;
					//물이라면 앞에 넣기 얼음이라면 뒤에넣기
					if(np.ice) {
						que.offerLast(np);
					}
					//내가 방문하지 않은 물이라면 한번 더 볼것이다.
					else {
						qs++;
						que.offerFirst(np);
					}
				} //end for(dir)
			}

			time++;
		}
	}
	
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=ROW || c>=COL)
			return true;
		return false;
	}
}
