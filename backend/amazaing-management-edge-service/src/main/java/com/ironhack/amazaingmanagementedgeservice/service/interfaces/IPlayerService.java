package com.ironhack.amazaingmanagementedgeservice.service.interfaces;

import com.ironhack.amazaingmanagementedgeservice.controller.dto.PlayerDTO;
import com.ironhack.amazaingmanagementedgeservice.model.Player;

import java.util.List;

public interface IPlayerService {

	List<Player> getAllPlayers();
	Player getPlayerById(Long id);
	Player storePlayer(PlayerDTO playerDTO);
	void updatePlayer(Long id, PlayerDTO playerDTO);
	void deletePlayer(Long id);

}
