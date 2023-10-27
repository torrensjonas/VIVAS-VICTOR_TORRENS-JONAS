import java.sql.Connection;
import java.sql.DriverManager;

public class Application {
	public static void main(String[] args) {


				Connection connection = null;
				try {

					Class.forName("org.h2.Driver");
					connection = DriverManager.getConnection
							("jdbc:h2:~/odontologia;INIT=RUNSCRIPT FROM'create.sql'", "sa", "sa");

				} catch (Exception exception) {
					exception.printStackTrace();

				} finally {
					try {
						connection.close();
					} catch (Exception exception) {
						exception.printStackTrace();

					}
				}
			}
		}



