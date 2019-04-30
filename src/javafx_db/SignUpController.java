
package javafx_db;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
 * FXML Controller class
 *
 * @author Sanchit
 */
public class SignUpController implements Initializable {

    @FXML
    public TextField firstName;
    @FXML
    public TextField lastName;
    @FXML
    public TextField userName;
    @FXML
    public TextField contNo;
    @FXML
    public TextField email;
    @FXML
    public PasswordField passWord;
    @FXML
    public PasswordField confPass;
    @FXML
    public Label dnm;
    
    @FXML
    public void submitButton(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        
        ConnectionClass connectionClass = new ConnectionClass();
        
        Connection con = connectionClass.getConnection();
        
        if(!firstName.getText().equals("") && !lastName.getText().equals("") && !userName.getText().equals("") && !contNo.getText().equals("") &&
                !email.getText().equals("") && !passWord.getText().equals("") && !confPass.getText().equals("")) {
            
            if(passWord.getText().equals(confPass.getText())) {
            
            dnm.setText("");
            
            String sql = "INSERT INTO USER (FIRSTNAME,LASTNAME,USERNAME,PASSWORD,CONTACT,EMAIL)"
                + "VALUES('"+firstName.getText().trim()+"','"+lastName.getText().trim()+"','"+userName.getText().trim()+"','"+passWord.getText().trim()+"',"
                + "'"+contNo.getText().trim()+"','"+email.getText().trim()+"')";
            Statement stmt = con.createStatement();
            int i =stmt.executeUpdate(sql);
            if(i>0) {
                JOptionPane.showMessageDialog(null, "Registration Successful");
                
                //return to login page
                
                Parent home = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Scene homeScene = new Scene(home);
                Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                homeStage.hide();
                homeStage.setScene(homeScene);
                homeStage.show();
                
            }else{
                JOptionPane.showMessageDialog(null, "Error...Try Again");
            }
        } 
        else{
            firstName.setText("");
            lastName.setText("");
            userName.setText("");
            passWord.setText("");
            confPass.setText("");
            contNo.setText("");
            email.setText("");
            
            dnm.setText("Password do not match. Try Again");
        }
            
            
        } else{
            
            JOptionPane.showMessageDialog(null, "Please fill all fields");
            
        }
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
