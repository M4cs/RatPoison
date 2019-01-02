package com.charlatano.scripts

import com.charlatano.game.CSGO.clientDLL
import com.charlatano.game.angle
import com.charlatano.game.clientState
import com.charlatano.game.entity.position
import com.charlatano.game.me
import com.charlatano.game.offsets.ClientOffsets.dwForceAttack
import com.charlatano.scripts.aim.findTarget
import com.charlatano.settings.*
import com.charlatano.utils.*
import org.jire.arrowhead.keyPressed
import org.jire.arrowhead.keyReleased

private val onBoneTriggerTarget = hook(1) {
	if (ENABLE_BONE_TRIGGER)
		findTarget(me.position(), clientState.angle(), false, BONE_TRIGGER_FOV, BONE_TRIGGER_BONE, false) >= 0
	else false
}

fun boneTrigger() = onBoneTriggerTarget {

	if ((keyReleased(FIRE_KEY) && BONE_TRIGGER_ENABLE_KEY && keyPressed(BONE_TRIGGER_KEY)) || (keyReleased(FIRE_KEY) && !BONE_TRIGGER_ENABLE_KEY)) {
		if (LEAGUE_MODE) mouse(MOUSEEVENTF_LEFTDOWN) else clientDLL[dwForceAttack] = 5.toByte() //Mouse press
		Thread.sleep(BONE_TRIGGER_SHOT_DELAY + randLong(16))
		if (LEAGUE_MODE) mouse(MOUSEEVENTF_LEFTUP) else clientDLL[dwForceAttack] = 4.toByte() //Mouse release
		Thread.sleep(BONE_TRIGGER_SHOT_DELAY + randLong(16))
	}
}