/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package co.edu.uniandes.csw.artwork.tests.selenium.pages;

import co.edu.uniandes.csw.artwork.tests.Utils;
import co.edu.uniandes.csw.auth.conexions.AuthenticationApi;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import static org.jboss.arquillian.graphene.Graphene.guardAjax;
import static org.jboss.arquillian.graphene.Graphene.waitModel;
import org.jboss.arquillian.graphene.page.Location;
import org.json.JSONException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Location("#/login")
public class LoginPage {
    
    

    @FindBy(id = "username-input")
    private WebElement usernameInput;

    @FindBy(id = "password-input")
    private WebElement passwordInput;

    @FindBy(id = "log-in-btn")
    private WebElement registerBtn;
    
    private static  Properties prop;
    private static InputStream input = null;
    private static final String path = System.getenv("AUTH0_PROPERTIES");
   

       
        static {
            prop = new Properties();
        try {
            input =  new FileInputStream(path);
            prop.load(input);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }
    
    public void login() throws IOException, UnirestException, JSONException, InterruptedException, ExecutionException {
        
      login(prop.getProperty("username").trim(), prop.getProperty("password").trim());
    }

    public void login(String username, String password) {
        
        waitModel().until().element(usernameInput).is().visible();
        usernameInput.clear();
        passwordInput.clear();
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        guardAjax(registerBtn).click();
        
    }
}

