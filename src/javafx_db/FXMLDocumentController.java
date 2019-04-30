/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_db;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Sanchit
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    public TextField userName;
    @FXML
    public PasswordField passWord;
    @FXML
    public Label incorrect;
    
    
    @FXML
    public void loginButton(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        
        ConnectionClass con = new ConnectionClass();
        Connection connection = con.getConnection();
        
        String sql = "SELECT USERNAME,PASSWORD FROM USER WHERE USERNAME = '"+userName.getText()+"'";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        
        if(!userName.getText().equals("") && !passWord.getText().equals("")) {
            if(passWord.getText().equals(rs.getString("PASSWORD"))) {
            JOptionPane.showMessageDialog(null, "Login Successful");
            
            //Open home page 
            Parent home = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene homeScene = new Scene(home);
            Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            homeStage.hide();
            homeStage.setScene(homeScene);
            homeStage.show();
            
            
            }else {
                JOptionPane.showMessageDialog(null, "Incorrect password or username");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Enter Username and Password");
        }
        
    }
    
    
    @FXML
    public void signupButton(ActionEvent actionEvent) throws IOException {
        
        Parent home = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene homeScene = new Scene(home);
        Stage homeStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        homeStage.hide();
        homeStage.setScene(homeScene);
        homeStage.show();
               
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
