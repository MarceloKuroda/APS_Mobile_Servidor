package com.example.aps_meu_dinheiro_servidor_usuarioDao;



import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.example.aps_meu_dinheiro_servidor_conta.Conta;
import com.example.aps_meu_dinheiro_servidor_contaDao.ContaStore;
import com.example.aps_meu_dinheiro_servidor_contaDao.ContaXmlDAO;
import com.example.aps_meu_dinheiro_servidor_usuario.Usuario;

public class UsuarioXmlDAO {
	private static final String XML_FILE = "./usuariostore.xml";
	private UsuarioStore usuarioStore;

	public UsuarioXmlDAO() {

		try {
			JAXBContext context = JAXBContext.newInstance(UsuarioStore.class);
			Unmarshaller um = context.createUnmarshaller();
			usuarioStore = (UsuarioStore) um
					.unmarshal(new FileReader(XML_FILE));

		} catch (Exception e) {
			// empty store
			usuarioStore = new UsuarioStore();
			usuarioStore.setNome("store");
			usuarioStore.setUsuarioList(new ArrayList<Usuario>());
		}
	}

	private void saveXML() {
		try {
			JAXBContext context = JAXBContext.newInstance(UsuarioStore.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(usuarioStore, new File(XML_FILE));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void save(Usuario usuario) {
		long newId = System.currentTimeMillis();
		usuario.setIdusuario(newId);
		usuarioStore.getUsuarioList().add(usuario);
		saveXML();
	}

	public Usuario getById(long id) {
		for (Usuario c : usuarioStore.getUsuarioList())
			if (c.getIdusuario() == id)
				return c;
		return null;
	}

	public String remove(long id) {		
		Usuario u = getById(id);
		if (u == null)
			return "usuario nao encontrado";

		usuarioStore.getUsuarioList().remove(u);
		
		saveXML();
		return "usuario removido";
	}

	public String update(long id,  String login, String senha) {
		Usuario c = getById(id);
		if (c == null)
			return "usuario nao encontrado";
		
		for (Usuario u : usuarioStore.getUsuarioList())
			if (u.getIdusuario() != id && u.getLogin().equals(login))
				return "usuario invalido";
			

		c.setLogin(login);
		c.setSenha(senha);
		saveXML();
		return "usuario atualizado";
	}


	public Usuario getAll(String login, String senha) {
		for (Usuario c : usuarioStore.getUsuarioList())
			if (c.getLogin().equals(login) && c.getSenha().equals(senha))
				return c;
			
		Usuario u = new Usuario();
		u.setIdusuario(0);
		u.setLogin("sem login");
		u.setSenha("sem senha");
		return u;		
	}
}
