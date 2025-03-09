package io.philz.jpa_multi_entity.app.squidgame.dto;

import com.querydsl.core.annotations.QueryProjection;

import io.philz.jpa_multi_entity.app.squidgame.constant.WorkerState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class MemberResponseDto {

	private long workerId;
	private String workerState;
	private long soldierId;
	private long managerId;

	@QueryProjection
	public MemberResponseDto(long workerId, WorkerState workerState, long soldierId, long managerId) {
		this.workerId = workerId;
		this.workerState = workerState.name();
		this.soldierId = soldierId;
		this.managerId = managerId;
	}
}
