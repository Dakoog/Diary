package diary;

import entity.User;

public class OperationsDiary {
    public static void main(String[] args) {
        DAO diary = new DAO();
        // przy dodawaniu rekordu w bazie identyfikator musi byc pusty -> Auto-increment
        User user2 =  User.builder().login("Stokrota").secret("łąka").build();
       // User user = new User(null, "Daniel", "Ogiński", "Dakoog", "Polska23");
        diary.save(user2);
    }
}
