package by.bsac;

import by.bsac.dao.UserDao;
import by.bsac.models.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        ApplicationContext app_ctx = new AnnotationConfigApplicationContext(SpringContext.class);

        UserDao user_dao = app_ctx.getBean(UserDao.class);

        System.out.println("Find all users: ");

        for (User user : user_dao.findAll()) {
            System.out.println(user.toString());
        }

        System.out.println();
        System.out.println("Find user by ID = 3 ");
        System.out.println();

        User id3 = user_dao.findById(3L);

        if(id3 == null) System.out.println( "User with ID = 3 not found. ");
        else System.out.println(id3.toString());

        System.out.println();
        System.out.println("Find user by ID = 333 ");
        System.out.println();

        User id333 = user_dao.findById(333L);

        if(id333 == null) System.out.println( "User with ID = 333 not found. ");
        else System.out.println(id333.toString());

        System.out.println();
        System.out.println("Create user Artyom Tkachev ");
        System.out.println();

        //User artyom_tkachev = new User("Artyom", "Tkachev");

        // user_dao.create(artyom_tkachev);

        System.out.println();
        System.out.println("Find user with fname = Glavniy ");
        System.out.println();

        User glavniy_user = user_dao.findByFname("Glavniy");

        System.out.println(glavniy_user.toString());

        System.out.println();
        System.out.println("Find all user with lname = voditel ");
        System.out.println();

        List<User> voditel_user = user_dao.findByLname("voditel");

        for(User user : voditel_user)
            System.out.println(user.toString());
    }

}
