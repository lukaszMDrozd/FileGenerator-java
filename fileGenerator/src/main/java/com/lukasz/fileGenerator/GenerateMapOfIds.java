package com.lukasz.fileGenerator;

import java.util.HashMap;

class GenerateMapOfIds {

    static HashMap<Long, String> doGenerateMapOfIds(Long limit, int stringLength){

        HashMap<Long, String> mapOfNumbers = new HashMap<>();
        Long tempLong;
        String tempString = GenerateRandomString.doGenerateRandomString(stringLength);

        while(mapOfNumbers.size() < limit + 1 ){
            tempLong = GenerateRandomId.doGenerateRandomId();
            IdsForMap numbers = new IdsForMap(tempLong, NumberToFixedString.doNumberToFixedString(tempLong), tempString);
            mapOfNumbers.put(numbers.getLongId(), numbers.getStringId());
        }
        return mapOfNumbers;
    }
}

