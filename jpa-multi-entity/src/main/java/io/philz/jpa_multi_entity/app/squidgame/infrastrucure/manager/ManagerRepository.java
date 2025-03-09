package io.philz.jpa_multi_entity.app.squidgame.infrastrucure.manager;

import org.springframework.data.jpa.repository.JpaRepository;

import io.philz.jpa_multi_entity.app.squidgame.entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long>, ManagerRepositoryCustom {
}
