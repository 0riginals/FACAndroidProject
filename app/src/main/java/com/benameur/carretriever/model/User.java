package com.benameur.carretriever.model;

import java.io.Serializable;

/**
 * Created by Paul VINOT et Théo TURQUIN
 */
public class User implements Serializable {

    private String pseudo;
    private String password;
    private Car car;
    private boolean hasCar;

    public User() {
    }

    public User(String ps, String pass, boolean car) {
        this.setPseudo(ps);
        this.setPassword(pass);
        this.setHasCar(car);
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public boolean isHasCar() {
        return hasCar;
    }

    public void setHasCar(boolean hasCar) {
        this.hasCar = hasCar;
    }

    @Override
    public String toString(){
        return "user with pseudo: " + pseudo;
    }
}
