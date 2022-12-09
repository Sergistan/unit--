import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MainTests {

    List<Person> personList = new ArrayList<>();
    Person person1;
    Person person2;
    Person person3;

    @BeforeAll
    public static void beforeNotice() {
        System.out.println("Начало тестирования: " + new Date());
    }

    @BeforeEach
    public void createPersons() {
        person1 = new Person("Sergey", "Utochkin", 27, Sex.MAN, Education.HIGHER);
        person2 = new Person("Andrey", "Makarov", 9, Sex.MAN, Education.SECONDARY);
        person3 = new Person("Maria", "Krasnaya", 33, Sex.WOMAN, Education.ELEMENTARY);
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
    }

    @AfterEach
    public void closePersons() {
        person1 = null;
        person2 = null;
        person3 = null;
        personList = null;
    }

    @AfterAll
    public static void afterNotice() {
        System.out.println("Конец тестирования: " + new Date());
    }

    @Test
    public void testPersonCountUnderAge18() {
        long expected = 2L;

        long countUnderAge = personList.stream().filter(person -> person.getAge() > 18).count();

        Assertions.assertTrue(countUnderAge == expected);
    }

    @Test
    public void testPersonCountUnderAge18Hamcrest() {
        long expected = 2L;

        long countUnderAge = personList.stream().filter(person -> person.getAge() > 18).count();

        assertThat(countUnderAge, allOf(greaterThan(1L), lessThan(3L), not(equalTo(0L)), equalTo(expected)));

    }

    @Test
    public void testListOfNamesOfRecruit() {
        List<String> expected = List.of("Utochkin");

        List<String> fio = personList.stream().filter(person -> person.getAge() >= 18 && person.getAge() <= 27).
                map(Person::getFamily).collect(Collectors.toList());

        Assertions.assertLinesMatch(expected, fio);
    }

    @Test
    public void testListOfNamesOfRecruitHamcrest() {
        List<String> expected = List.of("Utochkin");

        List<String> fio = personList.stream().filter(person -> person.getAge() >= 18 && person.getAge() <= 27).
                map(Person::getFamily).collect(Collectors.toList());

        assertThat(fio, equalTo(expected));
    }

    @Test
    public void testOfSortedList() {
        List<Person> expected = List.of(person1);

        List<Person> sortedList = personList.stream()
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> (person.getSex().equals(Sex.MAN) && person.getAge() >= 18 && person.getAge() <= 65)
                        || (person.getSex().equals(Sex.WOMAN) && person.getAge() >= 18 && person.getAge() <= 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        Assertions.assertEquals(expected, sortedList);
    }

    @Test
    public void testOfSortedListHamcrest() {
        List<Person> expected = List.of(person1);

        List<Person> sortedList = personList.stream()
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> (person.getSex().equals(Sex.MAN) && person.getAge() >= 18 && person.getAge() <= 65)
                        || (person.getSex().equals(Sex.WOMAN) && person.getAge() >= 18 && person.getAge() <= 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        assertThat(sortedList, contains(person1));
    }

    @Test
    public void testOfSortedListNotNull() {
        List<Person> expected = List.of(person1);

        List<Person> sortedList = personList.stream()
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> (person.getSex().equals(Sex.MAN) && person.getAge() >= 18 && person.getAge() <= 65)
                        || (person.getSex().equals(Sex.WOMAN) && person.getAge() >= 18 && person.getAge() <= 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        Assertions.assertNotNull(sortedList);
    }

    @Test
    public void testOfSortedListNotNullHamcrest() {
        List<Person> expected = List.of(person1);

        List<Person> sortedList = personList.stream()
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> (person.getSex().equals(Sex.MAN) && person.getAge() >= 18 && person.getAge() <= 65)
                        || (person.getSex().equals(Sex.WOMAN) && person.getAge() >= 18 && person.getAge() <= 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        assertThat(sortedList, notNullValue());
    }

    @Test
    public void listHaveSizeOf1() {
        int expected = 1;

        List<Person> sortedList = personList.stream()
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> (person.getSex().equals(Sex.MAN) && person.getAge() >= 18 && person.getAge() <= 65)
                        || (person.getSex().equals(Sex.WOMAN) && person.getAge() >= 18 && person.getAge() <= 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        assertThat(sortedList, hasSize(expected));
    }

    @Test
    public void checkPropertyPerson1HaveName() {
        assertThat(person1, Matchers.<Person>hasProperty("name"));
    }

    @Test
    public void checkPropertyPerson1HaveFamily() {
        assertThat(person1, Matchers.<Person>hasProperty("family"));
    }

    @Test
    public void checkPropertyPerson1HaveAge() {
        assertThat(person1, Matchers.<Person>hasProperty("age"));
    }

    @Test
    public void checkPropertyPerson1HaveSex() {
        assertThat(person1, Matchers.<Person>hasProperty("sex"));
    }

    @Test
    public void checkPropertyPerson1HaveEducation() {
        assertThat(person1, Matchers.<Person>hasProperty("education"));
    }

}
