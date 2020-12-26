import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        ArrayList<Apple> apples = new ArrayList<>(Arrays.asList(new Apple(1.0f), new Apple(1.0f)));
        ArrayList<Orange> oranges = new ArrayList<>(Arrays.asList(new Orange(1.5f), new Orange(1.5f)));

        Box<Apple> appleBox = new Box<>(apples);
        Box<Apple> appleBox1 = new Box<>(apples);
        Box<Orange> orangeBox = new Box<>(oranges);

        System.out.println(appleBox.compareTo(appleBox1));
        System.out.println(appleBox.compareTo(orangeBox));
    }

    //1. Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа);
    public static <T> void swap(T[] arr, int first, int second){
        //жаль что xor нельзя((
        T t = arr[first];
        arr[first] = arr[second];
        arr[second] = t;
    }

    //2. Написать метод, который преобразует массив в ArrayList;
    public static <T> ArrayList<T> asArrayList(T[] arr){
        return (ArrayList<T>) Arrays.asList(arr);
    }

}


