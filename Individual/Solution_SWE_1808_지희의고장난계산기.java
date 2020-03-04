import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * -지희의 고장난 계산기-
 * 1. 사용가능한 숫자는 ableNum에 집어넣는다. => 내림차순으로 정렬
 * 2. 0을 가장 마지막에 뽑아야 같은 숫자라도 적은 count를 지닌다.
 * 
 * 3. 순열이용하여 해당 숫자의 약수를 모두 구하여 list에 저장.
 * 4. 중복되는 숫자인지 파악하기 위해 Set사용 => visit으로 한다면 없어도 됨
 * 5. 뽑을때 현재 숫자 길이보다 길다면 더 볼 필요 없음.
 * 
 * 6. 나누기를 이용하여 해당 약수로 숫자를 계속 나누어갔음
 * 7. 가장 큰 수를 잡는다고 무조건 적게 누르는 것은 아니므로 완전 탐색 하였음.
 * 8. '1'이 입력 될 경우에는 따로 예외처리 필요함.
 */

/*
 * 메모리 : 33624KB 
 * 시간 : 188ms 
 * 코드길이 : 2173B 
 * 소요시간 : 1H
 */

//100P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV4yC3pqCegDFAUx
public class Solution_SWE_1808_지희의고장난계산기 {
	static List<Integer> ableNum;			//사용가능한 숫자 List
	static List<Integer> list;				//약수 모음 list
	static Set<Integer> set;				//중복 약수 담지 않기 위한 Set
	static HashMap<Integer, Integer> map;	//해당 숫자를 만드는데 필요한 클릭 수
	
	static int target,tarsize,minRes;
	static boolean isFind;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		String ts;
		for(int t =1; t<=T; ++t) {
			ableNum = new ArrayList<>();
			list = new ArrayList<>();
			set = new HashSet<>();
			map = new HashMap<>();
			isFind = false;
			minRes = Integer.MAX_VALUE;
			
			ts = br.readLine();
			for(int i = 0; i < 10; ++i) {
				if(ts.charAt(i*2) == '1')
					ableNum.add(i);
			}

			//사용가능한 숫자 내림차순 정렬
			ableNum.sort(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o2-o1;
				}
			});
			
			ts = br.readLine();
			tarsize = ts.length();			//목표하는 숫자의 길이
			target = Integer.parseInt(ts);	//목표하는 숫자
			
			//만일 1이고 '1'을 사용할 수 있다면, 또는 없다면?
			if(target == 1) {
				for(Integer ti : ableNum) {
					if(ti==1) isFind=true;
				}
				System.out.println("#"+t+" "+(isFind ? 2 : -1));
				continue;
			}
			
			//만들 수 있는 약수를 찾자.
			find(0, 0);
			
			divide(target, 0);
			System.out.println("#"+t+" "+(isFind ? minRes : -1));
		}
	}
	
	public static void divide(int num, int count) {
		//나눈 몫이 1이라면 계산 할 수 있다는 것이다.
		if(num == 1) {
			isFind = true;
			//최소 값으로 갱신해주자.
			minRes = minRes < count ? minRes : count;
			return;
		}
		
		int tnum;
		for(Integer ti : list) {
			//1로는 나누어도 같기 때문에 보지 않는다.
			if(ti == 1) continue;
			tnum = num/ti;
			//나눈 값이 0이라면 ti가 큰것
			//나눈 나머지가 0이 아니라면 변한 숫자의 약수가 아닌것.
			if(tnum == 0 || num%ti!=0) continue;
			//해당 숫자를 누르는데 필요한 클릭수와 연산자(*)을 누르는 +1 더해주기
			divide(tnum, count+1+map.get(ti));
		}
	}
	
	public static void find(int num, int count) {
		//만들 숫자보다 더 길다면 보지 않아도 된다.
		if(count > tarsize) return;
		
		if(num!=0) {
			//목표 숫자보다 더 크다면 볼 필요 없다.
			if(target/num == 0)
				return;
			else {
				//나눈 나머지가 0이라면 약수다
				if(target%num == 0) {
					//해당 약수가 이미 저장 되있다면 패스
					if(!set.contains(num)) {
						list.add(num);
						set.add(num);
						map.put(num, count);
					}
				}
			}
		}
		
		for(Integer ti : ableNum) {
			find(num*10+ti, count+1);
		}
	}
}
