import java.util.Random;
import java.util.Scanner;

abstract class userr
{
    server serv = new server();

    abstract void update(String message);
    abstract void set_server(String server);
    abstract String get_server();
    abstract void update_got_server();
}

interface user
{
    //server serv = new server();

    void update(String message);
    void set_server(String server);
    String get_server();
    void update_got_server(server serv);
}

class premium_user extends userr
{
    String server;

    public premium_user(server serv)
    {
        this.server = "ABC";
        this.serv = serv;
    }

    public void set_server(String server)
    {
        this.server = server;
    }

    public String get_server()
    {
        return this.server;
    }

    @Override
    public void update_got_server() {
        if (serv.previous_state.equalsIgnoreCase(state.OPERATIONAL))
        {
            if (serv.current_state.equalsIgnoreCase((state.PARTIALLY_DOWN)))
            {
                Scanner scanner = new Scanner(System.in);

                System.out.println("1. Use ABC and DEF both");
                System.out.println("2. Use DEF only\n");

                int choice = scanner.nextInt();
                String message = "";

                if(choice == 1)
                {
                    serv.current_user.set_server("ABC & DEF");
                    message = "Using ABC and DEF both";
                }

                if (choice == 2)
                {
                    serv.current_user.set_server("DEF");
                    message = "Using DEF only";
                }

                serv.current_user.update(message);
            }

            if (serv.current_state.equalsIgnoreCase(state.FULLY_DOWN))
            {
                String message = "Using partner DEF now";
                serv.current_user.update(message);
            }
        }

        if (serv.previous_state.equalsIgnoreCase((state.PARTIALLY_DOWN)))
        {
            if (serv.current_state.equalsIgnoreCase(state.FULLY_DOWN))
            {
                if (serv.current_user.get_server().equalsIgnoreCase("ABC & DEF"))
                {
                    serv.current_user.set_server("DEF");
                    serv.current_user.update("All services shifted to DEF server");
                }
            }
        }
    }

    @Override
    public void update(String message) {
        System.out.println(message);
    }
}

class regular_user extends userr
{
    String server;

    public regular_user(server serv)
    {
        this.server = "ABC";
        this.serv = serv;
    }

    public void set_server(String server)
    {
        this.server = server;
    }

    public String get_server()
    {
        return this.server;
    }

    @Override
    public void update_got_server() {
        if (serv.previous_state.equalsIgnoreCase(state.OPERATIONAL))
        {
            if (serv.current_state.equalsIgnoreCase(state.PARTIALLY_DOWN))
            {
                Scanner scanner = new Scanner(System.in);

                System.out.println("1. Continue with limited functionality");
                System.out.println("2. Transfer to DEF server for $20 per hour");

                int choice = scanner.nextInt();

                if (choice == 1)
                {
                    serv.current_user.update("Continuing with limited functionality on ABC server");
                }

                if (choice == 2)
                {
                    serv.current_user.set_server("DEF");
                    serv.current_user.update("Enjoying full functionality with DEF server\n" +
                            "Data copied to DEF server");
                }
            }

            if (serv.current_state.equalsIgnoreCase(state.FULLY_DOWN))
            {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Press 1 to transfer to DEF server for $20 per hour");

                int choice = scanner.nextInt();

                if (choice == 1)
                {
                    serv.current_user.set_server("DEF");
                    serv.current_user.update("Enjoying full functionality with DEF server\n" +
                            "Data copied to DEF server");
                }

                else
                {
                    serv.current_user.update("All data lost");
                }
            }
        }

        if (serv.previous_state.equalsIgnoreCase(state.PARTIALLY_DOWN))
        {
            if (serv.current_state.equalsIgnoreCase(state.OPERATIONAL))
            {
                if (serv.current_user.get_server().equalsIgnoreCase("DEF"))
                {
                    Random random = new Random();
                    int rand = random.nextInt();

                    serv.current_user.update("Bill : " + rand);
                    serv.current_user.set_server("ABC");
                }
            }
        }

        if (serv.previous_state.equalsIgnoreCase(state.FULLY_DOWN))
        {
            if (serv.current_state.equalsIgnoreCase(state.OPERATIONAL))
            {
                if (serv.current_user.get_server().equalsIgnoreCase("DEF"))
                {
                    Random random = new Random();
                    int rand = random.nextInt();

                    serv.current_user.update("Bill : " + rand);
                    serv.current_user.set_server("ABC");
                }
            }
        }
    }

    @Override
    public void update(String message) {
        System.out.println(message);
    }
}

class state
{
    public final static String OPERATIONAL = "operational";
    public final static String PARTIALLY_DOWN = "partially down";
    public final static String FULLY_DOWN = "fully down";
}

class server
{
    //premium_user premus;
    //regular_user regus;

    userr current_user;

    String current_state;
    String previous_state;

    public server()
    {
        current_state = state.OPERATIONAL;
    }

    /*public void set_premium_user(premium_user premus)
    {
        this.premus = premus;
    }*/

    /*public void set_regular_user(regular_user regus)
    {
        this.regus = regus;
    }*/

    public void set_premium_user()
    {
        this.current_user = new premium_user(this);
    }

    public void set_regular_user()
    {
        this.current_user = new regular_user(this);
    }

    public void set_current_state(String current_state , int user_type)
    {
        this.previous_state = this.current_state;
        this.current_state = current_state;

        if(user_type == 1)
            this.current_user.update("State changed from " + this.previous_state + " to " + this.current_state);
        if(user_type == 2)
            this.current_user.update("State changed from " + this.previous_state + " to " + this.current_state);

        /*if(user_type == 1)
            this.notify_premium_user();
        if(user_type == 2)
            this.notify_regular_user();*/
        this.notify_current_user();
    }

    public String get_current_state()
    {
        return this.current_state;
    }

    public void notify_current_user()
    {
        this.current_user.update_got_server();
    }

    public void notify_premium_user()
    {
        if (this.previous_state.equalsIgnoreCase(state.OPERATIONAL))
        {
            if (this.current_state.equalsIgnoreCase((state.PARTIALLY_DOWN)))
            {
                Scanner scanner = new Scanner(System.in);

                System.out.println("1. Use ABC and DEF both");
                System.out.println("2. Use DEF only\n");

                int choice = scanner.nextInt();
                String message = "";

                if(choice == 1)
                {
                    this.current_user.set_server("ABC & DEF");
                    message = "Using ABC and DEF both";
                }

                if (choice == 2)
                {
                    this.current_user.set_server("DEF");
                    message = "Using DEF only";
                }

                this.current_user.update(message);
            }

            if (this.current_state.equalsIgnoreCase(state.FULLY_DOWN))
            {
                String message = "Using partner DEF now";
                this.current_user.update(message);
            }
        }

        if (this.previous_state.equalsIgnoreCase((state.PARTIALLY_DOWN)))
        {
            if (this.current_state.equalsIgnoreCase(state.FULLY_DOWN))
            {
                if (this.current_user.get_server().equalsIgnoreCase("ABC & DEF"))
                {
                    this.current_user.set_server("DEF");
                    this.current_user.update("All services shifted to DEF server");
                }
            }
        }
    }

    public void notify_regular_user()
    {
        if (this.previous_state.equalsIgnoreCase(state.OPERATIONAL))
        {
            if (this.current_state.equalsIgnoreCase(state.PARTIALLY_DOWN))
            {
                Scanner scanner = new Scanner(System.in);

                System.out.println("1. Continue with limited functionality");
                System.out.println("2. Transfer to DEF server for $20 per hour");

                int choice = scanner.nextInt();

                if (choice == 1)
                {
                    this.current_user.update("Continuing with limited functionality on ABC server");
                }

                if (choice == 2)
                {
                    this.current_user.set_server("DEF");
                    this.current_user.update("Enjoying full functionality with DEF server\n" +
                                      "Data copied to DEF server");
                }
            }

            if (this.current_state.equalsIgnoreCase(state.FULLY_DOWN))
            {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Press 1 to transfer to DEF server for $20 per hour");

                int choice = scanner.nextInt();

                if (choice == 1)
                {
                    this.current_user.set_server("DEF");
                    this.current_user.update("Enjoying full functionality with DEF server\n" +
                                      "Data copied to DEF server");
                }

                else
                {
                    this.current_user.update("All data lost");
                }
            }
        }

        if (this.previous_state.equalsIgnoreCase(state.PARTIALLY_DOWN))
        {
            if (this.current_state.equalsIgnoreCase(state.OPERATIONAL))
            {
                if (this.current_user.get_server().equalsIgnoreCase("DEF"))
                {
                    Random random = new Random();
                    int rand = random.nextInt();

                    this.current_user.update("Bill : " + rand);
                    this.current_user.set_server("ABC");
                }
            }
        }

        if (this.previous_state.equalsIgnoreCase(state.FULLY_DOWN))
        {
            if (this.current_state.equalsIgnoreCase(state.OPERATIONAL))
            {
                if (this.current_user.get_server().equalsIgnoreCase("DEF"))
                {
                    Random random = new Random();
                    int rand = random.nextInt();

                    this.current_user.update("Bill : " + rand);
                    this.current_user.set_server("ABC");
                }
            }
        }

    }
}

public class container {
    public static void main(String[] args) {
       server serv = new server();

       //premium_user premus = new premium_user();
       //regular_user regus = new regular_user();

       //serv.set_premium_user(premus);
       //serv.set_regular_user(regus);

       Scanner scanner = new Scanner(System.in);
       int choice, type;

       while (true)
       {
           System.out.println("Enter User type : ");
           System.out.println("1. Premium user");
           System.out.println("2. Regular user");

           type = scanner.nextInt();

           if(type == 1)
               serv.set_premium_user();
           if(type == 2)
               serv.set_regular_user();

           while(true) {

               System.out.println("Change state into : ");

               if (serv.get_current_state().equalsIgnoreCase(state.OPERATIONAL)) {
                   System.out.println("1. " + state.PARTIALLY_DOWN);
                   System.out.println("2. " + state.FULLY_DOWN);

                   choice = scanner.nextInt();

                   if (choice == 1) {
                       serv.set_current_state(state.PARTIALLY_DOWN, type);
                   }

                   if (choice == 2) {
                       serv.set_current_state(state.FULLY_DOWN, type);
                   }
               }

               else if (serv.get_current_state().equalsIgnoreCase(state.PARTIALLY_DOWN)) {
                   System.out.println("1. " + state.OPERATIONAL);
                   System.out.println("2. " + state.FULLY_DOWN);

                   choice = scanner.nextInt();

                   if (choice == 1) {
                       serv.set_current_state(state.OPERATIONAL, type);
                       break;
                   }

                   if (choice == 2) {
                       serv.set_current_state(state.FULLY_DOWN, type);
                   }
               }

               else if (serv.get_current_state().equalsIgnoreCase(state.FULLY_DOWN)) {
                   System.out.println("1. " + state.OPERATIONAL);
                   System.out.println("2. " + state.PARTIALLY_DOWN);

                   choice = scanner.nextInt();

                   if (choice == 1) {
                       serv.set_current_state(state.OPERATIONAL, type);
                       break;
                   }

                   if (choice == 2) {
                       serv.set_current_state(state.PARTIALLY_DOWN, type);
                   }
               }

           }

        }
    }
}
