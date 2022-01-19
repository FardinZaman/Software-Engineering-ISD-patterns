import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface media_player
{
    public void play(String file_name , String audio_type , String size , double duration);
}

interface advanced_media_player
{
    public void play_vlc(String file_name , String size , double duration);
    public void play_mp4(String file_name , String size , double duration);
    public void play_flv(String file_name , String size , double duration);
}

class vlc_player implements advanced_media_player
{

    @Override
    public void play_vlc(String file_name , String size , double duration) {
        System.out.println("File name :" + file_name +
                           "\nFile type : vlc" +
                           "\nFile size : " + size +
                           "\nFile duration : " + duration + "\n");
    }

    @Override
    public void play_mp4(String file_name , String size , double duration) {

    }

    @Override
    public void play_flv(String file_name , String size , double duration) {

    }
}

class mp4_player implements advanced_media_player
{

    @Override
    public void play_vlc(String file_name , String size , double duration) {

    }

    @Override
    public void play_mp4(String file_name , String size , double duration) {
        System.out.println("File name :" + file_name +
                "\nFile type : mp4" +
                "\nFile size : " + size +
                "\nFile duration : " + duration + "\n");
    }

    @Override
    public void play_flv(String file_name , String size , double duration) {

    }
}

class flv_player implements advanced_media_player
{

    @Override
    public void play_vlc(String file_name , String size , double duration) {

    }

    @Override
    public void play_mp4(String file_name , String size , double duration) {

    }

    @Override
    public void play_flv(String file_name , String size , double duration) {
        System.out.println("File name :" + file_name +
                "\nFile type : flv" +
                "\nFile size : " + size +
                "\nFile duration : " + duration + "\n");
    }
}

class media_adapter implements media_player
{
    advanced_media_player admep;

    public media_adapter(String audio_type)
    {
        if(audio_type.equalsIgnoreCase("vlc"))
            admep = new vlc_player();

        if(audio_type.equalsIgnoreCase("mp4"))
            admep = new mp4_player();

        if(audio_type.equalsIgnoreCase("flv"))
            admep = new flv_player();
    }

    @Override
    public void play(String file_name , String audio_type , String size , double duration) {
        if(audio_type.equalsIgnoreCase("vlc"))
            admep.play_vlc(file_name , size , duration);

        if(audio_type.equalsIgnoreCase("mp4"))
            admep.play_mp4(file_name , size , duration);

        if(audio_type.equalsIgnoreCase("flv"))
            admep.play_flv(file_name , size , duration);

    }
}

class audio_player implements media_player
{
    media_adapter medap;
    String name;
    String type;
    String size;
    double duration;

    audio_player(String file_name , String audio_type, String size , double duration)
    {
        this.name = file_name;
        this.type = audio_type;
        this.size = size;
        this.duration = duration;
    }

    String get_name()
    {
        return this.name;
    }

    String get_type()
    {
        return this.type;
    }

    String get_size()
    {
        return this.size;
    }

    double get_duration()
    {
        return this.duration;
    }

    @Override
    public void play(String file_name , String audio_type, String size , double duration) {
        //this.duration = duration;

        if(audio_type.equalsIgnoreCase("mp3"))
        {
            System.out.println("File name : " + file_name +
                               "\nFile type : mp3" +
                               "\nFile size : " + size +
                               "\nFile duration : " + duration + "\n");
        }

        else if(audio_type.equalsIgnoreCase("vlc") ||
                audio_type.equalsIgnoreCase("mp4") ||
                audio_type.equalsIgnoreCase("flv"))
        {
            medap = new media_adapter(audio_type);
            medap.play(file_name , audio_type , size , duration);
        }

        else
        {
            System.out.println(audio_type + " media format not supported");
        }
    }
}

public class container {
    public static void main(String[] args) {
        audio_player audpl;

        List<audio_player> playlist = null;

        int choice;
        String name, size;
        double duration;
        double elapsed;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Create playlist");
            System.out.println("2. Add file to current playlist");
            System.out.println("3. Show current playlist");
            System.out.println("4. Create another playlist");
            System.out.println("5. Exit");

            choice = scanner.nextInt();
            if(choice == 5)
                break;

            switch(choice){
                case 1:{
                    if (playlist == null)
                    {
                        playlist = new ArrayList<>();
                        System.out.println("Playlist created");
                    }
                    else
                    {
                        System.out.println("Already a playlist running , press 4 for a new one");
                    }

                    break;
                }

                case 2:{
                    if (playlist != null)
                    {
                        System.out.println("Enter name");
                        name = scanner.next();

                        System.out.println("Enter size");
                        size = scanner.next();

                        System.out.println("Enter duration");
                        duration = scanner.nextDouble();

                        String[] split_name = name.split("\\.");
                        audpl = new audio_player(split_name[0] , split_name[1] , size , duration);

                        playlist.add(audpl);
                    }

                    else
                    {
                        System.out.println("No playlist available");
                    }

                    break;
                }

                case 3:{
                    if (playlist != null)
                    {
                        if (playlist.size() == 0)
                        {
                            System.out.println("playlist empty");
                        }
                        for (int i=0 ; i<playlist.size() ; i++)
                        {
                            audio_player temp = playlist.get(i);
                            temp.play(temp.get_name() , temp.get_type() , temp.get_size() , temp.get_duration());
                        }
                    }

                    else
                    {
                        System.out.println("No playlist available");
                    }

                    break;
                }

                case 4:{
                    if (playlist == null)
                    {
                        System.out.println("No playlist yet\nNew playlist created");
                        playlist = new ArrayList<>();
                    }

                    else
                    {
                        System.out.println("Enter elapsed time");

                        elapsed = scanner.nextDouble();
                        double time = 0;

                        for (int i=0 ; i<playlist.size() ; i++)
                        {
                            time += playlist.get(i).get_duration();
                        }

                        if (elapsed >= time)
                        {
                            System.out.println("New playlist created");
                            playlist.clear();
                            playlist = new ArrayList<>();
                        }

                        else
                        {
                            System.out.println("New playlist won't be available");
                        }
                    }

                    break;
                }

                default:
                    continue;
            }
        }
    }
}
