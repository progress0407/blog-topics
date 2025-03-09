package io.philz.jpa_multi_entity.app.local;

import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.philz.jpa_multi_entity.app.squidgame.entity.Manager;
import io.philz.jpa_multi_entity.app.squidgame.entity.Soldier;
import io.philz.jpa_multi_entity.app.squidgame.entity.Worker;
import io.philz.jpa_multi_entity.app.squidgame.infrastrucure.ManagerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Profile("local")
@Component
@RequiredArgsConstructor
@Slf4j
public class DataSampleInit implements ApplicationRunner {

	private final ManagerRepository managerRepository;

	@Override
	@Transactional
	public void run(ApplicationArguments args) {

		Worker worker1 = new Worker();
		Worker worker2 = new Worker();
		Worker worker3 = new Worker();
		Worker worker4 = new Worker();
		Worker worker5 = new Worker();
		Worker worker6 = new Worker();
		Worker worker7 = new Worker();
		Worker worker8 = new Worker();
		Worker worker9 = new Worker();

		Soldier soldier1 = new Soldier();
		Soldier soldier2 = new Soldier();
		Soldier soldier3 = new Soldier();

		Manager manager1 = new Manager();
		Manager manager2 = new Manager();

		manager1.bring(soldier1);
		manager2.bring(soldier2, soldier3);

		soldier1.bring(worker1, worker2);
		soldier2.bring(worker3, worker4, worker5);
		soldier2.bring(worker6, worker7, worker8, worker9);

		managerRepository.saveAll(List.of(manager1, manager2));

		log.info("샘플 데이터 생성");
	}
}
