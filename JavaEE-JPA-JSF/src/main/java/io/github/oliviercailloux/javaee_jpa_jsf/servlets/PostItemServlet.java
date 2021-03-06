package io.github.oliviercailloux.javaee_jpa_jsf.servlets;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.oliviercailloux.javaee_jpa_jsf.model.Item;
import io.github.oliviercailloux.javaee_jpa_jsf.service.ItemService;

@WebServlet("/servlet/postItemServlet")
public class PostItemServlet extends HttpServlet {
	@PersistenceContext
	private EntityManager em;

	@Inject
	private ItemService itemS;

	@Override
	@SuppressWarnings("resource")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final Item item = new Item();
		/** Ideally we’d use the client zone here. */
		final ZonedDateTime zonedTimestamp = ZonedDateTime.now(ZoneId.systemDefault());
		item.setName("MyItem dated " + zonedTimestamp);
		itemS.persist(item);
		resp.sendRedirect(resp.encodeRedirectURL("../getItems"));
	}
}
