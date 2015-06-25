package com.example.aps_meu_dinheiro_servidor_usuarioDao;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import com.example.aps_meu_dinheiro_servidor_usuario.Usuario;

@XmlRootElement
public class UsuarioStore {
	private String nome;
	private ArrayList<Usuario> usuarioList;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Usuario> getUsuarioList() {
		return usuarioList;
	}

	public void setUsuarioList(ArrayList<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}

}
