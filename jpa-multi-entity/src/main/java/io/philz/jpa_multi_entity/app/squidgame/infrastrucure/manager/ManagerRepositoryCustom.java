package io.philz.jpa_multi_entity.app.squidgame.infrastrucure.manager;

import io.philz.jpa_multi_entity.app.squidgame.entity.Manager;

public interface ManagerRepositoryCustom {

	Manager findAggregateByIdOrThrowOrThrow(long id);
}
