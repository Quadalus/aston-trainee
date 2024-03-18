package hw1;

import hw1.collection.CustomArrayList;
import hw1.collection.CustomList;
import hw1.common.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class CustomArrayTest {
    Person vova;
    Person grisha;
    Person kirill;
    private CustomList<Person> list;

    @BeforeEach
    public void performTests() {
        list = new CustomArrayList<>();
        vova = new Person(22, "Vova");
        grisha = new Person(5, "Grisha");
        kirill = new Person(70, "Kirill");
        list.add(vova);
        list.add(grisha);
        list.add(kirill);
    }

    @AfterEach
    public void clearData() {
        list = null;
        vova = null;
        grisha = null;
        kirill = null;
    }

    @Test
    public void creationArrayFromNegativeCapacityThrowsException() {
        int negativeCapacity = -1;
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new CustomArrayList<>(negativeCapacity), "IllegalArgumentException was expected");
    }

    @Test
    public void methodIsEmptyMustBeReturnTrueFromEmptyArray() {
        var list = new CustomArrayList<Person>();
        Assertions.assertEquals(0, list.size());
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    public void methodIsEmptyMustBeReturnFalseFromNotEmptyArray() {
        Assertions.assertEquals(3, list.size());
        Assertions.assertFalse(list.isEmpty());
    }

    @Test
    public void methodAddElementToEndIsNotEmptyArrayPositiveCase() {
        Person alex = new Person(25, "Alex");
        list.add(alex);
        var person = list.get(list.size() - 1);
        Assertions.assertEquals(4, list.size());
        Assertions.assertEquals(alex, person);
    }

    @Test
    public void methodAddElementToEndEmptyArrayPositiveCase() {
        var list = new CustomArrayList<Person>();
        Person alex = new Person(25, "Alex");
        list.add(alex);
        var person = list.get(list.size() - 1);
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(alex, person);
    }

    @Test
    public void methodAddElementToEndArrayWhenCapacityIsFull() {
        var list = new CustomArrayList<Person>(0);
        Person alex = new Person(25, "Alex");
        list.add(alex);
        var person = list.get(list.size() - 1);
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(alex, person);
    }

    @Test
    public void methodAddElementByIndexToMiddleIsNotEmptyArrayPositiveCase() {
        Person alex = new Person(25, "Alex");
        list.add(1, alex);
        var person = list.get(1);
        Assertions.assertEquals(4, list.size());
        Assertions.assertEquals(alex, person);
    }

    @Test
    public void methodAddElementByNegativeIndexToMiddleIsNotEmptyArrayMustBeThrowException() {
        Person alex = new Person(25, "Alex");
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> list.add(-1, alex), "ArrayIndexOutOfBoundsException was expected");
    }

    @Test
    public void methodClearNotEmptyArrayPositiveCase() {
        list.clear();
        Assertions.assertEquals(0, list.size());
    }

    @Test
    public void methodClearEmptyArrayPositiveCase() {
        var list = new CustomArrayList<Person>();
        list.clear();
        Assertions.assertEquals(0, list.size());
    }

    @Test
    public void methodGetElementFromEndArray() {
        var person = list.get(0);
        Assertions.assertEquals(vova, person);
    }

    @Test
    public void methodGetElementFromMiddleArray() {
        var person = list.get(1);
        Assertions.assertEquals(grisha, person);
    }

    @Test
    public void methodGetElementFromStartArray() {
        var person = list.get(2);
        Assertions.assertEquals(kirill, person);
    }

    @Test
    public void methodGetElementFromOutOfBoundArrayThrowException() {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> list.get(412), "ArrayIndexOutOfBoundsException was expected");
    }

    @Test
    public void methodAddAllToArray() {
        Set<Person> set = new HashSet<>();
        var nikita = new Person(36, "Nikita");
        var alina = new Person(12, "Alina");
        var ira = new Person(30, "Ira");
        set.add(nikita);
        set.add(alina);
        set.add(ira);
        list.addAll(set);
        Assertions.assertEquals(6, list.size());
    }

    @Test
    public void methodRemoveByIndexFromStartArray() {
        list.remove(0);
        var person = list.get(0);
        var person1 = list.get(1);
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(grisha, person);
        Assertions.assertEquals(kirill, person1);
    }

    @Test
    public void methodRemoveByIndexFromMiddleArray() {
        list.remove(1);
        var person = list.get(0);
        var person1 = list.get(1);
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(vova, person);
        Assertions.assertEquals(kirill, person1);
    }

    @Test
    public void methodRemoveByIndexFromEndArray() {
        list.remove(2);
        var person = list.get(0);
        var person1 = list.get(1);
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(vova, person);
        Assertions.assertEquals(grisha, person1);
    }

    @Test
    public void methodRemoveByNegativeIndexFromArrayMustByThrowException() {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> list.remove(-12), "ArrayIndexOutOfBoundsException was expected");
    }

    @Test
    public void methodRemoveByObjectFromArray() {
        list.remove(vova);
        var person = list.get(0);
        var person1 = list.get(1);
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(grisha, person);
        Assertions.assertEquals(kirill, person1);
    }

    @Test
    public void methodRemoveByWrongObjectFromArray() {
        list.remove(new Person(234, "Wrong"));
        var person = list.get(0);
        var person1 = list.get(1);
        var person2 = list.get(2);
        Assertions.assertEquals(vova, person);
        Assertions.assertEquals(grisha, person1);
        Assertions.assertEquals(kirill, person2);
    }

    @Test
    public void methodSortFromEmptyArrayDoNothing() {
        var list = new CustomArrayList<Person>();
        list.sort(Comparator.comparing(Person::getName));
    }

    @Test
    public void methodSortFromArrayByAgeAsc() {
        list.sort(Comparator.comparing(Person::getAge));
        var person = list.get(0);
        var person1 = list.get(1);
        var person2 = list.get(2);
        Assertions.assertEquals(grisha, person);
        Assertions.assertEquals(vova, person1);
        Assertions.assertEquals(kirill, person2);
    }

    @Test
    public void methodSortFromArrayByAgeDesc() {
        list.sort(Comparator.comparing(Person::getAge).reversed());
        var person = list.get(0);
        var person1 = list.get(1);
        var person2 = list.get(2);
        Assertions.assertEquals(kirill, person);
        Assertions.assertEquals(vova, person1);
        Assertions.assertEquals(grisha, person2);
    }

    @Test
    public void methodSortFromArrayByNameAsc() {
        list.sort(Comparator.comparing(Person::getName));
        var person = list.get(0);
        var person1 = list.get(1);
        var person2 = list.get(2);
        Assertions.assertEquals(grisha, person);
        Assertions.assertEquals(kirill, person1);
        Assertions.assertEquals(vova, person2);
    }

    @Test
    public void methodSortFromArrayByNameDesc() {
        list.sort(Comparator.comparing(Person::getName).reversed());
        var person = list.get(0);
        var person1 = list.get(1);
        var person2 = list.get(2);
        Assertions.assertEquals(vova, person);
        Assertions.assertEquals(kirill, person1);
        Assertions.assertEquals(grisha, person2);
    }
}
