// 출처 : https://programmers.co.kr/learn/courses/30/lessons/42889

function solution(N, stages) {
    var answerObjArr = [];
    var answer = [];
    var stagesCountArr = new Array(N+2).fill(0);
    var userLength = stages.length;

    for(let i=0; i<userLength; i++){
        stagesCountArr[stages[i]]++;
    }

    var userNum = userLength;
    for(var stageNum=1; stageNum<=N; stageNum++) {
        answerObjArr.push({
            stageNum : stageNum,
            failRate : (stagesCountArr[stageNum]/userNum)
        })
        userNum -= stagesCountArr[stageNum];
    }

    answerObjArr.sort((e1, e2) => {
        if(e1.failRate == e2.failRate){
            return e1.stageNum-e2.stageNum;
        }
        else{
            return e2.failRate - e1.failRate;
        }
    }).forEach((element) => answer.push(element.stageNum))

    return answer;
}