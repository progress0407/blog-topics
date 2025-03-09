package io.philz.jpa_multi_entity.app.squidgame.entity;

import static jakarta.persistence.CascadeType.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.DynamicUpdate;

import io.philz.jpa_multi_entity.app.global.BaseEntity;
import io.philz.jpa_multi_entity.app.global.DomainNotFoundException;
import io.philz.jpa_multi_entity.app.squidgame.constant.WorkerState;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.ToString;

/**
 * 세모
 */
@Entity
@DynamicUpdate
@Getter
@ToString
public class Soldier extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    @ToString.Exclude
    private Manager manager;

    @OneToMany(mappedBy = "soldier", cascade = ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Worker> workers = new HashSet<>();

    /**
     * 세모가 척살한 동그라미 수
     */
    private int executionCount = 0;

    public void bindManger(Manager manager) {
        this.manager = manager;
    }

    public void bring(Worker... workers) {

        this.workers.addAll(List.of(workers));
        for (Worker worker : workers) {
            worker.bindSoldier(this);
        }
    }

    public void eliminateWorker(long workerId) {

        Worker workerToKill = workers.stream().filter(worker -> worker.getId().equals(workerId))
            .findAny()
            .orElseThrow(DomainNotFoundException::new);

        workerToKill.beEliminated();
        executionCount++;
    }

    public void eliminateWorker() {
        executionCount++;
        getManager().decrementMemberCount();
    }

    public int totalMemberCount() {
        return workers.size();
    }
}
