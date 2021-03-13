// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12911

function solution(n) {
    let originN = n.toString(2).split("");
    var numArr = [];
    let isOne = false;
    let zeroCnt = 0;

    while(originN.length > 0){
        if(originN[originN.length-1] == '1'){
            isOne = true;
            numArr.push(originN.pop());
        }
        else if(originN[originN.length-1] == '0'){
            if(isOne){
                originN.pop();
                originN.push(numArr.pop());
                originN.push('0');
                while(zeroCnt-- > 0){
                    originN.push('0');
                }

                while(numArr.length > 0){
                    if(numArr[numArr.length-1] == '1')
                        originN.push(numArr.pop());
                    else{
                        numArr.pop();
                    }
                }
                break;
            }
            else{
                zeroCnt++;
                numArr.push(originN.pop())
            }
        }
    }
    
    if(originN.length==0){
        originN.push(numArr.pop())
        originN.push('0')
        while(zeroCnt-- > 0){
            originN.push('0');
        }
        while(numArr.length > 0){
            if(numArr[numArr.length-1] == '1')
                originN.push(numArr.pop());
            else{
                numArr.pop();
            }
        }
    }
    
    return Number("0b"+originN.join(""));
}