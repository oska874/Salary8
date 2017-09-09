package com.salary10.ezio.salary8;

/**
 * Created by ezio on 2017/9/9.
 */

public class sinStore {
    float[] ratios=new float[6];
    String cityName;

    private sinStore(String cityName, float old, float health, float unemp, float workinj, float birth, float house){
        this.cityName=cityName;
        this.ratios[MainActivity.OLDTT]=old;
        this.ratios[MainActivity.HEALTHTT]=health;
        this.ratios[MainActivity.UNEMPTT]=unemp;
        this.ratios[MainActivity.WORKINJTT]=workinj;
        this.ratios[MainActivity.BIRTHTT]=birth;
        this.ratios[MainActivity.HOUSETT]=house;
    }

    public int saveRatios(){
        int res = -1;

        return res;
    }
}
