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

/*interface user
{
    //server serv = new server();

    void update(String message);
    void set_server(String server);
    String get_server();
    void update_got_server(server serv);
}*/

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

                System.out.println("Premium user prompt: Server is partially down now. Do you want to use service from two servers (partially from the server of our company and partially from the server of DEF company) or from one server (server of DEF company)? ");
                System.out.println("Please choose :");
                System.out.println("1. one server");
                System.out.println("2. two servers\n");

                int choice = scanner.nextInt();
                String message = "";

                if(choice == 2)
                {
                    serv.current_user.set_server("ABC & DEF");
                    message = "Using ABC and DEF both";
                }

                if (choice == 1)
                {
                    serv.current_user.set_server("DEF");
                    message = "Using DEF only";
                }

                //serv.current_user.update(message);
            }

            if (serv.current_state.equalsIgnoreCase(state.FULLY_DOWN))
            {
                String message = "Premium user prompt: Server is fully down now. Service is now being provided by DEF company.";
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
                    serv.current_user.update("Premium user prompt: Server is fully down now. All of your services has been shifted to the server of DEF company.");
                }

                else
                    serv.current_user.update("Premium user prompt: Server is fully down now. All of your services has been shifted to the server of DEF company.");
            }
        }

        if (serv.previous_state.equalsIgnoreCase((state.FULLY_DOWN)))
        {
            if (serv.current_state.equalsIgnoreCase(state.OPERATIONAL))
            {
                serv.current_user.update("Premium user prompt: Now the server is operational again.");
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

                System.out.println("Regular user prompt: Server is partially down now. Do you want to continue using the limited functionality or pay $20 per hour to enjoy the full functionality taking service from server of DEF company? In the 2nd case, all your data will be copied to the server of DEF company.");
                System.out.println("Please choose :");
                System.out.println("1. limited functionality");
                System.out.println("2. pay for full functionality");

                int choice = scanner.nextInt();

                if (choice == 1)
                {
                    //serv.current_user.update("Continuing with limited functionality on ABC server");
                }

                if (choice == 2)
                {
                    serv.current_user.set_server("DEF");
                    /*serv.current_user.update("Enjoying full functionality with DEF server\n" +
                            "Data copied to DEF server");*/
                }
            }

            if (serv.current_state.equalsIgnoreCase(state.FULLY_DOWN))
            {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Regular user prompt: Server is fully down now. Do you want to pay $20 per hour to take service from the server of DEF company? Note that, it will copy all your data to the server of DEF company.");
                System.out.println("Please choose :");
                System.out.println("1. yes, pay $20 per hour\n" +
                        "2. no");

                int choice = scanner.nextInt();

                if (choice == 1)
                {
                    serv.current_user.set_server("DEF");
                    /*serv.current_user.update("Enjoying full functionality with DEF server\n" +
                            "Data copied to DEF server");*/
                }

                else
                {
                    serv.current_user.update("Regular user prompt: All data lost");
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
                    int rand = random.nextInt(1000);

                    serv.current_user.update("Regular user prompt: Now the server is operational again. Your total bill: " + rand + " taka ");
                    serv.current_user.set_server("ABC");
                }

                else
                    serv.current_user.update("Regular user prompt: Now the server is operational again. ");
            }

            if (serv.current_state.equalsIgnoreCase(state.FULLY_DOWN))
            {
                serv.current_user.update("Regular user prompt: Server is fully down now. \n");
            }
        }

        if (serv.previous_state.equalsIgnoreCase(state.FULLY_DOWN))
        {
            if (serv.current_state.equalsIgnoreCase(state.OPERATIONAL))
            {
                if (serv.current_user.get_server().equalsIgnoreCase("DEF"))
                {
                    Random random = new Random();
                    int rand = random.nextInt(1000);

                    serv.current_user.update("Regular user prompt: Now the server is operational again. Your total bill: " + rand + " taka");
                    serv.current_user.set_server("ABC");
                }

                else
                    serv.current_user.update("Regular user prompt: Now the server is operational again. ");
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
    userr premus;
    userr regus;

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
        this.premus = new premium_user(this);
    }

    public void set_regular_user()
    {
        this.regus = new regular_user(this);
    }

    public void set_current_state(String current_state)
    {
        this.previous_state = this.current_state;
        this.current_state = current_state;

        /*if(user_type == 1)
            this.current_user.update("State changed from " + this.previous_state + " to " + this.current_state);
        if(user_type == 2)
            this.current_user.update("State changed from " + this.previous_state + " to " + this.current_state);

        /*if(user_type == 1)
            this.notify_premium_user();
        if(user_type == 2)
            this.notify_regular_user();*/
        this.notify_current_users();
    }

    public String get_current_state()
    {
        return this.current_state;
    }

    public void notify_current_users()
    {
        this.current_user = this.premus;
        this.current_user.update_got_server();

        this.current_user = this.regus;
        this.current_user.update_got_server();
    }

    /*public void notify_premium_user()
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

    }*/
}

public class container {
    public static void main(String[] args) {
       server serv = new server();
       serv.set_premium_user();
       serv.set_regular_user();

       //premium_user premus = new premium_user();
       //regular_user regus = new regular_user();

       //serv.set_premium_user(premus);
       //serv.set_regular_user(regus);

       Scanner scanner = new Scanner(System.in);
       int choice, type;

       while (true)
       {
           /*System.out.println("Enter User type : ");
           System.out.println("1. Premium user");
           System.out.println("2. Regular user");*/

           type = scanner.nextInt();

           if(type == 1)
           {
               serv.set_current_state(state.PARTIALLY_DOWN);
           }

           else if(type == 2)
           {
               serv.set_current_state(state.FULLY_DOWN);
           }

           else if(type == 0)
           {
               serv.set_current_state(state.OPERATIONAL);
           }

           else
               continue;
           /*while(true) {

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

           }*/

        }
    }
}
