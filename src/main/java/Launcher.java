import com.nazarov.shop.dao.jdbc.JDBCProductDao;
import com.nazarov.shop.service.ProductService;
import com.nazarov.shop.web.servlets.ShowAllProductsRequestServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Launcher {
    public static void main(String[] args) throws Exception {



        JDBCProductDao jdbcProductDao = new JDBCProductDao();

        ProductService productService = new ProductService(jdbcProductDao);

        ShowAllProductsRequestServlet showAllProductsRequestServlet = new ShowAllProductsRequestServlet(productService);



        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(showAllProductsRequestServlet),"/products");

        Server server = new Server(8080);
        server.setHandler(contextHandler);
        server.start();

    }
}
