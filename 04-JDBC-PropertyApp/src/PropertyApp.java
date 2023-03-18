import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author Omprakash
 * @Company iNeuron
 * @see www.ineuron.ai
 *
 */
public class PropertyApp {

		public static void main(String[] args) throws Exception  {
		FileInputStream fis	= new FileInputStream("application.properties");
		Properties properties=new Properties();
		properties.load(fis);
		
		String url = properties.getProperty("url");
		String user = properties.getProperty("username");
		String password = properties.getProperty("password");
		
		System.out.println("Url is : "+url);
		System.out.println("user is : "+user);
		System.out.println("password is : "+password);
	}
}

