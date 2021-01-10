// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12940

function solution(n, m) {
    var gcd;
    var [min, max] = [Math.min(n, m), Math.max(n, m)];
    
    function getGcd(a, b){
        return b % a ? getGcd(b%a, a): a;
    }

    gcd = getGcd(min, max);
    return [gcd, n*m/gcd];
}