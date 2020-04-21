package signer;

import java.io.File;

public interface FileSigner {
	 
	public void setSignAttributeToFile(File file);
	
	public String fileValidator(File file);
	
}
