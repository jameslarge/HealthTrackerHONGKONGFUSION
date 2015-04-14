package Model;

public class Date implements Comparable<Date>{
    
    private int day;
    private int month;
    private int year;
    
    public Date(){}

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
        if(day > monthSize()){
            this.day = day - monthSize();
            setMonth(this.month + 1);
        }else if (day <= 0){
            setMonth(this.month - 1);
            this.day = monthSize() + day;
        }else {
            this.day = day;
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
        }else {
            this.month = month;
        }
    }

    public void setYear(int year) {
        this.year = year;
    }   
    
    public int monthSize(){
        if((month == 1 || month == 3 || month == 5 || month == 7 
                || month == 8 || month == 10 || month == 12)){
            return 31;
        }
        else if((month == 4 || month == 6 
                || month == 9 || month == 11)){
            return 30;
        }
        else{
            if(((this.year%4 == 0 && this.year%100 != 0) 
                || (this.year%4 == 0 && this.year%100 == 0 
                && this.year%400 == 0))){
                return 29;
            }else {
                return 28;
            }
        }
    }

    @Override
    public String toString() {
        return year + "-" + month + "-" + day;
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
                if(t.day < day) {
                    return -1;
                } else if (t.day > day) {
                    return 1;
                } else {
                    //same day
                    return 0;
                }
            }
        }
    }
    
}
