package io.philz.jpa_multi_entity.app.squidgame.entity;

import java.util.ArrayList;
import java.util.List;

import io.philz.jpa_multi_entity.app.global.BaseEntity;
import io.philz.jpa_multi_entity.app.squidgame.constant.WorkerState;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class Soldier extends BaseEntity {

    // 세모는 반드시 네모에 속함.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    @ToString.Exclude
    private Manager manager;

    @OneToMany(mappedBy = "soldier", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Worker> workers = new ArrayList<>();

    /**
     * 세모가 척살한 동그라미 수
     */
    private int executionCount = 0;

    /**
     * 동그라미 실행 로직: 상태 변경, 척살 카운트 증가, 상위 네모의 조직원수 감소
     */
    public void executeWorker(Worker worker) {
        if(worker.getState() != WorkerState.ELIMINATED) {
            worker.markEliminated();
            this.executionCount++;
            // 네모의 조직원수 업데이트 (예: 동그라미 한 명이 제거되었으므로 -1)
            manager.decrementMemberCount();
        }
    }
}
