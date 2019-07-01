package com.lukasz.fileGenerator;

import java.io.IOException;
import java.util.HashMap;

public class App {
    public static void main(String[] args) throws IOException {

        Long startTime;
        Long stopTime;
        Double resultTime;
        int transactionNumberRange = 50;
        int transactionNumberMinNumber = 50;
        long transactionNumber;
        long numberOfFiles = 1L;
        int stringLength = 6;

        startTime = System.currentTimeMillis();

        for(int i = 0; i < numberOfFiles; i++){
            if(transactionNumberMinNumber == transactionNumberRange){
                transactionNumber = (long) (transactionNumberMinNumber);
            } else {
                transactionNumber = (long) (transactionNumberMinNumber + (transactionNumberRange - transactionNumberMinNumber) * Math.random());
            }

            double scale = Math.pow(10, 2);
            double amount = Math.round(Math.random() * scale) / scale;

            HashMap<Long, String> mapOfNumbers = GenerateMapOfIds.doGenerateMapOfIds(transactionNumber, stringLength);

            CreateXMLFile.doCreateXMLFile(mapOfNumbers, amount);
        }


        stopTime = System.currentTimeMillis();
        resultTime = ((stopTime - startTime)/1000.00);

        System.out.println("Wygenerowano dane w czasie: " + String.format("%.2f", resultTime) + " sekund.");

    }
}
