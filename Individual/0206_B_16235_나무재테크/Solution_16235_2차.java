import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * -나무 재테크-
 * 1. 각 위치마다 나무의 나이를 Deque로 설정하였다.
 * └──새로운 나무는 first로 죽는 나무는 end로 더해주고나 빼준다.
 * 2. 봄,여름을 한번에 가을,겨울을 한번에 처리하였다.
 */

// 출처 : https://www.acmicpc.net/problem/16235
public class Solution_16235 {
	static int[][] addInfo;
	static int N, M, K;
	//왼쪽 위부터 시계방향
	static int[] drow = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dcol = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
	
	static int tempTreeSize, treeAge, nRow, nCol;
	
	public static class Board {
		public Deque<Integer> deque;
		
		// 영양분
		public int feed;
		Board(){
			deque = new LinkedList<Integer>();
			feed = 5;
		}
	}

	static Board[][] board;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 첫째 줄에 N, M, K
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 배열 크기
		M = Integer.parseInt(st.nextToken());	// 나무 갯수
		K = Integer.parseInt(st.nextToken());	// 지날 년수

		addInfo = new int[N+2][N+2];
		board = new Board[N+2][N+2];
		
		// 겨울에 더해질 영양소 배열 초기화 및 초기 배열 초기화
		for(int row = 1; row <= N; ++row) {
			st = new StringTokenizer(br.readLine());
			for(int col = 1; col <= N; ++col) {
				addInfo[row][col] = Integer.parseInt(st.nextToken());
				board[row][col] = new Board();
			}
		} //end for(row)
		
		//다음 M개의 줄에 상도가 심은 나무의 정보 x,y,z
		//x,y:위치, z:나이
		for(int index = 0; index < M; ++index) {
			st = new StringTokenizer(br.readLine());
			board[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]
					.deque.offerLast(Integer.parseInt(st.nextToken()));
		}
		
		for(int count = 0; count < K; ++count) {
			Spring_summer();
			Fall_winter();
		}

		// 총 나무 갯수
		int treeSize = 0;
		for(int row = 1; row <= N; ++row) {
			for(int col = 1; col <= N; ++col) {
				treeSize += board[row][col].deque.size();
			}
		}
		System.out.println(treeSize);
	}
	
	//봄, 여름
	//나이만큼 영양소 흡수 이후 나무나이+1
	public static void Spring_summer() {
		// 죽은 나무 영양분 저장위한 함수
		int tempFeed;
		//모든 땅 검사
		for(int row = 1; row <= N; ++row) {
			for(int col = 1; col <= N; ++col) {
				tempFeed = 0;
				tempTreeSize = board[row][col].deque.size();
				for(int index = 0; index < tempTreeSize; ++index) {
					treeAge = board[row][col].deque.peekFirst();
					
					//나무보다 해당 영양분이 많다면 빼주고 나무 나이 +1
					if(board[row][col].feed >= treeAge) {
						board[row][col].feed -= treeAge;
						board[row][col].deque.offerLast(board[row][col].deque.pollFirst()+1);
					}
					// 나무가 죽어버리는 경우
					else {
						tempFeed += board[row][col].deque.pollFirst()/2;
					}
				} //end for(index)
				
				//여름일때 해당 위치 다 돌았을 때 죽은 나무의 feed를 해당 자리에 더해주자
				board[row][col].feed += tempFeed;
			}
		} //end for(row)
	}

	//가을, 겨울
	// 주변 8칸에 나이 1인 나무 생성
	public static void Fall_winter() {
		//모든 땅 검사
		for(int row = 1; row <= N; ++row) {
			for(int col = 1; col <= N; ++col) {
				// 해당 위치 나무정보 모두 확인
				for(Integer var : board[row][col].deque) {
					// 나이가 5의 배수일때
					if(var % 5 == 0) {
						// 8방향 모두 나무 심어보기
						for(int dir = 0; dir < 8; ++dir) {
							nRow = row+drow[dir];
							nCol = col+dcol[dir];
							//0이거나 N+1인곳은 생성하면 안된다.
							if(nRow <= 0 || nCol <= 0 || nRow > N || nCol > N)
								continue;
							// 현재 위치 제외이기에 deque에 offer가 된다.
							board[nRow][nCol].deque.offerFirst(1);
						} //end for(dir)
					}
				}
				// 겨울일 때 해당 위치 영양분 더해주기
				board[row][col].feed += addInfo[row][col];
			}
		}
	}
}