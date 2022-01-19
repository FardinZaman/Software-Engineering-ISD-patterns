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

    public abstract void send(participant prtc , String message);
    public abstract void send(participant prtc , String message , List<student> students , List<Integer> marks);
}

class concrete_exam_controller extends exam_controller
{
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
    public void send(participant prtc, String message) {
        if(message.equalsIgnoreCase("Re-examine"))
        {
            System.out.println("Re-examine request got from student ID : " + prtc.get_id());
            this.exmn.notify(message , prtc.get_id()-1);
        }

        else
            System.out.println(message);
    }

    @Override
    public void send(participant prtc, String message, List<student> students, List<Integer> marks) {
        if(message.equalsIgnoreCase("Marks"))
        {
            //System.out.println("2");
            for(int i=0 ; i<students.size() ; i++)
            {
                students.get(i).notify("ID : " + students.get(i).get_id() + " marks : " + marks.get(i));
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
        System.out.println(message);
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
        this.marks = marks;
        this.excon.send(this , message , students , marks);
    }

    public void notify(String message , int id)
    {
        if(message.equalsIgnoreCase("Re-examine"))
        {
            Random random = new Random();
            boolean change = random.nextBoolean();

            if(!change)
            {
                this.excon.send(this , "Marks unchanged");
                count++;
                if (count >= 4)
                {
                    count = 0;
                    change = true;
                }
            }
            else
            {
                count = 0;
                int m = this.marks.get(id);
                int temp = id+1;

                boolean increase = random.nextBoolean();

                if(increase)
                {
                    int temp2 = m + 2;
                    if(temp2 > 100)
                        temp2 = 100;
                    this.marks.set(id , temp2);
                    this.excon.send(this , "Marks increased of student " + temp +
                                    "\nPrevious mark : " + m + "\nUpdated mark : " + this.marks.get(id));
                }

                else
                {
                    int temp2 = m - 2;
                    if(temp2 < 0)
                        temp2 = 0;
                    this.marks.set(id , temp2);
                    this.excon.send(this , "Marks decreased of student " + temp +
                            "\nPrevious mark : " + m + "\nUpdated mark : " + this.marks.get(id));
                }
            }
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

        System.out.println("Marks and scripts of students sent to Exam Controller Office");
        exmn.send("Marks" , students , marks);

        Scanner scanner = new Scanner(System.in);

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
        }
    }
}
