import java.util.LinkedList;
import java.util.Scanner;


interface shake {
    void name();
    void milk();
    void sugar();
    void ingredients();
    void price();
    void extra(boolean lactose , boolean candy , boolean cookie);

    Product get_shake();
}

class Product{
    LinkedList<String> pro;
    public Product(){
        pro = new LinkedList<String>();
    }

    public void add_properties(String p){
        pro.addLast(p);
    }

    public void show() {
        for(int i = 0 ; i < pro.size() ; i++) {
            System.out.println(pro.get(i));
        }
    }
}

class chocolate_shake implements shake{

    int base_price = 230;
    boolean lactose;
    boolean candy;
    boolean cookie;

    int almond_price = 0;
    int candy_price = 0;
    int cookie_price = 0;

    private Product product = new Product();

    public void name(){
        product.add_properties("Chocolate shake is created");
    }

    public void milk(){
        if(lactose == true)
            product.add_properties("It contains milk");
        else {
            product.add_properties("It contains almond milk");
            almond_price = 60;
        }
    }

    public void sugar(){
        product.add_properties("It contains sugar");
    }

    public void ingredients(){
        product.add_properties("It contains chocolate syrup");
        product.add_properties("It contains chocolate icecream");
    }

    public void extra(boolean lactose , boolean candy , boolean cookie){
        this.lactose = lactose;
        this.candy = candy;
        this.cookie = cookie;
    }

    public void price(){
        product.add_properties("It's basic price : " + base_price);

        if(lactose == false){
            product.add_properties("Extra 60 charged for almond milk");
        }

        if(candy == true){
            candy_price = 50;
            product.add_properties("Extra 50 charged for candy on top");
        }
        if(cookie == true) {
            cookie_price = 40;
            product.add_properties("Extra 40 charged for cookie on top");
        }

        int total_price = 0;
        total_price = base_price + almond_price + candy_price + cookie_price;
        product.add_properties("It's price is : " + total_price);
    }

    public  Product get_shake(){
        return product;
    }
}

class coffee_shake implements shake{

    int base_price = 230;
    boolean lactose;
    boolean candy;
    boolean cookie;

    int almond_price = 0;
    int candy_price = 0;
    int cookie_price = 0;

    private Product product = new Product();

    public void name(){
        product.add_properties("Coffee shake is created");
    }

    public void milk(){
        if(lactose == true)
            product.add_properties("It contains milk");
        else {
            product.add_properties("It contains almond milk");
            almond_price = 60;
        }
    }

    public void sugar(){
        product.add_properties("It contains sugar");
    }

    public void ingredients(){
        product.add_properties("It contains coffee");
        product.add_properties("It contains jello");
    }

    public void extra(boolean lactose , boolean candy , boolean cookie){
        this.lactose = lactose;
        this.candy = candy;
        this.cookie = cookie;
    }

    public void price(){
        product.add_properties("It's basic price : " + base_price);

        if(lactose == false){
            product.add_properties("Extra 60 charged for almond milk");
        }

        if(candy == true){
            candy_price = 50;
            product.add_properties("Extra 50 charged for candy on top");
        }
        if(cookie == true) {
            cookie_price = 40;
            product.add_properties("Extra 40 charged for cookie on top");
        }

        int total_price = base_price + almond_price + candy_price + cookie_price;
        product.add_properties("It's price is : " + total_price);
    }

    public  Product get_shake(){
        return product;
    }
}

class strawberry_shake implements shake{

    int base_price = 200;
    boolean lactose;
    boolean candy;
    boolean cookie;

    int almond_price = 0;
    int candy_price = 0;
    int cookie_price = 0;

    private Product product = new Product();

    public void name(){
        product.add_properties("Strawberry shake is created");
    }

    public void milk(){
        if(lactose == true)
            product.add_properties("It contains milk");
        else {
            product.add_properties("It contains almond milk");
            almond_price = 60;
        }
    }

    public void sugar(){
        product.add_properties("It contains sugar");
    }

    public void ingredients(){
        product.add_properties("It contains strawberry syrup");
        product.add_properties("It contains strawberry icecream");
    }

    public void extra(boolean lactose , boolean candy , boolean cookie){
        this.lactose = lactose;
        this.candy = candy;
        this.cookie = cookie;
    }

    public void price(){
        product.add_properties("It's basic price : " + base_price);

        if(lactose == false){
            product.add_properties("Extra 60 charged for almond milk");
        }

        if(candy == true){
            candy_price = 50;
            product.add_properties("Extra 50 charged for candy on top");
        }
        if(cookie == true) {
            cookie_price = 40;
            product.add_properties("Extra 40 charged for cookie on top");
        }

        int total_price = base_price + almond_price + candy_price + cookie_price;
        product.add_properties("It's price is : " + total_price);
    }

    public  Product get_shake(){
        return product;
    }
}

class vanilla_shake implements shake{

    int base_price = 190;
    boolean lactose;
    boolean candy;
    boolean cookie;

    int almond_price = 0;
    int candy_price = 0;
    int cookie_price = 0;

    private Product product = new Product();

    public void name(){
        product.add_properties("Vanilla shake is created");
    }

    public void milk() {
        if (lactose == true)
            product.add_properties("It contains milk");
        else {
            product.add_properties("It contains almond milk");
            almond_price = 60;
        }
    }

    public void sugar(){
        product.add_properties("It contains sugar");
    }

    public void ingredients(){
        product.add_properties("It contains vanilla flavoring");
        product.add_properties("It contains jello");
    }

    public void extra(boolean lactose , boolean candy , boolean cookie){
        this.lactose = lactose;
        this.candy = candy;
        this.cookie = cookie;
    }

    public void price(){
        product.add_properties("It's basic price : " + base_price);

        if(lactose == false){
            product.add_properties("Extra 60 charged for almond milk");
        }

        if(candy == true){
            candy_price = 50;
            product.add_properties("Extra 50 charged for candy on top");
        }
        if(cookie == true) {
            cookie_price = 40;
            product.add_properties("Extra 40 charged for cookie on top");
        }

        int total_price = base_price + almond_price + candy_price + cookie_price;
        product.add_properties("It's price is : " + total_price);
    }

    public  Product get_shake(){
        return product;
    }
}

class zero_shake implements shake{

    int base_price = 240;
    boolean lactose;
    boolean candy;
    boolean cookie;

    int almond_price = 0;
    int candy_price = 0;
    int cookie_price = 0;

    private Product product = new Product();

    public void name(){
        product.add_properties("Zero shake is created");
    }

    public void milk(){
        if(lactose == true)
            product.add_properties("It contains milk");
        else {
            product.add_properties("It contains almond milk");
            almond_price = 60;
        }
    }

    public void sugar(){
        product.add_properties("It contains sweetener , not sugar");
    }

    public void ingredients(){
        product.add_properties("It contains vanilla flavoring");
        product.add_properties("It contains sugar-free jello");
    }

    public void extra(boolean lactose , boolean candy , boolean cookie){
        this.lactose = lactose;
        this.candy = candy;
        this.cookie = cookie;
    }

    public void price(){
        product.add_properties("It's basic price : " + base_price);

        if(lactose == false){
            product.add_properties("Extra 60 charged for almond milk");
        }

        if(candy == true){
            candy_price = 50;
            product.add_properties("Extra 50 charged for candy on top");
        }
        if(cookie == true) {
            cookie_price = 40;
            product.add_properties("Extra 40 charged for cookie on top");
        }

        int total_price = base_price + almond_price + candy_price + cookie_price;
        product.add_properties("It's price is : " + total_price);
    }

    public  Product get_shake(){
        return product;
    }
}

class shake_shack{

    public boolean lactose = true;
    public boolean candy = false;
    public boolean cookie = false;

    shake shk;
    public void produce_shack(shake given){
        shk = given;
        shk.name();
        shk.extra(lactose , candy , cookie);
        shk.milk();
        shk.sugar();
        shk.ingredients();
        shk.price();
    }
}

public class carrier {
    public static void main(String[] args) {

        while (true) {
            String choice;

            LinkedList<Product> order_list = new LinkedList<Product>();

            System.out.println("");
            System.out.println("For opening a order press 'O'");
            //System.out.println("For closing the order press 'E'");

            Scanner in = new Scanner(System.in);
            choice = in.nextLine();

            int tracker = 0;

            if (choice.equalsIgnoreCase("O")) {
                while(true) {

                    shake_shack order = new shake_shack();

                    System.out.println("Choose the shake");
                    System.out.println(" 1.Chocolate shake");
                    System.out.println(" 2.Coffee shake");
                    System.out.println(" 3.Strawberry shake");
                    System.out.println(" 4.Vanilla shake");
                    System.out.println(" 5.Zero shake");
                    System.out.println("For closing the order press 'E'");

                    String choice_shake;
                    Scanner in2 = new Scanner(System.in);
                    choice_shake = in2.nextLine();

                    shake type_ordered;
                    type_ordered = new chocolate_shake();

                    if (choice_shake.equalsIgnoreCase("1")) {
                        type_ordered = new chocolate_shake();
                    }
                    else if (choice_shake.equalsIgnoreCase("2")) {
                        type_ordered = new coffee_shake();
                    }
                    else if (choice_shake.equalsIgnoreCase("3")) {
                        type_ordered = new strawberry_shake();
                    }
                    else if (choice_shake.equalsIgnoreCase("4")) {
                        type_ordered = new vanilla_shake();
                    }
                    else if (choice_shake.equalsIgnoreCase("5")) {
                        type_ordered = new zero_shake();
                    }
                    else if (choice_shake.equalsIgnoreCase("E")) {
                        if(tracker == 0){
                            System.out.println("Please choose at least one item");
                            continue;
                        }

                        System.out.println("Your order is closed");
                        System.out.println("");
                        System.out.println("You ordered " + tracker + " items");
                        System.out.println("");

                        for(int i=0 ; i<order_list.size() ; i++){
                            order_list.get(i).show();
                            System.out.println("");
                        }

                        break;
                    }
                    else if (choice_shake.equalsIgnoreCase("O")){
                        System.out.println("An order is running");
                        System.out.println("You want to add something ?");
                        continue;
                    }
                    else
                        continue;

                    System.out.println("You want lactose free ? Press 'Y' or 'N'");
                    String choice_lactose;
                    Scanner in3 = new Scanner(System.in);
                    choice_lactose = in3.nextLine();
                    if (choice_lactose.equalsIgnoreCase("Y"))
                        order.lactose = false;

                    System.out.println("You want candy on top ? Press 'Y' or 'N'");
                    String choice_candy;
                    Scanner in4 = new Scanner(System.in);
                    choice_candy = in4.nextLine();
                    if (choice_candy.equalsIgnoreCase("Y"))
                        order.candy = true;

                    System.out.println("You want cookie on top ? Press 'Y' or 'N'");
                    String choice_cookie;
                    Scanner in5 = new Scanner(System.in);
                    choice_cookie = in5.nextLine();
                    if (choice_cookie.equalsIgnoreCase("Y"))
                        order.cookie = true;

                    order.produce_shack(type_ordered);
                    Product made = type_ordered.get_shake();
                    order_list.addLast(made);

                    tracker++;
                    //made.show();
                }


            }

            else
                continue;

        }
    }
}

