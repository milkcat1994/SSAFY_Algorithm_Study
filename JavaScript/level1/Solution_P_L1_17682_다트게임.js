// 출처 : https://programmers.co.kr/learn/courses/30/lessons/17682

function solution(dartResult) {
    var answer = 0;
    var answerArr = new Array(3).fill(0);
    var arr = dartResult.split(/(\*|\#)/).filter(Boolean);
    var idx=0;
    var n = arr.length;

    for(var i=0; i < n; i++){
        if(arr[i] == "*"){
            answerArr[idx-1]*=2;
            answerArr[idx]*=2;
            idx++;
        }
        else if(arr[i] == "#"){
            answerArr[idx]*=-1;
            idx++;
        }
        else{
            let tarr = arr[i].split(/(S|D|T)/).filter(Boolean);
            let length = tarr.length;
            for(var j=0; j < length; j+=2){
                switch (tarr[j+1]) {
                    case "S":
                        answerArr[idx] = Math.pow(tarr[j], 1);
                        break;
    
                    case "D":
                        answerArr[idx] = Math.pow(tarr[j], 2);
                        break;
    
                    case "T":
                        answerArr[idx] = Math.pow(tarr[j], 3);
                        break;
                    default:
                        // do nothing
                        break;
                }
                idx++;
            }
            idx--;
        }
    }
    
    answerArr.map((e) => answer+=e);
    return answer;
}