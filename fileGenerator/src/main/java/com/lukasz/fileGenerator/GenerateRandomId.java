package com.lukasz.fileGenerator;

class GenerateRandomId {

    static Long doGenerateRandomId(){

        long minLImit = 1L;
        long maxLimit = 1000000L;
        return minLImit + (long) (Math.random() * maxLimit);
    }
}
