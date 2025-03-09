package io.philz.jpa_multi_entity.app.squidgame.infrastrucure.manager;

import static io.philz.jpa_multi_entity.app.squidgame.constant.WorkerState.*;
import static io.philz.jpa_multi_entity.app.squidgame.entity.QManager.*;
import static io.philz.jpa_multi_entity.app.squidgame.entity.QSoldier.*;
import static io.philz.jpa_multi_entity.app.squidgame.entity.QWorker.*;

import com.querydsl.jpa.impl.JPAQueryFactory;

import io.philz.jpa_multi_entity.app.global.DomainNotFoundException;
import io.philz.jpa_multi_entity.app.squidgame.entity.Manager;
import io.philz.jpa_multi_entity.app.squidgame.entity.QManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ManagerRepositoryCustomImpl implements ManagerRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public Manager findAggregateByIdOrThrowOrThrow(long workerId) {

		var manager = queryFactory
			.selectFrom(QManager.manager)
			.distinct()
				.leftJoin(QManager.manager.soldiers, soldier).fetchJoin()
				.leftJoin(soldier.workers, worker).fetchJoin()
			.where(worker.id.eq(workerId), worker.state.ne(ELIMINATED))
			.fetchOne();

		if (manager == null) {
			throw new DomainNotFoundException();
		}

		return manager;
	}
}
