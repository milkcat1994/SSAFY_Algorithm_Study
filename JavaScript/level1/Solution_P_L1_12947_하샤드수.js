// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12947

function solution(x) {
    var [sum, origin] = [0, x];
    
    while(x/10){
        sum+=x%10;
        x=Math.floor(x/10);
    }
    return origin%sum ? false : true;
}