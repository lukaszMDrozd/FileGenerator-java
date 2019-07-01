package com.lukasz.fileGenerator;

class GenerateRandomString {

    static String doGenerateRandomString(int n){
            String upperCaseLetterString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String lowerCaseLetterString = "abcdefghijklmnopqrstuvxyz";
            int index;
            StringBuilder sb = new StringBuilder(n);

            for(int i = 0; i < n; i++){
                if(i < 3){
                    index = (int) (upperCaseLetterString.length() * Math.random());
                    sb.append(upperCaseLetterString.charAt(index));
                } else {
                    index = (int) (lowerCaseLetterString.length() * Math.random());
                    sb.append(lowerCaseLetterString.charAt(index));
                }
            }  return sb.toString();
    }
}
