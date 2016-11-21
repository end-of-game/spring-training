package org.example.organizer.service;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.time.LocalDate;

import org.example.organizer.model.Priority;
import org.example.organizer.model.Task;
import org.junit.Test;

public class PriorityTaskComparatorTest {
	
	@Test
	public void testCompare1() {
		Task task1 = new Task("", Priority.HIGH, LocalDate.of(2016, 1, 1).atStartOfDay());
		Task task2 = new Task("", Priority.NORMAL, LocalDate.of(2016, 2, 1).atStartOfDay());
		
		PriorityTaskComparator comparator = new PriorityTaskComparator();
		
		assertThat(comparator.compare(task1, task2), greaterThan(0));
	}
	
	@Test
	public void testCompare2() {
		Task task1 = new Task("", Priority.NORMAL, LocalDate.of(2016, 2, 1).atStartOfDay());
		Task task2 = new Task("", Priority.NORMAL, LocalDate.of(2016, 1, 1).atStartOfDay());
		
		PriorityTaskComparator comparator = new PriorityTaskComparator();
		
		assertThat(comparator.compare(task1, task2), greaterThan(0));
	}
	
}
