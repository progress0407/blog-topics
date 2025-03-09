package io.philz.jpa_multi_entity.app.squidgame.entity;

import io.philz.jpa_multi_entity.app.global.BaseEntity;
import io.philz.jpa_multi_entity.app.squidgame.constant.WorkerState;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class Worker extends BaseEntity {

	// 동그라미는 반드시 소속된 세모에 속함.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "soldier_id")
	@ToString.Exclude
	private Soldier soldier;

	@Enumerated(EnumType.STRING)
	private WorkerState state = WorkerState.ACTIVE;

	public void markEliminated() {
		this.state = WorkerState.ELIMINATED;
	}

	public void bindSoldier(Soldier soldier) {
		this.soldier = soldier;
	}
}
