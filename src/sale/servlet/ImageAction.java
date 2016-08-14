package sale.servlet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.opensymphony.xwork2.ActionSupport;

import sale.util.URLUtil;

public class ImageAction extends ActionSupport implements ServletRequestAware {

	byte[] imageInByte = null;
	String imageId;

	private HttpServletRequest servletRequest;

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public ImageAction() {
		System.out.println("ImageAction");
	}

	public String execute() {
		return SUCCESS;
	}

	public synchronized byte[] getCustomImageInBytes() {

		System.out.println("imageId" + imageId);

		BufferedImage originalImage;
		try {
			originalImage = ImageIO.read(getImageFile(this.imageId));
			// convert BufferedImage to byte array
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "jpg", baos);
			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();
		} catch (IOException e) {
			System.out.println(this.imageId + " is not exits!");
			imageInByte = null;
		}

		return imageInByte;
	}

	private File getImageFile(String imageId) {
		if("".equals(imageId))
			imageId = null;
		File file = new File(URLUtil.PATH_SAVE_DIR + URLUtil.TYPE_PRODUCT, imageId);
		System.out.println(file.toString());
		return file;
	}

	public String getCustomContentType() {
		return "image/jpeg";
	}

	public String getCustomContentDisposition() {
		return "anyname.jpg";
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.servletRequest = request;

	}

}
