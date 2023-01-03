package sports.dao;

import sports.model.Football;
import java.util.List;

public interface FootballDao {
    void create(Football football);
    List<Long> readIdFootball();
    List<Football> readAll();
}
