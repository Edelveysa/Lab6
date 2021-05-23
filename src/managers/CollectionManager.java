package managers;

import data.HumanBeing;
import data.Mood;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Класс CollectionManager, отвечающий за работу с коллекцией.
 *
 * @version 1.1
 */

public class CollectionManager
{
    /** Поле FileManager*/
    private FileManager fileManager;
    /** Поле коллекции HumanBeing*/
    private Stack<HumanBeing> humans = new Stack<HumanBeing>();
    /** Поле времени инициализации*/
    private LocalDateTime time;

    public CollectionManager(FileManager fileManager)
    {
        this.time = null;
        this.fileManager = fileManager;
        loadCollection();
    }

    /**
     * Загрузка коллекции из файла.
     */

    private void loadCollection()
    {
        this.time = LocalDateTime.now();
        this.humans = fileManager.read();
        sortCollection();
    }

    /**
     * @return Коллекции людей.
     */

    public Stack<HumanBeing> getHumans()
    {
        return humans;
    }

    /**
     * @return Время создания коллекции.
     */

    public String getTime()
    {
        return time.toString();
    }

    /**
     * Добавление в коллекцию эллемента.
     * @param humanBeing
     */

    public void addToCollection(HumanBeing humanBeing)
    {
        humans.push(humanBeing);
        System.out.println("Элемент добавлен в коллекцию.");
    }

    /**
     * @return Тип коллекции.
     */

    public String typeOfCollection()
    {
        return humans.getClass().getName();
    }

    /**
     * Очистка коллекции.
     */
    public void clearCollection()
    {
        humans.clear();
        System.out.println("Коллекция очищена.");
    }

    /**
     * Сохранение коллекции в файл.
     */

    public void saveCollection()
    {
        fileManager.write(humans);
    }

    /**
     * Добавление элемента, если он является максимальным по скорости удара.
     * @param humanBeing
     */

    public void addMaxToCollection(HumanBeing humanBeing)
    {
        Optional<HumanBeing> max = null;
        try {
            max = getHumans()
                    .stream()
                    .max(Comparator.comparing(obj -> obj.getImpactSpeed()));
        } catch (NullPointerException e) {
            addToCollection(humanBeing);
            System.out.println("Это будет первым элементом в нашей коллекции.");
        } finally {
            if (max.get().getImpactSpeed() < humanBeing.getImpactSpeed()) {
                addToCollection(humanBeing);


            }
        }
    }

    /**
     * Генерация уникального id каждому элементу коллекции.
     * @return id
     */

    public static Long generateID()
    {
        return Long.valueOf(Math.round(Math.random()*1000));
    }

    /**
     * Сортировка коллекции.
     */

    public void sortCollection()
    {
        Comparator<HumanBeing> comparator = Comparator.comparing(obj -> obj.getId());
        comparator.thenComparing(obj -> obj.getCoordinates().getX());
        comparator.thenComparing(obj -> obj.getCoordinates().getY());
        comparator.thenComparing(obj -> obj.getImpactSpeed());
        Collections.sort(getHumans(), comparator);
    }

    /**
     * Подсчет меньшего настроения в коллекции.
     * @param mood
     */

    public void countLessMoodInCollection(Mood mood)
    {
        System.out.println(humans
                .stream()
                .filter(humanBeing -> humanBeing.getMood().getNumber() < Mood.findNumber(mood.name()))
                .count());

    }

    /**
     * Фильтрует коллекцию по названию саундтрека.
     * @param str
     */

    public void filterSoundCollection(String str)
    {
        humans.stream()
                .filter(obj -> obj.getSoundtrackName().startsWith(str))
                .forEach(obj -> System.out.println(obj.toString()));
    }

    /**
     * Сортировка и вывод коллекции по убыванию скорости удара.
     */

    public void printLessImpactSpeedCollection()
    {
        List<HumanBeing> list = getHumans();
        Comparator<HumanBeing> comparator = Comparator.comparing(obj->obj.getImpactSpeed());
        Collections.sort(list, comparator);
        Collections.reverse(list);
        list.stream().forEach(obj -> System.out.println(obj.toString()));

    }

    /**
     * Удаление по уникальному id.
     * @param id
     */

    public void removeByIdInCollection(int id)
    {
        HumanBeing human = humans
                            .stream()
                            .filter(obj -> (obj.getId() == id))
                            .findFirst()
                            .orElse(null);
        humans.remove(human);
        System.out.println("Удаление прошло успешно.");
    }

    /**
     * Удаление первого элемента в коллекции.
     */

    public void removeFirstInCollection()
    {
        humans.remove(0);
        System.out.println("Удаление прошло успешно.");
    }

    /**
     * Изменение элемента коллекции с сохранением уникального id.
     * @param id
     * @param humanBeing
     */

    public void updateIdInCollection(int id, HumanBeing humanBeing)
    {
        removeByIdInCollection(id);
        humans.add(humanBeing);
        System.out.println("Элемент исправлен! Вы можете вызвать команду \'show\' для проверки!");
    }

    /**
     * Вывод коллекции на экран.
     */

    public void showCollection()
    {
        humans.stream().forEach(obj -> System.out.println(obj.toString()));
    }

}


