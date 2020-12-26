import java.util.ArrayList;
import java.util.Arrays;

public class Box<E extends Fruit>  implements Comparable<Box<E>>{

    private ArrayList<E> items;

    public Box() {
    }

    public Box(ArrayList<E> items) {
        this.items = items;
    }

    public ArrayList<E> getItems() {
        return items;
    }

    public void setItems(ArrayList<E> items) {
        this.items = items;
    }

    //g. Не забываем про метод добавления фрукта в коробку.
    public void add(E item){
        items.add(item);
    }

    //e. Внутри класса коробка сделать метод compare, который позволяет сравнить
    // текущую коробку с той, которую подадут в compare в качестве параметра,
    // true - если их веса равны, false в противном случае(коробки с яблоками мы можем сравнивать
    // с коробками с апельсинами)
    @Override
    public int compareTo(Box o) {
        return Float.compare(this.getWeight(), o.getWeight());
    }

    //d. Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов и вес
    // одного фрукта(вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
    public float getWeight(){
        return this.items.get(0).weight * items.size();
    }
    //f. Написать метод, который позволяет пересыпать фрукты из текущей коробки
    // в другую коробку(помним про сортировку фруктов, нельзя яблоки высыпать в коробку с апельсинами),
    // соответственно в текущей коробке фруктов не остается, а в другую перекидываются объекты,
    // которые были в этой коробке;
    public void clone(Box<E> box){
        box.setItems(this.getItems());
        this.items.clear();
    }
}
