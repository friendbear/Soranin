import bears.BookJava;

/**
 * Created by kumagai on 2016/06/25.
 */
public class JavaMain {

    public static void main(String args[]) {
        new JavaClass().getJavaObject().getTitle();
    }
    static class JavaClass {

        BookJava getJavaObject() {
            BookJava book;
            book = new BookJava("Scala間姿デザイン", 3980);
            return book;
        }
    }
}
