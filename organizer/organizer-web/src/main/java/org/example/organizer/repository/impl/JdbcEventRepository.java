package org.example.organizer.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.example.organizer.model.Event;
import org.example.organizer.model.Person;
import org.example.organizer.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class JdbcEventRepository implements EventRepository {
	private static final String INSERT_QUERY = "insert into Event(description, beginDateTime, endDateTime) values (?,?,?)";
	private static final String SELECT_ALL_QUERY =
			"select e.description, e.beginDateTime, e.endDateTime, p.firstName, p.lastName "
			+ "from Event e "
			+ "left join Event_Participants ep "
			+ "left join Person p "
			+ "where e.id = ep.EVENT_ID "
			+ "and p.id = ep.PERSON_ID";
	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public List<Event> findAll() {
		List<Object[]> results = jdbc.query(SELECT_ALL_QUERY, new RowMapper<Object[]>() {
			@Override
			public Object[] mapRow(ResultSet rs, int i) throws SQLException {
				return new Object[] {
						new Event(
								rs.getString("description"),
								fromTimestamp(rs.getTimestamp("beginDateTime")),
								fromTimestamp(rs.getTimestamp("endDateTime"))),
						new Person(rs.getString("firstName"), rs.getString("lastName"), null)
				};
			}
		});
		
		Map<Event, Person> events = new HashMap<>();
		
		for (Object[] result : results) {
			Event event = (Event) results.get(0)[0];
			
			event.addParticipant((Person) result[1]);
		}
		return new ArrayList<>(events.keySet());
	}

	@Override
	public List<Event> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Event> findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event save(Event event) {
		if (event.getId() == null) {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbc.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement stmt = con.prepareStatement(INSERT_QUERY, new String[] {"id"});
					stmt.setString(1, event.getDescription());
					stmt.setTimestamp(2, toTimestamp(event.getBeginDateTime()));
					stmt.setTimestamp(2, toTimestamp(event.getEndDateTime()));
					return stmt;
				}
			},
		    keyHolder);
		} else {
			jdbc.update("update Event set description=?, beginDateTime=?, endDateTime=? where id=?",
					event.getDescription(),
					toTimestamp(event.getBeginDateTime()),
					toTimestamp(event.getEndDateTime()),
					event.getId());
		}
		return event;
	}

	@Override
	public void delete(Event event) {
		jdbc.update("delete from Event where id=?", event.getId());
	}
	
	private Timestamp toTimestamp(LocalDateTime dateTime) {
		return Timestamp.from(dateTime.toInstant(ZoneOffset.UTC));
	}

	private LocalDateTime fromTimestamp(Timestamp timestamp) {
		return timestamp.toLocalDateTime();
	}
}
