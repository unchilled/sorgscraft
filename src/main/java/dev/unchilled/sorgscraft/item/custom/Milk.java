package dev.unchilled.sorgscraft.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DrinkHelper;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;

public class Milk extends Item {

    public Milk(Properties pProperties) {
        super(pProperties);
    }

   public ItemStack finishUsingItem(ItemStack pStack, World pWorld, LivingEntity pEntityLiving) {
      if (!pWorld.isClientSide) {
         pEntityLiving.removeEffect(Effects.POISON);
         pEntityLiving.removeEffect(Effects.SLOW_FALLING);
         if(pEntityLiving.hasEffect(Effects.LEVITATION)) {
            pEntityLiving.removeEffect(Effects.LEVITATION);
            pEntityLiving.addEffect(new EffectInstance(Effects.SLOW_FALLING, 600, 0, true, false, false));
            pEntityLiving.addEffect(new EffectInstance(Effects.CONFUSION, 600, 0, true, false, false));
         } else {
            pEntityLiving.removeEffect(Effects.CONFUSION);
         };
         pEntityLiving.addEffect(new EffectInstance(Effects.SATURATION, 1, 4, true, false, false));
      }

      if (pEntityLiving instanceof PlayerEntity && !((PlayerEntity)pEntityLiving).abilities.instabuild) {
         pStack.shrink(1);
      }

      return pStack;
   }

   public int getUseDuration(ItemStack pStack) {
      return 16;
   }

   public UseAction getUseAnimation(ItemStack pStack) {
      return UseAction.DRINK;
   }

   public ActionResult <ItemStack> use(World pWorld, PlayerEntity pPlayer, Hand pHand) {
      return DrinkHelper.useDrink(pWorld, pPlayer, pHand);
   }
}
