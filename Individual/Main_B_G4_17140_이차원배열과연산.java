import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

/*
 * -이차원배열과 연산-
 * 1. 행과 열의 길이를 비교하여 R연산, C연산을 선택하며 각 방식은 (행,열)중 기준을 선택하는 것만 다르다.
 * 2. 이차원배열을 순회하며 각 숫자(Key)와 나온 횟수(Value)를 HashMap에 저장한다.
 * 3. HashMap의 EntrySet을 List로 가져와 Collection.sort를 이용하여 조건에 의해 정렬하였다.
 * 4. 정렬된 List를 Iterator를 통해 순회하며 각 좌표에 값을 할당시킨다.
 * 5. 이전 배열 크기에서 할당 되지 못한 좌표는 0으로 다시 초기화 해준다.
 * 6. 100번까지 반복하며 찾지못하면 -1 반환, 완성을 찾는다면 해당 횟수를 반환한다.
 */

//출처 : https://www.acmicpc.net/problem/17140
public class Main_B_G4_17140_이차원배열과연산 {
	static int R, C, K;
	static int[][] board = new int[101][101];
	
	//List 정렬 Class
	//정렬 우선 순위
	// 1. 수의등장 횟수 오름차순
	// 2. 수의 오름차순
	public static class myComparator implements Comparator<Map.Entry<Integer, Integer>> {
		@Override
		public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
			// 숫자가 나온 횟수에대해 오름차순으로 정렬
			int result = o1.getValue() - o2.getValue();
			//만일 숫자 나온 횟수가 같다면 key값 오름차순 정렬
			return result == 0 ? o1.getKey() - o2.getKey() : result;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		//첫째 줄에 r, c, k가 주어진다. (1 ≤ r, c, k ≤ 100)
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int rowSize=3, colSize=3;
		//3x3 으로 들어옴
		for(int row = 0; row < 3; ++row) {
			st = new StringTokenizer(br.readLine());
			for(int col = 0; col < 3; ++col) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		} //end for(row)
		
		Map<Integer,Integer> hm = new HashMap<Integer, Integer>();
		List<Map.Entry<Integer,Integer>> mList;
		myComparator mycom = new myComparator();
		Map.Entry<Integer, Integer> mEntry;
		
		int tempInt;
		int count;
		int maxColSize, maxRowSize;
		
		int t = 0;
		// 완성 됬으면 return
		if(board[R-1][C-1] == K) {
			System.out.println(t);
			return;
		}

		// R≥C => row 정렬
		// R<C => col 정렬
		while(++t <= 100) {
			if(rowSize >= colSize) {
				maxColSize = Integer.MIN_VALUE;
				for (int row = 0; row < rowSize; ++row) {
					count = 0;
					for (int col = 0; col < colSize; ++col) {
						//각 숫자와 각 횟수 저장
						tempInt = board[row][col];
						//해당 숫자 0이라면 무시
						if(tempInt == 0)
							continue;
						hm.put(tempInt, hm.get(tempInt) == null ? 1 : hm.get(tempInt)+1);
					} //end for(col)
					//한개 열 다담음. -> 정렬해서 해당 row에 삽입
					
					//map정보 담은 List를 정렬
					mList = new LinkedList<>(hm.entrySet());
					Collections.sort(mList, mycom);
					
					for(Iterator<Map.Entry<Integer, Integer>> iter = mList.iterator(); iter.hasNext();) {
						mEntry = iter.next();
						board[row][count++] = mEntry.getKey();
						board[row][count++] = mEntry.getValue();
					}
					
					//가장 긴 col길이 구하기
					maxColSize = maxColSize > count ? maxColSize : count;
					//남는 부분 0으로 채우기
					if(count <= colSize) {
						for(int i = count; i < colSize; ++i) {
							board[row][i] = 0;
						}
					}
					hm.clear();
					
				} //end for(row)
				colSize = maxColSize;
			} //end if(rowSize >= colSize)
			else {
				maxRowSize = Integer.MIN_VALUE;
				for (int col = 0; col < colSize; ++col) {
					count = 0;
					for (int row = 0; row < rowSize; ++row) {
						//각 숫자와 각 횟수 저장
						tempInt = board[row][col];
						//해당 숫자 0이라면 무시
						if(tempInt == 0)
							continue;
						hm.put(tempInt, hm.get(tempInt) == null ? 1 : hm.get(tempInt)+1);
					} //end for(row)
					//한개 열 다담음. -> 정렬해서 해당 row에 삽입
					//map정보 담은 List를 정렬
					mList = new LinkedList<>(hm.entrySet());
					Collections.sort(mList, mycom);
					
					// 정렬된 List를 돌면서 해당 좌표에 값을 정해준다.
					for(Iterator<Map.Entry<Integer, Integer>> iter = mList.iterator(); iter.hasNext();) {
						mEntry = iter.next();
						board[count++][col] = mEntry.getKey();
						board[count++][col] = mEntry.getValue();
					}
					
					//가장 긴 row길이 구하기
					maxRowSize = maxRowSize > count ? maxRowSize : count;
					//남는 부분 0으로 채우기
					if(count <= rowSize) {
						for(int i = count; i < colSize; ++i) {
							board[i][col] = 0;
						}
					}
					hm.clear();
					
				} //end for(row)
				rowSize = maxRowSize;
			} //end else
			
			// 완성 됬으면 return
			if(board[R-1][C-1] == K) {
				System.out.println(t);
				return;
			}
		} //end while
		
		// 100번만에 찾지 못했을 경우
		System.out.println(-1);
	} //end main
}
