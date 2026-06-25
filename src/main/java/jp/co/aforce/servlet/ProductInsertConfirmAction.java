package jp.co.aforce.servlet;

import java.io.File;
import java.nio.file.Paths;
import java.util.UUID;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import jp.co.aforce.beans.Products;
import jp.co.aforce.beans.Users;
import jp.co.aforce.tool.Action;

public class ProductInsertConfirmAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {
			return "redirect:login-in.jsp";
		}

		Users user = (Users) session.getAttribute("user");

		if (user.getAdmin() != 1) {
			return "redirect:/Top.action";
		}

		String productName = request.getParameter("productName");
		String category = request.getParameter("category");
		String priceText = request.getParameter("price");
		String stockText = request.getParameter("stock");
		String description = request.getParameter("description");

		if (isBlank(productName)
				|| isBlank(category)
				|| isBlank(priceText)
				|| isBlank(stockText)
				|| isBlank(description)) {

			request.setAttribute("errorMessage", "未入力の項目があります。すべて入力してください。");
			return "product-register.jsp";
		}

		int price;
		int stock;

		try {
			price = Integer.parseInt(priceText);
			stock = Integer.parseInt(stockText);
		} catch (NumberFormatException e) {
			request.setAttribute("errorMessage", "価格と在庫は数値で入力してください。");
			return "product-register.jsp";
		}

		String imagePath = saveImage(request);

		Products product = new Products();

		product.setProductName(productName);
		product.setCategory(category);
		product.setPrice(price);
		product.setStock(stock);
		product.setDescription(description);
		product.setImagePath(imagePath);

		request.setAttribute("product", product);

		return "product-insert-confirm.jsp";
	}

	private String saveImage(HttpServletRequest request) throws Exception {

		Part imagePart = request.getPart("imageFile");

		if (imagePart == null || imagePart.getSize() == 0) {
			return null;
		}

		String submittedFileName = Paths.get(imagePart.getSubmittedFileName())
				.getFileName()
				.toString();

		String extension = "";

		int dotIndex = submittedFileName.lastIndexOf(".");
		if (dotIndex != -1) {
			extension = submittedFileName.substring(dotIndex);
		}

		String savedFileName = UUID.randomUUID().toString() + extension;

		String uploadDirPath = request.getServletContext()
				.getRealPath("/image/products");

		File uploadDir = new File(uploadDirPath);

		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		String savePath = uploadDirPath + File.separator + savedFileName;

		imagePart.write(savePath);

		return "image/products/" + savedFileName;
	}

	private boolean isBlank(String value) {
		return value == null || value.isBlank();
	}
}