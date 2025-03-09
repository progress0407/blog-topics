package io.philz.jpa_multi_entity.app.squidgame.infrastrucure;

import static io.philz.jpa_multi_entity.app.squidgame.constant.WorkerState.*;
import static io.philz.jpa_multi_entity.app.squidgame.entity.QManager.*;
import static io.philz.jpa_multi_entity.app.squidgame.entity.QSoldier.*;
import static io.philz.jpa_multi_entity.app.squidgame.entity.QWorker.*;

import com.querydsl.jpa.impl.JPAQueryFactory;

import io.philz.jpa_multi_entity.app.global.DomainNotFoundException;
import io.philz.jpa_multi_entity.app.squidgame.entity.Manager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ManagerRepositoryCustomImpl implements ManagerRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public Manager findAggregateByIdOrThrowOrThrow(long id) {

		var fetched = queryFactory.selectFrom(manager)
			.distinct()
			.leftJoin(manager.soldiers, soldier).fetchJoin()
			.leftJoin(soldier.workers, worker).fetchJoin()
			.where(manager.id.eq(id),
				worker.state.ne(ELIMINATED))
			.fetchOne();

		if (fetched == null) {
			throw new DomainNotFoundException();
		}

		return fetched;
	}
}
