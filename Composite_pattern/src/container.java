import java.util.Scanner;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

interface component
{
    String get_name();
    String get_type();
    void set_directory(String directory);
    String get_directory();

    void list();
    void details();

    void add(component comp);
    void remove();

    void set_parent(component comp);
    component get_parent();
    List<component> get_children();

    void set_component_count(int count);
    int get_component_count();
}

class drive implements component
{
    String name;
    String type;
    String directory;

    component parent;
    int component_count;

    List<component> children = new ArrayList<>();

    public drive(String name)
    {
        this.name = name;
        this.type = "drive";
        this.directory = name;

        this.parent = null;
        this.component_count = 0;

        System.out.println("Drive " + name + " created");
    }

    @Override
    public String get_name() {
        return this.name;
    }

    @Override
    public String get_type() {
        return this.type;
    }

    @Override
    public void set_directory(String directory) {

    }

    @Override
    public String get_directory() {
        return this.directory;
    }

    @Override
    public void list() {
        System.out.println(this.name + ":\\");

        int c = this.children.size();

        for(int i=0 ; i<c ; i++)
        {
            this.children.get(i).list();
        }

    }

    @Override
    public void details() {
        System.out.println("\nName : " + this.get_name());
        System.out.println("Type : " + this.get_type());
        System.out.println("Directory : " + this.get_directory());
        System.out.println("Component count :" + this.get_component_count());

    }

    @Override
    public void add(component comp) {
        this.children.add(comp);
        if(comp.get_type().equalsIgnoreCase("file"))
            this.component_count++;
        if(comp.get_type().equalsIgnoreCase("folder"))
            this.component_count = this.component_count + comp.get_component_count();
        //this.component_count++;
        comp.set_parent(this);
        comp.set_directory(this.directory + ":");

    }

    @Override
    public void remove() {
        //this.children.clear();
        int c = this.children.size();

        for (int i=0 ; i<c ; i++)
        {
            //System.out.println(this.children.get(i).get_name());
            this.children.get(0).remove();
            //System.out.println("3");
        }

        this.component_count = 0;

    }

    @Override
    public void set_parent(component comp) {

    }

    @Override
    public component get_parent() {
        return this.parent;
    }

    @Override
    public List<component> get_children() {
        return this.children;
    }

    @Override
    public void set_component_count(int count) {
        this.component_count = count;

    }

    @Override
    public int get_component_count() {
        return this.component_count;
    }
}

class folder implements component
{
    String name;
    String type;
    String directory;

    component parent;
    int component_count;

    List<component> children = new ArrayList<>();

    public folder(String name)
    {
        this.name = name;
        this.type = "folder";

        this.parent = null;
        this.component_count = 0;

        System.out.println("Folder " + name + " created");
    }

    @Override
    public String get_name() {
        return this.name;
    }

    @Override
    public String get_type() {
        return this.type;
    }

    @Override
    public void set_directory(String directory) {
        this.directory = directory + "\\" + this.name;

    }

    @Override
    public String get_directory() {
        return this.directory;
    }

    @Override
    public void list() {
        component temp = this.parent;

        while(temp != null)
        {
            System.out.print("\t");
            temp = temp.get_parent();
        }

        System.out.print("---- ");
        System.out.print(this.name + "\n");

        int c = this.children.size();

        for(int i=0 ; i<c ; i++)
        {
            this.children.get(i).list();
        }

    }

    @Override
    public void details() {
        System.out.println("\nName : " + this.get_name());
        System.out.println("Type : " + this.get_type());
        System.out.println("Directory : " + this.get_directory());
        System.out.println("Component count :" + this.get_component_count());

    }

    @Override
    public void add(component comp) {
        this.children.add(comp);
        if(comp.get_type().equalsIgnoreCase("file")) {
            this.component_count++;
            component tracker = this.get_parent();
            while(tracker != null) {
                tracker.set_component_count(tracker.get_component_count() + 1);
                tracker = tracker.get_parent();
            }
        }
        if(comp.get_type().equalsIgnoreCase("folder")) {
            this.component_count = this.component_count + comp.get_component_count();
            this.get_parent().set_component_count(this.get_parent().get_component_count() + comp.get_component_count());
        }


        //this.component_count++;
        comp.set_parent(this);
        comp.set_directory(this.directory);

    }

    @Override
    public void remove() {
        int c = this.children.size();

        for (int i=0 ; i<c ; i++)
        {
            this.children.get(0).remove();
        }

        //this.get_parent().set_component_count(this.get_parent().get_component_count()-this.get_component_count());

        this.get_parent().get_children().remove(this);

        this.parent = null;

    }

    @Override
    public void set_parent(component comp) {
        this.parent = comp;

    }

    @Override
    public component get_parent() {
        return this.parent;
    }

    @Override
    public List<component> get_children() {
        return children;
    }

    @Override
    public void set_component_count(int count) {
        this.component_count = count;

    }

    @Override
    public int get_component_count() {
        return this.component_count;
    }
}

class file implements component
{
    String name;
    String type;
    String directory;

    component parent;

    public file(String name)
    {
        this.name = name;
        this.type = "file";

        this.parent = null;

        System.out.println("File " + name + " created");
    }

    @Override
    public String get_name() {
        return this.name;
    }

    @Override
    public String get_type() {
        return this.type;
    }

    @Override
    public void set_directory(String directory) {
        this.directory = directory + "\\" + this.name;

    }

    @Override
    public String get_directory() {
        return this.directory;
    }

    @Override
    public void list() {
        component temp = this.parent;

        while(temp != null)
        {
            System.out.print("\t");
            temp = temp.get_parent();
        }

        System.out.print("---- ");
        System.out.print(this.name + "\n");

    }

    @Override
    public void details() {
        System.out.println("\nName : " + this.get_name());
        System.out.println("Type : " + this.get_type());
        System.out.println("Directory : " + this.get_directory());

    }

    @Override
    public void add(component comp) {

    }

    @Override
    public void remove() {
        component tracker = this.get_parent();
        while(tracker != null) {
            tracker.set_component_count(tracker.get_component_count() - 1);
            tracker = tracker.get_parent();
        }

        this.get_parent().get_children().remove(this);

        this.parent = null;

    }

    @Override
    public void set_parent(component comp) {
        this.parent = comp;

    }

    @Override
    public component get_parent() {
        return this.parent;
    }

    @Override
    public List<component> get_children() {
        return null;
    }

    @Override
    public void set_component_count(int count) {

    }

    @Override
    public int get_component_count() {
        return 0;
    }
}

public class container {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<component> drives = new ArrayList<>();
        List<component> folders = new ArrayList<>();
        List<component> files = new ArrayList<>();

        List<component> temporary = new ArrayList<>();

        int choice;
        String s;

        while (true) {
            System.out.println("1. Create a drive");
            System.out.println("2. Create a folder");
            System.out.println("3. Create a file");
            System.out.println("4. Remove a drive");
            System.out.println("5. Remove a folder");
            System.out.println("6. Remove a file");

            System.out.println("7. List of a drive");
            System.out.println("8. List of a folder");
            System.out.println("9. Details of a drive");
            System.out.println("10. Details of a folder");
            System.out.println("11. Details of a file");
            System.out.println("12. Exit");

            choice = scanner.nextInt();

            if (choice == 12)
                break;

            switch (choice){
                case 1:{
                    System.out.println("Enter the drive name");
                    s = scanner.next();
                    component drv = new drive(s);
                    drives.add(drv);

                    break;
                }

                case 2:{
                    System.out.println("Enter the folder name");
                    s = scanner.next();

                    component fld = new folder(s);

                    int ch;

                    System.out.println("1. For inside a drive\n2. For inside a folder");

                    ch = scanner.nextInt();

                    if(ch == 1)
                    {
                        System.out.println("Enter the drive name");
                        s = scanner.next();

                        boolean found = false;

                        for (int i=0 ; i<drives.size() ; i++)
                        {
                            if (drives.get(i).get_name().equalsIgnoreCase(s))
                            {
                                drives.get(i).add(fld);
                                folders.add(fld);
                                found = true;

                                break;
                            }
                        }

                        if (!found)
                        {
                            System.out.println("No such drive found");
                        }
                    }

                    if (ch == 2)
                    {
                        System.out.println("Enter the folder name");
                        s = scanner.next();

                        boolean found = false;

                        for (int i=0 ; i<folders.size() ; i++)
                        {
                            if (folders.get(i).get_name().equalsIgnoreCase(s))
                            {
                                folders.get(i).add(fld);
                                folders.add(fld);
                                found = true;

                                break;
                            }
                        }

                        if (!found)
                        {
                            System.out.println("No such folder found");
                        }
                    }

                    break;
                }

                case 3:{
                    System.out.println("Enter the file name");
                    s = scanner.next();

                    component fle = new file(s);

                    int ch;

                    System.out.println("1. For inside a drive\n2. For inside a folder");

                    ch = scanner.nextInt();

                    if(ch == 1)
                    {
                        System.out.println("Enter the drive name");
                        s = scanner.next();

                        boolean found = false;

                        for (int i=0 ; i<drives.size() ; i++)
                        {
                            if (drives.get(i).get_name().equalsIgnoreCase(s))
                            {
                                drives.get(i).add(fle);
                                files.add(fle);
                                found = true;

                                break;
                            }
                        }

                        if (!found)
                        {
                            System.out.println("No such drive found");
                        }
                    }

                    if (ch == 2)
                    {
                        System.out.println("Enter the folder name");
                        s = scanner.next();

                        boolean found = false;

                        for (int i=0 ; i<folders.size() ; i++)
                        {
                            if (folders.get(i).get_name().equalsIgnoreCase(s))
                            {
                                folders.get(i).add(fle);
                                files.add(fle);
                                found = true;

                                break;
                            }
                        }

                        if (!found)
                        {
                            System.out.println("No such folder found");
                        }
                    }

                    break;
                }

                case 4:{
                    System.out.println("Enter drive name");
                    s = scanner.next();

                    boolean found = false;

                    for(int i=0 ; i<drives.size() ; i++)
                    {

                        if(drives.get(i).get_name().equalsIgnoreCase(s))
                        {
                            component temp = drives.get(i);
                            temporary = temp.get_children();
                            for(int j=0 ; j<temporary.size() ; j++)
                            {
                                if(temporary.get(j).get_type().equalsIgnoreCase("folder"))
                                    folders.remove(temporary.get(j));
                                if(temporary.get(j).get_type().equalsIgnoreCase("file"))
                                    files.remove(temporary.get(j));
                            }
                            temp.remove();
                            drives.remove(temp);
                            System.out.println("Drive " + s + " removed");
                            found = true;
                            break;
                        }
                    }

                    if (!found)
                    {
                        System.out.println("No such drive found");
                    }

                    break;
                }

                case 5:{
                    System.out.println("Enter folder name");
                    s = scanner.next();

                    boolean found = false;

                    for(int i=0 ; i<folders.size() ; i++)
                    {

                        if(folders.get(i).get_name().equalsIgnoreCase(s))
                        {
                            component temp = folders.get(i);
                            temporary = temp.get_children();
                            for(int j=0 ; j<temporary.size() ; j++)
                            {
                                if(temporary.get(j).get_type().equalsIgnoreCase("folder"))
                                    folders.remove(temporary.get(j));
                                if(temporary.get(j).get_type().equalsIgnoreCase("file"))
                                    files.remove(temporary.get(j));
                            }
                            temp.remove();
                            folders.remove(temp);
                            System.out.println("Folder " + s + " removed");
                            found = true;
                            break;
                        }
                    }

                    if (!found)
                    {
                        System.out.println("No such folder found");
                    }

                    break;
                }

                case 6:{
                    System.out.println("Enter file name");
                    s = scanner.next();

                    boolean found = false;

                    for(int i=0 ; i<files.size() ; i++)
                    {

                        if(files.get(i).get_name().equalsIgnoreCase(s))
                        {
                            component temp = files.get(i);
                            temp.remove();
                            files.remove(temp);
                            System.out.println("File " + s + " removed");
                            found = true;
                            break;
                        }
                    }

                    if (!found)
                    {
                        System.out.println("No such file found");
                    }

                    break;
                }

                case 7:{
                    System.out.println("Enter drive name");
                    s = scanner.next();

                    boolean found = false;

                    for(int i=0 ; i<drives.size() ; i++)
                    {

                        if(drives.get(i).get_name().equalsIgnoreCase(s))
                        {
                            component temp = drives.get(i);
                            temp.list();
                            found = true;
                            //break;
                        }
                    }

                    if (!found)
                    {
                        System.out.println("No such drive found");
                    }

                    break;
                }

                case 8:{
                    System.out.println("Enter folder name");
                    s = scanner.next();

                    boolean found = false;

                    for(int i=0 ; i<folders.size() ; i++)
                    {

                        if(folders.get(i).get_name().equalsIgnoreCase(s))
                        {
                            component temp = folders.get(i);
                            temp.list();
                            found = true;
                            //break;
                        }
                    }

                    if (!found)
                    {
                        System.out.println("No such folder found");
                    }

                    break;
                }

                case 9:{
                    System.out.println("Enter drive name");
                    s = scanner.next();

                    boolean found = false;

                    for(int i=0 ; i<drives.size() ; i++)
                    {

                        if(drives.get(i).get_name().equalsIgnoreCase(s))
                        {
                            component temp = drives.get(i);
                            temp.details();
                            found = true;
                            //break;
                        }
                    }

                    if (!found)
                    {
                        System.out.println("No such drive found");
                    }

                    break;
                }

                case 10:{
                    System.out.println("Enter folder name");
                    s = scanner.next();

                    boolean found = false;

                    for(int i=0 ; i<folders.size() ; i++)
                    {

                        if(folders.get(i).get_name().equalsIgnoreCase(s))
                        {
                            component temp = folders.get(i);
                            temp.details();
                            found = true;
                            //break;
                        }
                    }

                    if (!found)
                    {
                        System.out.println("No such folder found");
                    }

                    break;
                }

                case 11:{
                    System.out.println("Enter file name");
                    s = scanner.next();

                    boolean found = false;

                    for(int i=0 ; i<files.size() ; i++)
                    {

                        if(files.get(i).get_name().equalsIgnoreCase(s))
                        {
                            component temp = files.get(i);
                            temp.details();
                            found = true;
                            //break;
                        }
                    }

                    if (!found)
                    {
                        System.out.println("No such file found");
                    }

                    break;
                }

                default:
                    continue;

            }
        }
    }
}
