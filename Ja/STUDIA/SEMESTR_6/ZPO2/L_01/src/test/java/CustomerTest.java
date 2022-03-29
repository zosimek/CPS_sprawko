import ib.zpo2.L1.Token;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static ib.zpo2.L1.CustomerTokenDecoder.tokenDecode;
import static org.junit.jupiter.api.Assertions.*;

import static ib.zpo2.L1.CustomerTokenValidator.isValid;
import ib.zpo2.L1.CustomerTokenDecoder;
import ib.zpo2.L1.Customer;

import java.util.Arrays;
import java.util.List;

public class CustomerTest {

    List<String> id_1 = Arrays.asList("12");
    List<String> id_2 = Arrays.asList("12", "2");


// TESTS FOR CLASS CustomerTokenValidator //////////////////////////////////////////////////////////////////////////////
    @Test
    @DisplayName("Check if token contains the customerToken — in case it has not")
    // service id = [12]
    // payload
    //{
    //  "sub": "1234567890",
    //  "name": "John Doe",
    //  "iat": 1516239022
    //}
    public void jwtHasCustomerTokenFalse() {
        assertThrows(IllegalStateException.class, new Executable(){

            @Override
            public void execute() throws Throwable {
                isValid(id_1, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                        ".eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MD" +
                        "IyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c");
            }
        });
    }

    @Test
    @DisplayName("Check if customerToken has the right format — in case it has not")
    // service id = [12]
    // payload
    //{
    //  "customerToken": "incorrect",
    //  "sub": "1234567890",
    //  "name": "John Doe",
    //  "iat": 1516239022
    //}
    public void customerTokenRightFormatFalse(){
        assertThrows(IllegalStateException.class, new Executable() {

            @Override
            public void execute() throws Throwable {
                isValid(id_1,"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                        "eyJjdXN0b21lclRva2VuIjoiaW5jb3JyZWN0Iiwic3ViIjoiMTIzNDU2Nzg5MCIsIm5hbW" +
                        "UiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMn0.2UszlfcYAqEJGBnTKTlHv65JPczkY" +
                        "JYCy8df1lwVM1o");
            }
        });
    }


    @Test
    @DisplayName("Check if the customer has access to service (id = [12]) — in case it has not")
    // service id = [12]
    // payload
    //{
    //  "customerToken": "PL4325ex_fba110d0-4848-4dc0-9e8d-b1141cfdd64e43.54.6.2.1",
    //  "name": "John Doe",
    //  "iat": 1516239022
    //}
    public void customerHasAccessFalse_01(){
        assertThrows(IllegalStateException.class, new Executable() {

            @Override
            public void execute() throws Throwable {
                isValid(id_1,"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.ey" +
                        "JjdXN0b21lclRva2VuIjoiUEw0MzI1ZXhfZmJhMTEwZDAtNDg0OC00ZGMwLTllOGQtYjExNDF" +
                        "jZmRkNjRlNDMuNTQuNi4yLjEiLCJuYW1lIjoiSm9obiBEb2UiLCJpYXQiOjE1MTYyMzkwMjJ9" +
                        ".LkOBq3qNojGRtOzEOUk0pY6oMb4AzVW4dMOLh_typlg");
            }
        });
    }


    @Test
    @DisplayName("Check if the customer has access to service (id = [12, 2]) — in case it has to one and not to the other.")
    // service id = [12]
    // payload
    //{
    //  "customerToken": "PL4325ex_fba110d0-4848-4dc0-9e8d-b1141cfdd64e43.54.6.2.1",
    //  "name": "John Doe",
    //  "iat": 1516239022
    //}
    public void customerHasAccessFalse_02(){
        assertThrows(IllegalStateException.class, new Executable() {

            @Override
            public void execute() throws Throwable {
                isValid(id_2,"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.ey" +
                        "JjdXN0b21lclRva2VuIjoiUEw0MzI1ZXhfZmJhMTEwZDAtNDg0OC00ZGMwLTllOGQtYjExNDF" +
                        "jZmRkNjRlNDMuNTQuNi4yLjEiLCJuYW1lIjoiSm9obiBEb2UiLCJpYXQiOjE1MTYyMzkwMjJ9" +
                        ".LkOBq3qNojGRtOzEOUk0pY6oMb4AzVW4dMOLh_typlg");
            }
        });
    }

    @Test
    @DisplayName("Check if the isValid() method returns correct Customer class object — on case it does.")
    // service id = [12]
    // payload
    //{
    //  "customerToken": "PL4325ex_fba110d0-4848-4dc0-9e8d-b1141cfdd64e12.43.54.6.2.1",
    //  "name": "John Doe",
    //  "iat": 1516239022
    //}
    public void isValidReturnsCorrectCustomer(){
        Customer customer1 = new Customer("PL", "4325", "fba110d0-4848-4dc0-9e8d-b1141cfdd64e", "12.43.54.6.2.1");
        Customer customer2 = isValid(id_1, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjdXN0b21lclRva2VuIjoiUEw0MzI1ZXhfZmJhMTEwZDAtNDg0OC00ZGMwLTllOGQtYjExNDFjZmRkNjRlMTIuNDMuNTQuNi4yLjEiLCJuYW1lIjoiSm9obiBEb2UiLCJpYXQiOjE1MTYyMzkwMjJ9.MeoGAq-tptTG6_eW7IF9140Hh6ho6BXAH3pBnuEMWtY");
        assertEquals(customer1.getCountryCode(), customer2.getCountryCode());
        assertEquals(customer1.getId(), customer2.getId());
        assertEquals(customer1.getExternalId(), customer2.getExternalId());
        assertEquals(customer1.getEnableServices(), customer2.getEnableServices());
    }


    @Test
    @DisplayName("Check if the isValid() method returns correct Customer class object — in case it doesn't.")
    // service id = [12]
    // payload
    //{
    //  "customerToken": "GB666ex_ccc110d0-4848-4dc0-9e8d-b1141cfdd64e12",
    //  "name": "John Doe",
    //  "iat": 1516239022
    //}
    public void isValidReturnsIncorrectCustomer(){
        Customer customer1 = new Customer("PL", "4325", "fba110d0-4848-4dc0-9e8d-b1141cfdd64e", "12.43.54.6.2.1");
        Customer customer2 = isValid(id_1, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjdXN0b21lclRva2VuIjoiR0I2NjZleF9jY2MxMTBkMC00ODQ4LTRkYzAtOWU4ZC1iMTE0MWNmZGQ2NGUxMiIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMn0.3Ae-jLf6DBL2ATf4nu5ARC90-uTsZTJfGWQhnOvk5Bc");
        assertNotEquals(customer1.getCountryCode(), customer2.getCountryCode());
        assertNotEquals(customer1.getId(), customer2.getId());
        assertNotEquals(customer1.getExternalId(), customer2.getExternalId());
        assertNotEquals(customer1.getEnableServices(), customer2.getEnableServices());
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// TESTS FOR CLASS CustomerTokenDecoder/////////////////////////////////////////////////////////////////////////////////

//    The method decode() was omitted because its correctness was checked simultaneously with the method isValis()
//    in CustomerTokenValidator class.

@Test
@DisplayName("Check if the tokenDecode() returns correct token1 — in case it does.")
// service id = [12]
// payload
//{
//  "customerToken": "GB666ex_ccc110d0-4848-4dc0-9e8d-b1141cfdd64e12",
//  "name": "John Doe",
//  "iat": 1516239022
//}
public void isTokenDecodeReturnsCorrectToken(){
    Token token1 = new Token("{\"alg\":\"HS256\",\"typ\":\"JWT\"}", "{\"customerToken\":\"PL4325ex_fba110d0-4848-4dc0-9e8d-b1141cfdd64e12.43.54.6.2.1\",\"name\":\"John Doe\",\"iat\":1516239022}");
    Token token2 = tokenDecode("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjdXN0b21lclRva2VuIjoiUEw0MzI1ZXhfZmJhMTEwZDAtNDg0OC00ZGMwLTllOGQtYjExNDFjZmRkNjRlMTIuNDMuNTQuNi4yLjEiLCJuYW1lIjoiSm9obiBEb2UiLCJpYXQiOjE1MTYyMzkwMjJ9.MeoGAq-tptTG6_eW7IF9140Hh6ho6BXAH3pBnuEMWtY");

    assertEquals(token1.getHeader(), token2.getHeader());
    assertEquals(token1.getPayload(), token2.getPayload());

    }


    @Test
    @DisplayName("Check if the tokenDecode() returns correct token1 — in case it doesn't.")
// service id = [12]
// payload
//{
//  "customerToken": "GB666ex_ccc110d0-4848-4dc0-9e8d-b1141cfdd64e12",
//  "name": "John Doe",
//  "iat": 1516239022
//}
    public void isTokenDecodeReturnsIncorrectToken(){
        Token token1 = new Token("{\"alg\":\"HS255\",\"typ\":\"JWW\"}", "{\"customerToken\":\"BG666ex_ccc110d0-4848-4dc0-9e8d-b1141cfdd64e12.43.54.6.2.1\",\"name\":\"John Hoe\",\"iat\":1516239022}");
        Token token2 = tokenDecode("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjdXN0b21lclRva2VuIjoiUEw0MzI1ZXhfZmJhMTEwZDAtNDg0OC00ZGMwLTllOGQtYjExNDFjZmRkNjRlMTIuNDMuNTQuNi4yLjEiLCJuYW1lIjoiSm9obiBEb2UiLCJpYXQiOjE1MTYyMzkwMjJ9.MeoGAq-tptTG6_eW7IF9140Hh6ho6BXAH3pBnuEMWtY");

        assertNotEquals(token1.getHeader(), token2.getHeader());
        assertNotEquals(token1.getPayload(), token2.getPayload());

    }
}
