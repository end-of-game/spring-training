package com.example.organizer.batch;

import org.springframework.batch.core.launch.support.CommandLineJobRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BatchLauncher {
	private static final String[] CONFIG = {
			"persistence-context.xml",
			"batch-context.xml"
	};
}
