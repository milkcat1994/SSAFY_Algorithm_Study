// 출처 : https://programmers.co.kr/learn/courses/30/lessons/42883

function solution(number, k) {
    var answer = [];
    let removeCnt=0;
    let numberLength = number.length;
    let exNum=10;
    let nextNum;
    
    for(let idx=0; idx<numberLength; idx++){
        while(removeCnt < k && exNum < number.charAt(idx)*1){
            answer.pop();
            exNum = answer.length==0 ? 10 : answer[answer.length-1];
            removeCnt++;
        }
        
        nextNum = number.charAt(idx)*1;
        if(removeCnt == k){
            answer.push(number.slice(idx));
            break;
        }
        
        if(exNum >= nextNum){
            answer.push(nextNum);
            exNum = nextNum;
        }
    }
    return answer.join("").substring(0,numberLength-k);
}