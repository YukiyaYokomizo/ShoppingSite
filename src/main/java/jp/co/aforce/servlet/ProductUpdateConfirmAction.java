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
import jp.co.aforce.dao.ProductsDAO;
import jp.co.aforce.tool.Action;

public class ProductUpdateConfirmAction extends Action {

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

		String productIdText = request.getParameter("productId");
		String productName = request.getParameter("productName");
		String category = request.getParameter("category");
		String priceText = request.getParameter("price");
		String stockText = request.getParameter("stock");
		String description = request.getParameter("description");
		String currentImagePath = request.getParameter("currentImagePath");
		String deleteFlagText = request.getParameter("deleteFlag");

		int productId = Integer.parseInt(productIdText);
		int price = Integer.parseInt(priceText);
		int stock = Integer.parseInt(stockText);
		boolean deleteFlag = Boolean.parseBoolean(deleteFlagText);

		ProductsDAO dao = new ProductsDAO();

		Products oldProduct = dao.searchByIdForAdmin(productId);

		if (oldProduct == null) {
			return "redirect:/ProductManage.action";
		}

		String imagePath = saveImage(request);

		if (imagePath == null || imagePath.isBlank()) {
			imagePath = currentImagePath;
		}

		if (imagePath == null || imagePath.isBlank()) {
			imagePath = oldProduct.getImagePath();
		}

		Products product = new Products();

		product.setProductId(productId);
		product.setProductName(productName);
		product.setCategory(category);
		product.setPrice(price);
		product.setStock(stock);
		product.setDescription(description);
		product.setImagePath(imagePath);
		product.setDeleteFlag(deleteFlag);
		product.setSalesCount(oldProduct.getSalesCount());

		request.setAttribute("product", product);

		return "product-update-confirm.jsp";
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
}