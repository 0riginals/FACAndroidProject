package com.benameur.carretriever.controler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.benameur.carretriever.R;
import com.benameur.carretriever.model.Car;
import com.benameur.carretriever.model.User;
import com.benameur.carretriever.model.UserBank;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // Les différentes variables de notre applications
    private SharedPreferences preferences;

    // Branchements des différents élements de notre activity
    private EditText pseudoLogin;
    private EditText passwordLogin;
    private EditText pseudoRegister;
    private EditText passwordRegister;
    private EditText passwordConfirmRegister;
    private Button loginButton;
    private Button registerButton;
    private User user;
    private UserBank userBank;

    // Les différentes constantes que nous utiliserons pour cette activity
    public static final String KEY_PREF_PSEUDO = "KEY_PREF_PSEUDO";
    public static final String USERBANK_DATA = "USERBANK_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Une base de donnée en dur
        userBank = this.generateListOfUsers();
        user = new User();

        pseudoLogin = (EditText) findViewById(R.id.activity_main_login_pseudo);
        passwordLogin = (EditText) findViewById(R.id.activity_main_login_password);
        loginButton = (Button) findViewById(R.id.activity_main_login_button);
        pseudoRegister = (EditText) findViewById(R.id.activity_main_register_pseudo);
        passwordRegister = (EditText) findViewById(R.id.activity_main_register_password);
        passwordConfirmRegister = (EditText) findViewById(R.id.activity_main_register_confirmation_password);
        registerButton = (Button) findViewById(R.id.activity_main_register_button);

        preferences = getPreferences(MODE_PRIVATE);
        String pseudo = preferences.getString(KEY_PREF_PSEUDO, null);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // On récupère les différents champs
                // On vérifie les champs
                String pseudo = pseudoLogin.getText().toString();
                String password = passwordLogin.getText().toString();
                if(pseudo.length() < 1) {
                    Toast.makeText(MainActivity.this, "Erreur de connexion: Veuillez entrez un identifiant", Toast.LENGTH_SHORT).show();
                } else if(password.length() < 1){
                    Toast.makeText(MainActivity.this, "Erreur de connexion: Veuillez entrez un mot de passe", Toast.LENGTH_SHORT).show();
                }else {
                    user.setPseudo(pseudo);
                    user.setPassword(password);

                    if(userBank.checkUser(user)) {
                        Intent mapActivityIntent = new Intent(MainActivity.this, MapActivity.class);
                        mapActivityIntent.putExtra(KEY_PREF_PSEUDO, user.getPseudo());
                        mapActivityIntent.putExtra(USERBANK_DATA, userBank);
                        startActivity(mapActivityIntent);
                    } else {
                        Toast.makeText(MainActivity.this, "Erreur: Cet utilisateur n'existe pas", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pseudo = pseudoRegister.getText().toString();
                String password = passwordRegister.getText().toString();
                String confirm = passwordConfirmRegister.getText().toString();

                if(pseudo.length() < 1) {
                    Toast.makeText(MainActivity.this, "Erreur de connexion: Veuillez entrez un identifiant", Toast.LENGTH_SHORT).show();
                } else if(password.length() < 1) {
                    Toast.makeText(MainActivity.this, "Erreur de connexion: Veuillez entrez un mot de passe", Toast.LENGTH_SHORT).show();
                } else if(confirm.length() < 1) {
                    Toast.makeText(MainActivity.this, "Erreur de connexion: Veuillez entrez une confirmation", Toast.LENGTH_SHORT).show();
                } else if(!password.equals(confirm)) {
                    Toast.makeText(MainActivity.this, "Erreur de connexion: Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                } else {
                    user.setPseudo(pseudo);
                    user.setPassword(password);
                    if(!userBank.checkPseudo(user)) {
                        userBank.addUser(user);
                        Intent mapActivityIntent = new Intent(MainActivity.this, MapActivity.class);
                        mapActivityIntent.putExtra(USERBANK_DATA, userBank);
                        startActivity(mapActivityIntent);
                    } else {
                        Toast.makeText(MainActivity.this, "Erreur d'enregistrement: un utilisateur avec ce pseudo existe déjà", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    private UserBank generateListOfUsers() {
        User user1 = new User("Théo", "123", false);
        User user2 = new User("Paul", "123", true);
        // Coordonnée de l'université de Valenciennes
        Car car = new Car(50.3536,3.5110 );
        user2.setCar(car);

        return new UserBank(new ArrayList<>(Arrays.asList(
            user1,
            user2
        )));
    }

}
