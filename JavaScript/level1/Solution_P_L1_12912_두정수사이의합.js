// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12912

function sum(a){
    return a*(a+1)/2;
}

function solution(a, b) {
    var answer = 0;
    var c = Math.min(a, b);
    var d = Math.max(a, b);

    if(c<=0 && d>=0){
        c=-c;
        answer = sum(d) - sum(c);
    }
    else if(c<0 && d<0){
        c=-c; d=-d;
        answer = -(sum(c) - sum(d)) - d;
    }
    else if(c>=0 && d>=0){
        answer = sum(d) - sum(c) + c;
    }
    return answer;
}