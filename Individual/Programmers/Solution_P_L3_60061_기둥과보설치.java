import java.util.ArrayList;
import java.util.List;

/*
 * -기둥과 보 설치-
 * 1. 함수를 많이 세분화 시켰다. '보','기둥'인지 확인하는, 설치가 가능한지, 삭제가 가능한지...
 * 2. 삭제시 삭제 가능성을 판별하기 위해 해당 물체를 지우고 주변에 닿아있던 물체가 설치가 가능한지 판별하였다.
 * 3. isOut 함수 호출을 최대한 적게 하기 위해서는 배열 크기를 좀 더 크게 잡으면 해결될 듯.
 * 
 * 테스트 1 〉	통과 (0.15ms, 53.1MB)
 * 테스트 2 〉	통과 (0.18ms, 52.5MB)
 * 테스트 3 〉	통과 (0.12ms, 51.7MB)
 * 테스트 4 〉	통과 (0.16ms, 52.3MB)
 * 테스트 5 〉	통과 (0.15ms, 52.7MB)
 * 테스트 6 〉	통과 (0.15ms, 52.8MB)
 * 테스트 7 〉	통과 (0.08ms, 52.9MB)
 * 테스트 8 〉	통과 (0.09ms, 52.3MB)
 * 테스트 9 〉	통과 (0.15ms, 52MB)
 * 테스트 10 〉	통과 (2.31ms, 53.8MB)
 * 테스트 11 〉	통과 (2.91ms, 56.9MB)
 * 테스트 12 〉	통과 (4.59ms, 52.7MB)
 * 테스트 13 〉	통과 (6.07ms, 53.8MB)
 * 테스트 14 〉	통과 (2.26ms, 53.7MB)
 * 테스트 15 〉	통과 (9.20ms, 56.4MB)
 * 테스트 16 〉	통과 (4.28ms, 52.5MB)
 * 테스트 17 〉	통과 (4.85ms, 54.1MB)
 * 테스트 18 〉	통과 (4.30ms, 56.2MB)
 * 테스트 19 〉	통과 (7.59ms, 54MB)
 * 테스트 20 〉	통과 (4.73ms, 54.2MB)
 * 테스트 21 〉	통과 (5.36ms, 53.9MB)
 * 테스트 22 〉	통과 (6.13ms, 53.9MB)
 * 테스트 23 〉	통과 (4.04ms, 54.1MB)
 * 
 * 풀이 시간 1H 20M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/60061
public class Solution_P_L3_60061_기둥과보설치 {
	final int STICK = 0;
	final int BO = 1;
	final int BLANK = 2;
	
	public int[][] solution(int n, int[][] build_frame) {
		int map_size= n+1;
		int[][] map = new int[map_size][map_size];
		
		int[][] answer = {};
		
		int r,c;
		for(int[] operation : build_frame) {
			c = operation[0];
			r = operation[1];
			switch (operation[3]) {
			// delete
			case 0:
				if(operation[2] == STICK) {
					deleteStick(r, c, map);
				}
				else if(operation[2] == BO) {
					deleteBo(r, c, map);
				}
				break;
			// add
			case 1:
				if(operation[2] == STICK) {
					if(addStickAble(r, c, map)) {
						addStick(r, c, map);
					}
				}
				else if(operation[2] == BO) {
					if(addBoAble(r, c, map)) {
						addBo(r, c, map);
					}
				}
				break;
			default:
				// do nothing
				break;
			}
		}
		
		List<int[]> list = new ArrayList<>();
		for (int col = 0; col < map_size; ++col) {
			for (int row = 0; row < map_size; ++row) {
				if(isStick(row,col,map))
					list.add(new int[] {col,row,STICK});
				if(isBo(row,col,map))
					list.add(new int[] {col,row,BO});
			}
		}
		
		answer = new int[list.size()][3];
		for(int i=0; i<list.size(); ++i) {
			for(int j=0; j<3; ++j)
				answer[i][j] = list.get(i)[j];
		}
		return answer;
	}
	
	boolean isBo(int r, int c, int[][] map) {
		if(!isOut(r, c, map.length) && (map[r][c] & (1<<BO)) > 0)
			return true;
		return false;
	}
	
	boolean isStick(int r, int c, int[][] map) {
		if(!isOut(r, c, map.length) && (map[r][c] & (1<<STICK)) > 0)
			return true;
		return false;
	}
	
	void addBo(int r, int c, int[][] map) {
		map[r][c] |= (1<<BO);
	}
	
	void addStick(int r, int c, int[][] map) {
		map[r][c] |= (1<<STICK);
	}

	boolean addBoAble(int r, int c, int[][] map) {
		// 한쪽 끝이 기둥 위
		if(isStick(r-1,c,map)) {
			return true;
		}
		else if(isStick(r-1,c+1,map)) {
			return true;
		}
		// 양쪽끝이 다른 보와 연결
		else if(isBo(r,c-1,map) && isBo(r,c+1,map)) {
			return true;
		}
		return false;
	}
	
	boolean addStickAble(int r, int c, int[][] map) {
		// 바닥 위
		if(r==0) {
			return true;
		}
		// 보가 기둥 한쪽 끝부분 위에 있거나
		else if(isBo(r,c-1,map) || isBo(r,c,map)) {
			return true;
		}
		// 다른 기둥 위에 있거나
		else if(isStick(r-1,c,map)) {
			return true;
		}
		return false;
	}
	
	void deleteBo(int r, int c, int[][] map) {
		// 임시 보 삭제
		map[r][c]^=(1<<BO);
		boolean able = true;
		// 양끝 보 확인
		if(isBo(r,c-1,map) && !addBoAble(r, c-1, map) || isBo(r,c+1,map) && !addBoAble(r,c+1,map)) {
			able = false;
		}
		// 양끝 스틱 확인
		if(isStick(r,c,map) && !addStickAble(r,c,map) || isStick(r,c+1,map) && !addStickAble(r,c+1,map)) {
			able = false;
		}

		// 삭제 불가하다면 삭제 시키지 않는다.
		if(!able)
			map[r][c]|=(1<<BO);
	}
	
	void deleteStick(int r, int c, int[][] map) {
		// 임시 스틱 삭제
		map[r][c]^=(1<<STICK);
		boolean able = true;
		
		// 위에 스틱이 있고 설치 가능하다면
		if(isStick(r+1,c,map) && !addStickAble(r+1,c,map)) {
			able = false;
		}
		// 양쪽 보 각각 확인
		if(isBo(r+1,c-1,map) && !addBoAble(r+1,c-1,map) || isBo(r+1,c,map) && !addBoAble(r+1,c,map)) {
			able = false;
		}
		
		// 삭제 불가하다면 삭제 시키지 않는다.
		if(!able)
			map[r][c]|=(1<<STICK);
	}
	

	boolean isOut(int r, int c, int size) {
		if(r<0 || c<0 || r>=size || c>=size)
			return true;
		return false;
	}

	public static void main(String[] args) {
		Solution_P_L3_60061_기둥과보설치 sol = new Solution_P_L3_60061_기둥과보설치();
		int n = 5;;
//		int[][] build_frame = {{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}};
		int[][] build_frame = {{0,0,0,1},{2,0,0,1},{4,0,0,1},{0,1,1,1},{1,1,1,1},{2,1,1,1},{3,1,1,1},{2,0,0,0},{1,1,1,0},{2,2,0,1}};
		int[][] answer = sol.solution(n, build_frame);
		for (int row = 0; row < answer.length; ++row) {
			for (int col = 0; col < 3; ++col) {
				System.out.print(answer[row][col]+" ");
			}
			System.out.println();
		}
	}
}