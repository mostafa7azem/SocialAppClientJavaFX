package SocialAppClient;

import SocialAppGeneral.AppUser;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.Optional;

import static javafx.scene.layout.GridPane.setColumnSpan;
import static javafx.scene.layout.GridPane.setConstraints;

/**
 * Created by kemo on 09/11/2016.
 */
public class NavBar extends HBox {

    public NavBar(AppUser appUser)
    {

        setLayout();
        setNavButtons();
    }
    private void setLayout()
    {
        setConstraints(this,0,0);
        setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        setColumnSpan(this, 2);
    }
    private void setNavButtons()
    {

        Button homeBtn = new Button("home");
        homeBtn.setOnMouseClicked(event -> {
            ((MainWindow)getParent()).navigateTo(new HomePage());
        });
        Button profileBtn = new Button("profile");
        profileBtn.setOnMouseClicked(event -> {
            ((MainWindow)getParent()).navigateTo(new ProfilePage());
        });
        Button groupsBtn = new Button("groups");
        groupsBtn.setOnMouseClicked(event -> {
            ((MainWindow)getParent()).navigateTo(new GroupPage());
        });

        Button creatButton = new Button("Create");
        GroupPage page=new GroupPage();
        creatButton.setOnMouseClicked(event -> {
            Optional<String> check=page.createWindow();
            while (check.get().equals("")){
                page.createWindow();
            }
                 if(check.equals(Optional.empty())){
                     System.out.println("False");
                 }
                 else {
                         System.out.println(check.get());
                     }


        });
        getChildren().addAll(homeBtn, profileBtn, groupsBtn,creatButton);
    }
}
