// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12940

function solution(n, m) {
    var gcd;
    var [min, max] = [Math.min(n, m), Math.max(n, m)];
    
    for(var i=min; i>0; --i){
        if(!(min%i) && !(max%i)){
            gcd = i;
            break;
        }
    }
    
    return [gcd, n*m/gcd];
}