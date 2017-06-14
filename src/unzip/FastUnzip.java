package unzip;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class FastUnzip {
	private static final String FILE_NAME = "C:\\Users\\Feroz\\Desktop\\aa\\13.zip";
	private static final String OUTPUT_DIR = "C:\\Users\\Feroz\\Desktop\\aa\\bb\\";
	private static final int BUFFER_SIZE = 8192;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			long start_time = System.currentTimeMillis();
			readUsingZipFile();
			System.out.println("End ha iyedh "+(start_time - System.currentTimeMillis()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void readUsingZipFile() throws IOException {
		final ZipFile file = new ZipFile(FILE_NAME);
		System.out.println("Iterating over zip file : " + FILE_NAME);
		try {
			final Enumeration<? extends ZipEntry> entries = file.entries();
			while (entries.hasMoreElements()) {
				final ZipEntry entry = entries.nextElement();
				System.out.printf("File: %s Size %d Modified on %TD %n", entry.getName(), entry.getSize(),
						new Date(entry.getTime()));
				extractEntry(entry, file.getInputStream(entry));
			}
			System.out.printf("Zip file %s extracted successfully in %s", FILE_NAME, OUTPUT_DIR);
		} finally {
			file.close();
		}
	}

	/* * Example of reading Zip file using ZipInputStream in Java. */
	private static void readUsingZipInputStream() throws IOException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(FILE_NAME));
		final ZipInputStream is = new ZipInputStream(bis);
		try {
			ZipEntry entry;
			while ((entry = is.getNextEntry()) != null) {
				System.out.printf("File: %s Size %d Modified on %TD %n", entry.getName(), entry.getSize(),
						new Date(entry.getTime()));
				extractEntry(entry, is);
			}
		} finally {
			is.close();
		}
	}

	/* * Utility method to read data from InputStream */
	private static void extractEntry(final ZipEntry entry, InputStream is) throws IOException {
		String exractedFile = OUTPUT_DIR + entry.getName();
		File f = new File(exractedFile);
		if(!f.exists()){
            File s = new File(f.getParent());
            s.mkdir();
        }
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(exractedFile);
			final byte[] buf = new byte[BUFFER_SIZE];
			int read = 0;
			int length;
			while ((length = is.read(buf, 0, buf.length)) >= 0) {
				fos.write(buf, 0, length);
			}
		} catch (IOException ioex) {
			fos.close();
		}
	}

}
