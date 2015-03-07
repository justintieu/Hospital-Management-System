import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil 
{
	    private static SessionFactory sessionFactory;
	    private static ServiceRegistry serviceRegistry;

	    private static SessionFactory configureSessionFactory() throws HibernateException 
	    {
	    	Configuration configuration = new Configuration().configure();
	    	StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
	    	applySettings(configuration.getProperties());
	    	sessionFactory = configuration.buildSessionFactory(builder.build()); 

	    	return sessionFactory;
	    }


	    public static SessionFactory getSessionFactory() {
	        return configureSessionFactory();

	    }
}

