package com.daonguyen.servlet;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daonguyen.model.Product;

@WebServlet(name = "productServlet", urlPatterns = {"/product", "/product/add", "/product/edit", "/product/remove"})
public class ProductServlet extends HttpServlet {

	List<Product> products = null;

	@Override
	public void init() throws ServletException {
		products = new ArrayList<>();
		products.add(new Product(1, "Laptop", 10, 100));
		products.add(new Product(2, "Mobile", 20, 200));
		products.add(new Product(3, "Desktop", 30, 300));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String action = req.getServletPath();

		switch (action) {
			case "/product":
				if (!products.isEmpty()) {
					req.setAttribute("products", products);
				}
				req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
				break;
			case "/product/add":
				req.getRequestDispatcher("/WEB-INF/view/add.jsp").forward(req, resp);
                break;
            case "/product/edit":
                int editingId = Integer.parseInt(req.getParameter("code"));

                for (Product product : products) {
                    if (product.getCode() == editingId) {
                        req.setAttribute("product", product);
                        break;
                    }
                }

                req.getRequestDispatcher("/WEB-INF/view/edit.jsp").forward(req, resp);
                break;
            case "/product/remove":
                int removingId = Integer.parseInt(req.getParameter("code"));

                for (Product product : products) {
                    if (product.getCode() == removingId) {
                        products.remove(product);
                        break;
                    }
                }

                req.setAttribute("products", products);
                req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
                break;
			default:
				break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

        String action = req.getServletPath();
        System.out.println(action);

        switch (action) {
            case "/product/add":
                Product newProduct = new Product();
                newProduct.setCode(Integer.parseInt(req.getParameter("code")));
                newProduct.setName(req.getParameter("name"));
                newProduct.setAmount(Integer.parseInt(req.getParameter("amount")));
                newProduct.setPrice(Float.parseFloat(req.getParameter("price")));

                products.add(newProduct);
                break;
            case "/product/edit":
                int editingCode = Integer.parseInt(req.getParameter("code"));

                for (Product product : products) {
                    if (product.getCode() == editingCode) {
                        product.setName(req.getParameter("name"));
                        product.setAmount(Integer.parseInt(req.getParameter("amount")));
                        product.setPrice(Float.parseFloat(req.getParameter("price")));
                    }
                }

                break;
            default:
                break;
        }

		resp.sendRedirect(req.getContextPath() + "/product");
	}
}
