package server.utility;

import common.data.HumanBeing;
import common.data.Mood;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Класс CollectionManager, отвечающий за работу с коллекцией.
 *
 * @version 1.1
 */

public class CollectionManager implements Serializable
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

    public void loadCollection()
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
            ReplyManager.append("Это будет первым элементом в нашей коллекции.");
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
        ReplyManager.append(humans
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
                .forEach(obj -> ReplyManager.append(obj.toString()));
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
        list.stream().forEach(obj -> ReplyManager.append(obj.toString()));

    }

    /**
     * Удаление по уникальному id.
     * @param id
     */

    public void removeByIdInCollection(int id)
    {
        humans.remove(findElementInCollection(id));
    }

    public HumanBeing findElementInCollection(int id)
    {
        HumanBeing human = humans
                .stream()
                .filter(obj -> (obj.getId() == id))
                .findFirst()
                .orElse(null);
        return human;
    }

    /**
     * Удаление первого элемента в коллекции.
     */

    public void removeFirstInCollection()
    {
        humans.remove(0);
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
        ReplyManager.append("Элемент исправлен! Вы можете вызвать команду \'show\' для проверки!");
    }

    /**
     * Вывод коллекции на экран.
     */

    public void showCollection()
    {
        humans.stream().forEach(obj -> ReplyManager.append(obj.toString()));
    }

}


