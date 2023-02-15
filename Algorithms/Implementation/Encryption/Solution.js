function encryption(s) {
    // Write your code here
    let stringWithoutSpaces = s.replace(/\s/g,'');
    let stringLength = stringWithoutSpaces.length;
    let sqrtStringLength = Math.sqrt(stringLength);
    let floorNumber = Math.floor(sqrtStringLength);
    let ceilNumber = Math.ceil(sqrtStringLength);

    if(floorNumber * ceilNumber < stringLength){
        floorNumber += 1;
    }

    let subsStringArr = [];
    for(let i = 0; i < stringLength; i += ceilNumber){
        subsStringArr.push(stringWithoutSpaces.substr(i, ceilNumber));
    }

    let encryptedArray = [];
    for(let i = 0; i < ceilNumber; i++){
        let encryptedString = '';
        for(let j = 0; j < floorNumber; j++){
            if(subsStringArr[j][i] != undefined){
                encryptedString += subsStringArr[j][i];
            }
        }
        encryptedArray.push(encryptedString)
    }

    let result = encryptedArray.join(' ');
    return result;
}