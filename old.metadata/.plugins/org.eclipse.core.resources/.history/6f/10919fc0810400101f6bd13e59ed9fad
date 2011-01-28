package com.assaassociates.syraway.proto;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.assaassociates.syraway.dao.ICollaboratorDAO;
import com.assaassociates.syraway.model.Collaborator;


/**
 * 
 */

/**
 * @author waheb
 *
 */
public class TestServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6886483498496497962L;

	
	@Override
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		arg1.setContentType("text/html");
		PrintWriter out = arg1.getWriter();
		out.print("<b1>Test</b1>");

		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());

    	//listWiredBeans(out, ctx);
    	
    	ICollaboratorDAO oCollaboratorDAOImpl = ctx.getBean("CollaboratorDAO", ICollaboratorDAO.class);
    	
    	//out.print("<br/> CollaboratorDAO is :" + oTest );
    	
    	if(oCollaboratorDAOImpl != null){
    		String strName = oCollaboratorDAOImpl.getCollaboratorNameById("test");
    		if(strName != null){
    			out.print("<br/> CollaboratorDAO.getCollaboratorNameById(test) : " + strName );
    		}else{
    			Collaborator oCollaborator = new Collaborator();
    			final String strTmp = ("User" + (new Date()).getTime());
    			oCollaborator.setId(strTmp);
    			oCollaborator.setFirstName(strTmp);
    			oCollaborator.setLastName(strTmp);
    			
    			oCollaboratorDAOImpl.addCollaborator(oCollaborator);
    		}
    	}
	}


	private void listWiredBeans(PrintWriter out, WebApplicationContext ctx) {
		if(ctx != null){
    		String[] beans = ctx.getBeanDefinitionNames();
    		for (int i = 0; i < beans.length; i++) {
    			out.print("<br/>");
				out.print("bean : " + beans[i]);
			}
    	}
	}

}
