package ib.zpo2.L1;

public class Main {
    public static void main(String[] args) {
        ServiceConfig serviceConfig = new ServiceConfig();

        CustomerTokenValidator.isValid(serviceConfig.getId(), "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjdXN0b21lclRva2VuIjoiUEw0MzI1ZXhfZmJhMTEwZDAtNDg0OC00ZGMwLTllOGQtYjExNDFjZmRkNjRlMTIuNDMuNTQuNi4yLjEiLCJuYW1lIjoiSm9obiBEb2UiLCJpYXQiOjE1MTYyMzkwMjJ9.MeoGAq-tptTG6_eW7IF9140Hh6ho6BXAH3pBnuEMWtY");

    }
}
