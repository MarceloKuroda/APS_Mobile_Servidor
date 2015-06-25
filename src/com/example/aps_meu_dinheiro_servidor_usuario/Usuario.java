package com.example.aps_meu_dinheiro_servidor_usuario;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Usuario {
	private long idusuario;
	private String login;
	private String senha;

	public long getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(long idusuario) {
		this.idusuario = idusuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
