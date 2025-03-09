package io.philz.jpa_multi_entity.app.squidgame.entity;

import static io.philz.jpa_multi_entity.app.squidgame.constant.WorkerState.*;
import static jakarta.persistence.EnumType.*;

import org.hibernate.annotations.DynamicUpdate;

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

/**
 * 동그라미
 */
@Entity
@DynamicUpdate
@Getter
@ToString
public class Worker extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "soldier_id")
	@ToString.Exclude
	private Soldier soldier;

	@Enumerated(STRING)
	private WorkerState state = ACTIVE;

	public void markEliminated() {
		this.state = ELIMINATED;
	}

	public void bindSoldier(Soldier soldier) {
		this.soldier = soldier;
	}

	/**
	 * 네모 → 세모 → 동그라미
	 */
	public void beEliminated() {
		this.state = ELIMINATED;
	}

	/**
	 * 동그라미 → 세모 → 동그라미
	 */
	public void beEliminated2() {
		this.state = ELIMINATED;
		this.soldier.eliminateWorker();
	}
}
