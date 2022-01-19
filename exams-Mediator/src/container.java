import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

abstract class exam_controller
{
    List<student> students;
    examiner exmn;

    public void setStudents(List<student> students)
    {
        this.students = students;
    }

    public void setExaminer(examiner exmn)
    {
        this.exmn = exmn;
    }

    public abstract void update(List<Integer> checked);
    public abstract void send(participant prtc , String message);
    public abstract void send(participant prtc , String message , List<student> students , List<Integer> marks);
}

class concrete_exam_controller extends exam_controller
{
    List<Integer> request = new ArrayList<>();
    /*private List<student> students;
    private examiner exmn;

    public void setStudents(List<student> students)
    {
        this.students = students;
    }

    public void setExaminer(examiner exmn)
    {
        this.exmn = exmn;
    }*/

    @Override
    public void update(List<Integer> checked) {
        System.out.println("Exam controller prompt:");
        for(int i=0 ; i<request.size() ; i++)
        {
            if(checked.get(i) == -1)
                System.out.print(" Marks of student id " + request.get(i) + " unchanged.");
            else
                System.out.print(" Marks of student id " + request.get(i) + " updated from " + checked.get(i) + " to " + this.exmn.marks.get(request.get(i)-1) + ".");
        }

        System.out.println("");
        for(int i=0 ; i<request.size() ; i++)
        {
            if(checked.get(i) == -1)
                System.out.print(" student id " + request.get(i) + " has been informed about no change in marks.");
            else
                System.out.print(" Updated marks have been sent to student id " + request.get(i) + ".");
        }

        System.out.println("\n");
        for(int i=0 ; i<request.size() ; i++)
        {
            if(checked.get(i) == -1)
                this.students.get(request.get(i)-1).notify("unchanged");
            else
                this.students.get(request.get(i)-1).notify(checked.get(i) + "");
        }
    }

    @Override
    public void send(participant prtc, String message) {
        //List<Integer> request = new ArrayList<>();
        if(message.equalsIgnoreCase("Re-examine"))
        {
            request.add(prtc.get_id());
            //System.out.println("Re-examine request got from student ID : " + prtc.get_id());
            //this.exmn.notify(message , prtc.get_id()-1);
        }

        else
            System.out.println(message);
    }

    @Override
    public void send(participant prtc, String message, List<student> students, List<Integer> marks) {
        if(message.equalsIgnoreCase("Marks"))
        {
            //Scanner scanner = new Scanner(System.in);

            System.out.println("Exam controller prompt: Scripts and marks of student id 1,2,3,4,5 have been received.");

            for(int i=0 ; i<students.size() ; i++)
            {
                System.out.println("student id : " + students.get(i).get_id() + "," + " marks : " + marks.get(i));
            }

            System.out.println("\nScrutinization has been done.\n");

            int count = 0;

            for(int i=0 ; i<5 ; i++)
            {
                if(count > 2)
                    break;
                Random random = new Random();
                boolean wrong = random.nextBoolean();
                if(i == 4 && count == 0)
                    wrong = true;

                if(wrong == true)
                {
                    int prev;
                    System.out.println("Marks of student id " + students.get(i).get_id() + " were incorrect.");
                    //System.out.println("Previous marks: ", Corrected marks: 52");
                    boolean increase = random.nextBoolean();
                    if(increase == true)
                    {
                        int temp = marks.get(i);
                        prev = temp;
                        temp = temp + 3;
                        if(temp > 100)
                            temp = 100;
                        marks.set(i,temp);
                    }
                    else
                    {
                        int temp = marks.get(i);
                        prev = temp;
                        temp = temp - 3;
                        if(temp < 0)
                            temp = 0;
                        marks.set(i,temp);
                    }

                    count++;

                    System.out.println("Previous marks: " + prev + ", Corrected marks: " + marks.get(i));
                }
            }

            this.exmn.marks = marks;

            System.out.println("\nResult has been published to the students. \n");

            for(int i=0 ; i<students.size() ; i++) {
                students.get(i).notify("Result");
                /*System.out.println("Do you want to apply for re-examine?\n" +
                        "1. yes\n" +
                        "2. no");
                int choice = scanner.nextInt();

                if(choice == 1)
                    System.out.println("Re-examine request sent.\n");*/
            }

            if(request.size() > 0) {
                System.out.print("Exam controller prompt: Re-examine request got from student id");
                for (int i = 0; i < request.size(); i++) {
                    if (i == request.size() - 1 && i != 0)
                        System.out.print(" and " + request.get(i));
                    else
                        System.out.print(" " + request.get(i));
                }

                System.out.print("\nExam script of student id");
                for (int i = 0; i < request.size(); i++) {
                    if (i == request.size() - 1 && i != 0)
                        System.out.print(" and " + request.get(i));
                    else
                        System.out.print(" " + request.get(i));
                }
                System.out.println(" sent to the examiner\n");

                this.exmn.notify("Re-examine", this.request);
            }
        }

        else
            System.out.println(message);
    }
}

abstract class participant
{
    exam_controller excon;
    int id;

    public participant(exam_controller excon)
    {
        this.excon = excon;
    }

    public abstract int get_id();
}

class student extends participant
{
    public student(exam_controller excon , int id) {
        super(excon);
        this.id = id;
    }

    public int get_id()
    {
        return  this.id;
    }

    public void send(String message)
    {
        this.excon.send(this , message);
    }

    public void notify(String message)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Student " + this.id + " prompt: ");
        if(message.equalsIgnoreCase("Result"))
        {
            System.out.println("Result published. Got marks " + this.excon.exmn.marks.get(this.get_id()-1) + ".");
            System.out.println("Do you want to apply for re-examine?\n" +
                    "1. yes\n" +
                    "2. no");
            int choice = scanner.nextInt();

            if(choice == 1)
            {
                System.out.println("Re-examine request sent.\n");
                this.send("Re-examine");
            }
        }
        else if(message == "unchanged")
        {
            System.out.println("Marks unchanged.");
        }
        else
        {
            System.out.println("Marks were previously " + message + ". Now the corrected marks are " + this.excon.exmn.marks.get(this.id-1));
        }
    }
}

class examiner extends participant
{
    List<Integer> marks;
    private int count = 0;

    public examiner(exam_controller excon) {
        super(excon);
        this.id = -1;
    }

    @Override
    public int get_id() {
        return -1;
    }

    public void send(String message , List<student> students , List<Integer> marks)
    {
        System.out.println("Examiner prompt: Scripts and marks of student id 1,2,3,4,5 have been sent to exam controller office.\n");
        this.marks = marks;
        this.excon.send(this , message , students , marks);
    }

    public void notify(String message , List<Integer> request)
    {
        if(message.equalsIgnoreCase("Re-examine"))
        {
            System.out.print("Examiner prompt: Exam script of student id");
            for (int i = 0; i < request.size(); i++) {
                if (i == request.size() - 1 && i != 0)
                    System.out.print(" and " + request.get(i));
                else
                    System.out.print(" " + request.get(i));
            }
            System.out.println(" received for re-examine.");
            List<Integer> checked = new ArrayList<>();

            for(int i=0 ; i<request.size() ; i++) {
                Random random = new Random();
                boolean change = random.nextBoolean();

                if (!change) {
                    System.out.println("Marks of student id " + request.get(i) + " unchanged. ");
                    count++;
                    if (count >= 4) {
                        count = 0;
                        change = true;
                    }
                    checked.add(-1);
                }
                else {
                    count = 0;
                    int m = this.marks.get(request.get(i)-1);
                    //int temp = id + 1;
                    int id = request.get(i)-1;

                    boolean increase = random.nextBoolean();

                    checked.add(m);

                    if (increase) {
                        int temp2 = m + 2;
                        if (temp2 > 100)
                            temp2 = 100;
                        this.marks.set(id, temp2);
                        /*this.excon.send(this, "Marks increased of student " + temp +
                                "\nPrevious mark : " + m + "\nUpdated mark : " + this.marks.get(id));*/
                    } else {
                        int temp2 = m - 2;
                        if (temp2 < 0)
                            temp2 = 0;
                        this.marks.set(id, temp2);
                        /*this.excon.send(this, "Marks decreased of student " + temp +
                                "\nPrevious mark : " + m + "\nUpdated mark : " + this.marks.get(id));*/
                    }

                    System.out.println("Marks of student id " + request.get(i) + " changed. Previous marks were " + m + ", now the corrected marks are " + this.marks.get(id) + ".");
                }
            }
            System.out.println("");
            this.excon.update(checked);
        }
    }
}

public class container {
    public static void main(String[] args) {
        exam_controller excon = new concrete_exam_controller();
        examiner exmn = new examiner(excon);

        List<student> students = new ArrayList<>();

        for(int i=0 ; i<5 ; i++)
        {
            students.add(new student(excon , i+1));
        }

        List<Integer> marks = new ArrayList<>();
        Random random = new Random();

        for(int i=0 ; i<5 ; i++)
        {
            marks.add(random.nextInt(80) + 20);
        }

        excon.setExaminer(exmn);
        excon.setStudents(students);

        exmn.send("Marks" , students , marks);

        /*Scanner scanner = new Scanner(System.in);

        while (true)
        {
            int choice;
            System.out.println("1. Apply for recheck\nany other key to exit");
            choice = scanner.nextInt();

            if (choice == 1)
            {
                System.out.println("Enter the student id");
                int id = scanner.nextInt();

                while (id > 5)
                {
                    System.out.println("Enter valid id");
                    id = scanner.nextInt();
                }
                id--;

                students.get(id).notify("Re-examine request sent from student " + students.get(id).get_id());
                students.get(id).send("Re-examine");
                exmn.send("marks", students, marks);
            }

            else
            {
                break;
            }
        }*/
    }
}
