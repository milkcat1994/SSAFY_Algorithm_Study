import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * -Poker Game-
 * 1. 각 등급별 개별 구현
 */

/*
 * 메모리 : 26364KB 
 * 시간 : 109ms 
 * 코드길이 : 2620B 
 * 소요시간 : 30M
 */

//50P

//출처 : https://swexpertacademy.com/main/code/userProblem/userProblemDetail.do?contestProbId=AXEN3aEKDrsDFAVX
public class Solution_SWE_9760_PokerGame {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String cStr;
		int tc = Integer.parseInt(br.readLine());
		
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		int[] card = new int[13];
		init(map);
		for(int t = 1; t <= tc; ++t) {
			Arrays.fill(card, 0);
			
			Set<Character> set = new HashSet<>();
			st = new StringTokenizer(br.readLine(), " ");
			while(st.hasMoreTokens()) {
				cStr = st.nextToken();
				set.add(cStr.charAt(0));
				//해당 카드 나온 숫자 기억
				card[map.get(cStr.charAt(1))]++;
			}
			
			System.out.println("#"+t+" " + decision(card, set));
		}
	}
	
	static String decision(int[] card, Set<Character> set) {
		//Straight Flush 확인
		if(set.size() == 1) {
			//하나로 쭉 연결 되어있는지
			if(isStraight(card))
				return "Straight Flush";
		}
		
		// Four of a Kind 확인
		if(isFourCard(card)) return "Four of a Kind";
		
		// Full House 확인
		// 3장, 2장 있어야함. 즉, 1인값 있으면 안 됨
		if(isFullHouse(card)) return "Full House";
		
		// Flush확인
		if(set.size() == 1) {
			return "Flush";
		}
		
		// Straight 확인
		if(isStraight(card)) return "Straight";
		
		// Three of a kind 확인
		if(isTriCard(card)) return "Three of a kind";
		
		if(getTwoCard(card) == 2) return "Two pair";
		
		if(getTwoCard(card) == 1) return "One pair";
		
		return "High card";
	}
	
	static int getTwoCard(int[] card) {
		int temp = 0;
		for(int i = 0; i < 13; ++i) {
			if(card[i] == 2) {
				temp++;
			}
		}
		return temp;
	}
	
	static boolean isTriCard(int[] card) {
		for(int i = 0; i < 13; ++i) {
			if(card[i] == 3) {
				return true;
			}
		}
		return false;
	}
	
	static boolean isFourCard(int[] card) {
		for(int i = 0; i < 13; ++i) {
			if(card[i] == 4) {
				return true;
			}
		}
		return false;
	}
	
	static boolean isFullHouse(int[] card) {
		int temp = 0;
		for(int i = 0; i < 13; ++i) {
			if(temp == 0) {
				if(card[i] ==2 || card[i] == 3)
					temp = card[i];
			}
			else if(temp == 2) {
				if(card[i] == 3) return true;
			}
			else if(temp == 3) {
				if(card[i] == 2) return true;
			}
		}
		return false;
	}
	
	static boolean isStraight(int[] card) {
		for(int i = 0; i <= 9; ++i) {
			if(card[i]>0) {
				//지금부터 시작이다.
				for(int j = i; j <= i+4; ++j) {
					//중간에 하나라도 비어있으면 틀린것이다.
					if(card[j%13] == 0) {
						return false;
					}
				}
				// 잘 나왔다면 중간에 비지 않고 이어졌다는 것이다.
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 각 문자에 대해 index 매칭
	 */
	static void init(Map<Character, Integer> map) {
		//A, 2, 3, 4, 5, 6, 7, 8, 9, T, J, Q, K
		map.put('A', 0);
		for(char i = '2'; i <='9'; ++i) {
			map.put(i, i-'1');
		}
		map.put('T', 9);
		map.put('J', 10);
		map.put('Q', 11);
		map.put('K', 12);
	}
}
