import com.nazarov.shop.dao.jdbc.JDBCProductDao;
import com.nazarov.shop.service.ProductService;
import com.nazarov.shop.web.servlets.AddProductServlet;
import com.nazarov.shop.web.servlets.DeleteProductServlet;
import com.nazarov.shop.web.servlets.EditProductsServlet;
import com.nazarov.shop.web.servlets.ShowAllProductsRequestServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Launcher {
    public static void main(String[] args) throws Exception {



        JDBCProductDao jdbcProductDao = new JDBCProductDao();

        ProductService productService = new ProductService(jdbcProductDao);

        ShowAllProductsRequestServlet showAllProductsRequestServlet = new ShowAllProductsRequestServlet(productService);

        AddProductServlet addProductServlet = new AddProductServlet(productService);

        EditProductsServlet editProductsServlet = new EditProductsServlet(productService);

        DeleteProductServlet deleteProductServlet = new DeleteProductServlet(productService);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(showAllProductsRequestServlet),"/products");
        contextHandler.addServlet(new ServletHolder(addProductServlet),"/products/add");
        contextHandler.addServlet(new ServletHolder(editProductsServlet),"/products/edit/*");
        contextHandler.addServlet(new ServletHolder(deleteProductServlet),"/products/delete/*");

        Server server = new Server(8080);
        server.setHandler(contextHandler);
        server.start();

    }
}
