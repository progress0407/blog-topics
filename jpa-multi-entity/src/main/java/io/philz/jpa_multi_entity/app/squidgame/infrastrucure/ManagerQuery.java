package io.philz.jpa_multi_entity.app.squidgame.infrastrucure;

import static io.philz.jpa_multi_entity.app.squidgame.entity.QManager.*;
import static io.philz.jpa_multi_entity.app.squidgame.entity.QSoldier.*;
import static io.philz.jpa_multi_entity.app.squidgame.entity.QWorker.*;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;

import io.philz.jpa_multi_entity.app.global.PageResponse;
import io.philz.jpa_multi_entity.app.squidgame.dto.MemberResponseDto;
import io.philz.jpa_multi_entity.app.squidgame.dto.QMemberResponseDto;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ManagerQuery {

	private final JPAQueryFactory queryFactory;

	private PageResponse<?> queryMembers(Pageable paging) {

		List<MemberResponseDto> content = queryFactory.select(
				new QMemberResponseDto(worker.id, worker.state, manager.id, manager.id))
			.from(worker)
			.innerJoin(worker.soldier, soldier)
			.innerJoin(soldier.manager, manager)
			.offset(paging.getOffset())
			.limit(paging.getPageSize())
			.orderBy(soldier.id.desc())
			.fetch();

		Long totalCount = queryFactory.select(Wildcard.count)
			.from(worker)
			.fetchOne();

		return new PageResponse<>(content, totalCount, paging.getPageSize());
	}
}
