package Model;
import java.util.Date;

public class HKFDate implements Comparable<HKFDate>{
    
    private int day;
    private int month;
    private int year;
    
    public HKFDate(){
        Date currentDate = new Date();

        this.day = currentDate.getDay();
        this.month = currentDate.getMonth() + 1; //to be tested
        this.year = currentDate.getYear() + 1900; //fuck that shit
    }

    public HKFDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
    
    public HKFDate(String dateStr){
        splitString(dateStr);
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }
    
    public int getMonthForGraph(){
        return month - 1;
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
        System.out.println(parts[1]);
        setMonth(Integer.parseInt(parts[1]));
        setDay(Integer.parseInt(parts[2]));
    }

    @Override
    public String toString() {
        //return year + "-" + (month) + "-" + day;
        return String.format("%4d-%02d-%02d", year, month, day);
    }   

    @Override
    public int compareTo(HKFDate t) {
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
