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
    
    public Date(String dateStr){
        splitString(dateStr);
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
        this.day = day;
        
        
    }

    public void setMonth(int month) {
        this.month = month;
        
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
    
    public void splitString(String dateString) {
        String [] parts = dateString.split("-");
        
        setYear(Integer.parseInt(parts[0]));
        setMonth(Integer.parseInt(parts[1]));
        setDay(Integer.parseInt(parts[2]));
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
