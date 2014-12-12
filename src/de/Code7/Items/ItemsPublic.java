package de.Code7.Items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class ItemsPublic {


	static ArrayList<String> Lore = new ArrayList<String>();
	
	public static ItemStack CreateItem (String ItemName, String lore, Material mat, Boolean enchant, int amount) {
		
		Lore.clear();
		Lore.add(lore);
		
		
		ItemStack istack = new ItemStack(mat);
		ItemMeta imet = istack.getItemMeta();
		
		if(ItemName != ""){
			imet.setDisplayName(ItemName);
		}
		
		if(lore != ""){
			imet.setLore(Lore);
		}
		
		istack.setItemMeta(imet);
		
		if(enchant){
			istack.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 0);
		}
		
		if(amount != 0){
			istack.setAmount(amount);
		}
		
		return istack;
	}
}
