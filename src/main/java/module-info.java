module br.dev.annihilate.dukemarket.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    
    requires java.sql;
    
    opens br.dev.annihilate.dukemarket.javafx to javafx.fxml;
    opens br.dev.annihilate.dukemarket.javafx.controller to javafx.fxml;
   
    exports br.dev.annihilate.dukemarket.javafx;
    exports br.dev.annihilate.dukemarket.javafx.controller;
    exports br.dev.annihilate.dukemarket.javafx.DAO;
    exports br.dev.annihilate.dukemarket.javafx.model;
    exports connection;
    
    requires mysql.connector.java;
}
