// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12982

function solution(d, budget) {
    var answer = 0;
    d.sort((a,b) => a-b);
    let length = d.length;
    
    for(let i=0; i<length; i++){
        budget-=d[i];
        if(budget<0)
            break;
        else
            answer++;
    }
    
    return answer;
}