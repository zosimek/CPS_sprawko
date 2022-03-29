package ib.zpo2.L1;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ib.zpo2.L1.CustomerTokenDecoder.decode;
import static ib.zpo2.L1.CustomerTokenDecoder.tokenDecode;

public class CustomerTokenValidator {
    public static Customer isValid(List<String> id, String token){

        Token token1 = tokenDecode(token);

//      It is checked whether the JWT contains the customerToken field in its content.
//      If not, the appropriate exception is thrown.
        JsonObject jsonObject = (new JsonParser()).parse(token1.getPayload()).getAsJsonObject();
        if (!jsonObject.has("customerToken")){
            throw new IllegalStateException("The customerToken has not been found!");
        }

        String customerToken = String.valueOf(jsonObject.get("customerToken"));


//      It is checked if the customerToken type has a valid format.
//      If not, an exception is thrown.
        Pattern patternCT = Pattern.compile("\"([A-Z]{2})(\\d+)ex_([a-zA-Z0-9]{8}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{12})([\\d\\.]*\\d+)\"");
        Matcher matcherCT = patternCT.matcher(customerToken);

        if (!matcherCT.matches()){
            throw new IllegalStateException("The customerToken is in the wrong format!");
        }

        Customer customer = decode(token);

//      This will check if you have access to the service you are trying to use
//      (Your service has identifier = 12). If not, the appropriate exception is returned
        String[] enabled = customer.getEnableServices().split("\\.");
        List enalbedArray = new ArrayList<String>();
        enalbedArray.addAll(Arrays.asList(enabled));
        for (int i = 0; i < id.size(); i++){
            if (!enalbedArray.contains(id.get(i))){
                throw new IllegalStateException("The user is not authorised to use the service");
            }
        }

        return customer;
    }
}
