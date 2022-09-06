import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long underage = persons.stream()
                .filter(age -> age.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + underage);

        List<String> conscripts = persons.stream()
                .filter(sex -> sex.getSex() == Sex.MAN)
                .filter(age -> age.getAge() >= 18)
                .filter(age -> age.getAge() < 27)
                .map(Person::getFamily)
                .limit(15)
                .collect(Collectors.toList());
        System.out.println("Первые 15 призывников: " + conscripts);

        List<String> educatedEmployees = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() > 17)
                .filter(person -> person.getSex() == Sex.WOMAN && person.getAge() < 60 || person.getSex() == Sex.MAN && person.getAge() < 65)
                .map(Person::getFamily)
                .limit(15)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        System.out.println("Список потенциально работоспособных людей с ВО: " + educatedEmployees);

    }
}
