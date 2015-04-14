package Model;

public class Date implements Comparable<Date>{
    
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setDay(int day) {
        if((month == 1 || month == 3 || month == 5 || month == 7 
                || month == 8 || month == 10 || month == 12)){
            if(day > 31){
                this.day = 1;
                setMonth(this.month + 1); //Means we use the error checking in setMonth
            }else if (day <= 0) {
                this.day = 31;
                setMonth(this.month-1);
            }
        }
        else if ((month == 4 || month == 6 
                || month == 9 || month == 11)){
            if(day > 30){
                this.day = 1;
                setMonth(this.month + 1); //Means we use the error checking in setMonth
            }else if (day <= 0) {
                this.day = 30;
                setMonth(this.month-1);
            }
        }
        else{
            //Its Feburary, Consider Leap Years
            if(((this.year%4 == 0 && this.year%100 != 0) 
                || (this.year%4 == 0 && this.year%100 == 0 && this.year%400 == 0))){
                //It is a leap year, Feburary has 29 days
                if(day > 29){
                    this.day = 1;
                    setMonth(this.month + 1);
                } else if (day <= 0){
                    this.day = 29;
                    setMonth(this.month - 1);
                }
            }
            else {
                if(day > 28){
                    this.day = 1;
                    setMonth(this.month + 1);
                } else if (day <= 0){
                    this.day = 28;
                    setMonth(this.month - 1);
                }
            }
        }
    }

    public void setMonth(int month) {
        if(month > 12)
        {
            
            this.month = 1;
            this.year = this.year + (month/12);
        }
        else if(month <= 0)
        {
            this.month = 12;
            this.year = this.year - (month/12);
        }
    }

    public void setYear(int year) {
        this.year = year;
    }    

    @Override
    public String toString() {
        return year + "-" + (month+1) + "-" + day;
    }   

    @Override
    public int compareTo(Date t) {
        if(t.year < year) {
            return -1;
        }else if (t.year > year) {
            return 1;
        }
        else{
            //same year
            if(t.month < month) {
                return -1;
            }else if ( t.month > month){
                return 1;
            } else {
                //same month
                if(t.day < t.day) {
                    return -1;
                } else if (t.day > t.day) {
                    return 1;
                } else {
                    //same day
                    return 0;
                }
            }
        }
    }
    
}
