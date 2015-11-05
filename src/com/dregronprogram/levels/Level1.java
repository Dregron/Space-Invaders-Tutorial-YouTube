package com.dregronprogram.levels;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.dregronprogram.enemy_types.EnemyType;
import com.dregronprogram.enemy_types.EnemyTypeBasic;
import com.dregronprogram.game_screen.BasicBlocks;
import com.dregronprogram.game_screen.Player;

public class Level1 implements SuperLevel{

	private Player player;
	private ArrayList<EnemyType> enemies = new ArrayList<EnemyType>();
	
	public Level1(Player player){
		this.player = player;
		for(int y = 0; y < 5; y++){
			for(int x = 0; x < 10; x++){
				EnemyType e = new EnemyTypeBasic(150 + (x * 40), 25 + (y * 40), 1 , 3);
				enemies.add(e);
			}
		}
	}
	
	@Override
	public void draw(Graphics2D g) {
		if(enemies == null)
			return;
		
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).draw(g);
		}
	}

	@Override
	public void update(double delta, BasicBlocks blocks) {
		if(enemies == null)
			return;
		
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).update(delta, player, blocks);
		}
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).collide(i, player, blocks, enemies);
		}
		hasDirectionChange(delta);
	}

	@Override
	public void hasDirectionChange(double delta) {
		if(enemies == null)
			return;
		
		for(int i = 0; i < enemies.size(); i++){
			if(enemies.get(i).isOutOfBounds()){
				changeDurectionAllEnemys(delta);
			}
		}
	}

	@Override
	public void changeDurectionAllEnemys(double delta) {
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).changeDirection(delta);
		}
	}

	@Override
	public boolean isGameOver() {
		return false;
	}

	@Override
	public void destory() {
		
	}

	@Override
	public void reset() {
		
	}
}
