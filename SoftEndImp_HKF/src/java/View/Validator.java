
package View;

/**
 *
 * @author xmw13bzu
 */
public class Validator {
    
    String errorMessage;
    
    public Validator() {
        errorMessage = "";
    }
    
    public boolean isValid() {
        return errorMessage.equals("");
    }
    
    public String getErrMsg() {
        return errorMessage;
    }
    
    public void setErrMsg(String newMsg) {
        errorMessage = newMsg;
    }
    
    public void clearErrMsg() {
        errorMessage = "";
    }
    
    public void appendErrMsg(String newMsg) {
        if (!errorMessage.equals(""))
            errorMessage += "\n";
        
        errorMessage += newMsg;
    }
    
    public boolean isEmpty(String value) {
        return value == null || value.equals("");
    }
    
    public boolean isWithinRange(int value, int low, int high) {
        return value >= low && value <= high;
    }
    
    //http://www.regular-expressions.info/email.html
    public boolean validateEmail(String errMsg, String email){
        if (!isEmpty(email) && email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"))
            return true;
        else {
            appendErrMsg(errMsg);
            return false;
        }
    }
    
    //http://www.regular-expressions.info/dates.html
    //matches a date in yyyy-mm-dd format from between 1900-01-01 and 2099-12-31,
    public boolean validateDate(String errMsg, String dateString){
        if (!isEmpty(dateString) && dateString.matches("^(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$"))
            return true;
        else {
            appendErrMsg(errMsg);
            return false;
        }
    }
    
    //username between 3 and 12 characters containing only letters, numbers and underscores, and starting with a letter
    public boolean validateUsername(String errMsg, String username){
        if (!isEmpty(username) && username.matches("^[A-Za-z][A-Za-z0-9_]{2,11}$"))
            return true;
        else {
            appendErrMsg(errMsg);
            return false;
        }
    }
    
    //string containing only alphabetic characters and spaces
    public boolean validateName(String errMsg, String name){
        if (!isEmpty(name) && name.matches("[A-Za-z ]+"))
            return true;
        else {
            appendErrMsg(errMsg);
            return false;
        }
    }
    
    //string containing only alphanumeric characters and underscores, between 6 and 12 chars long
    public boolean validatePassword(String errMsg, String password){
        if (!isEmpty(password) && password.matches("[A-Za-z0-9_]{6,12}"))
            return true;
        else {
            appendErrMsg(errMsg);
            return false;
        }
    }
    
    public int validateInt(String errMsg, String number) {        
        int result = 0;
        
        try {
            result = Integer.parseInt(number);
        } catch (Exception ex) { 
            appendErrMsg(errMsg);
            result = 0;
        }
        
        return result;
    }

    public int validatePositiveInt(String errMsg, String number) {
        int result = 0;
        
        try {
            result = Integer.parseInt(number);
            if (result < 0) 
                throw new Exception("");
        } catch (Exception ex) { 
            appendErrMsg(errMsg);
            result = 0;
        }
        
        return result;
    }
    
    public int validateIntWithinRange(String errMsg, String number, int low, int high) {
        int result = 0;
        
        try {
            result = Integer.parseInt(number);
            if (!isWithinRange(result, low, high)) 
                throw new Exception("");
        } catch (Exception ex) { 
            appendErrMsg(errMsg);
            result = 0;
        }
        
        return result;
    }
}
