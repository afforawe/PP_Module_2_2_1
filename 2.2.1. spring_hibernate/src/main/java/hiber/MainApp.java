package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car audi = new Car("Audi", 6);
        Car bmw = new Car("BMW", 5);
        Car mercedes = new Car("Mercedes", 2);
        Car toyota = new Car("Toyota", 1);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", audi));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", mercedes));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", toyota));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", bmw));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }

        System.out.println("--------------------------------");
        System.out.println(userService.findUserByCar("Audi", 6).toString());


        context.close();
    }
}
