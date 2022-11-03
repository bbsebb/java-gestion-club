package fr.hoenheimsports.gestionclub;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
       Pattern pattern = Pattern.compile("([-\\/a-zA-Z ]+)(?: (?:(?:(?:(?:U|-)(\\d{2})(?:M|F))|(?:SM|SF)|)(\\d?))?|(?>\\(.*\\))?$)");
        Matcher m = pattern.matcher("ENTZHEIM SM1");
        System.out.println(m.matches());
        System.out.println(m.groupCount());
        System.out.println(m.group(3));
    }
}
