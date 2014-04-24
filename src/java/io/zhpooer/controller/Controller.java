package io.zhpooer.controller;

import io.zhpooer.bean.CustomerFormBean;
import io.zhpooer.domain.Customer;
import io.zhpooer.exception.CustomerIdConnotBeEmpty;
import io.zhpooer.service.BusinessService;
import io.zhpooer.service.impl.BusinessServiceImpl;
import io.zhpooer.util.WebUtil;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

@SuppressWarnings("serial")
public class Controller extends HttpServlet {
	BusinessService s = new BusinessServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
	        throws ServletException, IOException {
		res.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		String op = req.getParameter("op");
		if ("showAllCustomer".equals(op)) {
			showAllCustomer(req, res);
		} else if ("EditCustomerUI".equals(op)) {
			editCustomerUI(req, res);
		} else if ("DeleteCustomer".equals(op)) {
			deleteCustomer(req, res);
		} else if ("delMultiCustomer".equals(op)) {
			deleteMulti(req, res);
		} else if ("editCustomer".equals(op)) {
			editCustomer(req, res);
		} else if ("addCustomer".equals(op)) {
			addCustomer(req, res);
		} 
	}

	private void addCustomer(HttpServletRequest req, HttpServletResponse res)
	        throws ServletException, IOException {
		CustomerFormBean cfb = WebUtil.fillBean(req, CustomerFormBean.class);
		if (!cfb.validation()) {
			req.setAttribute("formBean", cfb);
			req.getRequestDispatcher("/editCustomer.jsp").forward(req, res);
			return;
		}
		ConvertUtils.register(new DateLocaleConverter(Locale.CHINA), Date.class);
		Customer c = new Customer();
		try {
			BeanUtils.copyProperties(c, cfb);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		String[] hobbies = req.getParameterValues("hobbies");
		if (hobbies != null && hobbies.length > 0) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < hobbies.length; i++) {
				if (i > 0) {
					sb.append(",");
				}
				sb.append(hobbies[i]);
			}
			c.setHobby(sb.toString());
		}
		s.addCustomer(c);
		res.sendRedirect(req.getContextPath() + "/");
	}

	private void editCustomer(HttpServletRequest req, HttpServletResponse res)
	        throws ServletException, IOException {
		CustomerFormBean cfb = WebUtil.fillBean(req, CustomerFormBean.class);
		if (!cfb.validation()) {
			req.setAttribute("formBean", cfb);
			req.getRequestDispatcher("/editCustomer.jsp").forward(req, res);
			return;
		}
		ConvertUtils
		        .register(new DateLocaleConverter(Locale.CHINA), Date.class);
		Customer c = new Customer();
		try {
			BeanUtils.copyProperties(c, cfb);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		String[] hobbies = req.getParameterValues("hobbies");
		if (hobbies != null && hobbies.length > 0) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < hobbies.length; i++) {
				if (i > 0) {
					sb.append(",");
				}
				sb.append(hobbies[i]);
			}
			c.setHobby(sb.toString());
		}
		try {
			s.updateCustomer(c);
		} catch (CustomerIdConnotBeEmpty e) {
			throw new RuntimeException(e);
		}
		res.sendRedirect(req.getContextPath() + "/");
	}

	private void deleteMulti(HttpServletRequest req, HttpServletResponse res)
	        throws IOException {
		String[] ids = req.getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				s.delCustomer(id);
			}
		}
		res.sendRedirect(req.getContextPath() + "/");
	}

	private void deleteCustomer(HttpServletRequest req, HttpServletResponse res)
	        throws IOException {
		String id = req.getParameter("id");
		if (id == null || id == "") {
			res.getWriter().write("id mustnt be null");
		} else {
			s.delCustomer(id);
			res.sendRedirect(req.getContextPath() + "/");
		}
	}

	private void editCustomerUI(HttpServletRequest req, HttpServletResponse res)
	        throws IOException, ServletException {
		String id = req.getParameter("id");
		if (id == null || id == "") {
			res.getWriter().write("id mustnt be null");
		} else {
			Customer c = s.findCustomerById(id);
			if (c == null)
				res.getWriter().write("id is invalid");
			else {
				req.setAttribute("c", c);
				req.getRequestDispatcher("/editCustomer.jsp").forward(req, res);
			}
		}
	}

	private void showAllCustomer(HttpServletRequest req, HttpServletResponse res)
	        throws ServletException, IOException {
		List<Customer> cs = s.findAll();
		req.setAttribute("cs", cs);
		req.getRequestDispatcher("/ListCustomers.jsp").forward(req, res);
	}

}
