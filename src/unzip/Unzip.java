package unzip;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Unzip {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			String path ="C:/Users/Feroz/Desktop/aa";
	}
	public void unzip(Path zipFile, Path outputPath){
	    try (ZipInputStream zis = new ZipInputStream(Files.newInputStream(zipFile))) {

	        ZipEntry entry = zis.getNextEntry();

	        while (entry != null) {

	            Path newFilePath = outputPath.resolve(entry.getName());
	            if (entry.isDirectory()) {
	                Files.createDirectories(newFilePath);
	            } else {
	                if(!Files.exists(newFilePath.getParent())) {
	                    Files.createDirectories(newFilePath.getParent());
	                }
	                try (OutputStream bos = Files.newOutputStream(outputPath.resolve(newFilePath))) {
	                    byte[] buffer = new byte[Math.toIntExact(entry.getSize())];

	                    int location;

	                    while ((location = zis.read(buffer)) != -1) {
	                        bos.write(buffer, 0, location);
	                    }
	                }
	            }
	            entry = zis.getNextEntry();
	        }
	    }catch(IOException e){
	        throw new RuntimeException(e);
	        //handle your exception
	    }
}
