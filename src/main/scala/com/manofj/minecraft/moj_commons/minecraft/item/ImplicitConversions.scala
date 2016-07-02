package com.manofj.minecraft.moj_commons.minecraft.item


import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.ResourceLocation

import com.manofj.minecraft.moj_commons.util.ImplicitConversions.AnyExtension


/**
  * アイテムに関する暗黙の変換を提供する
  */
object ImplicitConversions {

  /**
    * [[net.minecraft.item.ItemStack]] クラスを拡張､汎用メソッドを追加する
    */
  implicit class ItemStackExtension( val item: ItemStack )
    extends AnyVal
  {

    /**
      * 自身のアイテムオブジェクトの登録名称を取得する
      */
    def registryName: Option[ ResourceLocation ] = item.getItem.?.map( _.getRegistryName )

    /**
      * 自身に対して指定されたアイテムをマージできるか評価する
      */
    def mergeable( other: ItemStack ): Boolean =
      item.isStackable && isItemEqualExact( other ) && sizeRemaining > 0

    /**
      * 自身の複製に指定されたダメージ値, スタック数, NBTタグを設定して返す
      */
    def duplicate( damage: Int            = item.getItemDamage,
                   size:   Int            = item.stackSize,
                   tag:    NBTTagCompound = item.getTagCompound ): ItemStack =
      item.copy << { i =>
        i.stackSize = size
        i.setItemDamage( damage )
        i.setTagCompound( tag )
      }

    /**
      * 自身の最大スタック数から現在スタック数を引いたものを返す
      */
    def sizeRemaining: Int = item.getMaxStackSize - item.stackSize

    /**
      * 自身と指定されたアイテムのNBTタグを比較する
      */
    def isTagEqual( other: ItemStack ): Boolean = ItemStack.areItemStackTagsEqual( item, other )

    /**
      * 自身と指定されたアイテムを比較する
      * アイテムオブジェクト, メタデータ, NBTタグを比較対象とし､スタック数, ダメージ値は考慮しない
      * 二つのアイテムで上記の値が同等となる､あるいは両方のアイテムオブジェクトが `null` の場合は `true`
      * それ以外は `false` を返す
      */
    def isItemEqualExact( other: ItemStack ): Boolean =
      other.?.fold( false ) { _ =>
        val i2 = other.getItem
        item.getItem.?.fold( i2.?.isEmpty ) { i1 =>
          i1 == i2 &&
          ( !item.getHasSubtypes || item.getMetadata == other.getMetadata ) &&
          ItemStack.areItemStackTagsEqual( item, other )
      } }

  }

}
