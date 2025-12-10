/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Utils.HttpClientUtil;
import Utils.Requests.LoginRequest;
import Utils.Responses.LoginResponse;
import Utils.Requests.RegisterRequest;
import Utils.Responses.RegisterResponse;

/**
 *
 * @author JUANDA
 */
public class AuthService {

    private static final String BASE_URL = "http://localhost:8081/api/auth";
    private static final String FORMAT_URL = "http://localhost:8081/api/auth";
    private static final String MESSAGING_URL = "http://localhost:8081/api/auth";
    private static final String NOTIFICATION_URL = "http://localhost:8081/api/auth";
    private static final String DOCUMENT_URL = "http://localhost:8081/api/auth";
    
    public LoginResponse login(String email, String password) {
        try {
            LoginRequest request = new LoginRequest(email, password);
            return HttpClientUtil.post(BASE_URL, request, LoginResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public RegisterResponse register(RegisterRequest request){
        try{
            return HttpClientUtil.post(BASE_URL + "/register", request, RegisterResponse.class);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
