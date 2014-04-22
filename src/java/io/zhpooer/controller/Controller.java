package io.zhpooer.controller;

import io.zhpooer.domain.Customer;
import io.zhpooer.service.BusinessService;
import io.zhpooer.service.impl.BusinessServiceImpl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Controller extends HttpServlet {
	BusinessService s = new BusinessServiceImpl();
	
	@Override
    protected void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
		res.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		String op = req.getParameter("op");
		if("showAllCustomer".equals(op)){
			showAllCustomer(req, res);
		}
    }

	private void showAllCustomer(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<Customer> cs = s.findAll();
		req.setAttribute("cs", cs);
		req.getRequestDispatcher("/ListCustomers.jsp").forward(req, res);
    }
	
}
