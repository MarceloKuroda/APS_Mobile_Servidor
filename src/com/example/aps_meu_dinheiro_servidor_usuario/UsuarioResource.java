package com.example.aps_meu_dinheiro_servidor_usuario;



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
import com.example.aps_meu_dinheiro_servidor_usuarioDao.UsuarioXmlDAO;

@Path("usuario")
public class UsuarioResource {

	@Path("{login}/login/{senha}")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getAll(@PathParam("login") String login, @PathParam("senha") String senha) {
		UsuarioXmlDAO dao = new UsuarioXmlDAO();
    	return dao.getAll(login, senha);
    }
	
	@Path("{id}")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getId(@PathParam("id") long id) {
		UsuarioXmlDAO dao = new UsuarioXmlDAO();
    	return dao.getById(id);
    }
	
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario create(Usuario usuario) {
    	UsuarioXmlDAO dao = new UsuarioXmlDAO();
    	dao.save(usuario);
    	return usuario;
    }

    @Path("{id}/{login}/{senha}")
    @PUT
    @Produces(MediaType.TEXT_PLAIN)    
    public String update(@PathParam("id") long id, @PathParam("login") String login, @PathParam("senha") String senha) {
    	UsuarioXmlDAO dao = new UsuarioXmlDAO();
    	return dao.update(id,  login, senha);
    }    
    
    @Path("{id}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)    
    public String delete(@PathParam("id") long id) {
    	ContaXmlDAO Cdao = new ContaXmlDAO();
    	Cdao.removerContas(id);
    	
    	UsuarioXmlDAO dao = new UsuarioXmlDAO();
    	return dao.remove(id);
    }
	
}
