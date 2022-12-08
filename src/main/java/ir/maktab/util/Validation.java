package ir.maktab.util;

import ir.maktab.entity.person.Person;
import ir.maktab.exception.EmailFormatException;
import ir.maktab.exception.MobileNumberFormatException;
import ir.maktab.exception.PasswordFormatException;

import java.util.regex.Pattern;

public class Validation {

    private Validation() {}

    public static void checkPhoneNumberAndEmailAndPassword(Person person) {
        String mobileNumberRegex = "^\\d{11}$";
        if (!Pattern.matches(mobileNumberRegex, person.getPhoneNumber())) {
            throw new MobileNumberFormatException("the format of the mobile number is incorrect!");
        }
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8}$";
        if (!Pattern.matches(passwordRegex, person.getPassword())) {
            throw new PasswordFormatException("the format of the password is incorrect!");
        }
        String emailPassword = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        if (!Pattern.matches(emailPassword, person.getEmail())) {
            throw new EmailFormatException("the format of the email is incorrect!");
        }
    }
}
