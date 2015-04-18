package Model;
import java.util.Calendar;
import java.util.Date;

public class HKFDate implements Comparable<HKFDate>{
    
    private int day;
    private int month;
    private int year;
    private int hours;
    private int minutes;
    
    
    public HKFDate(){
        Calendar currentDate = Calendar.getInstance();

        this.day = currentDate.get(Calendar.DATE);
        this.month = currentDate.get(Calendar.MONTH) + 1; //to be tested
        this.year = currentDate.get(Calendar.YEAR); //fuck that shit
        this.hours = 23;
        this.minutes = 59;
    }

    public HKFDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hours = 23;
        this.minutes = 59;
    }
    
    public HKFDate(int day, int month, int year, int hours, int minutes) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hours = hours;
        this.minutes = minutes;
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
    
    public int getHours() {
        return hours;
    }
    
    public int getMinutes() {
        return minutes;
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
    
    public void setHours(int hour) {
        if(hour > 24) {
            this.hours = hour - 24;
            setDay(this.day + hour/24);
        }
        else if ( hour <= 0) {
            this.hours = 0 - hour/24;
            setDay(this.day - hour/24);
        }
    }
    
    public void setMinutes(int minutes) {
        if(minutes > 60) {
            this.minutes = minutes - 60;
            setHours(this.hours + 1);
        }
        else if (minutes <=0 ) {
            this.minutes = 0 - minutes;
            setHours(this.hours - 1);
        }
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
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HKFDate other = (HKFDate) obj;

        return this.day == other.day
                && this.month == other.month
                && this.year == other.year
                && this.hours == other.hours
                && this.minutes == other.minutes;
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
                    if(t.hours < hours) {
                        return -1;
                    } else if ( t.hours > hours) {
                        return 1;
                    } else {
                      //Same hour
                        if(t.minutes < minutes) {
                            return -1;
                        } else if ( t.minutes < minutes ) {
                            return 1;
                        }
                        else {
                            //Same time
                            return 0;
                        }
                    }                    
                }
            }
        }
    }
    
    /**
     * Method to calculate the difference between two dates 
     * @param date Second date
     * @return difference between dates in days
     */
    public int diffDay(HKFDate date) {
        int monthSize = date.monthSize();
        
        int diffYear = (date.year - year) * (12*monthSize);
        int diffMonth = (date.month - month) * (monthSize);        
        int diffDay = date.day - day;
        
        return diffYear + diffMonth + diffDay;
    }
    
    /**
     * Method to calculate the difference between two dates 
     * @param date Second date
     * @return difference between dates in minutes
     */
    public int diffMin(HKFDate date) {
        int diffHour = date.hours - hours * 60;
        int diffMin = date.minutes - minutes;
        
        return (diffDay(date) * 24 * 60) + diffHour + diffMin;
    }
    
    /**
     * Method to get the end date 
     * @param days amount of days to add on
     * @return End Date
     */
    public HKFDate getEndDate(int minutes) {
        HKFDate endDate = new HKFDate(day,month,year);
        
        endDate.setMinutes(minutes);
        
        return endDate;
    }    
    
}
