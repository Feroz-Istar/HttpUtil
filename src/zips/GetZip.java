package zips;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetZip
 */
@WebServlet("/GetZip")
public class GetZip extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetZip() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		File f = new File("C:\\Users\\Feroz\\workspace1111\\HttpUtil\\bb.zip");


        response.setHeader("Content-Disposition","attachment;filename=\"" + "bb.zip" + "\"");
        response.setContentLength((int)f.length());  

            response.setContentType("application/zip");
            response.setHeader("Content-Encoding", "gzip");
            response.addHeader("Content-Disposition","attachment;filename=\"" + "bb.zip" + "\"");    
            try{
            	byte[] arBytes = new byte[(int)f.length()];   
                FileInputStream is = new FileInputStream(f);   
                is.read(arBytes);   
                ServletOutputStream op = response.getOutputStream();   
                op.write(arBytes);   
                op.flush();   
         
               }catch(IOException ioe)
               {
                   ioe.printStackTrace();
               }
        
       
	}

	
}
