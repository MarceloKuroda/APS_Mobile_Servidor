package com.example.aps_meu_dinheiro_servidor_contaDao;



import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import com.example.aps_meu_dinheiro_servidor_conta.Conta;

@XmlRootElement
public class ContaStore {
	private String nome;
	private ArrayList<Conta> contaList;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Conta> getContaList() {
		return contaList;
	}

	public void setContaList(ArrayList<Conta> contaList) {
		this.contaList = contaList;
	}

}
