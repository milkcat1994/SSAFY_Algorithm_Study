import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * -가사 검색-
 * 1. word의 길이에 따라서 Trie 생성
 * 2. query는 ?가 접두사, 접미사로만 들어오므로 word를 inverse시킨 Trie도 생성필요
 * 3. ?가 나올시 단어의 개수는 현재 확인하고 있는 Node의 cnt이다.
 * 
 * 정확성  테스트
 * 테스트 1 〉	통과 (1.96ms, 52.8MB)
 * 테스트 2 〉	통과 (0.91ms, 52.7MB)
 * 테스트 3 〉	통과 (1.20ms, 52MB)
 * 테스트 4 〉	통과 (1.20ms, 53MB)
 * 테스트 5 〉	통과 (1.03ms, 52.6MB)
 * 테스트 6 〉	통과 (1.87ms, 53.1MB)
 * 테스트 7 〉	통과 (11.01ms, 54.1MB)
 * 테스트 8 〉	통과 (10.17ms, 54.3MB)
 * 테스트 9 〉	통과 (7.41ms, 54.5MB)
 * 테스트 10 〉	통과 (7.85ms, 54.5MB)
 * 테스트 11 〉	통과 (5.69ms, 53.1MB)
 * 테스트 12 〉	통과 (10.56ms, 55MB)
 * 테스트 13 〉	통과 (39.37ms, 59.5MB)
 * 테스트 14 〉	통과 (15.43ms, 56.1MB)
 * 테스트 15 〉	통과 (22.27ms, 59.4MB)
 * 테스트 16 〉	통과 (23.39ms, 59.8MB)
 * 테스트 17 〉	통과 (22.85ms, 58.9MB)
 * 테스트 18 〉	통과 (20.26ms, 59.6MB)
 * 
 * 효율성  테스트
 * 테스트 1 〉	통과 (353.35ms, 173MB)
 * 테스트 2 〉	통과 (610.75ms, 285MB)
 * 테스트 3 〉	통과 (463.35ms, 264MB)
 * 테스트 4 〉	통과 (453.40ms, 285MB)
 * 테스트 5 〉	통과 (723.86ms, 512MB)
 * 
 * 풀이 시간 : 2H+
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/60060
public class Solution_P_L4_60060_가사검색 {
	public int[] solution(String[] words, String[] queries) {
		int[] answer = new int[queries.length];
		
		// 단어 길이에 따른 word hashMap
		Map<Integer, Node> wordMap = new HashMap<>();
		Map<Integer, Node> inverseWordMap = new HashMap<>();

		for(int i=0; i<words.length; ++i) {
			makeTrie(wordMap, words[i]);
			makeInverseTrie(inverseWordMap, words[i]);
		}
		
		// query에 따른 값 저장
		Map<String, Integer> map = new HashMap<>();
		
		String query;
		int cnt;
		for(int idx=0; idx<queries.length; ++idx) {
			query = queries[idx];
			// 이미 가지고 있는 key라면
			if(map.containsKey(query)) {
				answer[idx] = map.get(query);
				continue;
			}
			
			cnt = 0;
			// ?로만 구성되어있는지
			if(query.charAt(0) == '?' && query.charAt(query.length()-1) == '?') {
				if(wordMap.containsKey(query.length()))
					cnt = wordMap.get(query.length()).cnt;
			}
			// ?가 접두사인지
			else if(query.charAt(0) == '?') {
				if(inverseWordMap.containsKey(query.length()))
					cnt = find(new StringBuffer(query).reverse().toString(), inverseWordMap.get(query.length()));
			}
			// ?가 접미사인지
			else {
				if(wordMap.containsKey(query.length()))
					cnt = find(query, wordMap.get(query.length()));
			}
			map.put(query, cnt);
			answer[idx] = cnt;
		}
		
		return answer;
	}
	
	void makeTrie(Map<Integer, Node> rootMap, String word) {
		char tch;
		Node node;
		
		if(!rootMap.containsKey(word.length()))
			rootMap.put(word.length(), new Node(0));
		node = rootMap.get(word.length());
		
		for(int j=0; j<word.length(); ++j) {
			tch = word.charAt(j);
			// 없다면 key와 Node를 생성해주기
			if(!node.hm.containsKey(tch))
				node.hm.put(tch, new Node(0));
			// 있다면 HashMap 가져오기
			node.cnt++;
			node = node.hm.get(tch);
		}
		node.cnt++;
	}
	
	void makeInverseTrie(Map<Integer, Node> rootMap, String word) {
		char tch;
		Node node;
		if(!rootMap.containsKey(word.length()))
			rootMap.put(word.length(), new Node(0));
		node = rootMap.get(word.length());
		
		for(int j=word.length()-1; j>=0; --j) {
			tch = word.charAt(j);
			// 없다면 key와 Node를 생성해주기
			if(!node.hm.containsKey(tch))
				node.hm.put(tch, new Node(0));
			// 있다면 HashMap 가져오기
			node.cnt++;
			node = node.hm.get(tch);
		}
		node.cnt++;
	}
	
	int find(String query, Node wordRootNode) {
		Node node = wordRootNode;
		
		//접두사, 접미사 구분
		char tch;
		for(int i=0; i<query.length(); ++i) {
			tch = query.charAt(i);
			switch (tch) {
			case '?':
				// ?가 나오면 해당 node의 cnt가 query의 값이다.
				i = query.length();
				break;

			default:
				if(node.hm.containsKey(tch))
					node = node.hm.get(tch);
				else 
					return 0;
				break;
			}
		}
		
		return node.cnt;
	}

	class Node {
		Map<Character, Node> hm;
		
		int cnt;
		Node(int cnt){
			this.cnt = cnt;
			hm = new HashMap<>();
		}
	}

	public static void main(String[] args) {
		Solution_P_L4_60060_가사검색 sol = new Solution_P_L4_60060_가사검색();
		String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
		String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
		int[] answer = sol.solution(words, queries);
		System.out.println(Arrays.toString(answer));
	}
}