package sale.servlet;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import sale.base.BaseSale;
import sale.model.Image;
import sale.model.Product;
import sale.util.LookupBean;
import sale.util.URLUtil;

public class UploadImage extends BaseSale{
	private File file;
	private String fileName;
	private String type;
	private String typeUpload;
	private String parentId;
	private LookupBean lookupBean;
	
	public String uploadImage(){
		if(null != file){
			try {
				BufferedImage image = ImageIO.read(file);
				File fileUpload = new File(URLUtil.PATH_SAVE_DIR + URLUtil.TYPE_PRODUCT + fileName + "." + type);
				if (!fileUpload.exists()) {
					fileUpload.mkdirs();
	            }
				ImageIO.write(image, type, fileUpload);
				Image imageUpload = new Image();
				imageUpload.setCreateDate(new Timestamp(new Date().getTime()));
				imageUpload.setUrl(fileName + "." + type);
				imageUpload.setType(typeUpload);
				imageUpload.setParent(parentId);
				imageUpload.setPartyId(0);
				Product product = lookupBean.getProductDao().getProduct(parentId);
				if(null != product){
					List<Image> listImage = product.getImages();
					if(null != listImage){
						for(Image imagecheck : listImage){
							if(imagecheck.getUrl().contains(fileName))
								return SUCCESS;
						}
					}
				}
				lookupBean.getImageDao().createImage(imageUpload);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}

	public File getFile() {
		return file;
	}

	public String getFileName() {
		return fileName;
	}

	public String getType() {
		return type;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeUpload() {
		return typeUpload;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public void setTypeUpload(String typeUpload) {
		this.typeUpload = typeUpload;
	}

	public void setLookupBean(LookupBean lookupBean) {
		this.lookupBean = lookupBean;
	}
}
