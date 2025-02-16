package com.evento.app;

import org.springframework.boot.SpringApplication;

public class TestEventoApplication {

	public static void main(String[] args) {
		SpringApplication.from(EventoApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
