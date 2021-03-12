// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12909

function solution(s){
    var sLength = s.length;
    let leftCnt=0;

    for(let idx=0; idx<sLength; idx++){
        switch (s.charAt(idx)) {
            case '(':
                leftCnt++;
                break;
            case ')':
                leftCnt--;
                if(leftCnt<0) return false;
                break;
            default:
                // do Nothing
                break;
        }
    }

    return leftCnt == 0 ? true : false;
}