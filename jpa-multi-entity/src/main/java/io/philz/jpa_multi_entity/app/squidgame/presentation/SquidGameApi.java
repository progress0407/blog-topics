package io.philz.jpa_multi_entity.app.squidgame.presentation;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.philz.jpa_multi_entity.app.global.PageResponse;
import io.philz.jpa_multi_entity.app.squidgame.application.SquidGameService;
import io.philz.jpa_multi_entity.app.squidgame.dto.WorkerResponseDto;
import io.philz.jpa_multi_entity.app.squidgame.infrastrucure.manager.ManagerQuery;
import lombok.RequiredArgsConstructor;

@RequestMapping("/squid-game")
@RestController
@RequiredArgsConstructor
public class SquidGameApi {

	private final SquidGameService service;
	private final ManagerQuery query;

	@GetMapping("/workers")
	public PageResponse<WorkerResponseDto> list(Pageable pageable) {

		var reponse = query.listWorkers(pageable);
		return reponse;
	}

	@PutMapping("/eliminate-worker/{workerId}")
	public String eliminateWorker(@PathVariable long workerId) {

		service.eliminateWorker(workerId);
		return "ELIMINATE";
	}

	@PutMapping("/eliminate-worker2/{workerId}")
	public String eliminateWorker2(@PathVariable long workerId) {

		service.eliminateWorker2(workerId);
		return "ELIMINATE";
	}
}
