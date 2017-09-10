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

    //计算税额
    public static float calcPit(float salary){
        float taxbase = salary - 3500;
        if (taxbase <= 0){
            return 0;
        }

        if (taxbase <=1500){
            taxbase = taxbase*3/100;
        }
        else if (taxbase <=4500){
            taxbase = taxbase*10/100 - 105;

        }
        else if (taxbase <= 9000){
            taxbase = taxbase*20/100 - 555;
        }
        else if (taxbase <= 35000){
            taxbase = taxbase*25/100 - 1005;
        }
        else if (taxbase <= 55000){
            taxbase = taxbase*30/100 - 2755;
        }
        else if (taxbase <= 80000){
            taxbase = taxbase*35/100 - 5505;
        }
        else {
            taxbase = taxbase*45/100 - 13505;
        }
        return taxbase;
    }

    //计算社保
    private float calSin(float salary,float baseUp,float baseDown){
        float sin=0;

        return sin;
    }

    //计算公积金
    private float houseSin(float salary, float baseUp,float baseDown){
        float house =0;

        return house;
    }

    private static float calYeb(float yearBounce,float monthBounce){
        float temp1,res;

        temp1 = yearBounce/12;
        if (temp1 <=1500){
            res = yearBounce*3/100;
        }
        else if (temp1 <=4500){
            res = yearBounce*10/100-105;
        }
        else if (temp1 <=9000){
            res = yearBounce*20/100 -555;
        }
        else if (temp1 <= 35000){
            res = yearBounce*25/100-1005;
        }
        else if(temp1<=55000){
            res = yearBounce*30/100-2755;
        }
        else if(temp1 <=80000){
            res =yearBounce*35/100-5505;
        }
        else {
            res = yearBounce*45/100-13505;
        }

        return res;
    }
    /*
在年终奖金发放的当月，年终奖金与当月工资组合扣税：
当工资收入减去五险一金后，大于或等于个税起征点时，工资和年终奖是各算各的，之间没有一点联系。
当工资收入减去五险一金后，比个税起征点小时，年终奖金的应纳税所得额就不是之前的年终奖收入金额了，
    年终奖应纳税所得额 = 年终奖金 - （个税起征点 - (工资收入 - 五险一金))。
 */
    public static float calYear(float yearBounce,float monthBounce,float sin){
        float tax=0;

        if ((monthBounce - sin) >= 3500){
            tax = calYeb(yearBounce,monthBounce);
        }
        else{
            tax = calYeb(yearBounce-(3500 - (monthBounce - sin)),monthBounce);
        }
        return tax;
    }
}
