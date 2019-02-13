package by.bsac;

import by.bsac.dao.UserDao;
import by.bsac.dao.UserDaoIml;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;

/**
 *
 */
@Configuration
public class SpringContext implements ApplicationContextAware {

    private DataSource data_source;
    private ApplicationContext app_ctx;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.app_ctx = applicationContext;
    }

    @Bean("data_source")
    public DataSource getDataSource() {

        DriverManagerDataSource ds = new DriverManagerDataSource();

        ds.setUrl("jdbc:mysql://localhost:36550/hibernate_demo");
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUsername("test");
        ds.setPassword("test1234");

        this.data_source = ds;

        return ds;
    }

    @Bean("session_factory")
    public LocalSessionFactoryBean getSessionFactory() {

        LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();

        lsfb.setDataSource(this.data_source);
        lsfb.setPackagesToScan("by.bsac.models");
        lsfb.setConfigLocation(this.app_ctx.getResource("classpath:hibernate.cfg.xml"));

        return lsfb;

    }

    @Bean("transactionManager")
    @Description("Transaction manager bean")
    public HibernateTransactionManager transactionManager() {

        //Create TransactionManager object
        HibernateTransactionManager transaction_manager = new HibernateTransactionManager();

        //Set parameters to them:
        transaction_manager.setSessionFactory(getSessionFactory().getObject());

        //Return statement:
        return transaction_manager;

    }

    @Bean("user_dao")
    public UserDao getUserDaoImpl() {
        return new UserDaoIml();
    }


}
