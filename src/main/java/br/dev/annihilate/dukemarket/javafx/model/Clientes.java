/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.dev.annihilate.dukemarket.javafx.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sesi2dia
 */
public class Clientes {
    
    int id;
    String cPF;
    String nome;
    String endereco;
    String cidade;
    String uF;
    Calendar dataCadastro;

    public Clientes() {
    }

    public Clientes(int id, String cPF, String nome, String endereco, String cidade, String uF, Calendar dataCadastro) {
        this.id = id;
        this.cPF = cPF;
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
        this.uF = uF;
        this.dataCadastro = dataCadastro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getcPF() {
        return cPF;
    }

    public void setcPF(String cPF) {
        this.cPF = cPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getuF() {
        return uF;
    }

    public void setuF(String uF) {
        this.uF = uF;
    }

    

    public String getDataCadastro() {
        if (this.dataCadastro != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return sdf.format(this.dataCadastro.getTime());
        } else {
            return "";
        }
    }

    public void setDataCadastro(String strDataCadastro) {
        try {
            this.dataCadastro = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.dataCadastro.setTime(sdf.parse(strDataCadastro));
        } catch (ParseException ex) {

            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    @Override
    public String toString() {
        return String.format("%3d | %15s | %-30s | %15s | %30s | %5s | %10s",this.id,this.cPF,this.nome,this.cidade,this.endereco,this.uF, this.getDataCadastro());
                
    }

}
