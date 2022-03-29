package ib.zpo2.L1;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Create a helper class CustomerTokenDecoder - the decode(string token) method
// should create and return a Customer object based on the token. Use known
// regular expressions and operations on String.
public class CustomerTokenDecoder {

//  The tokenDecode(string token) method returns object Token
    public static Token tokenDecode(String token){

//      Token decryption
//      copied from https://www.baeldung.com/java-jwt-token-decode
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));

//      The token is converted into an object form (Token) which will contain all
//      information from the token and facilitate the use of this information.
        Token token1 = new Token(header, payload);

        return token1;
    }


    public static Customer decode(String token) {

//      Calling tokenDecode(String token) from above.
        Token token1 = tokenDecode(token);

//      Parsing String to Json in order to filter the customerToken value
        JsonObject jsonObject = (new JsonParser()).parse(token1.getPayload()).getAsJsonObject();
        String customerToken = String.valueOf(jsonObject.get("customerToken"));

//      Breakdown of customerToken into usable values
        Pattern patternCT = Pattern.compile("\"([A-Z]{2})(\\d+)ex_([a-zA-Z0-9]{8}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{12})([\\d\\.]*\\d+)\"");
        Matcher matcherCT = patternCT.matcher(customerToken);
        matcherCT.matches();

        String countryCode = matcherCT.group(1);
        String id = matcherCT.group(2);
        String externalId = matcherCT.group(3);
        String enabledServices = matcherCT.group(4);

//      Create a class representing the Customer object - it should contain all the fields
//      that can be separated from the customer token (use appropriate types)
        Customer customer = new Customer(countryCode, id, externalId, enabledServices);

        return customer;

    }
}
