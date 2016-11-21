package org.example.organizer.service;

import java.util.Comparator;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.example.organizer.model.Task;

public class PriorityTaskComparator implements Comparator<Task> {

	@Override
	public int compare(Task t1, Task t2) {
		return new CompareToBuilder()
				.append(t1.getPriority(), t2.getPriority())
				.append(t1.getDueDateTime(), t2.getDueDateTime())
				.toComparison();
	}

}
