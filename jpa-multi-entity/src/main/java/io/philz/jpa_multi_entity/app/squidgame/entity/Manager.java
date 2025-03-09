package io.philz.jpa_multi_entity.app.squidgame.entity;

import static jakarta.persistence.CascadeType.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.DynamicUpdate;

import io.philz.jpa_multi_entity.app.global.BaseEntity;
import io.philz.jpa_multi_entity.app.global.DomainNotFoundException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.ToString;

/**
 * 네모
 */
@Entity
@DynamicUpdate
@Getter
@ToString
public class Manager extends BaseEntity {

	@OneToMany(mappedBy = "manager", cascade = ALL, orphanRemoval = true)
	@ToString.Exclude
	private List<Soldier> soldiers = new ArrayList<>();

	/**
	 * 네모의 총 조직원 수 (세모와 소속 동그라미 포함)
	 */
	private int totalMemberCount;

	public void decrementMemberCount() {
		this.totalMemberCount--;
	}

	public void bring(Soldier... soldiers) {

		this.soldiers.addAll(List.of(soldiers));
		for (Soldier soldier : soldiers) {
			soldier.bindManger(this);
			totalMemberCount++;
			totalMemberCount += soldier.totalMemberCount();
		}
	}

	public void eliminateWorker(long workerId) {

		decrementMemberCount();

		Soldier superiorWorker = soldiers.stream()
			.filter(soldier -> soldier.getWorkers().stream().map(BaseEntity::getId).toList().contains(workerId))
			.findAny()
			.orElseThrow(DomainNotFoundException::new);

		superiorWorker.eliminateWorker(workerId);
	}
}
