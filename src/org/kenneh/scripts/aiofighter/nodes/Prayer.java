package org.kenneh.scripts.aiofighter.nodes;

import org.kenneh.core.api.Misc;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.tab.Inventory;


public class Prayer extends Node {

	int[] ppots = {139, 141, 143, 2434, 14207, 14209, 14211, 14213, 14215,
			23243, 23245, 23247, 23249, 23251, 23253};

	int[] renewal = {21630, 21632, 21634, 21636, 23609, 23611, 23613, 23615, 23617, 23619};

	public int getPrayerPoints() {
		return Integer.parseInt(Widgets.get(749, 6).getText());
	}

	public boolean potPray() {
		return Misc.contains(ppots) && getPrayerPoints() < 200;
	}

	public boolean isRenewPotted() {
		return Settings.get(902) > 0;
	}

	public boolean potRenewal() {
		return Misc.contains(renewal) && !isRenewPotted();
	}

	public void drinkPrayerPot() {
		Inventory.getItem(ppots).getWidgetChild().interact("Drink");
		Task.sleep(1000);
	}

	public boolean prayerIsOn() {
		return org.powerbot.game.api.methods.tab.Prayer.isQuickOn();
	}

	public void drinkRenewal() {
		Inventory.getItem(renewal).getWidgetChild().interact("Drink");
		Task.sleep(1000);
	}
	
	public void enableQuickPrayer() {
		Widgets.get(749, 3).interact("Turn quick prayers on");
		Task.sleep(1000);
	}

	@Override
	public boolean activate() {
		return true;
	}

	@Override
	public void execute() {
		//if(!prayerIsOn()) {
		//	enableQuickPrayer();
		//}
		if(potPray()) {
			drinkPrayerPot();
		}
		if(potRenewal()) {
			drinkRenewal();
		}
	}

}