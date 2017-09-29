package com.sycatle.fastpot.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.util.Vector;

import com.sycatle.fastpot.FastPot;

	/**
	 * Created by Sycatle on 29/09/2017.
	 */

public class PotionListener implements Listener {
	
    private Double speed = FastPot.get().getConfig().getDouble("fastpot");
    
	@SuppressWarnings("deprecation")
	@EventHandler
    void onProjectileLaunch(final ProjectileLaunchEvent event) {
        if (event.getEntityType() == EntityType.SPLASH_POTION) {
            final Projectile projectile = event.getEntity();

            if (projectile.getShooter() instanceof Player && ((Player) projectile.getShooter()).isSprinting()) {
                final Vector velocity = projectile.getVelocity();

                velocity.setY(velocity.getY() - speed);
                projectile.setVelocity(velocity);
            }
        }
    }

    @SuppressWarnings("deprecation")
	@EventHandler
    void onPotionSplash(final PotionSplashEvent event) {
        if (event.getEntity().getShooter() instanceof Player) {
            final Player shooter = (Player) event.getEntity().getShooter();

            if (shooter.isSprinting() && event.getIntensity(shooter) > 0.6D) {
                event.setIntensity(shooter, 1.0D);
            }
        }
    }
	
}