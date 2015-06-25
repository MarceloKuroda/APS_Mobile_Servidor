package com.example.aps_meu_dinheiro_servidor_conta;



import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.aps_meu_dinheiro_servidor_contaDao.ContaXmlDAO;

@Path("conta")
public class ContaResource {

	@Path("{idusuario}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Conta> getAll(@PathParam("idusuario") long idusuario) {
		ContaXmlDAO dao = new ContaXmlDAO();
		return dao.getAll(idusuario);
	}
	
	@Path("{tipo}/{pago}/{idusuario}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Conta> get(@PathParam("tipo") String tipo, @PathParam("pago") boolean pago, @PathParam("idusuario") long idusuario) {
		ContaXmlDAO dao = new ContaXmlDAO();
		return dao.get(tipo, pago, idusuario);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Conta createDespesa(Conta conta) {
		ContaXmlDAO dao = new ContaXmlDAO();
		dao.save(conta);
		return conta;
	}

	
	@Path("{id}/update2/{descricao}/{valor}/{data}")
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public String update(@PathParam("id") long id, @PathParam("descricao") String descricao, @PathParam("valor") double valor, @PathParam("data") String data) {
		String desc = "";
		for(int i = 0; i < descricao.length(); i++){
			if (!String.valueOf(descricao.charAt(i)).equals("_")){
				desc += String.valueOf(descricao.charAt(i));
			}
			if (String.valueOf(descricao.charAt(i)).equals("_")){
				desc += String.valueOf(" ");
			}
		}
		
		String d = "";
		for(int i = 0; i < data.length(); i++){
			d += String.valueOf(data.charAt(i));
			if (i == 1 || i == 3){
				d += "/";
			}
		}
		
		ContaXmlDAO dao = new ContaXmlDAO();
		return dao.update(id, desc, valor, d);
	}

	@Path("{id}/update/{pago}")
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public String update2(@PathParam("id") long id, @PathParam("pago") boolean pago) {
		ContaXmlDAO dao = new ContaXmlDAO();

		return dao.update2(id, pago);
	}
	
	@Path("{id}")
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public String remove(@PathParam("id") long id) {
		ContaXmlDAO dao = new ContaXmlDAO();

		return dao.remove(id);
	}

}
