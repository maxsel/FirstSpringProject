package ua.epam.spring.core.loggers;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ua.epam.spring.core.Event;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Maksim_Sialiuk on 6/15/2016.
 */
public class DBLogger implements EventLogger {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void logEvent(Event event) {
        jdbcTemplate.update("INSERT INTO t_event (id, msg) VALUES (?,?)",
                new Random().nextInt(100), event.toString());
    }

    public void queries() {
        int count = jdbcTemplate.queryForObject("select count(*) from t_event", Integer.class);
        int cound = jdbcTemplate.queryForInt("select count(*) from t_event");
        String msg = jdbcTemplate.queryForObject("select msg from t_event where id = ?", new Object[]{1}, String.class);

        Event event = jdbcTemplate.queryForObject("select * from t_event where id = ?",
                new Object[]{1},
                new RowMapper<Event>() {
                    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
                        // do smth
                        return null;
                    }
                });
    }

    public void init() {
        try {
            jdbcTemplate.update("CREATE TABLE t_event (id int, msg varchar(100))");
        } catch (DataAccessException e) {
            // do nothing
        }
    }

    public void destroy() {
        List<Event> events = jdbcTemplate.query(
                "select * from t_event",
                new RowMapper<Event>() {
                    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Integer id = rs.getInt("id");
                        String msg = rs.getString("msg");
                        Event event = new Event(new Date(), DateFormat.getDateInstance());
                        event.setId(id);
                        event.setMsg(msg);
                        return event;
                    }
                }
        );
        System.out.println("FROM DB: " + events);
    }
}
