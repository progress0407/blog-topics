package io.philz.jpa_multi_entity.app.squidgame.infrastrucure.worker;

import io.philz.jpa_multi_entity.app.squidgame.entity.Worker;

public interface WorkerRepositoryCustom  {

	Worker findAggregateByIdOrThrowOrThrow(long id);
}
