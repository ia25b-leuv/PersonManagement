package controller;

import java.io.IOException;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Person;
import service.PersonService;
import java.util.ArrayList;

@WebServlet("/personen")
public class Controller extends HttpServlet{
	@Override
	public void init() throws ServletException{
		PersonService.insert(new Person("Test01", "test01", LocalDate.of(2000, 01, 01)));
		PersonService.insert(new Person("Test02", "test02", LocalDate.of(2000, 01, 01)));
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ArrayList<Person> persons = PersonService.getPersons();
		request.setAttribute("persons", persons);
		request.getRequestDispatcher("listPersons.jsp").forward(request, response);;
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String vorname = request.getParameter("vorname");
		String nachname = request.getParameter("nachname");
		String geburtsdatumString = request.getParameter("geburtsdatum");
		String methode = request.getParameter("method");
		LocalDate geburtsdatum = LocalDate.parse(geburtsdatumString);
		
		if(methode.equals("delete")) {
			doDelete(request, response);
			return;
		}else if(methode.equals("put")) {
			String uuid = request.getParameter("uuid");
			request.setAttribute("uuid", uuid);
			request.setAttribute("vorname", vorname);
			request.setAttribute("nachname", nachname);
			request.setAttribute("geburtsdatum", geburtsdatum);
			request.getRequestDispatcher("updatePerson.jsp").forward(request, response);
			return;
		}else if(methode.equals("updatePerson")) {
			doPut(request, response);
			return;
		}
		
		Person person = new Person(vorname, nachname, geburtsdatum);
		
		PersonService.insert(person);
		
		response.sendRedirect("createPerson.html");
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String uuid = request.getParameter("index");
		
		PersonService.delete(uuid);
		
		//response.sendRedirect("personen");
		response.setStatus(HttpServletResponse.SC_OK);
	}
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String vorname = request.getParameter("vorname");
		String nachname = request.getParameter("nachname");
		String geburtsdatumString = request.getParameter("geburtsdatum");
		String uuid = request.getParameter("uuid");
		
		LocalDate geburtsdatum = LocalDate.parse(geburtsdatumString);
		
		PersonService.update(uuid, new Person(vorname, nachname, geburtsdatum));
		
		response.sendRedirect("personen");
	}
}
