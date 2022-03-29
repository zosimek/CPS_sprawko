package ib.zpo2.L1;

import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Try {
    static String customerToken = "PL4325ex_fba110d0-4848-4dc0-9e8d-b1141cfdd64e12.43.54.6.2.1";

    public static void main(String[] args) {
        Pattern patternCT = Pattern.compile("([A-Z]{2})(\\d+)ex_([a-zA-Z0-9]{8}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{12})([\\d\\.]*\\d+)");
        Matcher matcherCT = patternCT.matcher(customerToken);
        matcherCT.matches();
//        "PL4325ex_fba110d0-4848-4dc0-9e8d-b1141cfdd64e12.43.54.6.2.1"

//        System.out.println(matcherCT.group(1));
//        System.out.println(matcherCT.group(2));
//        System.out.println(matcherCT.group(3));
//        System.out.println(matcherCT.group(4));
//
//        System.out.println(customerToken.getClass());
        ServiceConfig serviceConfig = new ServiceConfig();
        System.out.println(serviceConfig.getId().get(0));
    }


}
