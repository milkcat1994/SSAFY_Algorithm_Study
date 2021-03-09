// 출처 : https://programmers.co.kr/learn/courses/30/lessons/42583

function solution(bridge_length, weight, truck_weights) {
    var time = 0;
    var bridgeTruckArr = [];
    let curWeight = 0;
    
    for(time; truck_weights.length!=0 || bridgeTruckArr.length!=0; time++){
        if(bridgeTruckArr.length!=0){
            if(time-bridgeTruckArr[0].time == bridge_length){
                curWeight -= bridgeTruckArr.shift().weight;
            }
        }
        
        if(curWeight+truck_weights[0] <= weight){
            bridgeTruckArr.push({
                weight: truck_weights[0],
                time: time
            });
            curWeight+=truck_weights[0];
            truck_weights.shift();
        }
        else{
            if(bridgeTruckArr.length!=0)
                time=bridgeTruckArr[0].time+bridge_length-1;
        }
    }
    return time;
}