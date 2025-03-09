package io.philz.jpa_multi_entity.app.squidgame.infrastrucure.worker;

import static io.philz.jpa_multi_entity.app.squidgame.constant.WorkerState.*;
import static io.philz.jpa_multi_entity.app.squidgame.entity.QManager.*;
import static io.philz.jpa_multi_entity.app.squidgame.entity.QSoldier.*;
import static io.philz.jpa_multi_entity.app.squidgame.entity.QWorker.*;

import com.querydsl.jpa.impl.JPAQueryFactory;

import io.philz.jpa_multi_entity.app.global.DomainNotFoundException;
import io.philz.jpa_multi_entity.app.squidgame.entity.QWorker;
import io.philz.jpa_multi_entity.app.squidgame.entity.Worker;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WorkerRepositoryCustomImpl implements WorkerRepositoryCustom{

	private final JPAQueryFactory queryFactory;

	@Override
	public Worker findAggregateByIdOrThrowOrThrow(long id) {

		var result = queryFactory
			.selectFrom(worker)
			.distinct()
				.leftJoin(worker.soldier, soldier).fetchJoin()
				.leftJoin(soldier.manager, manager).fetchJoin()
			.where(worker.id.eq(id), worker.state.ne(ELIMINATED))
			.fetchOne();

		if (result == null) {
			throw new DomainNotFoundException();
		}

		return result;
	}
}
