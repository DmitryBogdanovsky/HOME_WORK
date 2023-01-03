package person.dao.dao;

import embeddable.Country;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sports.dao.FootballDao;
import sports.dao.FootballDaoImpl;
import sports.model.ClubRating;
import sports.model.Football;
import sports.model.StatisticClub;
import util.MysqlSessionFactory;

import java.util.List;

import static org.junit.Assert.*;

public class FootballDaoTest {

    FootballDao testObject;

    @Before
    public void setUp() {testObject = new FootballDaoImpl(MysqlSessionFactory
            .getSessionFactory("hibernate-test.cfg"));
    }

    @After
    public void tearDown() {testObject = null; }

    @Test
    public void create(){
        //Given
        Football clubRating = ClubRating
                .builder()
                .clubName("Dinamo Minsk")
                .country(new Country("Belarus"))
                .rating(8.9)
                .ligChampion(false)
                .build();
        Football statisticClub = StatisticClub
                .builder()
                .clubName("Dinamo Brest")
                .country(new Country("Belarus"))
                .victories(24)
                .defeats(10)
                .build();
        Football football = Football
                .builder()
                .clubName("CSKA")
                .country(new Country("Russia"))
                .build();
        int initialSize = testObject.readAll().size();

        //When
        testObject.create(clubRating);
        testObject.create(statisticClub);
        testObject.create(football);

        //Then
        int actualSize = testObject.readAll().size();
        assertEquals(initialSize + 3, actualSize);

    }

    @Test
    public void readIdFootball() {
        //Given
        Football football = Football
                .builder()
                .clubName("Shahter")
                .country(new Country("Belarus"))
                .build();

        //When
        testObject.create(football);
        List footballList = testObject.readIdFootball();

        //Then
        assertEquals(4,footballList.size());
    }


}