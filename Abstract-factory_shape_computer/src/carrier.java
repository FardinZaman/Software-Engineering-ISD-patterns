import java.util.Scanner;

interface shape{
    double perimeter();
    double area();
}

class circle implements shape{
    double radius;

    public circle(double radius){
        this.radius = radius;
    }

    public double perimeter(){
        return Math.PI * radius * 2;
    }

    public double area(){
        return Math.PI * radius * radius;
    }

    public String toString(){
        return "Circle ( radius : " + radius + " )" + "\nPerimeter : " + perimeter() + "\nArea : " + area();
    }
}

class triangle implements shape{
    double arm1;
    double arm2;
    double arm3;

    public triangle(double arm1, double arm2, double arm3)
    {
        this.arm1 = arm1;
        this.arm2 = arm2;
        this.arm3 = arm3;
    }

    public double perimeter(){
        return arm1 + arm2 + arm3;
    }

    public double area(){
        double s = (arm1 + arm2 + arm3) / 2;

        return Math.sqrt(s * (s - arm1) * (s - arm2) * (s - arm3));
    }

    public String toString(){
        return "Triangle ( " + "arm1 = " + arm1 + " , arm2 = " + arm2 + " , arm3 = " + arm3 + " )" + "\nPerimeter : " + perimeter() + "\nArea : " + area();
    }
}

class rectangle implements shape{
    double length;
    double width;

    public rectangle(double length, double width){
        this.length = length;
        this.width = width;
    }

    public double perimeter(){
        return 2 * (length + width);
    }

    public double area(){
        return length * width;
    }

    public String toString(){
        return "Rectangle ( " + "length = " + length + " , width = " + width + " )" + "\nPerimeter : " + perimeter() + "\nArea : " + area();
    }
}

class square implements shape{
    double arm;

    public square(double arm){
        this.arm = arm;
    }

    public double perimeter(){
        return 4 * arm;
    }

    public double area(){
        return arm * arm;
    }

    public String toString(){
        return "Square ( arm : " + arm + " )" + "\nPerimeter : " + perimeter() + "\nArea : " + area();
    }
}

abstract class computer{
    String name;
    String resolution;
    String cpu;
    String mmu;

    public String get_resolution(){
        return resolution;
    }

    public String toString(){
        return "Computer\n" + "Name : " + name +  "\nResolution : " + resolution + "\nCPU : " + cpu + "\nMMU : " + mmu;
    }
}

class computer_A extends computer{
    public computer_A(){
        name = "ComputerA";
        resolution = "200x200";
        cpu = "CPU_A";
        mmu = "MMU_A";
    }
}

class computer_B extends computer{
    public computer_B(){
        name = "ComputerB";
        resolution = "350x250";
        cpu = "CPU_B";
        mmu = "MMU_B";
    }
}

class computer_C extends computer{
    public computer_C(){
        name = "ComputerC";
        resolution = "550x430";
        cpu = "CPU_C";
        mmu = "MMU_C";
    }
}

class together
{
    public shape shp;
    public computer cmp;

    public together(shape shp , computer cmp){
        this.shp = shp;
        this.cmp = cmp;
    }

    public String toString(){
        return  shp + "\n" + cmp;
    }
}

class shape_builder
{
    public shape get_shape(String type)
    {
        if(type.equalsIgnoreCase("circle")){
            Scanner scanner = new Scanner(System.in);
            double r = scanner.nextDouble();
            return new circle(r);
        }

        if(type.equalsIgnoreCase("rectangle")){
            Scanner scanner = new Scanner(System.in);
            double l = scanner.nextDouble();
            double w = scanner.nextDouble();
            return new rectangle(l , w);
        }

        if(type.equalsIgnoreCase("square")){
            Scanner scanner = new Scanner(System.in);
            double r = scanner.nextDouble();
            return new square(r);
        }

        if(type.equalsIgnoreCase("triangle")){
            Scanner scanner = new Scanner(System.in);
            double a = scanner.nextDouble();
            double b = scanner.nextDouble();
            double c = scanner.nextDouble();
            return new triangle(a,b,c);
        }

        return null;
    }
}

class computer_selector
{
    public computer get_computer(String type)
    {
        if(type.equalsIgnoreCase("A")){
            return new computer_A();
        }

        if(type.equalsIgnoreCase("B")){
            return new computer_B();
        }

        if(type.equalsIgnoreCase("C")){
            return new computer_C();
        }

        return null;
    }
}

public class carrier {
    public static void main(String[] args)
    {
        computer_selector cmpslc = new computer_selector();
        shape_builder shpbld = new shape_builder();

        Scanner scanner = new Scanner(System.in);
        String type = scanner.nextLine();
        computer cmp = cmpslc.get_computer(type);
        type = scanner.nextLine();
        shape shp = shpbld.get_shape(type);

        together tgth = new together(shp , cmp);

        System.out.println(tgth);

        type = scanner.nextLine();
        computer cmp2 = cmpslc.get_computer(type);
        type = scanner.nextLine();
        shape shp2 = shpbld.get_shape(type);

        together tgth2 = new together(shp2, cmp2);

        System.out.println(tgth2);
    }
}
