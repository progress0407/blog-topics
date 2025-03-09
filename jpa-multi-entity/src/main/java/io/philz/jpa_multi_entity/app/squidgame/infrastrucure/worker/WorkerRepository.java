package io.philz.jpa_multi_entity.app.squidgame.infrastrucure.worker;

import org.springframework.data.jpa.repository.JpaRepository;

import io.philz.jpa_multi_entity.app.squidgame.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long>, WorkerRepositoryCustom {
}
