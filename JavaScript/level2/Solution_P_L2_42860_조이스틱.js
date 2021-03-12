// 출처 : https://programmers.co.kr/learn/courses/30/lessons/42860

function solution(name) {
    var answer = 0;
    const alphabetLength = 'Z'.charCodeAt(0)-'A'.charCodeAt(0)+1;
    let changeCnt=0;
    let nameLength = name.length;
    let isChanged = new Array(name.length).fill(true);
    
    // 'A'가 아닌것들은 isChanged로 관리하며, changeCnt로 갯수를 세어둔다.
    for(let idx = 0; idx<nameLength; idx++){
        if(name.charAt(idx) != 'A'){
            isChanged[idx] = false;
            changeCnt++;
        }
        answer += Math.min(Math.abs(name.charCodeAt(idx)-'A'.charCodeAt(0)), Math.abs(name.charCodeAt(idx) - ('A'.charCodeAt(0)+alphabetLength)));
    }
    
    let idx = 0, cnt=0, width=0;
    // 모든 것을 'A'로 바꿀 수 있을 때 까지 진행한다.
    // 해당 idx에서 width만큼 좌,우로 확인하며, 그와 가까운 방향으로 옮겨 다시 찾아다니는 방식 이용
    while(cnt!=changeCnt){
        if(!isChanged[((idx+nameLength)+width)%nameLength]) {
            isChanged[((idx+nameLength)+width)%nameLength] = true;
            cnt++;
            idx=((idx+nameLength)+width)%nameLength;
            answer+=width;
            width=0;
            continue;
        }
        
        if(!isChanged[((idx+nameLength)-width)%nameLength]) {
            isChanged[((idx+nameLength)-width)%nameLength] = true
            cnt++;
            idx=((idx+nameLength)-width)%nameLength;
            answer+=width;
            width=0;
            continue;
        }
        width++;
    }
    
    return answer;
}