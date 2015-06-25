package com.example.aps_meu_dinheiro_servidor_contaDao;



import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.example.aps_meu_dinheiro_servidor_conta.Conta;

public class ContaXmlDAO {

	private static final String XML_FILE = "./contastore.xml";
	private ContaStore contaStore;
	int i = 0;
	
	public ContaXmlDAO() {
		
		try {
			JAXBContext context = JAXBContext.newInstance(ContaStore.class);
			Unmarshaller um = context.createUnmarshaller();
			contaStore = (ContaStore) um.unmarshal(new FileReader(XML_FILE));
			
		} catch (Exception e) {
			//empty store
			contaStore = new ContaStore();
			contaStore.setNome("store");
			contaStore.setContaList( new ArrayList<Conta>() );
		}
	}
	
	private void saveXML() {
		try {
			JAXBContext context = JAXBContext.newInstance(ContaStore.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(contaStore, new File(XML_FILE));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void save(Conta conta) {
		long newId = System.currentTimeMillis();
		conta.setId( newId );
		contaStore.getContaList().add(conta);
		saveXML();
	}



	public Conta getById(long id) {
		for(Conta c : contaStore.getContaList())
			if(c.getId() == id)
				return c;
		return null;
	}

	public String remove(long id) {
		Conta c = getById(id);
		if(c == null)
			return "conta nao encontrada";
		
		contaStore.getContaList().remove(c);
		saveXML();
		return "conta removida";
	}
	
	public void removerContas(long idusuario) {
		i = 1;
		for (Conta c : contaStore.getContaList()){
			if (c.getIdusuario() == idusuario){
				contaStore.getContaList().remove(c);
				saveXML();
				break;
			}
			i++;
		}
		if(i <= contaStore.getContaList().size()){
			removerContas(idusuario);
		}
	}

	public String update(long id, String descricao, double valor, String data) {
		Conta c = getById(id);
		if(c == null)
			return "conta nao encontrada";
		
		c.setDescricao(descricao);
		c.setValor(valor);
		c.setData(data);
		saveXML();
		return "conta atualizada";
	}
	
	public String update2(long id, boolean pago) {
		Conta c = getById(id);
		if(c == null)
			return "conta nao encontrada";
	
		c.setPago(pago);
		saveXML();
		return "lista atualizada";
	}

	
	public List<Conta> getAll(long idusuario) {
		ArrayList<Conta> l =  new ArrayList<>(); 
		for(Conta c : contaStore.getContaList()){
			if(idusuario == c.getIdusuario()){
				l.add(c);
			}
		}
		
		return l;
	}

	public List<Conta> get(String tipo, boolean pago, long idusuario) {
		ArrayList<Conta> l =  new ArrayList<>(); 
		for(Conta c : contaStore.getContaList()){
			if(pago == c.getPago() && tipo.equals(c.getTipo()) && idusuario == c.getIdusuario()){
				l.add(c);
			}
		}
		
		return l;
	}
	
}
