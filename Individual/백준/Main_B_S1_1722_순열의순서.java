import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -순열의 순서-
 * 1. Factorial 값 이용한 위치 선정
 */

//이전 풀이 : https://github.com/Ladybug-Algorithm/Study_1/blob/master/04.%20milkcat1994/03_LineUp/3_d_1.cpp
//이전 풀이 : https://github.com/Ladybug-Algorithm/Study_1/blob/master/04.%20milkcat1994/03_LineUp/3_d_2.cpp

//출처 : https://www.acmicpc.net/problem/1722
public class Main_B_S1_1722_순열의순서 {
	static int N,flag;
	static BigDecimal[] factorial = new BigDecimal[21];
	static List<Integer> list = new ArrayList<>();
	static List<Integer> nList = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		flag = Integer.parseInt(st.nextToken());
		factorial[0]=new BigDecimal(0);
		factorial[1]=new BigDecimal(1);
		for(int i = 2; i<=N; ++i) {
			factorial[i] = factorial[i-1].multiply(new BigDecimal(i));
		}
		
		switch (flag) {
		//해당 순열 출력
		case 1:
			for(int i = 1; i <= N; ++i)
				list.add(i);
			getPermutation(new BigDecimal(st.nextToken()),N-1);
			break;
		// 몇번째 순열인지 출력
		case 2:
		default:
			BigDecimal tbd;
			for(int i = 1; i <= N; ++i) {
				list.add(Integer.parseInt(st.nextToken()));
				nList.add(i);
			}
			tbd = getNumber(0);
			sb.append(tbd);
			break;
		}
		
		System.out.print(sb.toString());
	}
	
	public static void getPermutation(BigDecimal n, int index) {
		if(index==0) {
			sb.append(list.get(index));
			list.remove(index);
			return;
		}
		
		BigDecimal tbd = factorial[index];
		for(int j = 0; j<=index; ++j) {
			//뺐을때 양수라면 더 뺼수 있으므로 해당 팩토리얼 값 더 뺴나가기
			if(n.compareTo(tbd) > 0)
				n = n.subtract(tbd);
			else {
				//뺐을때 음수나 0일경우 해당 j위치가 선택될 숫자이다.
				sb.append(list.get(j)).append(' ');
				list.remove(j);
				getPermutation(n,index-1);
				return;
			}
		}
	}
	
	public static BigDecimal getNumber(int index) {
		//다돌았다면 1반환
		if(index == N)
			return new BigDecimal(1);
		BigDecimal tbd = new BigDecimal(0);
		
		//앞부터 증가시키며 현재 list의 index와 같은 지 확인
		for(int i = 0; i < nList.size(); ++i) {
			//찾았다면 찾은 요소 지우고 다음 index 반복
			if(list.get(index) == nList.get(i)) {
				nList.remove(i);
				tbd = tbd.add(getNumber(index+1));
				return tbd;
			}
			else {
				//찾을 때까지 (N-1)-index의 팩토리얼 값 더해주기
				tbd = tbd.add(factorial[N-1-index]);
			}
		}
		return tbd;
	}
}
