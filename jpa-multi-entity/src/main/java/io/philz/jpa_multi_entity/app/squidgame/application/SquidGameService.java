package io.philz.jpa_multi_entity.app.squidgame.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.philz.jpa_multi_entity.app.squidgame.entity.Manager;
import io.philz.jpa_multi_entity.app.squidgame.entity.Worker;
import io.philz.jpa_multi_entity.app.squidgame.infrastrucure.manager.ManagerRepository;
import io.philz.jpa_multi_entity.app.squidgame.infrastrucure.worker.WorkerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SquidGameService {

	private final ManagerRepository managerRepository;
	private final WorkerRepository workerRepository;

	@Transactional
	public void eliminateWorker(long workerId) {

		Manager manager = managerRepository.findAggregateByIdOrThrowOrThrow(workerId);
		manager.eliminateWorker(workerId);
	}

	@Transactional
	public void eliminateWorker2(long workerId) {

		Worker worker = workerRepository.findAggregateByIdOrThrowOrThrow(workerId);
		worker.beEliminated2();
	}
}
