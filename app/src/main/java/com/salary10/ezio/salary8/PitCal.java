package com.salary10.ezio.salary8;

/**
 * Created by ezio on 17-9-4.
 */

/*
级数	应纳税所得额(含税)	                应纳税所得额(不含税)	        税率(%)	速算扣除数
1	不超过1500元的	                不超过1455元的	            3	    0
2	超过1500元至4,500元的部分	        超过1455元至4,155元的部分	    10	    105
3	超过4,500元至9,000元的部分	        超过4,155元至7,755元的部分	    20	    555
4	超过9,000元至35,000元的部分	    超过7,755元至27,255元的部分	25	    1,005
5	超过35,000元至55,000元的部分	    超过27,255元至41,255元的部分	30	    2,755
6	超过55,000元至80,000元的部分	    超过41,255元至57,505元的部分	35	    5,505
7	超过80,000元的部分	            超过57,505的部分	            45	    13,505
 */
public class PitCal {
    public float calcPit(float salary){
        float taxbase = salary - 3500;
        if (salary <= 0){
            return 0;
        }

        if (taxbase <=1500){

        }
        else if (taxbase <=4500){

        }
        else if (taxbase <= 9000){

        }
        else if (taxbase <= 35000){

        }
        else if (taxbase <= 55000){

        }
        else if (taxbase <= 80000){

        }
        else if (taxbase >800000){

        }
        return taxbase;
    }

}
