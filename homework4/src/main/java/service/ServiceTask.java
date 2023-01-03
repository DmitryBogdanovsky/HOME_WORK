package service;

import car.dao.CarDao;
import car.model.EngineVolume;
import car.model.ModelCar;
import embeddable.Country;
import person.dao.PersonDao;
import embeddable.Adress;
import car.model.Car;
import person.model.Employee;
import person.model.Person;
import car.dao.CarDaoImpl;
import person.dao.PersonDaoImpl;
import person.model.Student;
import sports.dao.FootballDao;
import sports.dao.FootballDaoImpl;
import sports.model.ClubRating;
import sports.model.Football;
import sports.model.StatisticClub;
import util.MysqlSessionFactory;

public class ServiceTask {
    public static void createCar() {

        Car model = ModelCar.builder()
                .marks("Opel")
                .color("red")
                .model("Omega")
                .year(2009)
                .build();
        Car volume = EngineVolume.builder()
                .marks("Mersedes")
                .color("RED")
                .volume(1850)
                .vin("ASD123587458DRE58657")
                .build();
        CarDaoImpl carDao = new CarDaoImpl(MysqlSessionFactory.getSessionFactory());

        carDao.create(model);
        carDao.create(volume);
    }

    public static void createPerson() {
        Person employee = Employee.builder()
                .name("Vladimir")
                .surname("Ivanov")
                .age(35)
                .company("Nike")
                .salary(12505)
                .adress(new Adress(25, "Lenina", "Minsk", 220040))
                .build();
        Person student = Student.builder()
                .name("Dmitry")
                .surname("Shniperson")
                .age(55)
                .faculty("Programming & ingeneering")
                .mark(8.9)
                .adress(new Adress(25, "Nezavisimosti", "Minsk", 220034))
                .build();
        PersonDaoImpl personDao = new PersonDaoImpl(MysqlSessionFactory.getSessionFactory());
        personDao.create(employee);
        personDao.create(student);
    }

    public static void createFootball() {
        Football clubRating = ClubRating.builder()
                .clubName("BARSELONA")
                .country(new Country("SPAIN"))
                .ligChampion(true)
                .rating(9.9)
                .build();
        Football clubRating1 = ClubRating.builder()
                .clubName("REAL")
                .country(new Country("SPAIN"))
                .ligChampion(true)
                .rating(8.9)
                .build();
        Football statisticClub = StatisticClub.builder()
                .clubName("CHELSY")
                .country(new Country("ENGLAND"))
                .victories(88)
                .defeats(30)
                .build();
        Football statisticClub1 = StatisticClub.builder()
                .clubName("LIVERPOOL")
                .country(new Country("ENGLAND"))
                .victories(55)
                .defeats(25)
                .build();
        Football football = Football.builder().
                clubName("REAL")
                .country(new Country("SPAIN"))
                .build();

        FootballDaoImpl footballDao = new FootballDaoImpl(MysqlSessionFactory.getSessionFactory());

        footballDao.create(clubRating);
        footballDao.create(clubRating1);
        footballDao.create(statisticClub);
        footballDao.create(statisticClub1);
        footballDao.create(football);

    }

    public static void printFootball() {
        FootballDao footballDao = new FootballDaoImpl(MysqlSessionFactory.getSessionFactory());
        System.out.println("Club in database:");
        footballDao.readAll().forEach(System.out::println);
        System.out.println("Id from Football: \n" + footballDao.readIdFootball());
    }

    public static void printPerson() {
        PersonDao personDao = new PersonDaoImpl(MysqlSessionFactory.getSessionFactory());
        System.out.println("Persons in database:");
        personDao.readAll().forEach(System.out::println);
        System.out.println("Id from Person: \n" + personDao.readIdPerson());
    }

    public static void printCar() {
        CarDao carDao = new CarDaoImpl(MysqlSessionFactory.getSessionFactory());
        System.out.println("Cars in database:");
        carDao.readAll().forEach(System.out::println);
        System.out.println("Id from Car: \n" + carDao.readIdCar());
    }


}
