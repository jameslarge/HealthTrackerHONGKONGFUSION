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
        splitStringDate(dateStr);
        this.hours = 23;
        this.minutes = 59;
    }
    
    public HKFDate(String dateStr, String timeStr){
        splitStringDate(dateStr);
        splitStringTime(timeStr);
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

    public void addDay(int day) {   
        
        //assuming user can't skip more than one month
        if(day+this.day > monthSize()){
            this.day = day+this.day - monthSize();
            setMonth(this.month + 1);
        }else{
            setDay(day+this.day);
        } 
        
    }
    
    public void setDay(int day){
        this.day = day;
    }

    public void setMonth(int month) {
        if(month > 12)
        {            
            this.month = 1;
            setYear(this.year + (month/12));
        }else{
            this.month = month;
        }
    }

    public void setYear(int year) {
        this.year = year;
    }

    
    public void addHours(int hours) {
        int newhours = this.hours + hours;
        
        this.hours = newhours % 24;
        addDay(newhours/24);
//        
//        if(this.hours + hours >= 24) {
//            this.hours = hours%24;
//            addDay(this.day + hours/24);
//        }else{            
//            setHours(this.hours + hours);
//        }
        
    }
    
    public void setHours(int hours){
        this.hours = hours;
    }
    
    public void addMinutes(int minutes){
        
        int newminutes = this.minutes + minutes;
        
        this.minutes = newminutes % 60;
        addHours(newminutes/60);
        
//        if(this.minutes + minutes >= 60) {
//            this.minutes = minutes%60;
//            addHours(this.hours + minutes/60);
//        }
//        else{
//            setMinutes(this.minutes + minutes);      
//        }
    }
    
    public void setMinutes(int minutes) {
        this.minutes = minutes;
        
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
    
    public void splitStringDate(String dateString) {
        String [] parts = dateString.split("-");
        
        setYear(Integer.parseInt(parts[0]));
        System.out.println(parts[1]);
        setMonth(Integer.parseInt(parts[1]));
        setDay(Integer.parseInt(parts[2]));
    }
    
     public void splitStringTime(String timeString) {
        String [] parts = timeString.split(":");
        
        setHours(Integer.parseInt(parts[0]));
        setMinutes(Integer.parseInt(parts[1]));
        
        
        
    }
    
    

    @Override
    public String toString() {
        //return year + "-" + (month) + "-" + day;
        return String.format("%4d-%02d-%02d", year, month, day);
    }
    
    public String timeToString(){
        return String.format("%02d:%02d", hours, minutes);
    }
    
    
    public String forGraphWithTime(){
        return this.getYear() + ", " + this.getMonthForGraph() + ", " + this.getDay() + ", " + this.getHours() + ", " + this.getMinutes();
    }
    
    public String forGraphWithoutTime(){
         return this.getYear() + ", " + this.getMonthForGraph() + ", " + this.getDay();
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
        HKFDate endDate = new HKFDate(day,month,year, hours,this.minutes);
        
        endDate.addMinutes(minutes);
        
        return endDate;
    }    
    
}
