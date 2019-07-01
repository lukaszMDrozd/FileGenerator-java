package com.lukasz.fileGenerator;

public class IdsForMap {

    private String stringId;

    private Long longId;

    IdsForMap(Long longNumber, String stringNumber, String randomString){
        this.stringId = stringNumber + randomString;
        this.longId = longNumber;
    }

    public boolean equals(Object o){

        if(getClass() != o.getClass()){
            return false;
        }
        IdsForMap nfm = (IdsForMap) o;

        return (nfm.getLongId().equals(o) &&
                nfm.getStringId().equals(o));
    }

    public String toString(){
        return getLongId() + " = " + getStringId();
    }

    String getStringId() {
        return stringId;
    }

    Long getLongId() {
        return longId;
    }
}
