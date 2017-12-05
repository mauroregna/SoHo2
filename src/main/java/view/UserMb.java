package view;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

import controller.ImageController;
import controller.UserController;
import view.AuthMb;
import model.Image;


@Named
@MultipartConfig(location="/tmp",
fileSizeThreshold=1024*1024, 
maxFileSize=1024*1024*5,
maxRequestSize=1024*1024*5*5)

public class UserMb {
	
	@Inject
	private AuthMb auth;
	@Inject 
	private ImageController imgCntl;
	@Inject
	private UserController usrCntl;
	private Part file;
	
	public void updateImageProfile(){
		try {
			Image img = null;
			if (file != null && file.getSize() > 0 && file.getContentType().startsWith("image/")) {
				img = imgCntl.upload(file);
				usrCntl.loadImageProfile(auth.getCurrentUser().getId(), img);
				auth.getCurrentUser().setImage(img);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se guardo la foto de perfil",
						null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}

		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"No pudimos subir el erchivo de imagen :" + e.getMessage(), null);
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}
	
	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}
	
}