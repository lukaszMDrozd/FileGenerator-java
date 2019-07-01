package com.lukasz.fileGenerator;

class NumberToFixedString {

    static String doNumberToFixedString(Long numberToFixed){
        String fixedString = String.valueOf(numberToFixed);

        StringBuilder sb = new StringBuilder();
        while(sb.length() + fixedString.length() < 6){
            sb.append('0');
        }
         return sb.append(numberToFixed).toString();
    }
}
