package io.philz.jpa_multi_entity.app.squidgame.presentation;

import org.springframework.stereotype.Controller;

import io.philz.jpa_multi_entity.app.squidgame.application.SquidGameService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SquidGameApi {

	private final SquidGameService service;


}
