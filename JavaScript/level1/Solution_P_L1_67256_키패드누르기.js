// 출처 : https://programmers.co.kr/learn/courses/30/lessons/67256

function solution(numbers, hand) {
    var button = [[3,1],
                [0,0],[0,1],[0,2],
                [1,0],[1,1],[1,2],
                [2,0],[2,1],[2,2],
                [3,0],[3,2]];
    var answer = '';
    var left = 10;
    var right = 11;

    const getDistance = (curIdx, targetIdx) => {
        return Math.abs(button[curIdx][0]-button[targetIdx][0]) + Math.abs(button[curIdx][1]-button[targetIdx][1]);
    };

    for(var idx=0; idx<numbers.length; idx++){
        switch (numbers[idx]) {
            // using left hand
            case 1:
            case 4:
            case 7:
                answer+='L';
                left = numbers[idx];
                break;
            // using right hand
            case 3:
            case 6:
            case 9:
                answer+='R';
                right = numbers[idx];
                break;
            // using closer hand
            default:
                let l = getDistance(left,numbers[idx]);
                let r = getDistance(right,numbers[idx]);
                if(l<r){
                    left = numbers[idx];
                    answer+='L';
                }
                else if(l>r){
                    right = numbers[idx];
                    answer+='R';
                }
                else{
                    if(hand==="left"){
                        left = numbers[idx];
                        answer+='L';
                    }
                    else{
                        right = numbers[idx];
                        answer+='R';
                    }
                }
                break;
        }
    }

    return answer;
}