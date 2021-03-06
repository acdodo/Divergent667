package com.rs.game.player.cutscenes.actions;

import com.rs.game.npc.Npc;
import com.rs.game.player.Player;
import com.rs.game.player.cutscenes.Cutscene;
import com.rs.game.world.WorldTile;

public class MoveNPCAction extends CutsceneAction {

	private final int x;
	private final int y;
	private final int plane;
	private final int movementType;

	public MoveNPCAction(int cachedObjectIndex, int x, int y, boolean run,
						 int actionDelay) {
		this(cachedObjectIndex, x, y, -1, run ? Player.RUN_MOVE_TYPE
				: Player.WALK_MOVE_TYPE, actionDelay);
	}

	public MoveNPCAction(int cachedObjectIndex, int x, int y, int plane,
						 int movementType, int actionDelay) {
		super(cachedObjectIndex, actionDelay);
		this.x = x;
		this.y = y;
		this.plane = plane;
		this.movementType = movementType;
	}

	@Override
	public void process(Player player, Object[] cache) {
		Npc npc = (Npc) cache[getCachedObjectIndex()];
		Cutscene scene = (Cutscene) cache[0];
		if (movementType == Player.TELE_MOVE_TYPE) {
			npc.setNextWorldTile(new WorldTile(scene.getBaseX() + x, scene
					.getBaseY() + y, plane));
			return;
		}
		npc.setRun(movementType == Player.RUN_MOVE_TYPE);
		npc.addWalkSteps(scene.getBaseX() + x, scene.getBaseY() + y);
	}

}
