package diary;

import entity.Post;
import entity.User;

import javax.persistence.NoResultException;
import java.util.InputMismatchException;
import java.util.List;

import java.util.Scanner;

public class Diary {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DAO diary = new DAO();
        User user = null;
        do {
            System.out.println("Podaj login");

            String login = input.next();
            System.out.println("Podaj hasło");
            String password = input.next();
            try {
                user = diary.getUser(login);
                if (!user.getSecret().equals(password)) {
                    user = null;
                    System.out.println("Nie poprawne hasło. Wpisz jeszcze raz!");
                }
            } catch (NoResultException nre) {
                System.out.println("Użytkownik o danym loginie " + login + " nie isntieje. Wpisz jeszcze raz");
            }
        }
        while (user == null);

        int options = 0;
        do {
            System.out.println("""
                    Podaj co chcesz zrobić:
                     * 1 - dodaj wpis do pamiętnika
                     * 2 - odczytaj dany wpis w pamiętniku
                     * 3 - zamknij program""");
            try {
                options = input.nextInt();

            } catch(InputMismatchException ime){
                options =0;

            }
            input.nextLine();

            switch (options) {
                case 1 -> {
                    System.out.println("Podaj tytuł swojego wpisu ");
                    String title = input.nextLine();
                    System.out.println("Wpisz treść do pamiętnika");
                    String text = input.nextLine();
                    diary.newPost(user.getId(), title, text);
                }
                case 2 -> {
                    // lista dostępnych wpisów
                    List<Post> posts =  diary.getPosts(user.getId());
                    for (Post post : posts
                         ) {
                        System.out.println("| "+post.getId()+" | Tytuł: "+post.getTitle());
                    }
                    System.out.println("Wpisz nr przyporządkowany wybranemu tytułowi wpisu");
                    int postId= input.nextInt();
                    Post post = null;// wpis wyjsciowy (trzymanie miejsca na szukany pozniej post)
                    for ( Post post2 :posts
                         ) {
                        if(post2.getId()==postId){
                            post = post2;
                            break;
                        }

                    }
                    System.out.println(post.getTitle().toUpperCase()+": \n"+post.getText());



                }
                case 3 -> exitOfDiary();
                default -> System.out.println("Zły wpis. Taka komenda nie istnieje.");
            }

        } while (options != 3);
    }

    public static void exitOfDiary() {
        System.out.println("Do zobaczenia.Pamiętaj, każdy dzień ma swój koloryt");
    }

}