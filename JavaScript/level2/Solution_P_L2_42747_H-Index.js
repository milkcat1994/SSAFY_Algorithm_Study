// 출처 : https://programmers.co.kr/learn/courses/30/lessons/42747

function solution(citations) {
    var answer = 0;
    var arr = citations.sort((a,b) => b-a);
    
    for(let idx=0; idx<arr.length; idx++){
        if(idx < arr[idx])
            answer++;
    }
    return answer;
}